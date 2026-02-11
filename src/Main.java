import model.Book;
import model.Loan;
import model.User;
import repository.BookRepository;
import repository.LoanRepository;
import repository.UserRepository;
import service.BookService;
import service.FileService;
import service.UserService;

void main() {
    BookService bookService = new BookService(new BookRepository());
    FileService<User> userService = new UserService(new UserRepository());
    LoanRepository loanRepository = new LoanRepository();

    //bookService.create(new Book("Two Towers", "Stephen King", 1988));
    bookService.getAll().forEach(System.out::println);
    bookService.findByTitle("it").forEach(System.out::println);
    System.out.println("------------------------------------");
    userService.create(new User("Zel", "zel@gmaill", "cheese"));
    userService.getAll().forEach(System.out::println);
    loanRepository.add(new Loan("zel@gmail", "109372"));
    loanRepository.retrieveAll().forEach(System.out::println);
}