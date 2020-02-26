package questao2;

public abstract class Figura {

	protected double area; 
	
	public abstract double area (double a, double b);
	
	@Override
	public String toString() {
		return ("Area : "+area);
	}
}
