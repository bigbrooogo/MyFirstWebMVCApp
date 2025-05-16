package com.bigbrooogo.spring.Services;

import com.bigbrooogo.spring.Models.Book;
import com.bigbrooogo.spring.Models.Person;
import com.bigbrooogo.spring.Repositories.BookRepository;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final EntityManager em;

    @Autowired
    public BookService(BookRepository bookRepository, EntityManager em) {
        this.bookRepository = bookRepository;
        this.em = em;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook) {
        Session session = em.unwrap(Session.class);
        Book oldBook = (Book) session.get(Book.class, id);
        oldBook.setAuthor(updatedBook.getAuthor());
        oldBook.setTitle(updatedBook.getTitle());
        oldBook.setYear(updatedBook.getYear());
    }

    @Transactional
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void takeBook(Person person, int id) {
        Session session = em.unwrap(Session.class);
        Book book = (Book) session.get(Book.class, id);
        book.setOwner(person);
        person.addBook(book);
    }

    @Transactional
    public void freeBook(int id) {
        Session session = em.unwrap(Session.class);
        Book book = (Book) session.get(Book.class, id);
        book.getOwner().deleteBook(book);
        book.setOwner(null);

    }
}
