package huffman;

import java.util.ArrayList;
import java.util.Scanner;

public class HuffRunner
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
	
			Huffman compressor = new Huffman(input.nextLine());
			
			System.out.println(compressor.toString(compressor.toTree(compressor.sortList(compressor.unsorted))));
		
	}

}
