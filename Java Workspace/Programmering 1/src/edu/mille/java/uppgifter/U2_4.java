package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U2_4 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a whole number: ");
        int n = s.nextInt();
        char c = (char)n;
        System.out.println(n+" in unicode means: '"+c+"'");
        s.close();
    }
}
