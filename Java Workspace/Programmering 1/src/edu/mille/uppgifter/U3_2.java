package edu.mille.uppgifter;

import java.util.Scanner;

public class U3_2 {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int[] n = new int[4];
        for (int i = 0; i<n.length; i++)
        {
            n[i] = s.nextInt();
        }
        for(int i = 0; i < n.length / 2; i++)
        {
            int temp = n[i];
            n[i] = n[n.length - i - 1];
            n[n.length - i - 1] = temp;
        }
        for (int i = 0; i<n.length; i++)
        {
            System.out.println(n[i]);
        }
        s.close();
    }
}
