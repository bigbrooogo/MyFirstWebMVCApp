package com.bigbrooogo.spring.Model;

import javax.validation.constraints.*;

public class Person {

    private int personId;

    @NotEmpty(message = "FIO cant be empty")
    @Size(min = 10, max = 60, message = "FIO should be between 10 and 60 letters")
    @Pattern(regexp = "[A-Z][a-z]{3,} [A-Z][a-z]{3,} [A-Z][a-z]{3,}", message = "FIO should be in format: Aaaaaa Bbbbbb Cccccc")
    private String FIO;


    @Min(value = 1920, message = "Year of birth should be greater then 1920 year")
    @Max(value = 2025, message = "Year of birth cant be greater then 2025 year")
    private int year;

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
}
