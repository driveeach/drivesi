import java.util.Scanner;
class Doze {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite uma frase de 20 caracteres no maximo:");
		String frase = sc.nextLine();
		char [] f = frase.toCharArray ();
		int espaco = 0;
		int a = 0;
		for (int i = 0; i<f.length; i++) {
			if (f[i] == ' ') espaco++;
			if (f[i] == 'a') a++;
		}
		System.out.println ("A frase tem " +espaco+ "espacos");
		System.out.println ("A frase tem " +a+ "a");
	}
}