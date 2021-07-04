
public class Allprimenumbers
{

	public static void main(String[] args)
	{
		int a[]= {3,7,8,9,2,13};
		for(int i=0;i<a.length;i++)
       {
			int flag=0;
	  for(int c=2;c<a[i]/2;c++)
	  {
		if(a[i]%c==0)
		{
			flag=1;
		}
		
	  }
		if(flag==0) {
			System.out.println("prime");
		}
		else
		{
			System.out.println(" not prime");

	}

}
	}
}

