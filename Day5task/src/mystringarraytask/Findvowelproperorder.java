package mystringarraytask;

public class Findvowelproperorder {

	
	    static boolean CheckVowel(String s)
	    {
	        int n = s.length();
	        s = s.toLowerCase();
	        char c = (char)64;
	        for (int i = 0; i < n; i++) 
	        {
	            if(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u') 
	            {
	                if (s.charAt(i) < c)
	                    return false;
	                else {
	                    c = s.charAt(i);
	                }
	            }
	        }
	        return true;
	    }
	    
		public static void main(String[] args) {
		    //String str = "full stack java revature training";
		    String str = "Aeeiou";
		    if(CheckVowel(str))
		        System.out.println("Yes");
		    else
		        System.out.println("No");
		}
	}

