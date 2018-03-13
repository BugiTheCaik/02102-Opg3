package opg2;

import java.util.Random; // Utilize random utility.
import java.util.Scanner;

public class RandomWalk {
	
	private static double X = 0; // X Coordinate
	private static double Y = 0; // Y Coordinate
	private static double N; // 2*N-Length Coordinate space
	private static Random random = new Random();
	private static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.print("Enter Size of Grid: ");	// Get our grid size value.
		if (console.hasNextDouble()) {
			N = Math.abs(console.nextDouble());
			console.close();
		}
		else {
			System.out.print("Grid can only be integer values.");
			console.close();
			return;
		}
		
		StdDraw.setXscale(-N,N);					// Setup our image grid for drawing.
		StdDraw.setYscale(-N,N);
		StdDraw.setPenRadius(0.5/N);
		
		while(X <= N && X >= -N && Y <= N && Y >= -N) {			// Run loop until (X,Y) Coordinate is outside our (-N,N) Grid.
			
			StdDraw.point(X,Y);	
			System.out.println("Position = ("+X+","+Y+")");
			
			if (random.nextBoolean()) {							// Determine if X or Y changes by one.
				if (random.nextBoolean()) {						// Determine X goes up or down by one.
					X++;
				}
				else {
					X--;
				}
			}
			else {
				if (random.nextBoolean()) {						// Determine if Y goes up or down by one.
					Y++;
				}
				else {
					Y--;
				}
			}
		}
	}

}
