import java.awt.Color;
/**
 * This class is used as a tester class for the RegularPolygon class. It tests all the instance variables and methods of the RegularPolygon class.
 * 
 * @author Rishabh Jain
 *
 */
public class RegularPolygonTester {
	/**
	 * Main method for testing the object of class RegularPolygon and and its instance variables and methods.
	 * 
	 * @param args not being used in this application.
	 */
	public static void main(String[] args) {
		RegularPolygon s = new RegularPolygon();
		System.out.println("1st Object is created with " + s.getNumOfSides() + " sides and "+ s.getRadius() + "radius");
		System.out.println();
		
		RegularPolygon b = new RegularPolygon(10);
		System.out.println("2nd Object is created with " + b.getNumOfSides() + " sides and "+ b.getRadius() + "radius");
		System.out.println();
		
		RegularPolygon c = new RegularPolygon(6, 3.945684);
		System.out.println("3rd Object is created with " + c.getNumOfSides() + " sides and "+ c.getRadius() + "radius");
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
	
		int y[] = new int[s.getYLocal().length];
		y=s.getY();
		System.out.println("The returned y coordinates of vertices in screen coordinate system are:");
		for (int i=0;i<y.length;i++)
		{
			System.out.println(y[i] + " ");
		}
		
		s.setNumOfSides(10);
		System.out.println("The number of sides has been set to " + s.getNumOfSides());
		System.out.println();
		
		s.setRadius(5.68992);
		System.out.println("The radius has been set to " + s.getRadius());
		System.out.println();
		
		s.setVertices();
		System.out.println("The local coordinates of the vertices have been assigned");
		System.out.println();
		
		boolean k = s.contains(7.36, 5.98);
		System.out.println("Checking whether the point (7.36,5.98) is contained by the polygon (True/False): "+ k);
		
	}

}
