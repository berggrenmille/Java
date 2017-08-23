package edu.mille.Project.Yatzy;

import com.sun.deploy.util.ArrayUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import jdk.internal.dynalink.beans.StaticClass;

import javax.swing.plaf.synth.Region;
import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//2017-08-22
public class Yatzy {
    public static String os;

    public static Scoreboard[] scoreboards; //The players virtual yatzy list
    public static int[] dices; // All dices in action
    public static boolean[] diceToRoll; // Keeps track if dice[i] should be rolled

    public static Scanner s; //Reads input
    public static Random ran; //generates random numbers
    public static boolean running = true;

    public static void main(String[] args) throws InterruptedException//Setup
    {
        s = new Scanner(System.in);
        ran = new Random();
        os = System.getProperty("os.name");
        System.out.println(os);
        while (running) {
            ClearConsole();
            application();

        }

        s.close();
    }

    public static void application() throws InterruptedException //Main menu
    {
        System.out.println("Yatzy Emulator 6000");
        System.out.println("1: Play alone");
        System.out.println("2: Play with a friend");
        System.out.println("3: How to play");
        System.out.println("4: Exit");
        int menuPick = GetInt();
        switch (menuPick) {
            case 1:
                Game(1);
                break;
            case 2:
                Game(2);
                break;
            case 3:
                HowToInformation();
                break;
            case 4:
                running = false;
                break;
            default:
                System.out.println("Faulty Input!");
                break;
        }
    }

    public static void Game(int players) throws InterruptedException {
        scoreboards = new Scoreboard[players];
        dices = new int[5];
        diceToRoll = new boolean[5];

        int turn = 0;
        while (turn < 15) {
            ClearConsole();
            for (int n = 0; n < players; n++) {
                //Game logic
                ResetDices(); //Make all dices rollable
                //First throw
                System.out.println("Omgång: " + (turn + 1));
                System.out.println("Player " + (n + 1));
                PromptEnterKey();
                RollDice(); //First Roll
                PrintDices();

                //pick dice to keep
                AfterRollPrompt();
                PromptEnterKey();
                PrintAviablePicks(n);
              //  PickDiceToKeep();

                //continue to roll
                PromptEnterKey();
                ClearConsole();


            }
            turn++;
        }
    }
    public static void AfterRollPrompt()
    {
        System.out.println("Enter \"1\" to check aviable picks.");
        System.out.println("Enter \"2\" to choose what dice to keep.");
        System.out.println("Enter \"4\" to roll active dices.");
        System.out.println("Enter \"4\" to roll all dice.");
        int select = GetInt();
        switch (select)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    public static void PrintAviablePicks(int player) {
        int ones = ReturnNumOfNumInDices(1);
        int twos = ReturnNumOfNumInDices(2);
        int threes = ReturnNumOfNumInDices(3);
        int fours = ReturnNumOfNumInDices(4);
        int fives = ReturnNumOfNumInDices(5);
        int sixes = ReturnNumOfNumInDices(6);


        ArrayList<Pick> aviablePicks = new ArrayList<Pick>();
        //Check what picks are aviable
        if (scoreboards[player].diceCombinations[0] && ones > 0) //ettor
        {
            int[] n = {ones};
            aviablePicks.add(new Pick("Ettor", n));
        }
        if (scoreboards[player].diceCombinations[1] && twos > 0) //tvåor
        {
            int[] n = {twos};
            aviablePicks.add(new Pick("Tvåor", n));
        }
        if (scoreboards[player].diceCombinations[2] && threes > 0) //treor
        {
            int[] n = {threes};
            aviablePicks.add(new Pick("Treor", n));
        }
        if (scoreboards[player].diceCombinations[3] && fours > 0) //fyror
        {
            int[] n = {fours};
            aviablePicks.add(new Pick("Fyror", n));
        }
        if (scoreboards[player].diceCombinations[4] && fives > 0) //femmor
        {
            int[] n = {fives};
            aviablePicks.add(new Pick("Femmor", n));
        }
        if (scoreboards[player].diceCombinations[5] && sixes > 0) //sexor
        {
            int[] n = {sixes};
            aviablePicks.add(new Pick("Sexor", n));
        }

        if (scoreboards[player].diceCombinations[6]) //par
        {
            int[] value = new int[1];
            if(ones >= 2) {
                value[0] = 1;
                aviablePicks.add(new Pick("Ett par", value));
            }
            if(twos >= 2) {
                value[0] = 2;
                aviablePicks.add(new Pick("Ett par", value));
            }
            if(threes >= 2) {
                value[0] = 3;
                aviablePicks.add(new Pick("Ett par", value));
            }
            if(fours >= 2) {
                value[0] = 4;
                aviablePicks.add(new Pick("Ett par", value));
            }
            if(fives >= 2) {
                value[0] = 5;
                aviablePicks.add(new Pick("Ett par", value));
            }
            if(sixes >= 2) {
                value[0] = 6;
                aviablePicks.add(new Pick("Ett par", value));
            }
        }

        if (scoreboards[player].diceCombinations[7]) //två par
        {
            ArrayList<Integer> doublePairValue = new ArrayList<Integer>();
            //steg ett leta efter dubbla av samma
            if(ones >= 4)
            {
                doublePairValue.add(1);
                doublePairValue.add(1);
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(twos >= 4)
            {
                doublePairValue.add(2);
                doublePairValue.add(2);
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(threes >= 4)
            {
                doublePairValue.add(3);
                doublePairValue.add(3);
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(fours >= 4)
            {
                doublePairValue.add(4);
                doublePairValue.add(4);
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(fives >= 4)
            {
                doublePairValue.add(5);
                doublePairValue.add(5);
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(sixes>= 4)
            {
                doublePairValue.add(6);
                doublePairValue.add(6);
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }

            //Steg 2 - leta efter olika
            if(ones >= 2)
            {
                doublePairValue.add(1);
            }
            if(twos >= 2)
            {
                doublePairValue.add(2);
            }
            if(threes >= 2)
            {
                doublePairValue.add(3);
            }
            if(fours >= 2)
            {
                doublePairValue.add(4);
            }
            if(fives >= 2)
            {
                doublePairValue.add(5);
            }
            if(sixes>= 2)
            {
                doublePairValue.add(6);
            }
            if(doublePairValue.size() == 2)
                aviablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
        }

        if (scoreboards[player].diceCombinations[8]) //tretal
        {
            if (ones >= 3 || twos >= 3 || threes >= 3 || fours >= 3 || fives >= 3 || sixes >= 3) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                if (ones >= 3)
                    temp.add(1);
                if (twos >= 3)
                    temp.add(2);
                if (threes >= 3)
                    temp.add(3);
                if (fours >= 3)
                    temp.add(4);
                if (fives >= 3)
                    temp.add(5);
                if (sixes >= 3)
                    temp.add(6);

                int[] n = ConvertFromIntegersToInts(temp); //Convert list to int array
                aviablePicks.add(new Pick("Tretal", n));
            }
        }
        if (scoreboards[player].diceCombinations[9])//fyrtal
            if (ones >= 4 || twos >= 4 || threes >= 4 || fours >= 4 || fives >= 4 || sixes >= 4) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                if (ones >= 4)
                    temp.add(1);
                if (twos >= 4)
                    temp.add(2);
                if (threes >= 4)
                    temp.add(3);
                if (fours >= 4)
                    temp.add(4);
                if (fives >= 4)
                    temp.add(5);
                if (sixes >= 4)
                    temp.add(6);

                int[] n = ConvertFromIntegersToInts(temp); //Convert list to int array
                aviablePicks.add(new Pick("Fyrtal", n));
            }

        if (scoreboards[player].diceCombinations[10]) //Lilla stege
        {
            int[] sortedDiceArr = dices;
            Arrays.sort(sortedDiceArr);
            if (dices[0] != 1)
                return;
            else if (dices[1] != 2)
                return;
            else if (dices[2] != 3)
                return;
            else if (dices[3] != 4)
                return;
            else if (dices[4] != 5)
                return;
            else
                aviablePicks.add(new Pick("Lilla Stege"));

        }

        if (scoreboards[player].diceCombinations[11]) //Stora stege
        {
            int[] sortedDiceArr = dices;
            Arrays.sort(sortedDiceArr);
            if (dices[1] != 2)
                return;
            else if (dices[2] != 3)
                return;
            else if (dices[3] != 4)
                return;
            else if (dices[4] != 5)
                return;
            else if (dices[5] != 6)
                return;
            else
                aviablePicks.add(new Pick("Stora Stege"));

        }

        if (scoreboards[player].diceCombinations[12])//kåk
        {
            ArrayList<Integer> values = new ArrayList<Integer>();
            //steg 1
            ArrayList<Integer> temp = new ArrayList<Integer>();
            if (ones >= 3)
                temp.add(1);
            if (twos >= 3)
                temp.add(2);
            if (threes >= 3)
                temp.add(3);
            if (fours >= 3)
                temp.add(4);
            if (fives >= 3)
                temp.add(5);
            if (sixes >= 3)
                temp.add(6);
            int[] n = ConvertFromIntegersToInts(temp); //Convert list to int array
            int biggestNum = 0;
            if (n.length == 0)
                return;
            else {
                for (int i = 0; i < n.length; i++) {
                    if (n[i] > biggestNum)
                        biggestNum = n[i];
                }
            }
            values.add(biggestNum);

            //steg 2 mindre del
            temp.clear();
            if (ones >= 2 && 1 != biggestNum)
                temp.add(1);
            if (twos >= 2 && 2 != biggestNum)
                temp.add(2);
            if (threes >= 2 && 3 != biggestNum)
                temp.add(3);
            if (fours >= 2 && 4 != biggestNum)
                temp.add(4);
            if (fives >= 2 && 5 != biggestNum)
                temp.add(5);
            if (sixes >= 2 && 6 != biggestNum)
                temp.add(6);
            n = ConvertFromIntegersToInts(temp); //Convert list to int array
            biggestNum = 0;
            if (n.length == 0)
                return;
            else {
                for (int i = 0; i < n.length; i++) {
                    if (n[i] > biggestNum)
                        biggestNum = n[i];
                }
            }
            values.add(biggestNum);
            aviablePicks.add(new Pick("Kåk", ConvertFromIntegersToInts(values)));
        }

        if (scoreboards[player].diceCombinations[13])//chans
        {
            aviablePicks.add(new Pick("Chans",dices));
        }

        if(scoreboards[player].diceCombinations[14]) //YATZY
        {
            aviablePicks.add(new Pick("Yatzy", dices));
        }
    }


    public static void PickDiceToKeep()
    {
        //System.out
    }

    public static void RollDice()
    {
        for (int i = 0; i < dices.length; i++)
        {
            if(diceToRoll[i] == true)
             dices[i] = ran.nextInt(6)+1;
        }
    }

    public static void RollAllDice() //Roll all dice
    {
        for (int i = 0; i < dices.length; i++)
        {
            dices[i] = ran.nextInt(6)+1;
        }
    }

    public static void ResetDices()
    {
        for (int i = 0; i < dices.length; i++)
        {
            diceToRoll[i] = true;
        }
    }

    public static void PrintDices()
    {
        for (int i = 0; i < dices.length; i++)
        {
            if(diceToRoll[i] == true)
                System.out.println("Dice "+(i+1) + " rolled: " + dices[i]);
            else
                System.out.println("Dice "+(i+1) + " stayed at: " + dices[i]);
        }
    }

    public static int ReturnNumOfNumInDices(int n) //Returns the amount of number ns there are in dices array
    {
        int count = 0;
        if(Arrays.asList(dices).contains(n))
        {
            for (int i : dices)
            {
                if(i == 1)
                    count++;
            }
        }
        return count;
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

    public static int[] ConvertFromIntegersToInts(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public static void PromptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        s.nextLine();
    }

    public static int GetScore(String diceComboName, int[] numbers)
    {
        switch (diceComboName)
        {
            case "Ettor":
                return numbers[0];
            case "Tvåor":
                return 2 * numbers[0];
            case "Treor":
                return 3 * numbers[0];
            case "Fyror":
                return 4 * numbers[0];
            case "Femmor":
                return 5 * numbers[0];
            case "Sexor":
                return 6 * numbers[0];
            case "Yatzy":
                return 50;
            default:
                int sum = 0;
                for (int i : dices)
                {
                    sum += i;
                }
                return sum;
        }
    }

    public static void SetScoreAndTickScoreboard(int player, String diceCombo) //adds the score to the players score + disables that dice combo to be used again for that player
    {
        scoreboards[player].totalScore += GetScore(diceCombo, dices);
        switch (diceCombo)
        {
            case "Ettor":
                scoreboards[player].diceCombinations[0] = false;
                break;
            case "Tvåor":
                scoreboards[player].diceCombinations[1] = false;
                break;
            case "Treor":
                scoreboards[player].diceCombinations[2] = false;
                break;
            case "Fyror":
                scoreboards[player].diceCombinations[3] = false;
                break;
            case "Femmor":
                scoreboards[player].diceCombinations[4] = false;
                break;
            case "Sexor":
                scoreboards[player].diceCombinations[5] = false;
                break;
            case "Blank":
                System.out.println("Player "+player+ " pleace choose what to cross");
                System.out.println("Enter a number and press enter: ");
                int i = GetInt();
                scoreboards[player].diceCombinations[i-1] = false;
                break;
        }
    }

    public static void HowToInformation() throws  InterruptedException
    {
        ClearConsole(); //Make room in console
        System.out.println("HOW TO PLAY: ..."); //info
        PromptEnterKey();
        application();//return to main menu
    }

    public static void ClearConsole()  throws InterruptedException
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.print("\r\n");
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }
}

