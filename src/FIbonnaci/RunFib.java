package FIbonnaci;
import java.util.Scanner;

public class RunFib {
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		Fibonnaci fib = new Fibonnaci();
		System.out.println("how many iterations");
		int user = input.nextInt();
		fib.findFib(0, 0, user);
	}

}
