/**
 * This class is used to model Square shapes. It is a sublclass of Shape and it inherits all its variables and methods.
 * It also overrides the setVertices method in the Shape class.
 * 
 * @author Rishabh Jain
 *
 */
public class Triangle extends Shape {
	/**
	 * This constructs the coordinates (local system) of vertices of the Triangle. This method overrides the setVertices method in the Shape superclass.
	 * 
	 * @param d value(double) that specifies the distance of each vertex from the center.
	 */
	public void setVertices(double d){
		double a = Math.PI / 3;
		this.xLocal= new double[3];
		this.yLocal= new double[3];
		this.xLocal[0]=d;
		this.xLocal[1]=(-1*d)*Math.cos(a);
		this.xLocal[2]=(-1*d)*Math.cos(a);
		this.yLocal[0]=0;
		this.yLocal[1]=(-1*d)*Math.sin(a);
		this.yLocal[2]=(d)*Math.sin(a);
	}

}
