package model;

import common.Identifiable;
import util.BookUtils;

import java.io.Serializable;

public class Book implements Identifiable, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String title;
    private String author;
    private Integer yearPublished;

    public Book(String title, String author, Integer yearPublished) {
        this.id = BookUtils.generateId.get();
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
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
        return "Book{" +
                "id=" + id +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }
}
