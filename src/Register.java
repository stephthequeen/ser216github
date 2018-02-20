import java.util.Scanner;

public class Register {
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		int count = 1;
		
		int userInt = -999;
		double userDub = 0;
		String userStr = "void";
		
		double total = 0;
		
		System.out.println("Start? Yes/No");
		userStr = input.nextLine();
		
		if(userStr.equals("Yes"))
		{
			System.out.println("Enter -999 to quit.");
			do
			{
				System.out.print("Item #" + count + " Price: $");
				userDub = input.nextDouble();
				
				if(userDub != -999)
				{
					total += userDub;
					count ++;
				}
				
				System.out.println("Total: $" + total);
			}while(userDub != -999);
			userStr = "No";
		}
		else if(userStr.equals("No"))
		{
			System.out.println("Goodnight.");
		}
		else
		{
			System.out.println("Error. Please use valid response.");
		}
		 
		
	}

}
