package pl.simplecoding.library.books

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long>