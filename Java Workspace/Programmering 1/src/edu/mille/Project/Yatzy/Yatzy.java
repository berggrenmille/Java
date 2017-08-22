package edu.mille.Project.Yatzy;

import java.util.Random;
import java.util.Scanner;

//2017-08-22
public class Yatzy {

    public static Scanner s;
    public static Random ran;
    public static boolean running = true;
    public static void main(String[] args) {
        s = new Scanner(System.in);
        ran = new Random();
        while (running)
        {
            application();
        }
        s.close();
    }

    public static void application()
    {
        System.out.println("Yatzy Emulator 6000");
        System.out.println("1: Play alone");
        System.out.println("2: Play with a friend");
        int menuPick = CheckForIntInput();
        switch (menuPick)
        {
            case 1:
                Game(1);
                break;
            case 2:
                Game(2);
                break;
            case 3:
                running = false;
                break;
            default:
                System.out.println("Wrong Input!");
                break;
        }
    }

    public static void Game(int players) {
        int[] dices = new int[6]; // All dices in action
        boolean[] diceToRoll = new boolean[6]; // Keeps track if dice[i] should be rolled
        boolean[] diceCombinations = new boolean[15];
        int turn = 0;

        while (turn++ < 15) {
            if (s.nextInt() == 0) {
                System.out.println("turn: " +turn);
                for (int i : dices) {
                    i = ran.nextInt(6)+1;
                    System.out.println("dice gave: "+i);
                }
            }
        }
    }



    public static int CheckForIntInput()
    {
        boolean check = false;
        int i = 0;
        do {
            if (s.hasNextInt()) {
                i = s.nextInt();
                check = true;
            } else {
                s.next();
                System.out.println("Please enter numbers only, try again: ");
            }
        } while (!check);
        return i;
    }
}