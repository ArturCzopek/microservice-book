package pl.simplecoding.library.books

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {

    @GetMapping("/")
    fun getAvailableBooks() = bookService.getAvailableBooks()

    @GetMapping("/{bookId}")
    fun getBookInfo(@PathVariable bookId: Long) = bookService.getBookInfo(bookId)

    @GetMapping("/is-available/{bookId}")
    fun isBookAvailable(@PathVariable bookId: Long) = bookService.isBookAvailable(bookId)

    @PostMapping("/new")
    fun addBook(@RequestBody newBook: BookDTO) = bookService.addBook(newBook.title, newBook.author, newBook.releaseYear)

    @PutMapping("/available/{bookId}")
    fun markBookAsAvailable(@PathVariable bookId: Long) = bookService.markBookAsAvailable(bookId)

    @PutMapping("/unavailable/{bookId}")
    fun markBookAsUnavailable(@PathVariable bookId: Long) = bookService.markBookAsUnavailable(bookId)
}