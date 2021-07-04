package mystringarraytask;

public class Findchracterpresentonprimelocationandconvertitupper {

	public static void main(String[] args) 
	{
	String b="ilovemyindia";
	
	System.out.println(b.length());
	System.out.println();
	for(int i=2;i<b.length();i++)
	{
		int flag=0;
		for(int v=2;v<i/2;v++)
		{
			if(i%v==0)  
			{
			flag=1;
			}
		}
		if(flag==0)
		{
			Character c=b.charAt(i);
			
			System.out.print(Character.toUpperCase(c));
		}
	}
	
			
	}
	}
	


