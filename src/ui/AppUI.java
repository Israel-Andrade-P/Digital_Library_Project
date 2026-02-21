package ui;

import app.Library;
import factory.LibraryFactory;
import model.Book;
import model.Loan;
import model.User;

import java.util.Scanner;

public class AppUI {
    static Scanner sc = new Scanner(System.in);
    static Library library = LibraryFactory.buildLibrary();

    public static void start() {
        while (true) {
            IO.println("\n=== MAIN MENU ===");
            IO.println("1 - Register new user");
            IO.println("2 - List users");
            IO.println("3 - Register new book");
            IO.println("4 - List books");
            IO.println("5 - Find books by title");
            IO.println("6 - Find books by author");
            IO.println("7 - Loan book");
            IO.println("0 - Exit");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> registerUser();
                case "2" -> listUsers();
                case "3" -> registerBook();
                case "4" -> listBooks();
                case "5" -> findBooksByTitle();
                case "6" -> findBooksByAuthor();
                case "7" -> loanBook();
                case "0" -> {
                    IO.println("Closing app...");
                    return;
                }
                default -> IO.println("Invalid option");
            }
        }
    }

    private static void registerUser() {
        User user = new User();
        IO.println("Name: ");
        user.setName(sc.nextLine());
        IO.println("Email: ");
        user.setEmail(sc.nextLine());
        library.registerUser(user);
        IO.println("User registered successfully");
    }

    private static void listUsers() {
        IO.println("-----------USERS-------------");
        library.listUsers().forEach(IO::println);
        IO.println("-----------||-------------");
    }

    private static void registerBook() {
        Book book = new Book();
        IO.println("Title: ");
        book.setTitle(sc.nextLine());
        IO.println("Author: ");
        book.setAuthor(sc.nextLine());
        IO.println("Year: ");
        book.setYearPublished(Integer.parseInt(sc.nextLine()));
        library.addBook(book);
        IO.println("Book registered successfully");
    }

    private static void listBooks() {
        IO.println("-----------BOOKS-------------");
        library.getBooks().forEach(IO::println);
        IO.println("-----------||-------------");
    }

    private static void findBooksByTitle() {
        IO.print("Book title: ");
        var input = sc.nextLine();
        IO.println("-----------RESULTS-------------");
        library.getByTitle(input).forEach(IO::println);
        IO.println("-----------||-------------");
    }

    private static void findBooksByAuthor() {
        IO.print("Author's name: ");
        var input = sc.nextLine();
        IO.println("-----------RESULTS-------------");
        library.getByAuthor(input).forEach(IO::println);
        IO.println("-----------||-------------");
    }

    private static void loanBook() {
        IO.println("Book's ID: ");
        var id = sc.nextLine();
        IO.println("Email: ");
        var email = sc.nextLine();
        Loan loan = library.borrowBook(email, id);
        IO.println("-----------LOAN'S INFO-------------");
        IO.println(loan);
        IO.println("-----------||-------------");
    }
}


