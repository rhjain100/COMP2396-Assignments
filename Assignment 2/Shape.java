import java.awt.Color;
/**
 * This class can be implemented to model general shapes.
 * 
 * @author Rishabh Jain
 *
 */
public class Shape {
	/**
	 * Color of the shape
	 */
	private Color color;
	/**
	 * Whether the shape is filled or not filled 
	 */
	private boolean filled;
	/**
	 * Orientation of shape (in radians in Screen Coordinate System)
	 */
	private	double theta;
	/**
	 * x coordinate of center (Screen coordinate system)
	 */
	private double xc;
	/**
	 * y coordinate of center (Screen coordinate system)
	 */
	private double yc;
	/**
	 * Array of x coordinates (Local coordinate system) of the shape's vertices in counter clockwise order.
	 */
	private double[] xLocal;
	/**
	 *  Array of y coordinates (Local coordinate system) of the shape's vertices in counter clockwise order
	 */
	private double[] yLocal;
	
	/**
	 * Method for retrieving the color of the shape.
	 * 
	 * @return color of shape
	 */
	public Color getColor() {
		return this.color;
	}
	/**
	 * Method for retrieving the fill-type of the shape.
	 * 
	 * @return boolean specifying whether the shape is filled or not
	 */
	public boolean getFilled() {
		return this.filled;
	}
	/**
	 * Method for retrieving the orientation (in radians) of the shape in screen coordinate system.
	 * 
	 * @return value in radians (double) showing orientation in screen coordinate system
	 */
	public double getTheta() {
		return this.theta;
	}
	/**
	 * Method for retrieving the x coordinate of center of the shape (in screen coordinate system).
	 * 
	 * @return x coordinate (double) of center in screen coordinate system
	 */
	public double getXc() {
		return this.xc;
	}
	
	/**
	 * Method for retrieving the y coordinate of center of the shape (in screen coordinate system).
	 * 
	 * @return y coordinate (double) of center in screen coordinate system
	 */
	public double getYc() {
		return this.yc;
	}
	/**
	 * Method for retrieving the x-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 * 
	 * @return array of x-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 */
	public double[] getXLocal() {
		return this.xLocal;
	}
	/**
	 *  Method for retrieving the y-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 * 
	 * @return array of y-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 */
	public double[] getYLocal() {
		return this.yLocal;
	}
	/**
	 * Method for setting the color of the shape
	 * 
	 * @param color color to be set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Method for setting the fill-type of the shape. 
	 * 
	 * @param filled boolean value for whether the shape is to be filled or not
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	/**
	 * Method for setting the orientation of the shape. 
	 * 
	 * @param theta double type value in radians to be set as the orientation
	 */
	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	/**
	 * Method for setting the x-coordinate of the center of the shape in the screen coordinate system.
	 * 
	 * @param xc double value to be set as x-coordinate of the center of the shape in screen coordinate system
	 */
	public void setXc(double xc) {
		this.xc = xc;
	}
	/**
	 * Method for setting the y-coordinate of the center of the shape in the screen coordinate system.
	 * 
	 * @param yc double value to be set as y-coordinate of the center of the shape in screen coordinate system
	 */
	
	public void setYc(double yc) {
		this.yc = yc;
	}
	/**
	 * Method for setting the x-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 * 
	 * @param xLocal array of x-coordinates to be set for the vertices (in counter clock-wise order) of the shape in its local coordinate system
	 */
	
	public void setXLocal(double[] xLocal) {
		this.xLocal = xLocal;
	}
	/**
	 * Method for setting the y-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 * 
	 * @param yLocal array of y-coordinate to be set for the vertices (in counter clock-wise order) of the shape in its local coordinate system
	 */
	public void setYLocal(double[] yLocal) {
		this.yLocal = yLocal;
	}
	/**
	 * This translates the center of the shape by dx and dy along x and y axes.
	 * 
	 * @param dx value (double) by which the x coordinate of the center changes.
	 * @param dy value (double) by which the y coordinate of the center changes.
	 */
	public void translate(double dx, double dy) {
		this.xc+=dx;
		this.yc+=dy;
	}
	/**
	 * This rotates the shape about its center by angle dt.
	 * 
	 * @param dt angle (in radians) by which the shape rotates.
	 */
	public void rotate(double dt) {
		this.theta+=dt;
	}
	/**
	 * This retrieves the x coordinates of vertices (anti clockwise) in accordance to the screen coordinate system.
	 * 
	 * @return array of x coordinates (rounded to nearest integers) of vertices in counter clockwise order.
	 */
	
	public int[] getX() {
		int[] xs = new int[this.getXLocal().length];
		for ( int i=0; i<xs.length; i++) {
			double a = this.xLocal[i] * Math.cos(this.theta);
			double b = this.yLocal[i] * Math.sin(this.theta);
			xs[i] = (int)(Math.round(a-b+this.xc));
		}
		return xs;
	}
	
	/**
	 * This retrieves the y coordinates of vertices (anti clockwise) of the shape in accordance to the screen coordinate system.
	 * 
	 * @return array of y coordinates (rounded to nearest integers) of vertices (in counter clockwise order) in screen coordinate system.
	 */
	
	public int[] getY() {
		int len = this.yLocal.length;
		int[] ys = new int[len];
		for ( int i=0; i<len; i++) {
			double a = this.xLocal[i] * Math.sin(this.theta);
			double b = this.yLocal[i] * Math.cos(this.theta);
			ys[i] = (int)(Math.round(a+b+this.yc));
		}
		return ys;
	}
	
}
