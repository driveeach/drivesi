package sort;

public class FourSort implements Ordenador {

	public void ordena(int[] A) {
		sort(A, 0, A.length - 1);
	}

	private void sort(int[] A, int ini, int fim) {
		if (A[ini] > A[fim]) {
			int temp = A[ini];
			A[ini] = A[fim];
			A[fim] = temp;
		}

		if (ini + 1 >= fim) {
			return;
		}

		int quarto = (fim - ini) / 4;
		sort(A, ini, fim - quarto - 1);
		sort(A, ini + quarto + 1, fim);
		sort(A, ini, fim - quarto - 1);
	}
}
