package edu.mille.uppgifter;

import java.util.Scanner;

public class U2_2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Svenska: ");
        String svenska = s.next();

        System.out.print("Engelska: ");
        String engelska = s.next();

        System.out.println("\n"+svenska + " heter \"" +engelska +"\" på engelska.");
        s.close();
    }
}
