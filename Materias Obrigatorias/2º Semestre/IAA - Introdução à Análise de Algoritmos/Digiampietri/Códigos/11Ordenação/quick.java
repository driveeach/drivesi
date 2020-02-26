class QuickSort implements Ordenador {

	public void ordena(int[] A) {
		quickSort(A, 0, A.length - 1);
	}

	private void quickSort(int[] A, int p, int r) {
		if (p < r) {
			int q = particao(A, p, r);
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);
		}
	}

	protected int particao(int[] A, int p, int r) {
		int x, i, j, temp;
		x = A[r]; // pivo
		i = p - 1;

		for (j = p; j <= r - 1; ++j) {
			if (A[j] <= x) {
				i++;
				// trocar
				temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}

		// reposicionar o pivo
		temp = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp;
		return (i + 1);
	}
}

class QuickSortAleatorio extends QuickSort {

	protected int particao(int[] A, int p, int r) {
		int i, temp;
		double f;

		// Escolhe um numero aleatorio entre p e r

		f = java.lang.Math.random(); // retorna um real f tal que 0 <= f < 1
		i = (int) (p + (r - p) * f);

		// i eh tal que p <= i < r

		// Troca de posicao A[i] e A[r]

		temp = A[r];
		A[r] = A[i];
		A[i] = temp;

		return super.particao(A, p, r);
	}
}