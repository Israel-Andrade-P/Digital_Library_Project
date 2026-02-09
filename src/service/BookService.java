package service;

import model.Book;
import repository.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.add(book);
    }

    public List<Book> getBooks() {
        return bookRepository.retrieveAll();
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
