import java.util.Scanner;
class Tres {
	public static void selecao (int [] n){
		// percorrer o array inteiro
		for (int j = 0; j < n.length; j++) {
			// determina o menor inteiro
			int posMenor = j;
			int menor = n[j];
			for (int i = j; i < n.length; i++) {
				if (menor > n[i]) {
					posMenor = i;
					menor = n[i];
				}
			}
			// troca o menor inteiro com a posicao j
			int aux = 0;
			aux = n[j];
			n[j] = menor;
			n[posMenor] = aux;
		}
		System.out.println ("Array ordenado:");
		for (int c : n) System.out.println (c);
	}
	public static void insercao (int [] n) {
		// percorre o array inteiro
		for (int i = 1; i < n.length; i++) {
			int aux = n[i]; // guarda o número da posição a ser inserida
			int j = i; // variavel para percorrer o array para tras da posicao inicial a ser guardada
			// desloca o array para abrir espaco
			while ((aux < n[j-1]) && (j>0)) {
				n[j] = n[j-1];
				j--;
			}
			n[j] = aux; // guarda no espaco aberto o numero inicial
		}
		System.out.println ("Array ordenado:");
		for (int c : n) System.out.println (c);
	}
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite quantos inteiros quer ordenar");
		int num = sc.nextInt();
		int [] array = new int [num];
		for (int i =0; i<num; i++) {
			System.out.println ("Digite o inteiro");
			array [i] = sc.nextInt();
		}
		selecao(array);
		insercao (array); 
	}
}