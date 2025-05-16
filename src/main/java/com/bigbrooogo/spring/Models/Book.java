package com.bigbrooogo.spring.Models;

import javax.persistence.*;

import jdk.jfr.Timestamp;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int bookId;

    @NotEmpty(message = "Title cant be empty")
    @Size(min = 1, max = 60, message = "Title should be between 1 and 60 letters")
    @Column(name="title")
    private String title;

    @NotEmpty(message = "Author cant be empty")
    @Size(min = 10, max = 60, message = "Author should be between 1 and 60 letters")
    @Column(name="author")
    private String author;

    @NotNull(message = "Year cant be empty")
    @Max(value = 2025, message = "Year of book birth should be between 1600 and 2025 year")
    @Min(value = 1600, message = "Year of book birth should be between 1600 and 2025 year")
    @Column(name="year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person owner;

    @Timestamp
    @Column(name = "pickdate")
    private LocalDateTime pickDate;

    @Transient
    private boolean delay;

    public Book() {
    }

    public Book(String title, String author, int year, int id, Person owner) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.bookId = id;
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getID() {
        return bookId;
    }

    public void setID(int id) {
        this.bookId = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public LocalDateTime getPickDate() {
        return pickDate;
    }

    public void setPickDate(LocalDateTime pickDate) {
        this.pickDate = pickDate;
    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bookId == book.bookId && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, year);
    }
}
