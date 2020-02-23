import java.util.Scanner;
class Dezenove {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		String frase = null;
		System.out.println ("Digite a frase");
		frase = sc.nextLine();
		char [] letras = frase.toCharArray();
		letras[0] = Character.toUpperCase(letras[0]);
		for (int i = 1; i<letras.length-1; i++) {
		if (letras[i] == ' ') letras[i+1] = Character.toUpperCase(letras[i+1]);
	}
	for (char c : letras) System.out.print (c);
	} 
}