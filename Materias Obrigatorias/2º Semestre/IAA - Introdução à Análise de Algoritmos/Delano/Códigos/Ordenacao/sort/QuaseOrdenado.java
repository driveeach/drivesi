package sort;

public class QuaseOrdenado {

	int[] ordena(int[] A) {

		int[] fora;
		int[] ordem;
		int contador = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i + 1] < A[i]) {
				contador++;
			}
		}

		fora = new int[contador];
		ordem = new int[A.length - contador];

		int f = 0;
		int o = 1;
		ordem[0] = A[0];
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i + 1] < A[i]) {
				fora[f++] = A[i + 1];
			} else {
				ordem[o++] = A[i + 1];
			}
		}

		return fusao(ordem, fora);
	}

	int[] fusao(int[] A, int[] B) {
		int posA = 0;
		int posB = 0;
		int posC = 0;
		int[] C = new int[A.length + B.length];

		while (posA < A.length && posB < B.length) {
			if (B[posB] <= A[posA]) {
				C[posC] = B[posB++];
			} else {
				C[posC] = A[posA++];
			}
			posC++;
		}

		while (posA < A.length) {
			C[posC++] = A[posA++];
		}

		while (posB < B.length) {
			C[posC++] = B[posB++];
		}

		return C;
	}
}
