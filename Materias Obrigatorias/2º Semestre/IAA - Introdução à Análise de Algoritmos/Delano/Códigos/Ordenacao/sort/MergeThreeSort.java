package sort;

public class MergeThreeSort implements Ordenador {

	public void ordena(int[] A) {
		mergeThreeSort(A, 0, A.length - 1);
	}

	private void mergeThreeSort(int[] A, int ini, int fim) {

		if (ini >= fim) {
			return;
		} else {
			int terco1 = ini + (fim - ini) / 3;
			int terco2 = ini + (2 * (fim - ini) / 3);

			mergeThreeSort(A, ini, terco1);
			mergeThreeSort(A, terco1 + 1, terco2);
			mergeThreeSort(A, terco2 + 1, fim);
			merge(A, ini, terco1, terco2);
			merge(A, ini, terco2, fim);
		}
	}

	private void merge(int[] A, int p, int q, int r) {
		// A subsequencia A[p...q] esta ordenada
		// A subsequencia A[q+1...r] esta ordenada

		int i, j, k;

		// Faz copias das subsequencia seq1 = A[p...q] e seq2 = A[q+1...r]

		int tamseq1 = q - p + 1; // tamanho da subsequencia 1
		int tamseq2 = r - q; // tamanho da subsequencia 2

		int[] seq1 = new int[tamseq1];

		for (i = 0; i < seq1.length; ++i) {
			seq1[i] = A[p + i];
		}

		int[] seq2 = new int[tamseq2];

		for (j = 0; j < seq2.length; ++j) {
			seq2[j] = A[q + j + 1];
		}

		// Faz a juncao das duas subsequencias

		k = p;
		i = 0;
		j = 0;

		while (i < seq1.length && j < seq2.length) {
			// Pega o menor elemento das duas sequencias

			if (seq2[j] <= seq1[i]) {
				A[k] = seq2[j];
				j++;
			} else {
				A[k] = seq1[i];
				i++;
			}
			k++;
		}

		// Completa com a sequencia que ainda nao acabou

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

		// A subsequencia A[p...r] esta ordenada
	}
}