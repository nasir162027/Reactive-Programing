package com.reactive.app.service.impl;

import com.reactive.app.entities.Book;
import com.reactive.app.repository.BookRepository;
import com.reactive.app.service.BookService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Book> create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll().delayElements( Duration.ofSeconds(2))
                .log()
                .map(book ->{book.setName(book.getName().toUpperCase());
                return book;
                });
    }

    @Override
    public Mono<Book> getBookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {

        Mono<Book> oldMonoBook = bookRepository.findById(bookId);
        return oldMonoBook.flatMap(book1->{
            book1.setBookId(book.getBookId());
            book1.setName(book.getName());
            book1.setDescription(book.getDescription());
            book1.setPublisher(book.getPublisher());
            book1.setAuthor(book.getAuthor());
            return bookRepository.save(book1);
        });
    }

    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository.findById(bookId).flatMap( bookRepository::delete );
    }

    @Override
    public Flux<Book> search(String query) {
        return null;
    }
}
