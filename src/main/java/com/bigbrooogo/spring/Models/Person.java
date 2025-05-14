package com.bigbrooogo.spring.Models;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int personId;

    @NotEmpty(message = "FIO cant be empty")
    @Size(min = 10, max = 60, message = "FIO should be between 10 and 60 letters")
    @Pattern(regexp = "[A-Z][a-z]{3,} [A-Z][a-z]{3,} [A-Z][a-z]{3,}", message = "FIO should be in format: Aaaaaa Bbbbbb Cccccc")
    @Column(name = "FIO")
    private String FIO;

    @Min(value = 1920, message = "Year of birth should be greater then 1920 year")
    @Max(value = 2025, message = "Year of birth cant be greater then 2025 year")
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String FIO, int year) {
        this.year = year;
        this.FIO = FIO;
        this.personId = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int id) {
        this.personId = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if(this.books == null) this.books = new ArrayList<Book>();
        this.books.add(book);
    }

    public void deleteBook(Book book) {
        if (this.books != null) this.books.remove(book);
    }
}
