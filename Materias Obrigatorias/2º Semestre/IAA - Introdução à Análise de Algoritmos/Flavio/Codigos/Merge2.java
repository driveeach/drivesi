class Merge2{
	public static void merge(int [] A, int p, int q, int r) {
		// A subseqüência A[p...q] está ordenada
		// A subseqüência A[q+1...r] está ordenada
		 int i, j, k;
		// Faz cópias - seq1 = A[p...q] e seq2 = A[q+1...r]
		 int tamseq1 = q - p + 1; // tamanho da subseqüência 1
		 int tamseq2 = r - q; // tamanho da subseqüência 2
		 int [] seq1 = new int [tamseq1];
		 for(i=0; i < seq1.length; i++)
			seq1[i] = A[p+i];
		
		 int [] seq2 = new int [tamseq2];
		 for(j=0; j < seq2.length; j++) 
			seq2[j] = A[q+j+1];
		// Faz a junção das duas subseqüências
		 k = p; i = 0; j = 0;
		 while (i < seq1.length && j < seq2.length) {
		// Pega o menor elemento das duas seqüências
			if(seq2[j] <= seq1[i]) {
				 A[k] = seq2[j];
				 j++;
			}
			else {
				 A[k] = seq1[i];
				 i++;
			}
			 k++;
		}
		// Completa com a seqüência que ainda não acabou
		 while (i < seq1.length) {
			 A[k] = seq1[i];
			 k++;
			 i++;
		}
		 while (j < seq2.length) {
			A[k] = seq2[j];
			 k++;
			j++;
		}
		// A subseqüência A[p...r] está ordenada
	}
	
	public static void mergeSort(int [] numeros, int ini, int fim) {
		if(ini < fim) {
		//Divisao 
		int meio = (ini + fim)/2;
		// Conquista
		 mergeSort(numeros, ini, meio);
		 mergeSort(numeros, meio+1, fim);
		// Combinação
		 merge(numeros, ini, meio, fim);
		}
		// Solução trivial: ordenacao de um único número.
	}
	
	public static void main(String[] args) {
		int[] conjunto = { 4, 1, 3, 2, 8, 9, 7, 5, 6, 12, 14, 11, 15, 13, 10 };
		for (int i = 0; i < conjunto.length; i++) {
			System.out.print(conjunto[i] + " ");
		}
		System.out.println("\n");
		mergeSort(conjunto, 0, conjunto.length -1);
		for (int i = 0; i < conjunto.length; i++) {
			System.out.print(conjunto[i] + " ");
		}
		System.out.println();
	}
}