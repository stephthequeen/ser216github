package huffman;
//BCHS 2015
public class EnhNode extends BSTNode{

	private char name;
	private String huffCode;
	public EnhNode left;
	public EnhNode right;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EnhNode(Comparable freq)
	{
		super(freq);
	}
	
	public int getChar() 
	{
		return name;
	}
	
	public void setChar(char n)
	{
		name = n;
	}
	
	public void setCode(String code)
	{
		huffCode = code;
	}
	
	public String getCode()
	{
		return huffCode;
	}
	
	public void setLeft(EnhNode l)
	{
		left = l;
	}
	
	public EnhNode getLeft()
	{
		return left;
	}
	
	public void setRight(EnhNode l)
	{
		right = l;
	}
	
	public EnhNode getRight()
	{
		return right;
	}

}
