package com.springframework.spring6webapp.bootstrap;

import com.springframework.spring6webapp.domain.Author;
import com.springframework.spring6webapp.domain.Book;
import com.springframework.spring6webapp.repositories.AuthorRepository;
import com.springframework.spring6webapp.repositories.BookRepository;
import com.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

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

        Author stephen = new Author();
        stephen.setFirstName("Stephen");
        stephen.setLastName("King");
        Author stephenPersisted = authorRepository.save(stephen);

        Author bram = new Author();
        bram.setFirstName("Bram");
        bram.setLastName("Stoker");
        Author bramPersisted = authorRepository.save(bram);

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("122479028");
        Book dddPersisted= bookRepository.save(ddd);

        Book sfg = new Book();
        sfg.setTitle("Spring Frameworg Guru");
        sfg.setIsbn("947759020");
        Book sfgPersisted = bookRepository.save(sfg);

        Book dracula = new Book();
        dracula.setTitle("Dracula");
        dracula.setIsbn("928749843");
        Book draculaPersisted= bookRepository.save(dracula);

        Book missBetty = new Book();
        missBetty.setTitle("Miss Betty");
        missBetty.setIsbn("9937474992");
        Book missBettyPersisted = bookRepository.save(missBetty);

        magnusPersisted.getBooks().add(dddPersisted);
        magnusPersisted.getBooks().add(sfgPersisted);
        authorRepository.save(magnusPersisted);

        bramPersisted.getBooks().add(draculaPersisted);
        bramPersisted.getBooks().add(missBettyPersisted);
        authorRepository.save(bramPersisted);

        //draculaPersisted.getAuthors().add(bramPersisted);
        Set<Book> b = bramPersisted.getBooks();

        System.out.println("In Bootstrap");
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of books: " + bookRepository.count());

        b.forEach(System.out::println);


    }

}
