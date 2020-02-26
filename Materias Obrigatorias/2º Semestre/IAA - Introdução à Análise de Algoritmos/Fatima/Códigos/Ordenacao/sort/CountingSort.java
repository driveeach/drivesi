package sort;

public class CountingSort {

	public int[] ordena(int[] A, int k) {
		int[] B = new int[A.length];
		int[] C = new int[k];
		final int n = A.length;
		for (int j = 0; j < n; j++) {
			C[A[j]] = C[A[j]] + 1;
		}
		for (int i = 1; i < k; i++) {
			C[i] = C[i] + C[i - 1];
		}
		for (int j = n - 1; j >= 0; j--) {
			B[C[A[j]] - 1] = A[j];
			C[A[j]] = C[A[j]] - 1;
		}
		return B;
	}
}
