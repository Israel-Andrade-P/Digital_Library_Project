package ui;

import app.Library;
import exception.BookNotFoundException;
import exception.BookWithActiveLoanException;
import exception.LoanNotFoundException;
import exception.UserNotFoundException;
import factory.LibraryFactory;
import model.Loan;

import java.util.Scanner;

public class LoanMenu {
    private static final Scanner sc = new Scanner(System.in);
    static Library library = LibraryFactory.buildLibrary();

    public static void displayMenu() {
        while (true) {
            IO.println("\n=== LOAN MENU ===");
            IO.println("1 - Loan book");
            IO.println("2 - Return book");
            IO.println("3 - See loan history");
            IO.println("4 - Back");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> loanBook();
                case "2" -> returnBook();
                case "3" -> findLoans();
                case "4" -> {
                    return;
                }
                default -> IO.println("Invalid option");
            }
        }
    }

    private static void loanBook() {
        Loan loan = null;
        try {
            IO.println("Book's ID: ");
            var id = sc.nextLine();
            IO.println("Email: ");
            var email = sc.nextLine();
            loan = library.borrowBook(email, id);
        } catch (UserNotFoundException | BookNotFoundException | BookWithActiveLoanException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
        IO.println("-----------LOAN'S INFO-------------");
        IO.println(loan);
        IO.println("-----------||-------------");
    }

    private static void returnBook() {
        try {
            IO.println("Book's ID: ");
            var id = sc.nextLine();
            IO.println("Email: ");
            var email = sc.nextLine();
            library.returnBook(email, id);
        } catch (UserNotFoundException | BookNotFoundException | IllegalArgumentException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
        IO.println("Book returned");
    }

    private static void findLoans() {
        try {
            IO.println("Email: ");
            library.findLoansByEmail(sc.nextLine()).forEach(IO::println);
        } catch (LoanNotFoundException e) {
            IO.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
    }
}
