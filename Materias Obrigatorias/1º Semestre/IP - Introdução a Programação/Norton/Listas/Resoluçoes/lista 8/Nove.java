import java.util.Scanner;

class Nove {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Numero maximo a ser imaginado?");
		int n = sc.nextInt();
		int [] chances = new int [n+1];
		for (int i =0; i <= n; i++) {
			chances[i] = i;
		}
		int inicio = 0;
		int fim = chances.length-1;
		int meio = (inicio+fim) / 2;
		System.out.println (chances[meio]);
		String resposta = null;
		do {
			System.out.println ("Esse numero é igual, maior ou menor do que voce imaginou?");
			resposta = sc.next();
			if (resposta.equals("menor")) {
				inicio = meio + 1;
				meio = (inicio+fim) / 2;
				System.out.println (chances [meio]);
			}
			if (resposta.equals("maior")) {
				fim = meio - 1;
				meio = (inicio+fim) / 2;
				System.out.println (chances [meio]);
			}
		}
		while (resposta.equals("maior") || resposta.equals("menor"));
	}
}