package com.example;

import java.util.Random;

// Craps

// 1st roll:
// Player rolls 2 dice
// If the sum of the 2 dice is 7 or 11, the player wins
// Else if the sum is 2, 3 or 12, the player loses
// Otherwise, the sum becomes the point and the player plays another round

// 2nd+ roll:
// Player rolls again
// If the sum equals the point, the player wins
// If the sum equals 7, the player loses
// Otherwise, play next round

public class Craps {
    // Declare object variables
    int dieValue1, dieValue2, point;
    boolean keepRolling;
    Random randInt = new Random();

    // Constructor, set initial variable values
    public Craps() {
        dieValue1 = 0;
        dieValue2 = 0;
        keepRolling = false;
    }

    // Generate random numbers for dice
    public void rollDice() {
        // Get a random number from 1 to 6 inclusive
        dieValue1 = randInt.nextInt(6) + 1;
        dieValue2 = randInt.nextInt(6) + 1;
    }

    // Play a round of Craps
    public String play() {
        // Roll dice and get their sum
        rollDice();
        int diceSum = dieValue1 + dieValue2;

        // First round logic
        if (!keepRolling) {
            switch (diceSum) {
                case 7:
                case 11:
                    return "Win";
                case 2:
                case 3:
                case 12:
                    return "Lose";
                default:
                    // Player has neither won nor lost, return to game loop
                    keepRolling = true;
                    point = diceSum;
                    return "Continue";
            }
        }

        // Second round onwards
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

    public static void main(String[] args) {
        Craps game = new Craps();
        System.out.println(game.play());
    }
}
