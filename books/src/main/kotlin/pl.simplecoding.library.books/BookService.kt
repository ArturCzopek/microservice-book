package pl.simplecoding.library.books

import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun getAvailableBooks() = bookRepository.findAll().filter { it.available }

    fun getBookInfo(bookId: Long): BookDTO = with(bookRepository.findById(bookId)) {
        when {
            this.isPresent -> this.get().toDTO()
            else -> throw NoSuchElementException("Book with id $bookId does not exist")
        }
    }

    fun isBookAvailable(bookId: Long) = with(bookRepository.findById(bookId)) {
        when {
            this.isPresent -> this.get().available
            else -> throw NoSuchElementException("Book with id $bookId does not exist")
        }
    }

    fun addBook(title: String, author: String, publicationYear: Int) = bookRepository.save(Book(0, title, author, publicationYear, true))

    fun markBookAsAvailable(bookId: Long) = with(bookRepository.findById(bookId)) {
        if (this.isPresent) {
            when {
                this.get().available -> throw IllegalStateException("Book is already available")
                else -> toggleBookStatus(this.get())
            }
        } else {
            throw NoSuchElementException("Book with id $bookId does not exist")
        }
    }

    fun markBookAsUnavailable(bookId: Long) = with(bookRepository.findById(bookId)) {
        if (this.isPresent) {
            when {
                !this.get().available -> throw IllegalStateException("Book is already available")
                else -> toggleBookStatus(this.get())
            }
        } else {
            throw NoSuchElementException("Book with id $bookId does not exist")
        }
    }

    private fun toggleBookStatus(book: Book) = bookRepository.save(book.copy(available = !book.available))
}
