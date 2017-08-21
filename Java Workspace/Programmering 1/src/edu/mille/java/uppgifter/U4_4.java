package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U4_4 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int rounded = ((n + 500) / 1000) * 1000; //Round to the closest multibel of 1000

        System.out.println(rounded);
    }
}
