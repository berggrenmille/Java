
import java.util.Scanner;

public class Palindrom {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Palindrome Tester");
		System.out.println("Please enter a word:");
		
		String input = sc.nextLine().toLowerCase();
		String result = new StringBuilder(input).reverse().toString().toLowerCase();

		if(input.equals(result))
		{
			System.out.println(input +" is a palindrom!");
        }else
        {
        	System.out.println(input + " is NOT a palindrom!");
        }
		sc.close();
	}
}
