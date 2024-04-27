package com.reactive.app;

import com.reactive.app.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class RepositoryTest {


    @Autowired
    private  BookRepository bookRepository;



    @Test
    public void findMethodTest() {

//
//        Mono<Book> nameMono = bookRepository.findByName("Angular front end");
//        StepVerifier.create(nameMono)
//                .expectNextCount(1)
//                .verifyComplete();

//        Flux<Book> authorFlux = bookRepository.findByAuthor("ravi rampal");
//        StepVerifier.create(authorFlux)
//                .expectNextCount(2)
//                .verifyComplete();

        bookRepository.findByNameAndAuthor("The Catcher in the Rye", "J.D. Salinger")
                .as( StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();


    }


    @Test
    public void queryMethodsTest() {
//
//        bookRepository.getAllBooksByAuthor("Basic Python Book","Python rocker")
//                .as(StepVerifier::create)
//                .expectNextCount(1)
//                .verifyComplete();

        bookRepository.searchBookByTitle("%Lord%")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

    }
}
