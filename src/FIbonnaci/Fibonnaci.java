package FIbonnaci;

public class Fibonnaci {
	
	public int finish;
	
	public Fibonnaci()
	{
		
	}
	
	public int findFib(int one, int two, int count)
	{
		if((count != 0) && (two == 0))
		{
			System.out.println(two);
			return findFib(two, 1 + two, count - 1);
		}
		else if(!(count == 0))
		{
			System.out.println(two);
			return findFib(two, one + two, count - 1);
		}
		else
		{
			System.out.println(two);
			return 0;
		}
		
	}

}
