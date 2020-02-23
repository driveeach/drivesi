import java.util.Scanner;
class Vinte {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite a frase");
		String frase = sc.nextLine();
		char [] a = frase.toCharArray();
		char [] resposta = new char [a.length];
		for (int i = 0; i<a.length; i++) {
			if (a[i] >= '0' && a[i] <= '9') resposta[i] = '^';
		}
		for (char c : a) System.out.print (c);
		System.out.println();
		for (char c : resposta) System.out.print (c);
	}
}