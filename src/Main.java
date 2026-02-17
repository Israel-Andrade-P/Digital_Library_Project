import app.Library;
import repository.BookRepository;
import repository.LoanRepository;
import repository.UserRepository;
import service.BookService;
import service.LoanService;
import service.UserService;

void main() {
    Library library = new Library(
            new BookService(new BookRepository()),
            new UserService(new UserRepository()),
            new LoanService(new LoanRepository())
    );
}