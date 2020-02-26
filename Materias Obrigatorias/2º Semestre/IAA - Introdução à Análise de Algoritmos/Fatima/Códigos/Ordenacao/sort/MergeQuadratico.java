package sort;

public class MergeQuadratico implements Ordenador {

	public void ordena(int[] vetor) {
		mergeSort(vetor, 0, vetor.length - 1);
	}

	private void mergeSort(int[] vetor, int ini, int fim) {

		if (ini != fim) {
			int meio = (ini + fim) / 2;
			mergeSort(vetor, ini, meio);
			mergeSort(vetor, meio + 1, fim);
			merge(vetor, ini, fim);
		}
	}

	private void merge(int[] vetor, int ini, int fim) {

		int posA = ini;
		int posB = (ini + fim) / 2 + 1;

		while (posA != posB && posB <= fim) {

			if (vetor[posA] > vetor[posB]) {
				int tmp = vetor[posB];
				for (int i = posB; i > posA; i--) {
					vetor[i] = vetor[i - 1];
				}
				vetor[posA] = tmp;
				posB++;
			}
			posA++;
		}
	}

	public static void main(String[] args) {
		Ordenador sort = new MergeQuadratico(); 
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