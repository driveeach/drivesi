package sort;

public class TwoWaySelectionSort implements Ordenador {

	public void ordena(int[] A) {
		twoWaySelectionSort(A, 0, A.length - 1);
	}

	private void twoWaySelectionSort(int[] A, int ini, int fim) {

		if (ini >= fim) {
			return;
		} else {
			int tmp;
			int[] minMax = minMax(A, ini, fim);

			if (ini == minMax[1] && fim == minMax[0]) {
				// troca A[ini] <-> A[fim]
				tmp = A[ini];
				A[ini] = A[fim];
				A[fim] = tmp;
			} else {
				// troca A[ini] <-> A[min]
				tmp = A[ini];
				A[ini] = A[minMax[0]];
				A[minMax[0]] = tmp;

				// troca A[fim] <-> A[max]
				tmp = A[fim];
				A[fim] = A[minMax[1]];
				A[minMax[1]] = tmp;
			}
			twoWaySelectionSort(A, ini + 1, fim - 1);
		}
	}

	private int[] minMax(int[] v, int ini, int fim) {

		if (ini >= fim - 1) {
			if (v[ini] < v[fim]) {
				return new int[] { ini, fim };
			} else {
				return new int[] { fim, ini };
			}
		} else {
			int meio = (ini + fim) / 2;
			int[] minMax1 = minMax(v, ini, meio);
			int[] minMax2 = minMax(v, meio + 1, fim);

			if (v[minMax1[0]] > v[minMax2[0]]) {
				minMax1[0] = minMax2[0];
			}

			if (v[minMax1[1]] < v[minMax2[1]]) {
				minMax1[1] = minMax2[1];
			}

			return minMax1;
		}
	}
}
