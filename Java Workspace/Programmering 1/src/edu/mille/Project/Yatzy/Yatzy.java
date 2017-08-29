package edu.mille.project.Yatzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//2017-08-22
public class Yatzy {
    public static String os;
    public static String osName;

    public static Player[] players; //The players virtual yatzy list
    public static int[] dices; // All dices in action
    public static int count = 1;
    public static boolean[] diceToRoll; // Keeps track if dice[i] should be rolled

    public static Scanner s; //Reads input
    public static Random ran; //generates random numbers
    public static boolean running = true;

    public static void main(String[] args) throws InterruptedException//Setup
    {
        String[] diceCombinations =
        {
                "Ettor", "Tvåor", "Treor", "Fyror", "Femmor", "Sexor",
                "Ett par", "Två par", "Tretal", "Fyrtal", "Lilla Stege",
                "Stora Stege", "Kåk", "Chans", "Yatzy"
        };

        s = new Scanner(System.in);
        ran = new Random();
        os = System.getProperty("os.name");
        osName = System.getProperty("user.name");
        System.out.println(os);
        System.out.println(osName);
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
        Yatzy.players = new Player[players];
        for (int i = 0; i<players; i++)
        {
            Yatzy.players[i] = new Player();
            for (int j = 0; j < Yatzy.players[i].diceComboChecks.length; j++)
                Yatzy.players[i].diceComboChecks[j] = true;
            for (int k = 0; k < players; k++)
            {

            }
            System.out.println("Player "+(i+1)+" please enter your name: ");
            Yatzy.players[i].playerName = s.nextLine();
        }
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
                System.out.println(Yatzy.players[n].playerName);
                PromptEnterKey();
                RollDice(); //First Roll
                PrintDice();

                AfterRollPrompt(n);
                count = 1;
                PromptEnterKey();
                ClearConsole();
            }
            turn++;
        }
        //game finished

    }
    public static void AfterRollPrompt(int player) throws InterruptedException
    {
        if(count != 3)
        {
            System.out.println("Enter \"1\" to check available picks.");
            System.out.println("Enter \"2\" to choose what dice to keep.");
            System.out.println("Enter \"3\" to roll active dices.");
            System.out.println("Enter \"4\" to roll all dice.");
            System.out.println("Enter \"5\" to exit application");
            int select = GetInt();

            switch (select) {
                case 1:
                    ShowAvailablePicks(player);
                    break;
                case 2:
                    PickDiceToKeep(player);
                    break;
                case 3:
                    count++;
                    RollDice();
                    ClearConsole();
                    PrintDice();
                    AfterRollPrompt(player);
                    break;
                case 4:
                    count++;
                    RollAllDice();
                    ClearConsole();
                    PrintDice();
                    AfterRollPrompt(player);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.exit(0);
            }
        }
        else
        {
            System.out.println("Enter \"1\" to check available picks.");
            System.out.println("Enter \"5\" to exit application");
            int select = GetInt();
            switch (select) {
                case 1:
                    ShowAvailablePicks(player);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.exit(0);
            }
        }
    }
    public static void ShowAvailablePicks(int player)  throws InterruptedException
    {
        ArrayList<Pick> canPick = GetAvailablePicks(player);
        if (canPick.size() > 0) {
            System.out.println("Enter: 0 to cancel");
            for (int i = 0; i < canPick.size(); i++) {
                System.out.println("Enter: " + (i + 1) + " to score: " + canPick.get(i).toString());
            }
            System.out.println("Enter: " + (canPick.size()+1) + " to cross a dice combo");
            int selection = GetInt();
            if (selection == 0) {
                ClearConsole();
                PrintDice();
                AfterRollPrompt(player);
            }
            else if(selection == canPick.size()+1)
            {
                /*
                 * Cross a dice combo
                 */
                ClearConsole();

            }
            else if (selection > canPick.size()) {
                ClearConsole();
                System.out.println("Faulty input try again");
                PrintDice();
                AfterRollPrompt(player);
            } else {
                TickDiceCombo(player, canPick.get(selection - 1));
                ApplyScore(player, canPick.get(selection - 1));
                System.out.println("Current score: " + players[player].score);
            }
        }

    }

    /*
    *All available dice combinations are detected here.
    *WARNING DARK MAGIC INSIDE*
    * ENTER AT YOUR OWN RISK *
     */
    public static ArrayList<Pick> GetAvailablePicks(int player) {
        int ones = ReturnNumOfNumInDices(1);
        int twos = ReturnNumOfNumInDices(2);
        int threes = ReturnNumOfNumInDices(3);
        int fours = ReturnNumOfNumInDices(4);
        int fives = ReturnNumOfNumInDices(5);
        int sixes = ReturnNumOfNumInDices(6);

        ArrayList<Pick> availablePicks = new ArrayList<Pick>();
        //Check what picks are aviable
        if (players[player].diceComboChecks[0] && ones > 0) //ettor
        {
            int[] n = {ones};
            availablePicks.add(new Pick("Ettor", n));
        }
        if (players[player].diceComboChecks[1] && twos > 0) //tvåor
        {
            int[] n = {twos};
            availablePicks.add(new Pick("Tvåor", n));
        }
        if (players[player].diceComboChecks[2] && threes > 0) //treor
        {
            int[] n = {threes};
            availablePicks.add(new Pick("Treor", n));
        }
        if (players[player].diceComboChecks[3] && fours > 0) //fyror
        {
            int[] n = {fours};
            availablePicks.add(new Pick("Fyror", n));
        }
        if (players[player].diceComboChecks[4] && fives > 0) //femmor
        {
            int[] n = {fives};
            availablePicks.add(new Pick("Femmor", n));
        }
        if (players[player].diceComboChecks[5] && sixes > 0) //sexor
        {
            int[] n = {sixes};
            availablePicks.add(new Pick("Sexor", n));
        }
        if (players[player].diceComboChecks[6]) //par
        {
            ArrayList<Integer> values = new ArrayList<Integer>();
            int[] valuesArr;
            if(ones >= 2) {
                values.add(1);
            }
            if(twos >= 2) {
                values.add(2);
            }
            if(threes >= 2) {
                values.add(3);
            }
            if(fours >= 2) {
                values.add(4);
            }
            if(fives >= 2) {
                values.add(5);
            }
            if(sixes >= 2) {
                values.add(6);
            }
            valuesArr = ConvertFromIntegersToInts(values);
            for (int i = 0; i < values.size(); i++)
            {
                int[] temp = new int[1];
                temp[0] = valuesArr[i];
                availablePicks.add(new Pick("Ett par", temp));
            }
        }

        if (players[player].diceComboChecks[7]) //två par
        {
            ArrayList<Integer> doublePairValue = new ArrayList<Integer>();
            //steg ett leta efter dubbla av samma
            if(ones >= 4)
            {
                doublePairValue.add(1);
                doublePairValue.add(1);
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(twos >= 4)
            {
                doublePairValue.add(2);
                doublePairValue.add(2);
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(threes >= 4)
            {
                doublePairValue.add(3);
                doublePairValue.add(3);
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(fours >= 4)
            {
                doublePairValue.add(4);
                doublePairValue.add(4);
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(fives >= 4)
            {
                doublePairValue.add(5);
                doublePairValue.add(5);
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
                doublePairValue.clear();
            }
            if(sixes>= 4)
            {
                doublePairValue.add(6);
                doublePairValue.add(6);
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
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
                availablePicks.add(new Pick("Två par", ConvertFromIntegersToInts(doublePairValue)));
        }

        if (players[player].diceComboChecks[8]) //tretal
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
                availablePicks.add(new Pick("Tretal", n));
            }
        }
        if (players[player].diceComboChecks[9])//fyrtal
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
                availablePicks.add(new Pick("Fyrtal", n));
            }

        if (players[player].diceComboChecks[10]) //Lilla stege
        {
            int[] sortedDiceArr = dices;
            Arrays.sort(sortedDiceArr);
            if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3 && dices[3] == 4 && dices[4] == 5)
                availablePicks.add(new Pick("Lilla Stege"));
        }

        if (players[player].diceComboChecks[11]) //Stora stege
        {
            int[] sortedDiceArr = dices;
            Arrays.sort(sortedDiceArr);
            if (dices[0] == 2 && dices[1] == 3 && dices[2] == 4 && dices[3] == 5 && dices[4] == 6)
                availablePicks.add(new Pick("Stora Stege"));
        }

        if (players[player].diceComboChecks[12])//kåk
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
            if (n.length != 0)
            {
                for (int i = 0; i < n.length; i++) {
                    if (n[i] > biggestNum)
                        biggestNum = n[i];
                }
                if(biggestNum != 0)
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
                if (n.length != 0)
                {
                    for (int i = 0; i < n.length; i++) {
                        if (n[i] > biggestNum)
                            biggestNum = n[i];
                    }
                }
                if(biggestNum != 0)
                  values.add(biggestNum);
                if(values.size() > 1)
                    availablePicks.add(new Pick("Kåk", ConvertFromIntegersToInts(values)));
            }
        }

        if (players[player].diceComboChecks[13])//chans
        {
            availablePicks.add(new Pick("Chans",dices));
        }

        if(players[player].diceComboChecks[14]) //YATZY
        {
            if(ones == 5 || twos == 5 || threes == 5 || fours == 5 || fives == 5 || sixes == 5)
            availablePicks.add(new Pick("Yatzy", dices));
        }
        Pick[] returnpicks = new Pick[availablePicks.size()];
        return availablePicks;
    }


    //Pick what dice to keep
    public static void PickDiceToKeep(int player) throws InterruptedException
    {
        System.out.println("Do you want to keep any of the dice?");
        System.out.println("Enter: 0 to return.");
        System.out.println("Enter: 1 to toggle dice 1, 2 for dice 2 etc...");
        System.out.println("*Only enter one number per row*");
        int n = -1;
        while(n != 0)
        {
            n = GetInt();
            if(n != 0) {
                diceToRoll[n - 1] = !diceToRoll[n - 1];
                if (diceToRoll[n - 1] == true)
                    System.out.println("Dice: " + n + " is now activated!");
                else
                    System.out.println("Dice: " + n + " is now deactivated!");
            }
        }
        ClearConsole();
        PrintDice();
        AfterRollPrompt(player);
    }

    //Roll all active dice
    public static void RollDice()
    {
        for (int i = 0; i < dices.length; i++)
        {
            if(diceToRoll[i] == true)
             dices[i] = ran.nextInt(6)+1;
        }
    }

    //activate all dice and roll them
    public static void RollAllDice() //Roll all dice
    {
        for(boolean b : diceToRoll)
        {
            b = true;
        }
        RollDice();
    }

    public static void ResetDices()
    {
        for (int i = 0; i < dices.length; i++)
        {
            diceToRoll[i] = true;
        }
    }

    public static void PrintDice()
    {
        for (int i = 0; i < dices.length; i++)
        {
            if(diceToRoll[i] == true)
                System.out.println("Dice "+(i+1) + " rolled: " + dices[i]);
            else
                System.out.println("Dice "+(i+1) + " stays at: " + dices[i]);
        }
    }

    public static int ReturnNumOfNumInDices(int n) //Returns the amount of number ns there are in dices array
    {
        int count = 0;
        boolean contains = false;
        for (int dice : dices)
        {
            if(dice == n)
                contains = true;
        }
        if(contains)
        {
            for (int i : dices)
            {
                if(i == n)
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
            case "Ett par":
                return (numbers[0]*2);
            case "Två par":
                return ((numbers[0]*2) + (numbers[1]*2));
            case "Tretal":
                return numbers[0] * 3;
            case "Fyrtal":
                return numbers[0] * 4;
            case "Lilla Stege":
                return 15;
            case "Stora Stege":
                return 21;
            case "Kåk":
                int sum1 = 0;
                sum1 += numbers[0] * 3;
                sum1 += numbers[1] * 2;
                return sum1;
            case "Chans":
                int sum2 = 0;
                for (int i : dices)
                {
                    sum2 += i;
                }
                return sum2;
            case "Yatzy":
                return 50;
            default:
                System.out.println("Faulty PickName");
                return 0;

        }
    }

    public static void ApplyScore(int player, Pick combo)
    {
        players[player].score += GetScore(combo.pickName, combo.num);
    }

    public static void TickDiceCombo(int player, Pick combo) //adds the score to the players score + disables that dice combo to be used again for that player
    {
        switch (combo.pickName)
        {
            case "Ettor":
                players[player].diceComboChecks[0] = false;
                break;
            case "Tvåor":
                players[player].diceComboChecks[1] = false;
                break;
            case "Treor":
                players[player].diceComboChecks[2] = false;
                break;
            case "Fyror":
                players[player].diceComboChecks[3] = false;
                break;
            case "Femmor":
                players[player].diceComboChecks[4] = false;
                break;
            case "Sexor":
                players[player].diceComboChecks[5] = false;
                break;
            case "Ett par":
                players[player].diceComboChecks[6] = false;
                break;
            case "Två par":
                players[player].diceComboChecks[7] = false;
                break;
            case "Tretal":
                players[player].diceComboChecks[8] = false;
                break;
            case "Fyrtal":
                players[player].diceComboChecks[9] = false;
                break;
            case "Lilla Stege":
                players[player].diceComboChecks[10] = false;
                break;
            case "Stora Stege":
                players[player].diceComboChecks[11] = false;
                break;
            case "Kåk":
                players[player].diceComboChecks[12] = false;
                break;
            case "Chans":
                players[player].diceComboChecks[13] = false;
                break;
            case "Yatzy":
                players[player].diceComboChecks[14] = false;
                break;
            case "Blank":
                System.out.println("Player "+player+ " pleace choose what to cross");
                System.out.println("Enter a number and press enter: ");
                int i = GetInt();
                players[player].diceComboChecks[i-1] = false;
                break;
        }
    }

    public static void HowToInformation() throws  InterruptedException
    {
        ClearConsole();
        System.out.println("HOW TO PLAY: ...");
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

