package opg2;

import java.util.Random;
import java.util.Scanner;

public class RandomWalk {
	
	private static double X = 0; // X Coordinate
	private static double Y = 0; // Y Coordinate
	private static double N; // 2*N-Length Coordinate space
	
	public static void main(String[] args) {
		Random random = new Random();
		Scanner console = new Scanner(System.in);
		System.out.print("Enter Size of Grid: ");
		if (console.hasNextDouble()) {
			N = Math.abs(console.nextDouble());
			console.close();
		}
		else {
			System.out.print("Please enter a value for Grid Size");
			console.close();
			return;
		}
		StdDraw.setXscale(-N,N);
		StdDraw.setYscale(-N,N);
		StdDraw.setPenRadius(0.5/N);
		while(X <= N && X >= -N && Y <= N && Y >= -N) {
			StdDraw.point(X,Y);
			System.out.println("Position = ("+X+","+Y+")");
			if (random.nextBoolean()) {
				if (random.nextBoolean()) {
					X++;
				}
				else {
					X--;
				}
			}
			else {
				if (random.nextBoolean()) {
					Y++;
				}
				else {
					Y--;
				}
			}
		}
	}

}
