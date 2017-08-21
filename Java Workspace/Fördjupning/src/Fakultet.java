import java.math.BigInteger;
import java.util.Scanner;

public class Fakultet {
	public static boolean run = true;
	public static Scanner scanner;
	
	public static BigInteger RecF(int n)
	{
		if(n == 1)
		{
			return BigInteger.ONE; //return 1
		}else
		{
			return BigInteger.valueOf(n).multiply(RecF(n-1)); //return value of n * !(n-1)
		}	
	}
	
	public static BigInteger IterF(int n)
	{
		if(n == 1)
		{
			return BigInteger.ONE; //return 1
		}else
		{
			BigInteger tempValue = BigInteger.ONE;
			for(int i = 2; i<=n; i++ )
			{
				tempValue = tempValue.multiply(BigInteger.valueOf(i));
			}
			return tempValue;
		}	
	}
	
	public static void Error()
	{
		System.out.println("Invalid Value");
	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		scanner = new Scanner(System.in);
		while(run)
		{
			application();
		}
		scanner.close();
	}
	
	public static void application() throws InterruptedException
	{
		// TODO Auto-generated method stub

		 //Scanner so Input can be read
		Integer n = null; //not a primitive type (can be null) needed null values for prototyping :(
		System.out.print("Please enter a whole positive number: ");
		while (!scanner.hasNextInt())//give user another shot if typing invalid input
		{
			Error();
			scanner.next();
		}
		n = Integer.valueOf(scanner.nextInt()); // assign n the value of input
		
		System.out.println("Do you want to solve !" + n + " with a recursive or iterative function?");
		System.out.println("Please enter 1 for recursive(slower),  2 for iterative(fastest) or 3 for both(slowest): ");
		
		while (!scanner.hasNextInt()) //give user another shot if typing invalid input
		{
			Error();
			scanner.next();
		}
		switch(scanner.nextInt())
		{
		case 1:
			System.out.println(RecF(n.intValue()));
			break;
		case 2:
			System.out.println(IterF(n.intValue()));
			break;
		case 3:
			System.out.println("Recursive: "+RecF(n.intValue()));
			System.out.println("Iterative: "+IterF(n.intValue()));
			break;
		case 4:
			run = false; 
			return;
		default:
			Error();	
			System.out.println();
			Thread.sleep(1000);
			return;
		}
		System.out.println();
		Thread.sleep(5000);
	}
}
