package pl.simplecoding.library.books

import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun getAvailableBooks() = bookRepository.findAll().filter { it.available }

    fun getBookInfo(bookId: Long) = handleBookIfExist(bookId) { it.toDTO() }

    fun addBook(title: String, author: String, publicationYear: Int) = bookRepository.save(Book(0, title, author, publicationYear, true))

    fun isBookAvailable(bookId: Long) = handleBookIfExist(bookId) { it.available }

    fun markBookAsAvailable(bookId: Long) = handleBookIfExist(bookId) {
        if (it.available) throw IllegalStateException("Book is already available") else toggleBookStatus(it)
    }

    fun markBookAsUnavailable(bookId: Long) = handleBookIfExist(bookId) {
        if (!it.available) throw IllegalStateException("Book is already unavailable") else toggleBookStatus(it)
    }

    private fun toggleBookStatus(book: Book) = bookRepository.save(book.copy(available = !book.available))

    private inline fun <T> handleBookIfExist(id: Long, block: (Book) -> T): T = with(bookRepository.findById(id)) {
        when {
            this.isPresent -> block(this.get())
            else -> throw NoSuchElementException("Book with id $id does not exist")
        }
    }
}
