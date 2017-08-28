package edu.mille.project.lotto;

import java.util.Scanner;

//2017-08-28
public class Main {
    public static Scanner s;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        application();
        s.close();
    }

    public static void application() {
        System.out.println("Please enter a number to show the corresponding results!");
        System.out.println("1: for Lotto");
        System.out.println("2: for Måltips");
        System.out.println("3: for Stryktips");
        int i = GetInt();
        switch (i)
        {
            case 1:
                lotto.CalculateResults(35,7);
                lotto.ShowResults();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public static int GetInt()
    {
        boolean check = false;
        int i = 0;
        do {
            if (s.hasNextInt()) {
                i = s.nextInt();
                check = true;
                s.nextLine();
            } else {
                s.next();
                System.out.println("Please enter numbers only, try again: ");
            }
        } while (!check);
        return i;
    }
}