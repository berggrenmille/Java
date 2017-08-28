package edu.mille.project.yatzy;

public class Player
{
    public String playerName;
    public int score = 0;
    boolean[] diceComboChecks;


    public Player()
    {
        diceComboChecks = new boolean[15];
        for(boolean b : diceComboChecks)
        {
            b = true;
        }
    }
}
