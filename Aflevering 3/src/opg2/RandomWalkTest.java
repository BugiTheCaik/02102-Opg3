package opg2;

import java.util.Random; // Utilize random utility.
import java.util.Scanner;

public class RandomWalkTest {
	
	private static double X = 0; // X Coordinate
	private static double Y = 0; // Y Coordinate
	private static double N; // 2*N-Length Coordinate space
	private static long steps = 0;
	private static Random random = new Random();
	private static Scanner console = new Scanner(System.in);
	
	
	// Values for Color state machine. (Fun)
	private static int state = 0;
	private static int r = 255;
	private static int g = 0;
	private static int b = 0;
	
	
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
			
			
			
			
			StdDraw.setPenColor(r, g, b);
			StdDraw.point(X,Y);	
			RGB(); 												// Change color
			System.out.println("Position = ("+X+","+Y+")");
			steps++;
			
			if (random.nextBoolean()) {							// Determine if X or Y changes by one.
				
				X += nextSkewedBoundedDouble(-1, 1, 0.0005, -X);
				
				
//				if (random.nextBoolean()) {						// Determine X goes up or down by one.
//					X++;
//				}
//				else {
//					X--;
//				}
			}
			else {
				
				Y += nextSkewedBoundedDouble(-1, 1, 0.0005, -Y);
				
				
//				if (random.nextBoolean()) {						// Determine if Y goes up or down by one.
//					Y++;
//				}
//				else {
//					Y--;
//				}
			}
		}
		
		System.out.println("Total number of steps: " + steps);
	}
	
	private static void RGB() { // State machine, loops through all colors.
		if(state == 0){
		    g++;
		    if(g == 255)
		        state = 1;
		}
		if(state == 1){
		    r--;
		    if(r == 0)
		        state = 2;
		}
		if(state == 2){
		    b++;
		    if(b == 255)
		        state = 3;
		}
		if(state == 3){
		    g--;
		    if(g == 0)
		        state = 4;
		}
		if(state == 4){
		    r++;
		    if(r == 255)
		        state = 5;
		}
		if(state == 5){
		    b--;
		    if(b == 0)
		        state = 0;
		}
	}
	
    final static public Random RANDOM = new Random(System.currentTimeMillis());

    static public double nextSkewedBoundedDouble(double min, double max, double skew, double bias) {
        double range = max - min;
        double mid = min + range / 2.0;
        double unitGaussian = RANDOM.nextGaussian();
        double biasFactor = Math.exp(bias);
        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
        return retval;
    }
}
