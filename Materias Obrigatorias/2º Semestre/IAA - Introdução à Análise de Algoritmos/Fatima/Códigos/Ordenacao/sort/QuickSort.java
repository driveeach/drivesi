package sort;

public class QuickSort implements Ordenador {

	public void ordena(int[] vetor) {
		quickSort(vetor, 0, vetor.length - 1);
	}

	private void quickSort(int[] vetor, int ini, int fim) {
		if (ini < fim) {
			int q = particao(vetor, ini, fim);
			quickSort(vetor, ini, q - 1);
			quickSort(vetor, q + 1, fim);
		}
	}

	protected int particao(int[] vetor, int ini, int fim) {

		int pivo, i, j, temp;

		pivo = vetor[fim];
		i = ini;
		j = fim - 1;

		while (i <= j) {
			if (vetor[i] <= pivo) {
				i++;
			} else if (vetor[j] > pivo) {
				j--;
			} else {
				temp = vetor[i];
				vetor[i] = vetor[j];
				vetor[j] = temp;
				i++;
				j--;
			}
		}
		vetor[fim] = vetor[i];
		vetor[i] = pivo;
		return i;
	}

	public static void main(String[] args) {
		Ordenador sort = new QuickSort();
		int[] vetor;
		vetor = new int[] { 8, 7, 6, 5, 4, 3, 2, 1 };
		sort.ordena(vetor);
		Main.imprimeArranjo(vetor);
		vetor = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		sort.ordena(vetor);
		Main.imprimeArranjo(vetor);
		vetor = new int[] { 1, 8, 3, 6, 5, 4, 7, 2 };
		sort.ordena(vetor);
		Main.imprimeArranjo(vetor);
		vetor = new int[] { 7, 2, 5, 4, 3, 6, 1, 8 };
		sort.ordena(vetor);
		Main.imprimeArranjo(vetor);
	}
}
