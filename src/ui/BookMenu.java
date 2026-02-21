package ui;

import app.Library;
import exception.BookAlreadyExistsException;
import exception.BookNotFoundException;
import exception.InventoryEmptyException;
import factory.LibraryFactory;
import model.Book;

import java.util.Scanner;

public class BookMenu {
    private static final Scanner sc = new Scanner(System.in);
    static Library library = LibraryFactory.buildLibrary();

    public static void displayMenu() {
        while (true) {
            IO.println("\n=== BOOK MENU ===");
            IO.println("1 - Register new book");
            IO.println("2 - List books");
            IO.println("3 - Find books by title");
            IO.println("4 - Find books by author");
            IO.println("5 - See available books");
            IO.println("6 - Back");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> registerBook();
                case "2" -> listBooks();
                case "3" -> findBooksByTitle();
                case "4" -> findBooksByAuthor();
                case "5" -> getAvailableBooks();
                case "6" -> {
                    return;
                }
                default -> IO.println("Invalid option");
            }
        }
    }

    private static void registerBook() {
        try {
            Book book = new Book();
            IO.println("Title: ");
            book.setTitle(sc.nextLine());
            IO.println("Author: ");
            book.setAuthor(sc.nextLine());
            IO.println("Year: ");
            book.setYearPublished(Integer.parseInt(sc.nextLine()));
            library.addBook(book);
        } catch (BookAlreadyExistsException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
        IO.println("Book registered successfully");
    }

    private static void listBooks() {
        try {
            IO.println("-----------BOOKS-------------");
            library.getBooks().forEach(IO::println);
            IO.println("-----------||-------------");
        } catch (InventoryEmptyException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
    }

    private static void findBooksByTitle() {
        try {
            IO.print("Book title: ");
            var input = sc.nextLine();
            IO.println("-----------RESULTS-------------");
            library.getByTitle(input).forEach(IO::println);
            IO.println("-----------||-------------");
        } catch (BookNotFoundException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
    }

    private static void findBooksByAuthor() {
        try {
            IO.print("Author's name: ");
            var input = sc.nextLine();
            IO.println("-----------RESULTS-------------");
            library.getByAuthor(input).forEach(IO::println);
            IO.println("-----------||-------------");
        } catch (BookNotFoundException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
    }

    private static void getAvailableBooks() {
        try {
            IO.println("-----------RESULTS-------------");
            library.findAvailableBooks().forEach(IO::println);
            IO.println("-----------||-------------");
        } catch (BookNotFoundException | InventoryEmptyException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
    }
}
