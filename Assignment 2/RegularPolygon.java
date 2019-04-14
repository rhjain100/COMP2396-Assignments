import java.awt.Color;
/**
 * This class is a subclass of the Shape class, and is used to model regular n sided polygons. 
 * 
 * @author Rishabh Jain
 *
 */
public class RegularPolygon extends Shape {
	/**
	 * An integer value specifying the number of sides of the regular n-sided polygon.
	 */
	private int numOfSides;
	/**
	 * A double value specifying the radius of the regular n-sided polygon
	 */
	private double radius;
	
	/**
	 * A constructor for building a regular n-sided polygon with a radius of r
	 * 
	 * @param n number of sides
	 * @param r radius of the shape
	 */
	public RegularPolygon(int n, double r) {
		if (n<3) {
			this.numOfSides=3;
		} else {
			this.numOfSides=n;
		}
		if (r<0){
			this.radius=0;
		} else {
		this.radius=r;
		}
		this.setVertices();
	}
	/**
	 * A constructor for building a regular n-sided polygon with a radius of 1.0. 
	 * 
	 * @param n number of sides
	 */
	public RegularPolygon(int n) {
		if (n<3) {
			this.numOfSides=3;
		} else {
			this.numOfSides=n;
		}
		this.radius=1.0;
		this.setVertices();
	}
	/**
	 * A constructor for building a regular 3-sided polygon with a radius of 1.0
	 */
	public RegularPolygon() {
		this.numOfSides=3;
		this.radius=1.0;
		this.setVertices();
		
	}
	/**
	 * Method for retrieving the number of sides of the regular polygon.
	 * 
	 * @return number of sides of the polygon
	 */
	public int getNumOfSides() {
		return this.numOfSides;
	}
	/**
	 * Method for retrieving the radius of the regular polygon 
	 * 
	 * @return radius of the polygon
	 */
	public double getRadius() {
		return this.radius;
	}
	/**
	 * Method for setting the number of sides of the regular polygon
	 * 
	 * @param n value to be set as the number of sides
	 */
	public void setNumOfSides(int n) {
		if (n<3) {
			this.numOfSides=3;
		} else {
			this.numOfSides = n;
		}
		this.setVertices();
	}
	/**
	 * Method for setting the radius of the regular polygon
	 * 
	 * @param r
	 */
	public void setRadius(double r) {
		if (r<0){
			this.radius=0;
		} else {
			this.radius=r;
		}
		this.setVertices();
	}
	/**
	 * Method for setting the local coordinates of the vertices of the regular n-sided polygon based on its number of sides and radius.
	 */
	public void setVertices() {
		double[] xL= new double[this.numOfSides];
		double[] yL= new double[this.numOfSides];
		double al;
		
		if(this.numOfSides%2 == 0)
		{
			al = Math.PI/this.numOfSides;
		}
		
		else
		{
			al = 0;
		}
		for(int i = 0; i < this.getNumOfSides(); i++)
		{
			xL[i] = (this.getRadius())*(Math.cos(al - (i)*(2*Math.PI/this.getNumOfSides())));
			yL[i] = (this.getRadius())*(Math.sin(al - (i)*(2*Math.PI/this.getNumOfSides())));
		}
		this.setXLocal(xL);
		this.setYLocal(yL);
	}
	/**
	 * Method for determining if a point (x, y) in the screen coordinate system is contained by the regular n-sided polygon.
	 * 
	 * @param x x-coordinate of point to be checked if contained in screen coordinate system
	 * @param y y-coordinate of point to be checked if contained in screen coordinate system
	 * @return boolean specifying whether the point is contained or not
	 */
	public boolean contains(double x, double y) {
		double xl = ((x-this.getXc())*Math.cos(-this.getTheta()))-((y-this.getYc())*Math.sin(-this.getTheta()));
		double yl = ((x-this.getXc())*Math.sin(-this.getTheta()))+((y-this.getYc())*Math.cos(-this.getTheta()));
		double[] xs=this.getXLocal();
		double minx = xs[0];
		for(int i=1; i<xs.length; i++) {
			if(minx>xs[i]) {
				minx=xs[i];
			}
		}
		double nx=xl;
		double ny=yl;
		for(int i=0; i<numOfSides; i++) {
			if(i!=0) {
				double a = nx;
				nx = (nx)*Math.cos(2*Math.PI/numOfSides)-(ny)*Math.sin(2*Math.PI/numOfSides);
				ny = (a)*Math.sin(2*Math.PI/numOfSides)+(ny)*Math.cos(2*Math.PI/numOfSides);
			}
			if(nx<minx) {
				return false;
			}
		}
		return true;
		
	}
	
	
}
