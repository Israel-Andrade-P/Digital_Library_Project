package service;

import exception.BookAlreadyExistsException;
import model.Book;
import repository.BookRepository;

import java.util.List;

public class BookService implements FileService<Book>{
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book book) {
        if (checkByTitleAndAuthor(book.getTitle(), book.getAuthor())) throw new BookAlreadyExistsException("Book already exists");

        bookRepository.add(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.retrieveAll();
    }

    @Override
    public boolean exists(String id) {
        return bookRepository.exists(id);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.getByAuthor(author);
    }

    private boolean checkByTitleAndAuthor(String title, String author) {
        return bookRepository.retrieveAll().stream()
                .anyMatch(b -> b.getTitle().equals(title) && b.getAuthor().equals(author));
    }
}
