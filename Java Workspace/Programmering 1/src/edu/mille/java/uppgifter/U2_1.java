package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U2_1 {
    public static void main(String[] args) {
        //Beräkna area av en triangel
        Scanner s = new Scanner(System.in);

        System.out.println("Calculate the area of a triangle!");
        System.out.println("The length of the base of the triangle: ");
        float b = s.nextFloat();
        System.out.println("The height of the triangle: ");
        float h = s.nextFloat();

        System.out.println("Area A = " + h*b/2f);
        s.close();

    }
}
