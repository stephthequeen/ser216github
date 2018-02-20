package Sorting;

public class RunSorting {

	public static void main(String[] args) {
		
		Sorting test = new Sorting();
		int [] store = new int [5];
		store[0] = 3;
		store[1] = 5;
		store[2] = 2;
		store[3] = 1;
		store[4] = 4;
		
		store = test.mergeSort(store, 0, 4);
		
		for(int i = 0; i < 5; i++)
		System.out.println(store[i]);
		
	}

}
