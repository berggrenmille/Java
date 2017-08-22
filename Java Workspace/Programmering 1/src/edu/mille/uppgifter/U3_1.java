package edu.mille.uppgifter;

import java.util.Scanner;

public class U3_1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] n = new int[3];
        for(int i = 0; i < n.length; i++)
        {
            n[i] = s.nextInt();
        }

        int sum = 0;
        for (int j = 0; j<n.length;j++)
        {
            sum += n[j];
        }
        System.out.println(sum/n.length);
        s.close();
    }
}
