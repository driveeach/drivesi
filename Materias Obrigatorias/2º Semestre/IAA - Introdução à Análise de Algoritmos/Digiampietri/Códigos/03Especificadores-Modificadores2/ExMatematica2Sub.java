package especificadores3;

public class ExMatematica2Sub extends ExMatematica2{
	public final static double pi = 3.1415926;
	
	/* O metodo abaixo nao pode ser redefinido pois na classe original 
	 * (ExMatematica2) ele era final. */
	/*
	public final static double quadrado(double x){
		return x*x;
	}
	*/
	
	/* O metodo cubo nao era final na super-classe (ExMatematica2) e, por isso,
	 * pode ser redefinida.
	 */
	public static double cubo(double x){
		double temp = x*x;
		return temp*x;
	}
}
