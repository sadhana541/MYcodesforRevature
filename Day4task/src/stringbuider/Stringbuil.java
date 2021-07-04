package stringbuider;

public class Stringbuil {

	public static void main(String[] args) {
		int b,c=0;
		String s="My name is shivani";
		StringBuilder sb=new StringBuilder();
		String ar[]=s.split(" ");
		
		for (int i = 0; i < ar.length; i++) 
		{
		int r=ar[i].length();
	  // sb.append(ar[i].substring(0,r-1));
	
	System.out.println(sb.append(ar[i].substring(0,r-1)).append(Character.toUpperCase(ar[i].charAt(r-1))).append(" "));

	}
		
}
}