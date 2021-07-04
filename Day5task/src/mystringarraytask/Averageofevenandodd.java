package mystringarraytask;

public class Averageofevenandodd
{
	public static void main(String[] args)
	{
		int arr[]= {2,3,5,6,8,4};
		int evensum=0;
		int oddsum=0;
		
		
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]%2==0)
			{
				evensum=evensum + arr[i];
			}
			else 
				oddsum=oddsum+arr[i];
		}
		System.out.println("evensum"+evensum);
		System.out.println("oddsum"+oddsum);
	}

}
