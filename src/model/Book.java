package model;

import common.Identifiable;

import java.io.Serializable;

import static util.BookUtils.generateId;

public class Book implements Identifiable, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String title;
    private String author;
    private Integer yearPublished;

    public Book(String title, String author, Integer yearPublished) {
        this.id = generateId.get();
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public Book() {
        this.id = generateId.get();
    }

    public String getId() {
        return id;
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

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nTitle: %s\nAuthor: %s\nYear: %d\n", this.id, this.title, this.author, this.yearPublished);
    }
}
