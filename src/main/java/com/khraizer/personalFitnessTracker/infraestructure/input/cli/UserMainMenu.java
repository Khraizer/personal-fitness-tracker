package com.khraizer.personalFitnessTracker.infraestructure.input.cli;

import com.khraizer.personalFitnessTracker.application.dto.request.*;
import com.khraizer.personalFitnessTracker.application.dto.request.search.WorkoutSearchDto;
import com.khraizer.personalFitnessTracker.application.dto.response.*;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutHandlerCli;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutLogExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutLogHandlerCli;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Displays the main user menu after login.
 * Allows viewing workouts, logging workouts, and checking workout history.
 */
@Component
public class UserMainMenu {

    private final Scanner scanner;
    private final WorkoutHandlerCli workoutHandlerCli;
    private final WorkoutExerciseHandlerCli workoutExerciseHandlerCli;
    private final WorkoutLogHandlerCli workoutLogHandlerCli;
    private final WorkoutLogExerciseHandlerCli workoutLogExerciseHandlerCli;

    public UserMainMenu(Scanner scanner,
                        WorkoutHandlerCli workoutHandlerCli,
                        WorkoutExerciseHandlerCli workoutExerciseHandlerCli,
                        WorkoutLogHandlerCli workoutLogHandlerCli,
                        WorkoutLogExerciseHandlerCli workoutLogExerciseHandlerCli) {
        this.scanner = scanner;
        this.workoutHandlerCli = workoutHandlerCli;
        this.workoutExerciseHandlerCli = workoutExerciseHandlerCli;
        this.workoutLogHandlerCli = workoutLogHandlerCli;
        this.workoutLogExerciseHandlerCli = workoutLogExerciseHandlerCli;
    }

    /**
     * Starts the menu loop for the logged-in user.
     */
    public void showMenu(UserResponseDto userResponseDto) {
        boolean menu = true;

        while (menu) {
            printMainMenu(userResponseDto);
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (option) {
                case 1 -> handleShowWorkouts();         // List available workouts
                case 2 -> handleLogWorkout(userResponseDto); // Log a workout session
                case 3 -> showLogWorkouts(userResponseDto);  // Show workout history
                case 4 -> menu = false;                 // Exit
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void printMainMenu(UserResponseDto user) {
        System.out.println("\nWelcome " + user.getFirstName() + " to Your Personal Fitness Tracker!");
        System.out.println("1. Show Workouts");
        System.out.println("2. Log Workout");
        System.out.println("3. View Logged Workouts");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    /**
     * Displays all available workouts and their structure.
     */
    private void handleShowWorkouts() {
        Optional<Map<Integer, WorkoutResponseDto>> allWorkouts = workoutHandlerCli.getAll();

        if (allWorkouts.isEmpty()) {
            System.out.println("No workouts available.");
            return;
        }

        Map<Integer, WorkoutResponseDto> workouts = allWorkouts.get();

        System.out.println("\nAvailable Workouts:");
        workouts.forEach((index, workout) ->
                System.out.println(index + ". " + workout.getName() + " - " + workout.getDescription()));

        System.out.print("\nEnter the number of a workout to view details, or 'back' to return: ");
        String userInput = scanner.nextLine();

        if(userInput.equalsIgnoreCase("back")) return;

        try {
            int selectedIndex = Integer.parseInt(userInput);
            WorkoutResponseDto selectedWorkout = workouts.get(selectedIndex);

            Optional<List<WorkoutExerciseResponseDto>> exercisesOpt =
                    workoutExerciseHandlerCli.findByWorkout(new WorkoutSearchDto(selectedWorkout.getId()));

            if (exercisesOpt.isEmpty() || exercisesOpt.get().isEmpty()) {
                System.out.println("No exercises found for this workout.");
                return;
            }

            List<WorkoutExerciseResponseDto> exercises = exercisesOpt.get();
            WorkoutResponseDto workoutInfo = exercises.get(0).workout();

            System.out.println("\nWorkout Structure: " + workoutInfo.getName());
            System.out.println("Description: " + workoutInfo.getDescription());
            System.out.println("Exercises:");

            for (WorkoutExerciseResponseDto e : exercises) {
                System.out.println("- " + e.exercise().name() + ": " + e.sets() + " sets of " + e.quantity() + " reps");
            }

            System.out.println("\nNote: Ensure proper form and take 1-minute rest between sets.");
            System.out.print("Press Enter to return... ");
            scanner.nextLine();

        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Logs a workout for the current user, including time per exercise and calories burned.
     */
    private void handleLogWorkout(UserResponseDto userResponseDto) {


        Optional<Map<Integer, WorkoutResponseDto>> allWorkouts = workoutHandlerCli.getAll();

        if (allWorkouts.isEmpty()) {
            System.out.println("No workouts to log.");
            return;
        }

        Map<Integer, WorkoutResponseDto> workouts = allWorkouts.get();

        System.out.println("\nSelect a Workout to Log:");
        workouts.forEach((index, workout) ->
                System.out.println(index + ". " + workout.getName() + " - " + workout.getDescription()));

        System.out.print("Enter the number of a workout to log: ");
        String userInput = scanner.nextLine();

        try {
            int selectedIndex = Integer.parseInt(userInput);
            WorkoutResponseDto selectedWorkout = workouts.get(selectedIndex);

            Optional<WorkoutResponseDto> workoutOpt = workoutHandlerCli.findByName(selectedWorkout.getName());
            if (workoutOpt.isEmpty()) {
                System.out.println("Workout not found.");
                return;
            }

            WorkoutResponseDto workout = workoutOpt.get();

            // Prepare DTOs
            UserRequestDto userDto = new UserRequestDto(
                    userResponseDto.getFirstName(),
                    userResponseDto.getLastName(),
                    userResponseDto.getWeight(),
                    userResponseDto.getEmail(),
                    userResponseDto.getPassword()
            );

            WorkoutRequestDto workoutDto = WorkoutRequestDto.builder()
                    .name(workout.getName())
                    .description(workout.getDescription())
                    .build();

            System.out.println("Logging " + workoutDto.name());

            // Create workout log with empty time and calories (will be updated later)
            WorkoutLogResponseDto workoutLog = workoutLogHandlerCli.save(
                    WorkoutLogRequestDto.builder()
                            .user(userDto)
                            .workout(workoutDto)
                            .date(LocalDate.now())
                            .calories(0)
                            .time(0)
                            .build()
            );

            // Get exercises and ask time per exercise
            List<WorkoutExerciseResponseDto> exercises =
                    workoutExerciseHandlerCli.findByWorkout(new WorkoutSearchDto(selectedWorkout.getId())).get();

            int totalTime = 0;
            double totalCalories = 0.0;

            for (WorkoutExerciseResponseDto dto : exercises) {
                System.out.print("Enter time taken for "+dto.exercise().name()+" (in minutes): ");
                int time = scanner.nextInt();
                scanner.nextLine();

                totalTime += time;
                totalCalories += workoutExerciseHandlerCli.calculateCaloriesBurned(userDto.weight(), time, dto.exercise().met());

                WorkoutLogExerciseRequestDto requestDto = new WorkoutLogExerciseRequestDto(
                        WorkoutLogRequestDto.builder()
                                .id(workoutLog.getId())
                                .user(userDto)
                                .workout(workoutDto)
                                .build(),
                        ExerciseRequestDto.builder().name(dto.exercise().name()).build(),
                        time
                );

                workoutLogExerciseHandlerCli.save(requestDto);
            }

            // Update log with actual totals
            workoutLogHandlerCli.update(WorkoutLogRequestDto.builder()
                    .id(workoutLog.getId())
                    .user(userDto)
                    .workout(workoutDto)
                    .date(LocalDate.now())
                    .calories(totalCalories)
                    .time(totalTime)
                    .build());

            System.out.println("Workout " + workoutLog.getWorkout().getName() + " logged successfully!");
            System.out.println("Total time: " + totalTime);

        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Invalid Input!");
        }
    }

    /**
     * Displays the user's workout logs and lets them view details of each one.
     */
    private void showLogWorkouts(UserResponseDto userResponseDto) {
        UserRequestDto userDto = new UserRequestDto(
                userResponseDto.getFirstName(),
                userResponseDto.getLastName(),
                userResponseDto.getWeight(),
                userResponseDto.getEmail(),
                userResponseDto.getPassword()
        );

        Map<Integer, WorkoutLogResponseDto> workoutLogs = workoutLogHandlerCli.findByUser(userDto);
        if (workoutLogs.isEmpty()) {
            System.out.println("No workouts logs available.");
            return;
        }

        System.out.println("\nWorkout History (Sorted by Date Descending): ");
        workoutLogs.forEach((index, workout) ->
                System.out.println(index + ". " + "Date: " + workout.getDate() + "\nWorkout: " + workout.getWorkout().getName()));

        System.out.print("\nEnter the number of a workout log to view details, or 'back' to return: ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("back")) {
            return;
        }

        try {
            int selectedIndex = Integer.parseInt(userInput);
            WorkoutLogResponseDto selectedWorkout = workoutLogs.get(selectedIndex);

            List<WorkoutLogExerciseResponseDto> workoutLogExercises =
                    workoutLogExerciseHandlerCli.findByWorkoutLog(
                            WorkoutLogRequestDto.builder().id(selectedWorkout.getId()).build(), userDto
                    );

            System.out.println("\nWorkout Details for '" +
                    workoutLogExercises.get(0).workout().getWorkout().getName() +
                    "' on " + workoutLogExercises.get(0).workout().getDate());

            workoutLogExercises.forEach(log ->
                    System.out.println("- " + log.exercise().name() + ": " + log.time() + " minutes")
            );

            System.out.println("Total time: " + workoutLogExercises.get(0).workout().getTime() + " minutes");
            System.out.println("Calories: " + String.format("%.1f", workoutLogExercises.get(0).workout().getCalories()));

            System.out.print("Press enter to return to the menu: ");
            scanner.nextLine();

        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Invalid input.");
        }
    }
}
