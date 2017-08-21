package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U2_6a {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int rounded = ((n + 99) / 100 ) * 100; //Round to the closest upper multibel of 100
        System.out.println("Uttag: "+rounded +" kr.");
        s.close();
    }
}
