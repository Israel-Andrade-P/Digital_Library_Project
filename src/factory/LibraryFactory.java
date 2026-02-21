package factory;

import app.Library;
import repository.BookRepository;
import repository.LoanRepository;
import repository.UserRepository;
import service.BookService;
import service.LoanService;
import service.UserService;

public class LibraryFactory {
    public static Library buildLibrary() {
        return new Library(
                new BookService(new BookRepository()),
                new UserService(new UserRepository()),
                new LoanService(new LoanRepository())
        );
    }
}
