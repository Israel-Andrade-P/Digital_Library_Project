package app;

import exception.BookNotFoundException;
import exception.BookWithActiveLoanException;
import model.Book;
import model.Loan;
import model.User;
import service.BookService;
import service.LoanService;
import service.UserService;

import java.util.List;
import java.util.Set;

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

    public Loan borrowBook(String email, String bookId) {
        User _ = userService.getById(email);
        Book _ = bookService.getById(bookId);
        if (loanService.hasActiveLoan(bookId)) throw new BookWithActiveLoanException("Book already borrowed");

        Loan loan = new Loan(email, bookId);
        loanService.create(loan);
        return loan;
    }

     public void returnBook(String email, String bookId) {
         User _ = userService.getById(email);
         Book _ = bookService.getById(bookId);
        loanService.updateReturnStatus(email, bookId);
     }

     public List<Loan> findLoansByEmail(String email) {
        return loanService.getByEmail(email);
     }

    public List<Book> findAvailableBooks() {
        Set<String> borrowedBooks = loanService.getBooksWithActiveLoans();
        List<Book> books = bookService.getAll();

        List<Book> availableBooks = books.stream().filter(b -> !borrowedBooks.contains(b.getId())).toList();
        if (availableBooks.isEmpty()) throw new BookNotFoundException("All books already taken");

        return books.stream().filter(b -> !borrowedBooks.contains(b.getId())).toList();
    }

     public void registerUser(User user) {
        userService.create(user);
     }

     public List<User> listUsers() {
        return userService.getAll();
     }
}
