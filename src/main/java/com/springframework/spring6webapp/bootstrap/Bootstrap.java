package com.springframework.spring6webapp.bootstrap;

import com.springframework.spring6webapp.domain.Author;
import com.springframework.spring6webapp.domain.Book;
import com.springframework.spring6webapp.domain.Publisher;
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
        Author magnusPersisted = authorRepository.save(magnus);

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("122479028");
        Book dddPersisted= bookRepository.save(ddd);

        Book sfg = new Book();
        sfg.setTitle("Spring Frameworg Guru");
        sfg.setIsbn("947759020");
        Book sfgPersisted = bookRepository.save(sfg);

        Publisher bonnier = new Publisher();
        bonnier.setLocation("Sweden");
        bonnier.setName("Bonnier");
        Publisher bonnierPersisted = publisherRepository.save(bonnier);

        dddPersisted.setPublisher(bonnierPersisted);
        sfgPersisted.setPublisher(bonnierPersisted);

        dddPersisted.getAuthors().add(magnusPersisted);
        sfgPersisted.getAuthors().add(magnusPersisted);

        magnusPersisted.getBooks().add(dddPersisted);
        magnusPersisted.getBooks().add(sfgPersisted);

        bookRepository.save(dddPersisted);
        bookRepository.save(sfgPersisted);
        authorRepository.save(magnusPersisted);
        publisherRepository.save(bonnierPersisted);

        System.out.println("In Bootstrap");
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());

    }

}
