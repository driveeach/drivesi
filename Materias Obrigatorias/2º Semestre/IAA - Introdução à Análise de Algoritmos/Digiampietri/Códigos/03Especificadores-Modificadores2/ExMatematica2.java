package especificadores3;

public class ExMatematica2 {
	/* Atributos FINAL sao constantes (nao podem ser modificados). 
	 * Atributos STATIC pertence a classe nao a um objeto (instancia).
	 */
	public final static double pi = 3.141592;
	
	/* Metodos FINAL nao podem ser redefinidos nas sub-classes.
	 * Metodos STATIC pertencem a classe e nao a um objeto (instancia).
	 */
	public final static double quadrado(double x){
		return x*x;
	}
	
	public static double cubo(double x){
		return x*x*x;
	}
	
	public final static double perimetro(double raio){
		return 2*pi*raio;
	}

}
