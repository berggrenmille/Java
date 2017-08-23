package edu.mille.Project.Yatzy;

public class Scoreboard
{
    int totalScore = 0;
    boolean[] diceCombinations;

    public Scoreboard()
    {
        diceCombinations = new boolean[15];
        for(boolean b : diceCombinations)
        {
            b = true;
        }
    }
}
