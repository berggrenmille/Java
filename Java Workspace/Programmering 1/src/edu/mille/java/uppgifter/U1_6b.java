package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U1_6b {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int rounded = ((n + 99) / 100 ) * 100; //Round to the closest upper multibel of 100

        int num500 = rounded / rounded;
        rounded -= num500 * 500;

        int num100 = rounded / 100;

        System.out.println("Uttag i 500-sedlar: "+ num500 * 500 + " kr.");
        System.out.println("Uttag i 100-sedlar: "+ num100 * 100 + " kr.");

    }
}
