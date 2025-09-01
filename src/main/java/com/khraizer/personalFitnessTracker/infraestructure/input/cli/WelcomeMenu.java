package com.khraizer.personalFitnessTracker.infraestructure.input.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * This menu is shown automatically when the application starts.
 */
@Component
public class WelcomeMenu implements CommandLineRunner {

    private final Scanner scanner;
    private final RegisterMenu registerMenu;
    private final LoginMenu loginMenu;

    public WelcomeMenu(Scanner scanner, RegisterMenu registerMenu, LoginMenu loginMenu) {
        this.scanner = scanner;
        this.registerMenu = registerMenu;
        this.loginMenu = loginMenu;
    }

    /**
     * Displays the main menu and handles user input.
     */
    @Override
    public void run(String... args) throws Exception {
        boolean menu = true;
        int option;

        while (menu){
            // Display welcome options
            System.out.println("\n");
            System.out.println("Welcome to the Personal Fitness Tracker");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (option){
                case 1:
                    // Redirect to registration menu
                    registerMenu.showMenu();
                    break;
                case 2:
                    // Redirect to login menu
                    loginMenu.showMenu();
                    break;
                case 3:
                    // Exit the application
                    System.out.println("Good bye!");
                    menu = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}
