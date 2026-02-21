package ui;

import java.util.Scanner;

public class AppUI {
    static Scanner sc = new Scanner(System.in);

    public static void start() {

        while (true) {
            IO.println("\n=== MAIN MENU ===");
            IO.println("1 - USERS");
            IO.println("2 - BOOKS");
            IO.println("3 - LOANS");
            IO.println("0 - EXIT");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> UserMenu.displayMenu();
                case "2" -> BookMenu.displayMenu();
                case "3" -> LoanMenu.displayMenu();
                case "0" -> {
                    IO.println("Closing app...");
                    return;
                }
                default -> IO.println("Invalid option");
            }
        }
    }
}


