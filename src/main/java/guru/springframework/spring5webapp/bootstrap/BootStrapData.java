package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.hibernate.cfg.annotations.ListBinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Publisher p = new Publisher("Penguine","1234 Caymanway", "Maineville", "OH", 45039);
        Publisher p2 = new Publisher("Person", "1234 Caymanway", "Maineville", "OH", 45039);
        publisherRepository.save(p);
        publisherRepository.save(p2);

            Author eric = new Author("Eric", "Evans");
            Book bookeric = new Book( "Domain Driven Design", "12345");
            eric.getBooks().add(bookeric);
            bookeric.getAuthors().add(eric);
            bookeric.setPublisher(p);
            p.getBooks().add(bookeric);

            authorRepository.save(eric);
            bookRepository.save(bookeric);
             publisherRepository.save(p);



        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book( "J2EE Development without EJB", "12341234");
        eric.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(p);
        p.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(p);
       System.out.println("Started in bootstrap");
        System.out.println("Number of books "+ bookRepository.count());
        System.out.println("Publisher "+ publisherRepository.count());
        System.out.println("Publisher "+p.getBooks().size());
    }
}
