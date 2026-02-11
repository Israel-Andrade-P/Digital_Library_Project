import app.Library;
import model.Book;
import model.User;
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

    //library.addBook(new Book("IT", "Stephen King", 1977));
    //library.addBook(new Book("The Shining", "Stephen King", 1977));
    library.getBooks().forEach(System.out::println);
    System.out.println("--------------------------------------");
    //library.registerUser(new User("Zel", "zel@gmail", "cheese"));
    library.listUsers().forEach(System.out::println);
    //library.borrowBook("zel@gmail", "542351");
    System.out.println("---------------------------------------");
    library.findLoansByEmail("zel@gmail").forEach(System.out::println);
    library.returnBook("zel@gmail", "542351");
    library.findLoansByEmail("zel@gmail").forEach(System.out::println);
}