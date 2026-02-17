package service;

import exception.BookAlreadyExistsException;
import exception.BookNotFoundException;
import exception.InventoryEmptyException;
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
        List<Book> books = bookRepository.retrieveAll();
        if (books.isEmpty()) throw new InventoryEmptyException("No books added yet");
        return bookRepository.retrieveAll();
    }

    @Override
    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("Book with ID %s not found", id)));
    }

    @Override
    public boolean exists(String id) {
        return bookRepository.exists(id);
    }

    public List<Book> findByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        if (books.isEmpty()) throw new BookNotFoundException("Book not found");
        return books;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = bookRepository.getByAuthor(author);
        if (books.isEmpty()) throw new BookNotFoundException("Book not found");
        return bookRepository.getByAuthor(author);
    }

    private boolean checkByTitleAndAuthor(String title, String author) {
        return bookRepository.retrieveAll().stream()
                .anyMatch(b -> b.getTitle().equals(title) && b.getAuthor().equals(author));
    }
}
