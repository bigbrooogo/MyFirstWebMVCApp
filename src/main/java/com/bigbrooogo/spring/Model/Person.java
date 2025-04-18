package com.bigbrooogo.spring.Model;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name;
    private String surname;
    private String lastname;
    private String FIO;
    private int year;

    public Person() {

    }

    public Person(String name, String surname, String lastname, int year) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.year = year;
        this.FIO = String.format("%s %s %s", name, surname, lastname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}
