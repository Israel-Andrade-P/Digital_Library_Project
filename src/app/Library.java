package app;

import exception.BookNotFoundException;
import exception.BookWithActiveLoanException;
import exception.UserNotFoundException;
import model.Book;
import model.Loan;
import service.BookService;
import service.LoanService;
import service.UserService;

import java.util.List;

public class Library {
    private final BookService bookService;
    private final UserService userService;
    private final LoanService loanService;

    public Library(BookService bookService, UserService userService, LoanService loanService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanService = loanService;
    }

    public void addBook(Book book) {
        bookService.create(book);
    }

    public List<Book> getBooks() {
        return bookService.getAll();
    }

    public List<Book> getByTitle(String title) {
        return bookService.findByTitle(title);
    }

    public List<Book> getByAuthor(String author) {
        return bookService.findByAuthor(author);
    }

    public void borrowBook(String email, String bookId) {
        if (!userService.exists(email)) throw new UserNotFoundException(String.format("User with email %s not found", email));
        if (!bookService.exists(bookId)) throw new BookNotFoundException(String.format("Book with ID %s not found", bookId));
        if (loanService.hasActiveLoan(bookId)) throw new BookWithActiveLoanException("Book already borrowed");

        loanService.create(new Loan(email, bookId));
    }

     public void returnBook(String email, String bookId) {
         if (!userService.exists(email)) throw new UserNotFoundException(String.format("User with email %s not found", email));
         if (!bookService.exists(bookId)) throw new BookNotFoundException(String.format("Book with ID %s not found", bookId));
        loanService.updateReturnStatus(email, bookId);
     }
}
