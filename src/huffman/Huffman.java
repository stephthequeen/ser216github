package huffman;
//BCHS 2015

import java.util.ArrayList;


public class Huffman {
	
	private String text;
	private String perfText;
	public ArrayList <EnhNode> unsorted;
	public ArrayList <EnhNode> finalSorted = new ArrayList<EnhNode>();
	
	public Huffman(String t)
	{
		text = t;
		
		unsorted = new ArrayList <EnhNode>();
		
		for(int count = 0; count < 26; count ++)
		{
			unsorted.add(new EnhNode(0));
		}
		
		text = text.toUpperCase();
		text = text.replaceAll("\\W", "");
		//System.out.println(text);
		perfText = new String(text);
		traverse(text);	
	}
	
	public void traverse(String text)
	{
		int count = 0;
		while(count < text.length())		//0 < 3
		{
			if(text.charAt(count) >= 65 && text.charAt(count) <= 90)
			{
				//System.out.println("adding char " + text.charAt(count));
				add(text.charAt(count));	//add(A)
			}
			count++;
		}
	}
	
	public void add(char c)
	{
		int index = 26 - (((int) c) - 65);	//index = 0
		
		EnhNode edit = unsorted.get(index - 1);
		edit.setInfo((int)edit.getInfo() + 1);
		edit.setChar((char)c);
		
		//System.out.println("added EnhNode " + (char)edit.getChar() + " with frequency " + edit.getInfo());
	}

	
	public ArrayList <EnhNode> sortList(ArrayList <EnhNode> unsorted)
	{
		ArrayList <EnhNode> sorted = new ArrayList <EnhNode>();
		
		for(EnhNode test : unsorted)
		{
			if((int)test.getInfo() > 0)
			{
				if(sorted.isEmpty())
				{
					//System.out.println("adding object " + (char)test.getChar());
					sorted.add(test);
				}
				else
				{
					//System.out.println("adding object " + (char)test.getChar());
					sorted = addToList(sorted, test);
				}
			}
		}
		
		for(EnhNode enter : sorted)
		{
			finalSorted.add(enter);
		}
		return sorted;
	}
	
	public ArrayList <EnhNode> addToList(ArrayList <EnhNode> oldList, EnhNode toAdd)
	{
		ArrayList <EnhNode> newList = oldList;
		
		forLoop:
		for(EnhNode test : newList)
		{
			if(toAdd.getChar() >= 65)
			{
				if((int)toAdd.getInfo() > (int)test.getInfo())
				{
					//System.out.println("enter sort");
					newList.add(newList.indexOf(test), toAdd);
					//System.out.println("object " + (char)toAdd.getChar() + " sorted to pos " + newList.indexOf(toAdd));
					return newList;
				}
			}
			else
			{
				//System.out.println("enter num sort");
	
				if((int)toAdd.getInfo() >= (int)test.getInfo())
				{
					newList.add(newList.indexOf(test), toAdd);
					//System.out.println("num object w freq" + (int)toAdd.getInfo() + " sorted to pos " + newList.indexOf(toAdd));
					return newList;
				}
			}
			
		}
		
		newList.add(toAdd);
		//System.out.println("general object sorted to pos " + newList.indexOf(toAdd));
		return newList;
	}
	
	public EnhNode toTree(ArrayList <EnhNode> sorted)
	{
		EnhNode left;
		EnhNode right;
		EnhNode root;
		
		//System.out.println("start size " + sorted.size());
		while(sorted.size() >= 2)
		{
			right = sorted.get((sorted.size() -1));
			sorted.remove((sorted.size() -1));
			//System.out.println((char)right.getChar() + " removed");
			left = sorted.get((sorted.size() -1));
			sorted.remove((sorted.size() -1));
			//System.out.println((char)left.getChar() + " removed");
			//System.out.println("size now " + sorted.size());
			
			root = new EnhNode(((int)left.getInfo()) + ((int)right.getInfo()));
			//System.out.println("root created: freq " + root.getInfo());
			root.setLeft(left);
			root.setRight(right);
			
			//System.out.println("adding num object w freq " + root.getInfo() + " to sorted list");
			sorted = addToList(sorted, root);
			
		}
		//System.out.println("end size " + sorted.size());
		
		
		return sorted.get(0);
	}
	
	public String toString(EnhNode root)
	{
		String output = new String("Huffman Code Table: \n");
		
		huffTable(root, new String());
		
		ArrayList <EnhNode> resort = reSort();
		
		for(EnhNode stringMe : resort)
		{
			output = output + (stringMe.getInfo() + " " + (char)stringMe.getChar() + ": " + stringMe.getCode() + "\n");
		}
		
		output = output + "\nCompressed File Contents: \n";
		
		int count = 0;
		int bitLength = 0;
		while(count < perfText.length())		
		{
			if(perfText.charAt(count) >= 65 && perfText.charAt(count) <= 90)
			{
				char testMe = perfText.charAt(count);
				for(EnhNode find: resort)
				{
					if(testMe == (char)find.getChar())
					{
						output = output + (testMe + ": " + find.getCode() + "\n");
						String bits = (String)find.getCode();
						bitLength += bits.length();
					}
				}
			}
			count++;
		}
		
		int stringBits = perfText.length() * 8;
		double calc = 100 * (((double)stringBits - ((double) bitLength)) / (double)stringBits);
		
		output = output + "\nCompressed " + stringBits + " bits into " + bitLength + " bits.\n";
		output = output + "Compression rate " + (int)calc + "%";
		
		return output;
	}
	
	public ArrayList <EnhNode> reSort()
	{
		ArrayList <EnhNode> newList = new ArrayList <EnhNode>();
		
		for(EnhNode toList : finalSorted)
		{
			innerLoop:
			if(newList.isEmpty())
			{
				newList.add(toList);
			}
			else
			{
				for(EnhNode against : newList)
				{
					if(toList.getInfo() == against.getInfo())
					{
						if(toList.getChar() < against.getChar())
						{
							newList.add(newList.indexOf(against), toList);
							break innerLoop;
						}
					}
				}
				
				newList.add(toList);
				break innerLoop;
			}
		}
		
		return newList;
	}
	
	public void huffTable(EnhNode root, String huffCode)
	{
		//System.out.println((char)root.getChar() + " entered hufftable");
		
		String output = new String();
		
		if(root.getRight() == null)
		{
			//System.out.println((char)root.getChar() + " has no right node");
			
			if(root.getLeft() == null)
			{
				//System.out.println((char)root.getChar() + " has no left node");
				(root).setCode(huffCode);
				return;
			}
		}
		
		if(root.getRight() != null)
		{
			//System.out.println((char)root.getChar() + " has a right node");
			
			huffTable(root.getRight(), huffCode + "1");
		}
		
		if(root.getLeft()!= null)
		{
			//System.out.println((char)root.getChar() + " has a left node");
			
			huffTable(root.getLeft(), huffCode + "0");
			return;
		}
		
		//System.out.println((char)root.getChar() + " did not trigger");
		return;
	}
	
	public String encode(ArrayList table, String text)
	{
		String coded = new String();
		
		return coded;
	}

}
