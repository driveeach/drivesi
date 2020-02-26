package javadoc;

/**
 * Esta classe prove algumas operacoes matematicas alem de conter uma
 * constante com o valor aproximado de <i>pi</i>.
 * 
 * @author Luciano Antonio Digiampietri
 * @version 1.0
 * @see especificadores3.ExMatematica2
 */

public class MatematicaJavadoc {

	public final static double pi = 3.141592;
	
	/** Metodo estatico quadrado recebe um numero decimal como entrada e 
	 * retorna o quadrado deste numero.
	 * @param x : variavel do tipo <i>double</i> que tera seu valor elevado
	 * ao quadrado.
	 * @return valor do tipo <i>double</i> contendo o quadrado do valor de entrada
	 */
	public final static double quadrado(double x){
		return x*x;
	}

	/** Metodo estatico cubo: recebe um numero decimal como entrada e 
	 * retorna o cubo deste numero.
	 * @param x : variavel do tipo <i>double</i> que tera seu valor elevado
	 * ao cubo.
	 * @return valor do tipo <i>double</i> contendo o cubo do valor de entrada
	 */
	public static double cubo(double x){
		return x*x*x;
	}
	
	/** Metodo estatico perimetro: recebe um numero decimal como entrada (raio) e 
	 * retorna o perimetro de um circulo com esse raio.
	 * @param raio : variavel do tipo <i>double</i> que corresponde ao valor do raio de um circulo.
	 * @return valor do tipo <i>double</i> contendo o perimetro de um circulo cujo raio seja dado 
	 * pela variavel raio.
	 */
	public final static double perimetro(double raio){
		return 2*pi*raio;
	}

}
