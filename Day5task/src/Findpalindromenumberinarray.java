
public class Findpalindromenumberinarray {

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		int arra[]= {121,541,656,666,789,670};                                                           
	String h="hell";
		for(int i=0;i<arra.length;i++)
			{
			int n=0;
			int t=arra[i];
			while(arra[i]>0)
			{
	       int r=arra[i]%10;
	       n=(n*10)+r;
	       arra[i]=arra[i]/10;
			}
			System.out.println(n);
	     if(t==n)
	     {
	    	 System.out.println("palindrome");
	     }
	     else
	     {
	    	 System.out.println(" not palindrome");
	     }
             }
	
	System.out.println(h.charAt(1));
	}
}
