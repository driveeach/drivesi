package especificadores3;

public class Matematica1Executavel {
	public static void main(String[]args) {
		double a = 1.6;
		/* Jah que os metodos da classe ExMatematica1 nao sao estaticos
		 * eh necessario instanciar a classe para usar seus metodos.   */
		ExMatematica1 mat = new ExMatematica1();
		System.out.println("Quadrado: " + mat.quadrado(a));
		System.out.println("Cubo: " + mat.cubo(a));
		System.out.println("Perimetro: " + mat.perimetro(a));
		System.out.println();
		/* Jah que o atributo pi nao eh final, pode ser modificado */
		mat.pi = 4;
		System.out.println("Quadrado: " + mat.quadrado(a));
		System.out.println("Cubo: " + mat.cubo(a));
		System.out.println("Perimetro: " + mat.perimetro(a));		
	}
}

/* RESULTADO DA EXECUCAO DO PROGRAMA:

Quadrado: 2.5600000000000005
Cubo: 4.096000000000001
Perimetro: 10.0530944

Quadrado: 2.5600000000000005
Cubo: 4.096000000000001
Perimetro: 12.8

*/ 