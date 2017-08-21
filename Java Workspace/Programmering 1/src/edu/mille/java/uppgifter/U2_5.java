package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U2_5 {
    public static void main(String[] args) {
        System.out.println("Type in a number to floor: ");
        float n = new Scanner(System.in).nextFloat();
        n+= .5f;
        System.out.println((int)n);
    }
}
