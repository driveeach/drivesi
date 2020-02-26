package sort;

public class StoogeSort implements Ordenador {

	public void ordena(int[] A) {
		stoogeSort(A, 0, A.length - 1);
	}

	private void stoogeSort(int[] A, int ini, int fim) {

		if (A[ini] > A[fim]) {
			int temp = A[ini];
			A[ini] = A[fim];
			A[fim] = temp;
		}

		if (ini + 1 >= fim) {
			return;
		}

		int terco = (fim - ini + 1) / 3;
		stoogeSort(A, ini, fim - terco);
		stoogeSort(A, ini + terco, fim);
		stoogeSort(A, ini, fim - terco);
	}
}
