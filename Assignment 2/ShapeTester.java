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
		Shape s = new Shape(); 
		System.out.println("Object is Created");
		System.out.println();
		
		s.setColor(new Color(0,255,0)); 
		System.out.println("Color is set");
		System.out.println();
		
		s.setFilled(true);
		System.out.println("Shape filled (true/false): " + s.getFilled());
		System.out.println();
		
		s.setTheta(3.14159);
		System.out.println("Theta is set as: " + s.getTheta());
		System.out.println();
		
		s.setXc(1);
		s.setYc(1); 
		System.out.println("X and Y in local coordinate system: " + s.getXc() + " and "+ s.getXc());
		System.out.println();
		
		double[] p = new double[3];
		p[0] = 5;
		p[1] = -7;
		p[2]= 10;
		
		double[] q = new double[3];
		q[0] = 5;
		q[1] = -7;
		q[2]= 10;
		s.setXLocal(p);
		s.setYLocal(q);
		System.out.println("Local coordinates have been assigned");
		System.out.println();
		
		System.out.println("X coordinates (local system) of the shape's vertices:");
		double[] xn = s.getXLocal();
		for(int i=0;i<s.getXLocal().length;i++)
		{
			System.out.println(xn[i] + " ");
		}
		System.out.println();
		 
		System.out.println("Y coordinates (local system) of the shape's vertices are:");
		double[] yn = s.getYLocal();
		for(int i=0;i<s.getYLocal().length;i++)
		{
			System.out.println(yn[i] + " ");
		}
		System.out.println();
		
		s.translate(1.36, 2.155);
		System.out.println("Translate method has been used");
		System.out.println("The X and Y coordinates of the center have been changed to " + s.getXc() + " and "+ s.getYc());
		System.out.println();
		
		s.rotate(3.14159);
		System.out.println("Rotate method called");
		System.out.println("New value of theta is " + s.getTheta());
		System.out.println();
		
		int[] x = new int[s.getXLocal().length];
		x = s.getX();
		System.out.println("The returned x coordinates of vertices in screen coordinate system are:");
		for (int i=0; i<x.length; i++)
		{
			System.out.println(x[i] + " ");
		}
		System.out.println();
		System.out.println();
		
		int[] y = new int[s.getYLocal().length];
		y = s.getY();
		System.out.println("The returned y coordinates of vertices in screen coordinate system are:");
		for (int i=0;i<y.length;i++)
		{
			System.out.println(y[i] + " ");
		}
	}
}
