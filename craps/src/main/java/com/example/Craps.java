package com.example;

import java.util.Random;
import java.util.Scanner;
import java.lang.Character;

public class Craps {
    // Declare object variables
    int dieValue1, dieValue2, diceSum, point;
    boolean keepRolling;
    Random randInt = new Random();
    
    static Scanner scan = new Scanner(System.in);
    static boolean keepPlaying = false;

    // Constructor to initialise variables
    public Craps() {
        dieValue1 = 0;
        dieValue2 = 0;
        keepRolling = false;
    }

    // Generate random numbers for dice and get their sum
    public void rollDice() {
        // Get a random number from 1 to 6 inclusive
        dieValue1 = randInt.nextInt(6) + 1;
        dieValue2 = randInt.nextInt(6) + 1;

        diceSum = dieValue1 + dieValue2;
    }

    // Play a round of Craps
    public String play() {
        // Roll dice and get their sum
        rollDice();

        // First round logic
        if (!keepRolling) {
            System.out.println("\n\nNEW GAME!\n\n");
            switch (diceSum) {
                case 7:
                case 11:
                    return "Win";
                case 2:
                case 3:
                case 12:
                    return "Loss";
                default:
                    // Player has neither won nor lost, return to game loop
                    keepRolling = true;
                    point = diceSum;
                    return "Continue";
            }
        }

        // Second round+ logic
        if (diceSum == point) {
            keepRolling = false;
            return "Win";
        } else if (diceSum == 7) {
            keepRolling = false;
            return "Loss";
        }

        // Player didn't win or lose, return to game loop
        return "Continue";
    }
    
    // Method to ask the player if they want to play again
    static void promptPlayAgain() {
        System.out.println("\nWould you like to play another game? (y/n)");
                
        char answer = scan.next().charAt(0);

        if (Character.toLowerCase(answer) == 'y') {
            keepPlaying = true;
        } else if (Character.toLowerCase(answer) == 'n') {
            keepPlaying = false;
        }
    }

    // Main method, runs game loop
    public static void main(String[] args) {
        // Initialise craps and scan objects
        Craps craps = new Craps();
        
        String outcome;
        
        // Welcome player
        System.out.println("_".repeat(38));
        System.out.println("\nWELCOME TO CRAPS!");
        System.out.println("_".repeat(38));
        
        // Game loop
        do {
            // Play first round
            outcome = craps.play();
            
            // Check outcome of the first round
            if (outcome.equals("Win") || outcome.equals("Loss")) {
                System.out.printf("Immediate %s! You rolled a %d\n", outcome, craps.diceSum);
                
                promptPlayAgain();
            } else {
                System.out.printf("No immediate win or loss. \nThe point is %d.\n", craps.point);
                
                // Table head
                System.out.format("%s%28s", "\n  New Roll", "Outcome\n");
                System.out.println("_".repeat(38));
                
                // While loop to keep rolling
                while (craps.keepRolling) {
                    outcome = craps.play();
                    
                    // Output round outcome
                    System.out.format("| %-2d  %31s |\n", craps.diceSum, outcome);
                }
                
                System.out.println("_".repeat(38) + "\n");
                
                // Output game outcome
                if (outcome.equals("Win")) {
                    System.out.println("You have won! ");
                } else if (outcome.equals("Loss")) {
                    System.out.println("You have lost!");
                }
                
                promptPlayAgain();
            }
        } while (keepPlaying);
    }
}
