package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U4_2 {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int temp = n;
        temp++;
        temp*=2;
        temp-=6;
        temp/=2;
        temp+=3;
        temp-=n;
        System.out.println(temp==1);

    }
}
