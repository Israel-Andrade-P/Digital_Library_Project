package model;

import util.BookUtils;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String title;
    private String author;
    private Integer yearPublished;
    private boolean isBorrowed;

    public Book(String title, String author, Integer yearPublished) {
        this.id = BookUtils.generateId.get();
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isBorrowed = false;
    }

    public Book(String title, String author, Integer yearPublished, boolean isBorrowed) {
        this.id = BookUtils.generateId.get();
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isBorrowed = isBorrowed;
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

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                ", isBorrowed=" + isBorrowed +
                '}';
    }
}
