package validationusingjava;

public class Regularexpression {

	public static void main(String[] args) {
		
		String adar = "5551 0107 9876";
		if (adar != null && adar.matches("[2-9]{1}[0-9]{3} [0-9]{4} [0-9]{4}")) {
			System.out.println("Valid adar");
		} else {
			System.out.println("Invalid adar");
		}

		String licence = "MH-0619850034761 ";
		
		if (licence!= null && licence.matches("[A-Z]{2}\\-[0-9]{13}")) 
		{
			System.out.println("Valid adar");
		} else {
			System.out.println("Invalid adar");
		}

		String bikenumber = "cj 3658"; 
		if (bikenumber.matches("[a-z]{2} [0-9]{4}" )) {
			System.out.println("Valid number");
		} else {
			System.out.println("Invalid Number");
		}

	}

}
