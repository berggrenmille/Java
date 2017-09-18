package edu.mille.project.mastermind;

import edu.mille.utils.Input;

import java.util.Random;
import java.util.Scanner;

//2017-09-04
public class Mastermind
{
    public Scanner s; //Reads input
    public Random ran; //generates random numbers
    public static boolean running = true;

    public static void main(String[] args)
    {
        MastermindGUI gui = new MastermindGUI();
    }
    public void Application()
    {
        System.out.println("MASTERMIND");
        System.out.println("1: Play - 2 players");
        System.out.println("2: How to play");
        System.out.println("3: Exit");
        int menuPick = Input.GetInt(s);
        switch (menuPick) {
            case 1:
                Game();
                break;
            case 2:
                //display how to play
                break;
            case 3:
                running = false;
                break;
            default:
                break;
        }
    }

    public static void Game()
    {

    }
}