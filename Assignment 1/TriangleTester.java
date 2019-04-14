import java.awt.Color;
/**
 * This class is used as a tester class for the Triangle class. It tests all the instance variables and methods of the Triangle class.
 * 
 * @author Rishabh Jain
 *
 */
public class TriangleTester {

	/**
	 * Main method for testing the object of class Triangle and and its instance variables and methods.
	 * 
	 * @param args not being used in this application.
	 */
	public static void main(String[] args) {
		Triangle t = new Triangle();
		System.out.println("Object is Created");
		System.out.println();
		
		t.color = new Color(0,255,0);
		System.out.println("Color is set");
		System.out.println();
		
		t.filled = true;
		System.out.println("Shape filled (true/false):" + t.filled);
		System.out.println();
		
		t.theta = 3.14159;
		System.out.println("Theta is set as: " + t.theta);
		System.out.println();
		
		t.xc = 1;
		t.yc = 1;
		System.out.println("X and Y in local coordinate system: " + t.xc + " and "+ t.yc);
		System.out.println();
		
		t.xLocal = new double[3];
		t.xLocal[0] = 5;
		t.xLocal[1] = -7;
		t.xLocal[2]= 10;
		t.yLocal = new double[3];
		t.yLocal[0] = 5;
		t.yLocal[1] = -7;
		t.yLocal[2]= 10;
		t.setVertices(7.56);
		System.out.println("Local coordinates have been assigned");
		System.out.println();
		
		System.out.println("X coordinates(local system) of the triangle's vertices:");
		for(int i=0;i<t.xLocal.length;i++)
		{
			System.out.println(t.xLocal[i] + " ");
		}
		System.out.println();
		 
		System.out.println("Y coordinates (local system) of the triangle's vertices are:");
		for(int i=0;i<t.yLocal.length;i++)
		{
			System.out.println(t.yLocal[i] + " ");
		}
		System.out.println();
		
		t.translate(1.36, 2.155);
		System.out.println("Translate method has been used");
		System.out.println("The X and Y coordinates of the center have been changed to " + t.xc + " and "+ t.yc);
		System.out.println();
		
		t.rotate(3.14159);
		System.out.println("Rotate method called");
		System.out.println("New value of theta is " + t.theta);
		System.out.println();
		
		int[] x = new int[t.xLocal.length];
		x = t.getX();
		System.out.println("The returned x coordinates of vertices in screen coordinate system are:");
		for (int i=0; i<x.length; i++)
		{
			System.out.println(x[i] + " ");
		}
		System.out.println();
		System.out.println();
		
	
		int y[] = t.getY();
		System.out.println("The returned y coordinates of vertices in screen coordinate system are:");
		for (int i=0;i<y.length;i++)
		{
			System.out.println(y[i] + " ");
		}
		
		
		
	}

}
