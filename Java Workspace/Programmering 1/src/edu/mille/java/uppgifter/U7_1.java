package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U7_1
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = "";

        while(!input.equals("höger") && !input.equals("vänster"))
        {
            System.out.println("Höger eller vänster");
            input = s.nextLine().toLowerCase();
        }

        if(input.equals("höger"))
        {
            System.out.println("Du cyklar åt höger");
        }
        else
        {
            System.out.println("Du cyklar åt vänster");
        }


    }
}


