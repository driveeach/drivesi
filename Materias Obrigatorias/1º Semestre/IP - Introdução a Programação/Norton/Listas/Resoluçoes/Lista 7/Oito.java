import java.util.Scanner;

class Oito {
	static void arranjo (String palavra) {
		int aux = 1;
		char [] teste = palavra.toCharArray ();
		for (int i = 0; i<teste.length; i++) {
			if (teste[i] == ' ') aux++;
		}
		Scanner sc = new Scanner (palavra);
		String [] array = new String[aux];
		for (int i = 0; i<array.length; i++) {
			array [i] = sc.next();
		}
		for (String c : array) System.out.println (c);
	}
	public static void main (String [] args) {
		arranjo ("as12; bbcdd --- 987 .:.:. @Alf@#");
	}
}