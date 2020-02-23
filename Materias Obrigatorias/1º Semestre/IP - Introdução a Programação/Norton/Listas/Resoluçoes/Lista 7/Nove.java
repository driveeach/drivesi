import java.util.Scanner;

class Nove {
	static float conversao (String numero) {
		float novo = Float.parseFloat (numero);
		return novo;
	}
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite o numero:");
		String numero = sc.nextLine();
		System.out.println (conversao (numero));
	}
}