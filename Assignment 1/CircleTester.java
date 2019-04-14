import java.awt.Color;
/**
 * This class is used as a tester class for the Circle class. It tests all the instance variables and methods of the Circle class.
 * 
 * @author Rishabh Jain
 *
 */
public class CircleTester {
	
	/**
	 * Main method for testing the object of class Circle and and its instance variables and methods.
	 * 
	 * @param args not being used in this application.
	 */
	public static void main(String[] args) {
		Circle c = new Circle(); // object created
		System.out.println("Object is Created");
		System.out.println();
		
		c.color = new Color(0,255,0); // color assigned
		System.out.println("Color is set");
		System.out.println();
		
		c.filled = true; //color filled
		System.out.println("Circle filled (true/false) :" + c.filled);
		System.out.println();
		
		c.theta = 3.14159; //theta assigned
		System.out.println("Theta is set as: " + c.theta);
		System.out.println();
		
		c.xc = 1; //center's x coordinate given
		c.yc = 1; //center's y coordinate given
		System.out.println("X and Y in local coordinate system: " + c.xc + " and "+ c.yc);
		System.out.println();
		
		// x coordinates (local system) of surrounding square's vertices assigned
		c.xLocal = new double[2];
		c.xLocal[0] = 5;
		c.xLocal[1] = -7;
		// y coordinates (local system) of surrounding square's vertices assigned
		c.yLocal = new double[2];
		c.yLocal[0] = 5;
		c.yLocal[1] = -7;
		c.setVertices(7.56); //calling function to assign x and y local coordinates of the square surrounding the circle.
		System.out.println("Local coordinates have been assigned");
		System.out.println();
		
		System.out.println("Y coordinates of the square's vertices surrounding the circle are:");
		for(int i=0;i<c.xLocal.length;i++)
		{
			System.out.println(c.xLocal[i] + " ");
		}
		System.out.println();
		 
		System.out.println("Y coordinates of the square's vertices surrounding the circle are:");
		for(int i=0;i<c.yLocal.length;i++)
		{
			System.out.println(c.yLocal[i] + " ");
		}
		System.out.println();
		
		c.translate(1.36, 2.155); //calling function to translate the circle
		System.out.println("Translate method has been used");
		System.out.println("The X and Y coordinates of the center have been changed to " + c.xc + " and "+ c.yc);
		System.out.println();
		
		
		c.rotate(3.14159); // calling function to rotate the shape by specified angle in radians
		System.out.println("Rotate method called");
		System.out.println("New value of theta is " + c.theta);
		System.out.println();
		
		
		int[] x = new int[c.xLocal.length];
		x = c.getX(); // calling function to get x coordinates of surrounding square in screen coordinate system
		System.out.println("The returned x coordinates of vertices in screen coordinate system are:");
		for (int i=0; i<x.length; i++)
		{
			System.out.println(x[i] + " ");
		}
		System.out.println();
		System.out.println();
		
	
		int y[] = c.getY(); // calling function to get y coordinates of surrounding square in screen coordinate system
		System.out.println("The returned y coordinates of vertices in screen coordinate system are:");
		for (int i=0;i<y.length;i++)
		{
			System.out.println(y[i] + " ");
		}
		

	}

}
