package Sorting;

public class Sorting {
	
	public Sorting()
	{
		
	}
	
	public int[] mergeSort(int a[], int low, int high)
	{
		if(low == high)
		{
			return a;
		}
		
		int length = high-low+1;
		int pivot = (low + high)/2;//if this is a decimal it will just round down
		
		mergeSort(a, low, pivot);
		mergeSort(a, pivot + 1, high);
		
		int working [] = new int[length];
		
		for(int count = 0; count < length; count ++)
		{	//make working[] the same as a[] starting at wherever the low point is
			working[count] = a[low + count];
		}
		
		int m1 = 0;
		int m2 = pivot - low + 1;
		
		for(int count = 0; count < length; count++)//for the number of values in this recurse
		{
			if(m2 <= high - low)//if the distance between the middle and the bottom (including the pivot)
			{					//is greater than between the top and the bottom
				if(m1 <= pivot - low)//if the distance between the pivot and the bottom
				{					//is greater than or equal to 0
					if(working[m1] > working[m2])//if the number at working[0] is
					{							//greater than the number at working[bottom of the top set
						a[count + low] = working[m2++];//a[bottom of the set + count] == working[bottom of the top set + 1]
					}
					else//if the number at working[0] is greater than the number at
					{	//working[bottom of the bottom set]
						a[count + low] = working[m1++]; //a[bottom of the set + count] = working[bottom + 1]
					}
				}
				else//if the distance between the pivot and the bottom is less than 0
				{	//wait what?
					a[count + low] = working[m2++];//a[bottom of the set + count] == working[bottom of the top set + 1]
				}
			}
			else//if the distance between high and low is less than the position of the
			{	//bottom of the top set
				a[count + low] = working[m1++];//
			}
		}
		
		return a;
	}
}