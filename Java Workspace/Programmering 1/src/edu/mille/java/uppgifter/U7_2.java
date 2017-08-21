package edu.mille.java.uppgifter;

import com.sun.applet2.preloader.event.ApplicationExitEvent;
import javafx.application.Application;

import java.util.Scanner;

public class U7_2
{
    public static Scanner s;
    public static void main(String[] args)
    {
        s = new Scanner(System.in);
        application();
        s.close();

    }

    public static void application()
    {
        int i;
        int j;
        int k;

        System.out.println("Har du rätt till skatteavdrag på dubbel bosättning?");
        System.out.println("Svara på frågorna nedan för att kolla.");
        System.out.println("Tänkte du bo ensamstående eller sammanboende?");
        System.out.println("1. Ensamstående.");
        System.out.println("2. sammanboende.");
        try
        {
            i = s.nextInt();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return;
        }
        switch (i)
        {
            case 1:
                break;
            case 2:
                break;
            default:
                return;
        }
        System.out.println("Hur många år varar arbetet?");
        try
        {
            j = s.nextInt();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return;
        }
        switch (i)
        {
            case 1:
                if(j > 1)
                {
                    System.out.println("Icke godkänd!");
                    return;
                }
                break;
            case 2:
                if(j>3)
                {
                    System.out.println("Icke godkänd!");
                    return;
                }
                break;
            default:
                return;
        }
        System.out.println("Hur många kilometer har du till arbetet just nu?");
        try
        {
            k = s.nextInt();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return;
        }
        if(k>=50)
        {
            System.out.println("Godkänd!");
            return;
        }else
        {
            System.out.println("Icke godkänd!");
            return;
        }
    }
}
