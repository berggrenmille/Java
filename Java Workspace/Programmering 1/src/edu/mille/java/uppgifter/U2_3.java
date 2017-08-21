package edu.mille.java.uppgifter;

import java.util.Scanner;

public class U2_3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Substantiv: ");
        String sub = s.next();

        System.out.print("PluralÄndelse: ");
        String plur = s.next();
        if(plur.toCharArray()[0] == '-')
        {
            plur = plur.replace("-", "");
            System.out.println("\nEn "+sub+", flera "+sub+plur);
        }else
        {
            StringBuilder temp = new StringBuilder(sub);
            temp.deleteCharAt(temp.length()-1);
            String tempSub = temp.toString();
            System.out.println("\nEn "+sub+", flera "+tempSub+plur);
        }
        s.close();

    }
}
