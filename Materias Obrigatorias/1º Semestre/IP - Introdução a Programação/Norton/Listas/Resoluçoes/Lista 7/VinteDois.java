import java.util.Scanner;
class VinteDois {
	public static double frequenciaRelativa (String a) {
		char [] c = a.toCharArray();
		int vogal = 0;
		double frequencia = 0;
		int espaco = 0;
		for (int i = 0; i<c.length; i++) {
			if (c[i] == 'a' || c[i] == 'e' || c[i] == 'i' || c[i] == 'o' || c[i] == 'u') vogal++;
			if (c[i] == ' ') espaco++;
		}
		frequencia = ((double)vogal / (c.length - espaco)) * 100;
		return frequencia;
	}
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite a frase");
		String a = sc.nextLine();
		System.out.println("Frequencia relativa de vogais: "+frequenciaRelativa (a)+ "%");
	}
}