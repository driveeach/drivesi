class SelectionSort implements Ordenador {

	public void ordena(int[] A) {
		int i, j, indiceDoMinimo, temp;
		int fim = A.length;

		for (i = 0; i < fim - 1; i++) {

			indiceDoMinimo = i;
			for (j = i + 1; j < fim; j++) {
				if (A[j] < A[indiceDoMinimo]) {
					indiceDoMinimo = j;
				}
			}

			temp = A[i];
			A[i] = A[indiceDoMinimo];
			A[indiceDoMinimo] = temp;
		}
	}
}
class InsertionSort implements Ordenador {

	public void ordena(int[] A) {
		int i, j, v;
		int fim = A.length;

		for (i = 1; i < fim; i++) {
			v = A[i];
			j = i;
			while ((j > 0) && (A[j - 1] > v)) {
				A[j] = A[j - 1];
				j = j - 1;
			}
			A[j] = v;
		}
	}
}
class BubbleSort implements Ordenador {

	public void ordena(int[] A) {
		int i, j, temp;
		int fim = A.length;

		for (i = fim - 1; i > 0; i--) {

			for (j = 1; j <= i; j++) {
				if (A[j - 1] > A[j]) {
					// Troca os dois de lugar
					temp = A[j - 1];
					A[j - 1] = A[j];
					A[j] = temp;
				}
			}
		}

	}
}