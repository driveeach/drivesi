import java.util.Scanner;
class VinteUm {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		int qntd = 0;
		double preco = 0;
		int tamanho = 0;
		System.out.println ("Quantos tipos de produtos serao inicializados?");
		tamanho = sc.nextInt();
		double [][] nota = new double [tamanho+1][3];
		for (int i = 0; i<tamanho; i++) {
			System.out.println ("Digite a quantidade e o valor unitário");
			qntd = sc.nextInt();
			preco = sc.nextDouble();
			nota [i][0] = qntd;
			nota [i][1] = preco;
			nota [i][2] = preco * qntd;
			nota [nota.length-1][nota[2].length-1] += nota[i][2];
		}
		for (double [] c: nota) {
			for (double a : c) {
				if (a!=0) System.out.print (a+ "\t");
				else System.out.print("\t");
				// System.out.println ("\t");
			}
			System.out.println ();
		}
	}
}