package opg1;

import java.util.Scanner;

public class main {
	
	public static void main(String[] arg) {
		boolean Running = true;
		// Konstruktør for Scanner.
		Scanner console = new Scanner(System.in);
		Long Checknumber;

		while (Running) {
			System.out.print("Indtast et tal større end 1: ");
			String CheckNumber = console.nextLine();
			
			// Hvis input er et tal.
			if (isNumeric(CheckNumber)) {
				Checknumber = Long.parseLong(CheckNumber);
				
				// Hvis input er større end 1.
				if (Checknumber > 1) {
				System.out.println("Primtalfaktorer: " + PrimeFactors(Checknumber));	
				}
				// Hvis input er 0.
				else if (Checknumber == 0) {
					Running = false;
					System.out.println("Program stoppet.");	
				}
				else {
					System.out.println("Ugyldigt tal.");		
					
				}
			}
			System.out.println(" ");	
		
		}
		
	}

	// Denne metode bestemmer primtalfaktorerne.
	// Pre-betingelse: Tal som skal kontrolleres.
	// Post-betingelse: Returnere primtalfaktorerne.
	public static String PrimeFactors(Long CheckNumber) {
		Long TempChecknumber = CheckNumber;
		String Text ="";
		// Kør fra 2 til CheckNumber. 
		for (long i=2; i<CheckNumber; i++) {

			
			// Kør så længe divisionen giver et helta.
			while ((TempChecknumber % i) == 0) {
				
				// Da divisionen giver et heltal, kan vi fuldføre divisionen.
				TempChecknumber = TempChecknumber / i;	
				
				Text += i + ", ";
				
			}
		

		}
		// Retunere primtaltaktorer.
		return Text.substring(0, Text.length() - 2);
		
	}
	
	// Denne metode afgøre om en streng kun indeholder tal.
	// Pre-betingelse: input skal være en streng og ikke tom.
	// Post-betingelse: result fortæller om strengen kun indeholder tal. Dette returneres, 
	public static boolean isNumeric(String input) {
		boolean result = true;
		
		// Hvis input streng ikke er tom.
		if (input.isEmpty() == false) {
			
			for (int i=0; i <= input.length()-1; i++) {
	
				// Hvis char. index ikke er et tal.
				if(Character.isDigit(input.charAt(i)) == false) {
					result = false;
				}
			}
		}
		else {
			result = false;
		}
		
	return result;
		
	}
}
