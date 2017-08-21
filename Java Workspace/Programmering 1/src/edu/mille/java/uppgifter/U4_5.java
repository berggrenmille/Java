package edu.mille.java.uppgifter;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
import java.util.Scanner;

public class U4_5 {
    public static void main(String[] args)
    {
        //Ta in täljare och nämnare, 4 5 etc...
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String[] inputArray = input.split(" ", -1);

        //Gör om indata till blandad form. 5 3 --> 1 !/2
        int numerator = Integer.parseInt(input.split(" ")[0]) ; //parse first part of string
        int denominator = Integer.parseInt(input.split(" ")[1]) ; //parse second part of string

        int wholes = numerator / denominator;
        System.out.println(wholes + " "+(numerator - (denominator * wholes)) +"/"+denominator);


    }
}
