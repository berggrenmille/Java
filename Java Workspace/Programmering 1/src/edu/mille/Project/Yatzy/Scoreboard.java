package edu.mille.Project.Yatzy;

public class Scoreboard
{
    int totalScore = 0;
    boolean[] diceCombinations = new boolean[15];

    public Scoreboard()
    {
        for(boolean b : diceCombinations)
        {
            b = true;
        }
    }
}
