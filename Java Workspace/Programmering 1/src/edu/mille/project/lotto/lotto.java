package edu.mille.project.lotto;

public class lotto
{
    public static int maxNum;
    public static int guessNum;
    public static double[] PercentX;


    public static void CalculateResults(int max, int _guessNum)
    {
        maxNum = max;
        guessNum = _guessNum;
        PercentX = new double[(int)guessNum];
        /*
        * to calculate P(6 rätt)
        * we take ((n(n+1))/2) * P(1:a fel) * P(2:a rätt) ...
         */
        for (int i = 1; i<= guessNum; i++)
        {
            System.out.println("Calculating: " +CalculateNumerator(guessNum) + " "+CalculateDenominator() );
            if(i == guessNum)
            {
                PercentX[i-1] = CalculateNumerator(guessNum)/CalculateDenominator();
            }
            else
            {
                PercentX[i-1] = CalculateConstant(i) * (CalculateNumerator(guessNum)/CalculateDenominator());
            }
            System.out.println(PercentX[i-1]);
        }
    }
    public static void ShowResults()
    {
        System.out.println("wow");
    }

    public static double CalculateNumerator (int n)
    {
        if(n == 0)
            return 1;
        else if(n == 1)
            return 1;
        else
            return n * CalculateNumerator(n-1);
    }

    public static double CalculateDenominator()
    {
        double product = 1;
        for (long i = maxNum; i > (maxNum-guessNum); i--)
        {
            product *= i;
        }
        return product;
    }

    public static double CalculateConstant(long n)
    {
        //Formula
        //(n(n+1))/2
        double k = (n*(n+1))/2;
        return k;
    }

}
