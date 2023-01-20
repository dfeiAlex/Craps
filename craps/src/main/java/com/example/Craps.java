package com.example;

import java.util.Random;
import java.util.Scanner;
import java.lang.Character;

// Craps

// 1st roll:
// Player rolls 2 dice
// If the sum of the 2 dice is 7 or 11, the player wins
// Else if the sum is 2, 3 or 12, the player loses
// Otherwise, the sum becomes the point and the player plays another round

// 2nd+ roll:
// Player rolls again
// If the sum equals the point, the player wins
// Else if the sum equals 7, the player loses
// Otherwise, play next round

public class Craps {
    // Declare object variables
    int dieValue1, dieValue2, diceSum, point;
    boolean keepRolling;
    Random randInt = new Random();

    // Constructor, set initial variable values
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

    public void debug() {
        System.out.printf("Dice: %d, %d\n", dieValue1, dieValue2);
        System.out.println("Keep rolling: " + keepRolling);
        System.out.println("Point: " + point);
    }
    
    // Play a round of Craps
    public String play() {
        // Roll dice and get their sum
        rollDice();
        
        System.out.println("Rolling...");

        // First round logic
        if (!keepRolling) {
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

    // Main method, runs game loop
    public static void main(String[] args) {
        // Declare craps object
        Craps craps = new Craps();
        Scanner scan = new Scanner(System.in);
        
        boolean keepPlaying = false;
        String outcome;
        
        do {
            System.out.println("_".repeat(30));
            System.out.println("\nWELCOME TO CRAPS!");
            System.out.println("_".repeat(30) + "\n");
            
            outcome = craps.play();
            
            if (outcome.equals("Win") || outcome.equals("Loss")) {
                System.out.printf("Immediate %s! You rolled a %d", outcome, craps.diceSum);
                System.out.println("\nWould you like to play another game? (y/n)");
                
                char answer = scan.next().charAt(0);
                
                if (Character.toLowerCase(answer) == 'y') {
                    keepPlaying = true;
                } else if (Character.toLowerCase(answer) == 'n') {
                    keepPlaying = false;
                }
            } else {
//                System.out.printf("No immediate win or loss. Point is %d.\n", craps.point);
//                System.out.format("%s%30s", "\nNew Roll", "Outcome\n");
//                System.out.println("_".repeat(38));
//                while (craps.keepRolling) {
//                    return;
//                }
            }
        } while (keepPlaying);
    }
}
