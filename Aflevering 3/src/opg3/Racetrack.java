package opg3;

import java.util.Scanner;
// ssdsd

public class Racetrack {

	private static double X = 0; // X-Coordinate
	private static double Y = 19; // Y-Coordinate
	private static double pX = 0; // Next X-Coordinate
	private static double pY = 0; // Next Y-Coordinate
	private static double vX = 0; // Velocity X-Coordinate
	private static double vY = 0; // Velocity Y-Coordinate
	private static int Turns = 0; // Turn counter.
	private static int CheckP = 0; // Checkpoint Counter
	private static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		InitMap(); // Draw racetrack & Player
		while(Running()) { // Continue the race until finish or collision.
			NextMove(); // Get user input and calculate next move
			CheckPoints(); // Ensure user follows entire race course
			Draw(); // Draw the players movement
		}
		console.close();
	}
	
	private static void NextMove() { // Get the user input and calculate next move.
		System.out.print("Enter next move (1-9): ");
		pX = X; // Save previous move (For Line drawing)
		pY = Y;
		if (console.hasNextDouble()) {
			double N = console.nextDouble(); // Get user input, and change velocity vector accordingly
			if (N == 1) {
				vX --;
				vY --;
			}
			else if (N == 2) {
				vY --;
			}
			else if (N == 3) {
				vX ++;
				vY --;
			}
			else if (N == 4) {
				vX --;
			}
			else if (N == 5) {
			}
			else if (N == 6) {
				vX ++;
			}
			else if (N == 7) {
				vX --;
				vY ++;
			}
			else if (N == 8) {
				vY ++;
			}
			else if (N == 9) {
				vY ++;
				vX ++;
			}
			else {
				System.out.println("Please input a value between 1-9");
			}
		}
		X += vX; // New position is based on our velocity vector.
		Y += vY;
		Turns++; // Count turns (Competitive aspect of game)
	}
	
	private static boolean Running() { // Game ends if player crashes or wins.
		if (Math.abs(X) >= 24 || Math.abs(Y) >= 24 ) { // If outside outer wall dimension; Lose.
			System.out.println("You hit an edge!");
			return false;
		}
		else if (Math.abs(X) <= 12 && Math.abs(Y) <= 12) { // If inside inner wall dimension; Lose.
			System.out.println("You hit an edge!");
			return false;
		}
		else if (X >= -1.5 && pX < -1.5 && Y > 0 && CheckP == 3) { // If Checkpoints reached and player crosses finish line; Win.
			System.out.println("You crossed the finish line!\n It took " + Turns + " turns");
			return false;
		}
		else {
			return true;
		}
	}
	
	
	private static void CheckPoints() { // Ensure player crosses race course.
		if (CheckP == 0 && X > 0 && Y < 0) {
			CheckP ++;
		}
		else if (CheckP == 1 && X < 0 && Y < 0) {
			CheckP ++;
		}
		else if (CheckP == 2 && X < 0 && Y > 0) {
			CheckP ++;
		}
		
	}
	
	private static void InitMap() { // Initialize map. Slow process.
		StdDraw.setXscale(-25,25);
		StdDraw.setYscale(-25,25); 
		
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY); // Draw race course ground
		StdDraw.filledSquare(0,0,24); 
		
		StdDraw.setPenColor(StdDraw.BLACK); // Draw lines in race course
		StdDraw.setPenRadius(0.1/50); 
		for (int x = -23; x < 24; x++) {
			for (int y = -23; y < 24; y++) {
				StdDraw.line(x, -24, x, 24);
				StdDraw.line(-24, y, 24, y);
				
			}
		}
		
		StdDraw.setPenColor(StdDraw.WHITE); // Remove center part of course
		StdDraw.filledSquare(0,0,12);
		
		for (double y = 23.5; y >= 12; y--) { // Draw Finish line
			if (y % 2 == 0.5) {
				StdDraw.setPenColor(StdDraw.WHITE);
			}
			else {
				StdDraw.setPenColor(StdDraw.BLACK);
			}
			StdDraw.filledSquare(-1.5, y, 0.5);
			
		}
		
		StdDraw.setPenColor(StdDraw.BLACK); // Draw Race course edges and player.
		StdDraw.setPenRadius(0.2/50);
		StdDraw.square(0, 0, 24);
		StdDraw.square(0, 0, 12);
		StdDraw.setPenRadius(0.5/50);
		
		StdDraw.point(X, Y);
	}
	
	private static void Draw() { // Draw player movement.
		StdDraw.setPenRadius(0.5/50);
		StdDraw.point(X, Y);
		StdDraw.setPenRadius(0.25/50);
		StdDraw.line(pX, pY, X, Y);
	}
}
