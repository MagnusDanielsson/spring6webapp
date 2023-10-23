package com.springframework.spring6webapp.bootstrap;

import com.springframework.spring6webapp.domain.Author;
import com.springframework.spring6webapp.domain.Book;
import com.springframework.spring6webapp.repositories.AuthorRepository;
import com.springframework.spring6webapp.repositories.BookRepository;
import com.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Author magnus = new Author();
        magnus.setFirstName("Magnus");
        magnus.setLastName("Danielsson");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("122479028");

        Author magnusSaved = authorRepository.save(magnus);
        Book dddSaved= bookRepository.save(ddd);

        magnusSaved.getBooks().add(dddSaved);
        //dddSaved.getAuthors().add(magnusSaved);

        magnusSaved= authorRepository.save(magnusSaved);
        //dddSaved = bookRepository.save(dddSaved);

        System.out.println("In Bootstrap");
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of books: " + bookRepository.count());

    }
}
