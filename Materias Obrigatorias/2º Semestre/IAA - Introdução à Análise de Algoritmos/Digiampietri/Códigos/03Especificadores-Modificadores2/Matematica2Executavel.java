package especificadores3;

public class Matematica2Executavel {
	public static void main(String[]args) {
		double a = 1.6;
		/* Jah que os metodos da classe ExMatematica2 sao estaticos
		 * NAO eh necessario instanciar a classe para usar seus metodos.   */
		System.out.println("Quadrado: " + ExMatematica2.quadrado(a));
		System.out.println("Cubo: " + ExMatematica2.cubo(a));
		System.out.println("Perimetro: " + ExMatematica2.perimetro(a));
		System.out.println();
		/* O atributo FINAL pi nao pode ser modificado. */
		// ExMatematica2.pi = 4; // ESTA LINHA NAO FUNCIONA
		System.out.println("Quadrado: " + ExMatematica2.quadrado(a));
		System.out.println("Cubo: " + ExMatematica2.cubo(a));
		System.out.println("Perimetro: " + ExMatematica2.perimetro(a));		
	}
}

/* RESULTADO DA EXECUCAO DO PROGRAMA:

Quadrado: 2.5600000000000005
Cubo: 4.096000000000001
Perimetro: 10.0530944

Quadrado: 2.5600000000000005
Cubo: 4.096000000000001
Perimetro: 10.0530944

*/ 