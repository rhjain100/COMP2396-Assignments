import java.awt.Color;
import java.lang.Math;
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
	public Color color;
	/**
	 * Whether the shape is filled or not filled 
	 */
	public boolean filled;
	/**
	 * Orientation of shape (in radians in Screen Coordinate System)
	 */
	public	double theta;
	/**
	 * x coordinate of center (Screen coordinate system)
	 */
	public	double xc;
	/**
	 * y coordinate of center (Screen coordinate system)
	 */
	public double yc;
	/**
	 * Array of x coordinates (Local coordinate system) of the shape's vertices in counter clockwise order.
	 */
	public double[] xLocal;
	/**
	 *  Array of y coordinates (Local coordinate system) of the shape's vertices in counter clockwise order
	 */
	public double[] yLocal;
	
	/**
	 * This constructs the vertices of the shape. A Dummy method that is overridden by setVertices mathod in Subclasses.
	 * 
	 * @param d value(double) that is used to determine the vertices.
	 */
	public void setVertices(double d) {	
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
		int len = this.xLocal.length;
		int xs[] = new int[len];
		for ( int i=0; i<len; i++) {
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
		int ys[] = new int[len];
		for ( int i=0; i<len; i++) {
			double a = this.xLocal[i] * Math.sin(this.theta);
			double b = this.yLocal[i] * Math.cos(this.theta);
			ys[i] = (int)(Math.round(a+b+this.yc));
		}
		return ys;
	}
	
}
