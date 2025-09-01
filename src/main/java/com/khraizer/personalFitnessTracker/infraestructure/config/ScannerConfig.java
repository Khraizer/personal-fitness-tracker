package com.khraizer.personalFitnessTracker.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ScannerConfig {

    /**
     * Defines a Scanner bean to be used throughout the application.
     * This Scanner reads input from the standard input stream (System.in).
     */
    @Bean
    public Scanner getInstance(){
        return new Scanner(System.in);
    }
}
