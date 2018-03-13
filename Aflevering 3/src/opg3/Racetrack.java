package opg3;

import java.util.Scanner;


public class Racetrack {
	private static Scanner console = new Scanner(System.in);
	private static double[][] PlayerData = new double[2][9];
	
	// Array index
	private static byte APlayer = 0; // Player
	private static byte AX = 1; // X-Coordinate
	private static byte AY = 2; // Y-Coordinate
	private static byte ApX = 3; // Next X-Coordinate
	private static byte ApY = 4; // Next Y-Coordinate
	private static byte AvX = 5; // Velocity X-Coordinate
	private static byte AvY = 6; // Velocity Y-Coordinate
	private static byte ACheckP = 7; // Checkpoint Counter
	private static byte ATurns = 8; // Checkpoint Counter
	
	public static void main(String[] args) {
		int Player = 0;		
		
		// Int for player 1.
		PlayerData[0][AX] = 0;
		PlayerData[0][AY] = 20;
		PlayerData[0][APlayer] = 0;
		
		// Int for player 2.
		PlayerData[1][AX] = 0;
		PlayerData[1][AY] = 14;
		PlayerData[1][APlayer] = 1;
		InitMap(); // Draw racetrack & Player
		
	
		
		while(Running(PlayerData[Player])) { // Continue the race until finish or collision.
			Player ++;
			
			if (Player > 1) {
				Player = 0;
			}
			System.out.print("Player " + (Player + 1) + ": Enter next move (1-9): ");
			
			NextMove(PlayerData[Player]); // Get user input and calculate next move

			CheckPoints(PlayerData[Player]); // Ensure user follows entire race course
			
			Draw(PlayerData[Player]); // Draw the player 1 movement
			

		}
		console.close();
	}
	

	private static void NextMove(double[] Data) { // Get the user input and calculate next move.
		
		//pX1 = X1; // Save previous move (For Line drawing)
		//pY1 = Y1;
		
		Data[ApX] = Data[AX];
		Data[ApY] = Data[AY];
		
		if (console.hasNextDouble()) {
			double N = console.nextDouble(); // Get user input, and change velocity vector accordingly
			if (N == 1) {
				//vX1 --;
				//vY1 --;
				Data[AvX] --;
				Data[AvY] --;
			}
			else if (N == 2) {
				//vY1 --;
				Data[AvY] --;
			}
			else if (N == 3) {
				//vX1 ++;
				//vY1 --;
				Data[AvX] ++;
				Data[AvY] --;
			}
			else if (N == 4) {
				//vX1 --;
				Data[AvX] --;
			}
			else if (N == 5) {
			}
			else if (N == 6) {
				//vX1 ++;
				Data[AvX] ++;
			}
			else if (N == 7) {
				//vX1 --;
				//vY1 ++;
				Data[AvX] --;
				Data[AvY] ++;
			}
			else if (N == 8) {
				//vY1 ++;
				Data[AvY] ++;
			}
			else if (N == 9) {
				//vY1 ++;
				//vX1 ++;
				Data[AvX] ++;
				Data[AvY] ++;
			}
			else {
				System.out.println("Please input a value between 1-9");
			}
		}
		Data[AX] += Data[AvX];
		Data[AY] += Data[AvY];

		Data[ATurns]++; // Count turns (Competitive aspect of game)
	}
	
	private static boolean Running(double[] Data) { // Game ends if player crashes or wins.
		if (Math.abs(Data[AX]) >= 24 || Math.abs(Data[AY]) >= 24 ) { // If outside outer wall dimension; Lose.
			System.out.println("Player " + ((int)Data[APlayer] + 1) + " hit an edge!");
			return false;
		}
		else if (Math.abs(Data[AX]) <= 12 && Math.abs(Data[AY]) <= 12) { // If inside inner wall dimension; Lose.
			System.out.println("Player " + ((int)Data[APlayer] + 1) + " hit an edge!");
			return false;
		}
		else if (Data[AX] >= -1.5 && Data[ApX] < -1.5 && Data[AY] > 0 && Data[ACheckP] == 3) { // If Checkpoints reached and player crosses finish line; Win.
			System.out.println("Player " + ((int)Data[APlayer] + 1) + "Wins! You crossed the finish line!\n It took " + Data[ATurns] + " turns");
			return false;
		}
		else if (PlayerData[0][AX] == PlayerData[1][AX] && PlayerData[0][AY] == PlayerData[1][AY]) {
			System.out.println("Car crash!");
			return false;
		}
		else {
			return true;
		}
	}
	
	
	private static void CheckPoints(double[] Data) { // Ensure player crosses race course and prevent from entering previous block.
		// 0. block.
		if (Data[AX] > -12 && Data[AX] < 12 && Data[AY] > 12 && Data[AY] < 24) {
			if (Data[ACheckP]  == 1) {
				System.out.println("You can not go back!");
			}
				
			
		}
		
		// 1. block.
		if (Data[AX] > 12 && Data[AX] < 24 && Data[AY] > -24 && Data[AY] < 24) {
			if (Data[ACheckP] == 0) {
				Data[ACheckP] ++;
			}
			
			if (Data[ACheckP] == 2) {
				System.out.println("You can not go back!");
			}
		}
		
		// 2. block.
		if (Data[AX] > -12 && Data[AX] < 12 && Data[AY] > -24 && Data[AY] < -12) {
			if (Data[ACheckP] == 1) {
				Data[ACheckP] ++;
			}
			
			if (Data[ACheckP] == 3) {
				System.out.println("You can not go back!");
			}
		}
		
		// 3. block.
		if (Data[AX] < -12 && Data[AX] > -24 && Data[AY] > -24 && Data[AY] < 24) {
			if (Data[ACheckP] == 2) {
				Data[ACheckP] ++;
			}
			
			if (Data[ACheckP] == 0) {
				System.out.println("You can not go back!");
			}
		}		

		
	}
	
	private static void InitMap() { // Initialize map. Slow process.
				
		StdDraw.setXscale(-25,25);
		StdDraw.setYscale(-25,25); 
		
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY); // Draw race course ground
		StdDraw.filledSquare(0,0,24); 
		
		StdDraw.setPenColor(StdDraw.BLACK); // Draw lines in race course
		StdDraw.setPenRadius(0.1/50); 
		
		// Draw main grid.
		
		for (int x = -23; x < 24; x++) {
			for (int y = -23; y < 24; y++) {
				StdDraw.line(x, -24, x, 24);
				StdDraw.line(-24, y, 24, y);
				
			}
		}
		
		StdDraw.setPenColor(StdDraw.WHITE); // Remove center part of course
		StdDraw.filledSquare(0,0,12);  // Draw white square.
		
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
		
		// Draw player 1 starting point.
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(PlayerData[0][AX], PlayerData[0][AY]);
		
		// Draw player 2 starting point.
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(PlayerData[1][AX], PlayerData[1][AY]);
		
		// Draw blocks
		StdDraw.setPenColor(StdDraw.GREEN); // Blocks
		StdDraw.setPenRadius(0.1/50); 
		// B1
		StdDraw.line(12, 12, 12, 24);		
		// B2
		StdDraw.line(12, -12, 12, -24);
		// B3
		StdDraw.line(-12, -12, -12, -24);
		// B4
		StdDraw.line(-12, 12, -12, 24);
	}
	
	private static void Draw(double[] Data) { // Draw player movement.
		if (Data[APlayer] == 1) {
			StdDraw.setPenColor(StdDraw.BLUE);
		}
		else {
			StdDraw.setPenColor(StdDraw.RED);
		}
		StdDraw.setPenRadius(0.5/50);
		StdDraw.point(Data[AX], Data[AY]);
		StdDraw.setPenRadius(0.25/50);
		StdDraw.line(Data[ApX], Data[ApY], Data[AX], Data[AY]);
	}
}
