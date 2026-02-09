import model.Book;
import repository.BookRepository;
import service.BookService;

void main() {
    BookService service = new BookService(new BookRepository());

    //service.createBook(new Book("Pet Sematery", "Stephen King", 1979));

    service.getBooks().stream().forEach(System.out::println);
    System.out.println("------------------------------------");
}