package mystringarraytask;


	import java.util.*;

	public class Removeduplicatescharacterfromarray
	{
		public static void main(String[] args) {
			String str = "Java fullstack training Revature";
			str = str.toLowerCase();
			
			String ans = "";
			
			for(int i=0; i<str.length(); i++)
			{
			    if(!ans.contains(String.valueOf(str.charAt(i))))
			     ans = ans + str.charAt(i);
			}
			
			System.out.println(ans);;
			
		}
	}





