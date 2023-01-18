package com.example;

import java.util.Random;

// Craps

// 1st roll:
// Player rolls 2 dice
// If the sum of the 2 dice is 7 or 11, the player wins
// Else if the sum is 2, 3 or 12, the player loses
// Otherwise, the sum becomes the point and the player plays another round

public class Craps {
    int dieValue1, dieValue2, point;
    boolean keepRolling;
    Random randInt = new Random();

    public Craps() {
        dieValue1 = 0;
        dieValue2 = 0;
        keepRolling = false;
    }

    public void rollDice() {
        dieValue1 = randInt.nextInt(6) + 1;
        dieValue2 = randInt.nextInt(6) + 1;
    }

    public String play() {
        rollDice();
        int diceSum = dieValue1 + dieValue2;

        // First round
        if (!keepRolling) {
            switch (diceSum) {
                case 2:
                case 3:
                case 12:
                    return "Loss";
                case 7:
                case 11:
                    return "Win";
                default:
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

        return "Continue";
    }

    public static void main(String[] args) {
        Craps obj = new Craps();
        System.out.println(obj.play());
    }
}
