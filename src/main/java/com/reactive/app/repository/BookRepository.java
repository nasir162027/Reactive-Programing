package com.reactive.app.repository;

import com.reactive.app.entities.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {

    Mono<Book> findByName(String name);

    Flux<Book> findByAuthor(String author);


    Flux<Book> findByPublisher(String publisher);

    Flux<Book> findByNameAndAuthor(String name,String author);

    @Query("select * from book_details where name = :name AND author = :auth")
    Flux<Book> getAllBooksByAuthor(String name, @Param("auth") String author);


    @Query("select * from book_details where name  LIKE :title")
    Flux<Book> searchBookByTitle(String title);
}
