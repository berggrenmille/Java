package edu.mille.project.lotto;

public class lotto
{
    public static int maxNum;
    public static int guessNum;
    public static int[] numXRight;


    public static void CalculateResults(int max, int guessNum)
    {
        maxNum = max;
        guessNum = guessNum;
        numXRight = new int[guessNum];
        /*
        * to calculate P(6 rätt)
        * we take ((n(n+1))/2) * P(1:a fel) * P(2:a rätt) ...
         */
        for (int i = 1; i<= guessNum; i++)
        {
            if(i == guessNum)
            {

                numXRight[i-1] = FacultyRecursive(guessNum)/Product();
            }
        }
    }
    public static void ShowResults()
    {
        System.out.println(numXRight[6]);
    }

    public static int FacultyRecursive (int n)
    {
        if(n == 0)
            return 1;
        else if(n == 1)
            return 1;
        else
            return n * FacultyRecursive(n-1);
    }

    public static int Product()
    {
        int product = 1;
        for (int i = maxNum; i >= (maxNum-guessNum); i--)
        {
            System.out.println("w");
            product *= i;
        }
        return product;
    }

}
