package sort;

public class RadixSort {
	public int[] ordena(int[] A, int d) {
		for (int i = 0; i < d; i++) {
			A = CountingSort(A, i + 1);
		}
		return A;
	}

	int digito(int n, int d) {
		return (int) ((n % Math.pow(10, d)) / (Math.pow(10, d - 1)));
	}

	private int[] CountingSort(int[] A, int d) {
		int[] B = new int[A.length];
		int[] C = new int[10];
		final int n = A.length;
		for (int j = 0; j < n; j++) {
			int index = digito(A[j], d);
			C[index] = C[index] + 1;
		}
		for (int i = 1; i <= 9; i++) {
			C[i] = C[i] + C[i - 1];
		}
		for (int j = n - 1; j >= 0; j--) {
			int index = digito(A[j], d);
			B[C[index] - 1] = A[j];
			C[index] = C[index] - 1;
		}
		return B;
	}
}