package sort;

import java.util.Random;

public class BogoSort implements Ordenador {

	public void ordena(int[] A) {
		Random random = new Random();
		boolean sorted;

		do {
			sorted = true;
			for (int i = 0; i < A.length - 1; i++) {
				if (A[i] > A[i + 1]) {
					sorted = false;
					break;
				}
			}

			if (!sorted) {
				for (int i = A.length - 1; i > 0; i--) {
					int swapPosition = random.nextInt(i);
					int temp = A[i];
					A[i] = A[swapPosition];
					A[swapPosition] = temp;
				}
			}
		} while (!sorted);
	}

}
