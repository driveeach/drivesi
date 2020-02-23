import java.util.Scanner;
class Treze {
	public static void main (String [] args) {
		Scanner s = new Scanner (System.in);
		System.out.println ("Digite quantos números terão no arranjo:");
		int tamanho = s.nextInt();
		int [] numero = new int [tamanho];
		for (int i = 0; i<tamanho; i++) {
			Scanner sc = new Scanner (System.in);
			System.out.println ("Digite qual é o número:");
			numero [i] = sc.nextInt();
		}
		
		for (int ult = numero.length - 1; ult > 0; ult--){
			for (int i = 0; i<ult; i++) {
				if (numero[i] > numero [i+1]) {
					int aux = numero[i];
					numero [i] = numero [i+1];
					numero [i+1] = aux;
				}
			}
		}
		
		System.out.println ("O valor máximo é " +numero [numero.length-1]+ " e o valor minimo é " +numero [0]);
	}
}