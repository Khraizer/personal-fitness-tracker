package com.khraizer.personalFitnessTracker.infraestructure.input.cli;

import com.khraizer.personalFitnessTracker.application.dto.response.UserResponseDto;
import com.khraizer.personalFitnessTracker.application.exception.InvalidCredentialsException;
import com.khraizer.personalFitnessTracker.application.exception.UserDoesNotExists;
import com.khraizer.personalFitnessTracker.application.handler.input.UserHandlerCli;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

/**
 * Handles user login through CLI input.
 * Redirects authenticated users to the appropriate menu based on their role.
 */
@Component
public class LoginMenu {

    private final UserHandlerCli userHandlerCli;
    private final UserMainMenu mainMenu;
    private final AdminMainMenu adminMainMenu;
    private final Scanner scanner;

    public LoginMenu(UserHandlerCli userHandlerCli, UserMainMenu mainMenu, AdminMainMenu adminMainMenu, Scanner scanner) {
        this.userHandlerCli = userHandlerCli;
        this.mainMenu = mainMenu;
        this.adminMainMenu = adminMainMenu;
        this.scanner = scanner;
    }

    /**
     * Displays the login form and authenticates the user.
     */
    public void showMenu() {
        System.out.println("\n-------------------------------");
        System.out.println("Login Form");
        System.out.println("-------------------------------");
        System.out.println("Please, type your credentials");

        // Read user credentials from input
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            // Attempt to authenticate user
            Optional<UserResponseDto> authenticatedUser = userHandlerCli.checkCredentials(email, password);

            if (authenticatedUser.isPresent()) {
                System.out.println(authenticatedUser.get().getRole());

                // Redirect based on user role
                if (authenticatedUser.get().getRole().equals("Client")) {
                    mainMenu.showMenu(authenticatedUser.get());
                } else if (authenticatedUser.get().getRole().equals("Admin")) {
                    adminMainMenu.showMenu(authenticatedUser.get());
                }
            }
        } catch (InvalidCredentialsException | UserDoesNotExists e) {
            // Handle login failure
            System.out.println(e.getMessage());
        }
    }
}
