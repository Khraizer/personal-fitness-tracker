package com.khraizer.personalFitnessTracker.infraestructure.input.cli;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.exception.UserExistsException;
import com.khraizer.personalFitnessTracker.application.handler.input.UserHandlerCli;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;

/**
 * Handles user registration through CLI input.
 * Validates user data and invokes user creation if valid.
 */
@Component
public class RegisterMenu {

    private final UserHandlerCli userHandlerCli;
    private final Scanner scanner;
    private final Validator validator;

    public RegisterMenu(UserHandlerCli userHandlerCli, Scanner scanner, Validator validator) {
        this.userHandlerCli = userHandlerCli;
        this.scanner = scanner;
        this.validator = validator;
    }

    /**
     * Displays the registration form, validates user input,
     * and attempts to create a new user account.
     */
    public void showMenu() {
        System.out.println("\n");
        System.out.println("-------------------------------");
        System.out.println("Registration Form");
        System.out.println("-------------------------------");

        // Collect user input
        System.out.print("Please, type your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Please, type your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Please, type your weight (KG): ");
        Double weight = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        System.out.print("Please, type your email: ");
        String email = scanner.nextLine();

        System.out.print("Please, type your password: ");
        String password = scanner.nextLine();

        // Create DTO and validate
        UserRequestDto userDto = new UserRequestDto(firstName, lastName, weight, email, password);
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userDto);

        if (!violations.isEmpty()) {
            // Show validation errors
            System.out.println("\n❌ Registration Failed!");
            violations.forEach(v -> System.out.println(v.getMessage()));
        } else {
            try {
                // Save user
                userHandlerCli.save(userDto);
                System.out.println("Registration Successfully!");
            } catch (UserExistsException e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
        }
    }
}
