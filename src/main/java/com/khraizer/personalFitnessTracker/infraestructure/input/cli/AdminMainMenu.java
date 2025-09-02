package com.khraizer.personalFitnessTracker.infraestructure.input.cli;

import com.khraizer.personalFitnessTracker.application.dto.request.ExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.ExerciseResponseDto;
import com.khraizer.personalFitnessTracker.application.dto.response.UserResponseDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutResponseDto;
import com.khraizer.personalFitnessTracker.application.exception.ExerciseExistsException;
import com.khraizer.personalFitnessTracker.application.handler.input.ExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutHandlerCli;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Displays the admin panel for managing exercises and workouts.
 */
@Component
public class AdminMainMenu {

    private final Scanner scanner;
    private final ExerciseHandlerCli exerciseHandlerCli;
    private final WorkoutHandlerCli workoutHandlerCli;
    private final WorkoutExerciseHandlerCli workoutExerciseHandlerCli;

    public AdminMainMenu(Scanner scanner,
                         ExerciseHandlerCli exerciseHandlerCli,
                         WorkoutHandlerCli workoutHandlerCli,
                         WorkoutExerciseHandlerCli workoutExerciseHandlerCli) {
        this.scanner = scanner;
        this.exerciseHandlerCli = exerciseHandlerCli;
        this.workoutHandlerCli = workoutHandlerCli;
        this.workoutExerciseHandlerCli = workoutExerciseHandlerCli;
    }

    /**
     * Displays the admin menu and handles input for admin operations.
     */
    public void showMenu(UserResponseDto userResponseDto) {
        boolean menu = true;

        while (menu) {
            printMainMenu(userResponseDto);
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option) {
                case 1 -> showExercises();
                case 2 -> showWorkouts();
                case 3 -> registerExercise();
                case 4 -> registerWorkout();
                case 5 -> menu = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void printMainMenu(UserResponseDto user) {
        System.out.println("\nWelcome Admin " + user.getFirstName() + " to panel Admin!");
        System.out.println("1. Show Exercises");
        System.out.println("2. Show Workouts");
        System.out.println("3. Register Exercise");
        System.out.println("4. Register Workout");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    /**
     * Displays all registered exercises.
     */
    private void showExercises() {
        List<ExerciseResponseDto> exercises = exerciseHandlerCli.getAll();

        if (exercises.isEmpty()) {
            System.out.println("Exercises Empty!");
        } else {
            System.out.println("Exercises List:");
            exercises.forEach(System.out::println);
        }
    }

    /**
     * Displays all workouts.
     */
    private void showWorkouts() {
        Map<Integer, WorkoutResponseDto> workouts = workoutHandlerCli.getAll().get();

        if (workouts.isEmpty()) {
            System.out.println("Workouts Empty!");
        } else {
            System.out.println("Workout List:");
            workouts.forEach((index, workout) -> System.out.println(workout.getName()));
        }
    }

    /**
     * Allows admin to register a new exercise.
     */
    private void registerExercise() {
        System.out.print("Please, enter the new exercise name: ");
        String exerciseName = scanner.nextLine();

        try {
            System.out.print("Please, enter the Metabolic Equivalent of Task: ");
            Double met = scanner.nextDouble();
            scanner.nextLine();

            exerciseHandlerCli.save(ExerciseRequestDto.builder().name(exerciseName).met(met).build());
            System.out.println("Exercise Registered Successfully!");

        } catch (ExerciseExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Allows admin to register a new workout and add exercises to it.
     */
    private void registerWorkout() {
        System.out.print("Please, enter the new workout name: ");
        String workoutName = scanner.nextLine();

        System.out.print("Please, enter the new workout description: ");
        String description = scanner.nextLine();

        // Save workout
        WorkoutRequestDto dto = WorkoutRequestDto.builder()
                .name(workoutName)
                .description(description)
                .build();

        workoutHandlerCli.save(dto);

        // Retrieve the saved workout
        WorkoutResponseDto workoutResponseDto = workoutHandlerCli.findByName(workoutName).get();
        WorkoutRequestDto searchWorkout = WorkoutRequestDto.builder()
                .id(workoutResponseDto.getId())
                .build();

        boolean registerProcess = true;

        while (registerProcess) {
            Map<Integer, ExerciseResponseDto> exercisesAvailable = exerciseHandlerCli.findAvailable(searchWorkout);

            System.out.println("Exercises Available List:");
            exercisesAvailable.forEach((index, exercise) ->
                    System.out.println(index + ". " + exercise.name()));

            ExerciseResponseDto selectedExercise = null;

            while (selectedExercise == null) {
                try {
                    System.out.print("Please, select the exercise to add to the workout: ");
                    int exerciseOption = scanner.nextInt();
                    scanner.nextLine();

                    if (!exercisesAvailable.containsKey(exerciseOption)) {
                        System.out.println("Invalid Input");
                        continue;
                    }

                    selectedExercise = exercisesAvailable.get(exerciseOption);

                } catch (InputMismatchException | NullPointerException e) {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                }
            }

            ExerciseRequestDto exerciseRequestDto = ExerciseRequestDto.builder()
                    .id(selectedExercise.id())
                    .build();

            System.out.println("You're selected: " + selectedExercise.name());

            System.out.print("Please, enter the sets quantity: ");
            int sets = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Please, enter the quantity per set: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            WorkoutExerciseRequestDto workoutExerciseRequestDto = WorkoutExerciseRequestDto.builder()
                    .workout(searchWorkout)
                    .exercise(exerciseRequestDto)
                    .sets(sets)
                    .quantity(quantity)
                    .build();

            workoutExerciseHandlerCli.save(workoutExerciseRequestDto);
            System.out.println("Workout Exercise Registered Successfully!");

            System.out.print("Add another? (yes): ");
            String option = scanner.nextLine();
            if (!option.equalsIgnoreCase("yes")) {
                registerProcess = false;
            }
        }

        System.out.println("Workout Registered Successfully!");
    }
}
