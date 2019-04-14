/**
 * This class is used to model Circle shapes. It is a sublclass of Shape and it inherits all its variables and methods.
 * It also overrides the setVertices, getX and getY methods in the Shape class.
 * 
 * @author Rishabh Jain
 *
 */
public class Circle extends Shape {
	
	/**
	 * This constructs the upper left and lower right vertices of the square enclosing Circle (overrides the setVertices in the Shape superclass).
	 * 
	 * @param d value(double) that specifies the radius of the Circle.
	 */
	public void setVertices(double d) {
		this.xLocal = new double[2];
		this.yLocal = new double[2];
		
		this.xLocal[0]=(-1*d);
		this.xLocal[1]=d;
		
		this.yLocal[0]=(-1*d);
		this.yLocal[1]=d;
	}
	
	/**
	 * This retrieves the x coordinates of left and lower right vertices of Square enclosing the Circle in accordance to the screen coordinate system.
	 * 
	 * @return array of x coordinates (integers) of left and lower right vertices of the enclosing square in screen coordinate system.
	 */
	public int[] getX() {
		int[] xs = new int[2];
		
		xs[0] = (int)(Math.round(this.xLocal[0]+this.xc));
		xs[1] = (int)(Math.round((this.xLocal[1])+this.xc));
		return xs;
	}
	/**
	 * This retrieves the y coordinates of left and lower right vertices of Square enclosing the Circle in accordance to the screen coordinate system.
	 * 
	 * @return array of y coordinates (integers) of left and lower right vertices of the enclosing square in screen coordinate system.
	 */
	public int[] getY() {
		int[] ys = new int[2];
		ys[0] = (int)(Math.round(this.yLocal[0]+this.yc));
		ys[1] = (int)(Math.round((this.yLocal[1])+this.yc));
		return ys;
	}
}
