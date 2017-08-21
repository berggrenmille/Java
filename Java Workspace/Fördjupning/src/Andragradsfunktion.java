
import java.util.Scanner;

public class Andragradsfunktion {
	
	public static void Error()
	{
		System.out.println("Invalid Value");
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		 //ax2 + bx + c = 0
        System.out.println("ax^2 + bx + c = 0");
        
        System.out.print("Please enter value for a: ");
        while (!sc.hasNextDouble())//give user another shot if typing invalid input
		{
			Error();
			sc.next();
		}
        double a = sc.nextDouble();
        System.out.print("Please enter value for b: ");
        while (!sc.hasNextDouble())//give user another shot if typing invalid input
		{
			Error();
			sc.next();
		}
        double b = sc.nextDouble();
        System.out.print("Please enter value for c: ");
        while (!sc.hasNextDouble())//give user another shot if typing invalid input
		{
			Error();
			sc.next();
		}
        double c = sc.nextDouble();

        double x1 = 0;
        double x2 = 0;
        double discriminate = (b * b) - (4 * a * c); //diskriminanten
        
        //Output
        if (discriminate < 0) //inga lösningar
        {
            System.out.println("Ekvationen saknar reela lösningar.");
        }
        else if (discriminate == 0) //En lösning
        {
            x1 = -b / (2 * a); 
            System.out.println("x1 = x2 = " + x1);
        }
        else //2 lösningar
        {
            x1 = (-b + Math.sqrt(discriminate)) / (2 * a);
            x2 = (-b - Math.sqrt(discriminate)) / (2 * a);
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
        sc.close();

	}

}
