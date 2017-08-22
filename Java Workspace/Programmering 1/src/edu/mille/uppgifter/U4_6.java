package edu.mille.uppgifter;

import java.util.Scanner;

public class U4_6 {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        /*
        Förvandla timmar min sek till endast tim min sek
         */

        //Input
        System.out.println("Ange antalet timmar: ");
        float nTim;
        try
        {
            nTim = s.nextFloat();
        }catch (Exception e)
        {
          nTim = 0;
          throw e;
        }
        System.out.println("Ange antalet minuter: ");
        float nMin;
        try
        {
            nMin = s.nextFloat();
        }catch (Exception e)
        {
            nMin = 0;
            throw e;
        }
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
        float tim = 0f;
        float min = 0f;
        float sek = 0f;

        tim += nTim;
        tim += nMin / 60f;
        tim += nSek / 60f / 60f;

        min = tim*60;

        sek = min*60;

        //output
        System.out.println("Tidsomvandling ger:");
        System.out.println(tim+" h = "+min+" min = "+sek +" s");

    }
}
