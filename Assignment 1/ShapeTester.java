import java.awt.Color;
/**
 * This class is used as a tester class for the Shape class. It tests all the instance variables and methods of the Shape class.
 * 
 * @author Rishabh Jain
 *
 */
public class ShapeTester {

	/**
	 * Main method for testing the object of class Shape and and its instance variables and methods.
	 * 
	 * @param args not being used in this application.
	 */
	public static void main(String[] args) {
		Shape s = new Shape(); // object created
		System.out.println("Object is Created");
		System.out.println();
		
		s.color = new Color(0,255,0); // color assigned
		System.out.println("Color is set");
		System.out.println();
		
		s.filled = true; //color filled
		System.out.println("Shape filled (true/false):" + s.filled);
		System.out.println();
		
		s.theta = 3.14159; //theta assigned
		System.out.println("Theta is set as: " + s.theta);
		System.out.println();
		
		s.xc = 1; //center's x coordinate given
		s.yc = 1; //center's y coordinate given
		System.out.println("X and Y in local coordinate system: " + s.xc + " and "+ s.yc);
		System.out.println();
		
		// x coordinates (local system) of shape vertices assigned
		s.xLocal = new double[3];
		s.xLocal[0] = 5;
		s.xLocal[1] = -7;
		s.xLocal[2]= 10;
		// y coordinates (local system) of surrounding shape's vertices assigned
		s.yLocal = new double[3];
		s.yLocal[0] = 5;
		s.yLocal[1] = -7;
		s.yLocal[2]= 10;
		s.setVertices(7.56); //calling function to assign x and y local coordinates of the shape.
		System.out.println("Local coordinates have been assigned");
		System.out.println();
		
		System.out.println("X coordinates (local system) of the shape's vertices:");
		for(int i=0;i<s.xLocal.length;i++)
		{
			System.out.println(s.xLocal[i] + " ");
		}
		System.out.println();
		 
		System.out.println("Y coordinates (local system) of the shape's vertices are:");
		for(int i=0;i<s.yLocal.length;i++)
		{
			System.out.println(s.yLocal[i] + " ");
		}
		System.out.println();
		
		s.translate(1.36, 2.155);
		System.out.println("Translate method has been used");
		System.out.println("The X and Y coordinates of the center have been changed to " + s.xc + " and "+ s.yc);
		System.out.println();
		
		s.rotate(3.14159);
		System.out.println("Rotate method called");
		System.out.println("New value of theta is " + s.theta);
		System.out.println();
		
		int[] x = new int[s.xLocal.length];
		x = s.getX();
		System.out.println("The returned x coordinates of vertices in screen coordinate system are:");
		for (int i=0; i<x.length; i++)
		{
			System.out.println(x[i] + " ");
		}
		System.out.println();
		System.out.println();
		
	
		int y[] = s.getY();
		System.out.println("The returned y coordinates of vertices in screen coordinate system are:");
		for (int i=0;i<y.length;i++)
		{
			System.out.println(y[i] + " ");
		}
		
		
		
	}

}
