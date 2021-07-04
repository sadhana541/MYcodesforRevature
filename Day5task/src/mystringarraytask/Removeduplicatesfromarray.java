package mystringarraytask;


	import java.util.*;

	public class Removeduplicatesfromarray
	{
		public static void main(String[] args) {
			int a[] = {1,2,3,4,1,3,2};
			int n = a.length;
			Arrays.sort(a);
			int[] temp = new int[n];
	        int j = 0;
	  
	        for (int i = 0; i < n - 1; i++) {
	            if (a[i] != a[i + 1]) {
	                temp[j++] = a[i];
	            }
	        }
	  
	        temp[j++] = a[n - 1];
	        
	        for(int i=0; i<j; i++)
	        {
	            System.out.println(temp[i]);
	        }
		}
	}



