/**
 * This class is used to model Square shapes. It is a sublclass of Shape and it inherits all its variables and methods.
 * It also overrides the setVertices method in the Shape class.
 * 
 * @author Rishabh Jain
 *
 */
public class Square extends Shape {
	/**
	 * This constructs the coordinates (local system) of vertices of the square. This overrides the setVertices method in the Shape superclass.
	 * 
	 * @param d value(double) that specifies half length of the square's side.
	 */
	public void setVertices(double d){
		this.xLocal= new double[4];
		this.yLocal= new double[4];
		this.xLocal[0]=d;
		this.xLocal[1]=d;
		this.xLocal[2]=(-1*d);
		this.xLocal[3]=(-1*d);
		this.yLocal[0]=d;
		this.yLocal[1]=(-1*d);
		this.yLocal[2]=(-1*d);
		this.yLocal[3]=d;
	}
}
