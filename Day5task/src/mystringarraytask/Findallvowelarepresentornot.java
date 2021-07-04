package mystringarraytask;

public class Findallvowelarepresentornot {

	public static void main(String[] args) 
	{
		String d="aeiou";
		int a=0;
		int e=0;
		int ii=0;
		int o=0;
		int u=0;
		for(int i=0;i<d.length();i++)
		{
 			if(d.charAt(i)=='a')
              
              {
            	  a=1;
	
               }
              else if(d.charAt(i)=='e')
              {
            	  
            	  e=1;
              }
 			
 			
              else if(d.charAt(i)=='i')
              {
            	  
            	  ii=1;
              }
              else if(d.charAt(i)=='o')
              {
            	  
            	  o=1;
              }
              else 
              {
            	  
            	  u=1;
              }
 			
 			
		}
		
		if(a==1&&e==1&&ii==1&&o==1&&u==1)
		{
			System.out.println("yes all vowels are present");
		}
		else
		{
			System.out.println("no all vowels are  not present");
		}
		}

}

