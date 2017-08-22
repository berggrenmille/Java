package edu.mille.uppgifter;

import java.util.Scanner;

public class U4_7 {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        /*
        Förvandla timmar min sek till endast tim min sek
         */

        //Input
        System.out.println("Ange antalet sekunder: ");
        float nSek;
        try
        {
            nSek = s.nextFloat();
        }catch (Exception e)
        {
            nSek = 0;
            throw e;
        }

        //calculate
        int tim = 0;
        int min;
        int sek;

        tim = (int)nSek / 3600;
        nSek -= tim * 3600;

        min = (int)nSek / 60;
        nSek -= min * 60;

        sek = (int)(nSek+0.5f);

        //Output
        System.out.println("Tidsomvandling ger:");
        System.out.println(tim+" h, "+min+" min,  "+sek +" s");

    }
}
