package com.bigbrooogo.spring.Model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class Book {

    private int bookId;

    @NotEmpty(message = "Title cant be empty")
    @Size(min = 1, max = 60, message = "Title should be between 1 and 60 letters")
    private String title;

    @NotEmpty(message = "Author cant be empty")
    @Size(min = 10, max = 60, message = "Author should be between 1 and 60 letters")
    private String author;

    @NotEmpty(message = "Author cant be empty")
    @Size(min = 1600, max = 2025, message = "Year of book birth should be between 1600 and 2025 year")
    private int year;

    private Integer personId;

    public Book() {
    }

    public Book(String title, String author, int year, int id, Integer personId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.bookId = id;
        this.personId = personId;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
