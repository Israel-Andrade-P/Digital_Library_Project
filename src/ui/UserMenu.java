package ui;

import app.Library;
import exception.UserAlreadyRegisteredException;
import exception.UserNotFoundException;
import factory.LibraryFactory;
import model.User;

import java.util.Scanner;

class UserMenu {
    private static final Scanner sc = new Scanner(System.in);
    static Library library = LibraryFactory.buildLibrary();

    public static void displayMenu() {
        while (true) {
            IO.println("\n=== USER MENU ===");
            IO.println("1 - Register new user");
            IO.println("2 - List users");
            IO.println("3 - Back");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> registerUser();
                case "2" -> listUsers();
                case "3" -> {
                    return;
                }
                default -> IO.println("Invalid option");
            }
        }
    }

    private static void registerUser() {
        try {
            User user = new User();
            IO.println("Name: ");
            user.setName(sc.nextLine());
            IO.println("Email: ");
            user.setEmail(sc.nextLine());
            library.registerUser(user);
        } catch (UserAlreadyRegisteredException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
        IO.println("User registered successfully");
    }

    private static void listUsers() {
        try {
            IO.println("-----------USERS-------------");
            library.listUsers().forEach(IO::println);
            IO.println("-----------||-------------");
        } catch (UserNotFoundException e) {
            IO.println(e.getMessage());
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR: " + e.getMessage());
        }
    }
}
