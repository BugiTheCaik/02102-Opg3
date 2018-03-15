package opg1;

import java.util.Scanner;

public class PrimeFactor {
	
	
	public static void main(String[] arg) {
		boolean Running = true;
		// Konstruktør for Scanner.
		Scanner console = new Scanner(System.in);
		long Checknumber;

		while (Running) {
			System.out.print("Indtast et tal større end 1: ");
			String CheckNumber = console.nextLine();
			
			// Hvis input er et tal.ll
			if (isNumeric(CheckNumber)) {
				Checknumber = Long.parseLong(CheckNumber);
				// Kun til at teste om størst mulige long virker
				//Checknumber = 9223372036854775807L;
				// Hvis input er større end 1.
				if (Checknumber > 1) {
					PrimeFactors(Checknumber);
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
	public static void PrimeFactors(long CheckNumber) {
		//long TempChecknumber = CheckNumber;
		String Text ="";

	
		for (long i=2; i<CheckNumber; i++) {
	
			while (CheckNumber % i == 0) {
					
					// Da divisionen giver et heltal, kan vi fuldføre divisionen.
				CheckNumber /= i;	
					
				Text += i + ", ";
	
			}
			

		
		}


		System.out.println("");
		System.out.print(" Primtalfaktorer: ");
		
		if (Text.length() >= 2)  {
		// Retunere primtaltaktorer.
			System.out.println(Text.substring(0, Text.length() - 2));
		}
		else {
			System.out.println("Ingen.");
		}
		
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
