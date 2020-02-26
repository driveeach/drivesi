package sort;

public class HeapSort implements Ordenador {

	private void refazHeapMax(int A[], int i, int compHeap) {
		int esq, dir, maior, temp;

		esq = 2 * i + 1; // esquerda(i) => 2 * i + 1
		dir = 2 * i + 2; // direita (i) => 2 * i + 2

		if (esq < compHeap && A[esq] > A[i]) {
			maior = esq;
		} else {
			maior = i;
		}

		if (dir < compHeap && A[dir] > A[maior]) {
			maior = dir;
		}

		if (maior != i) {
			// trocar A[i] <==> A[maior]
			temp = A[i];
			A[i] = A[maior];
			A[maior] = temp;

			// Ajusta a posicao de maior, se incorreta.
			refazHeapMax(A, maior, compHeap);
		}
	}

	private void constroiHeapMax(int[] A) {
		int compHeap = A.length;
		for (int i = (A.length) / 2 - 1; i >= 0; i--) {
			refazHeapMax(A, i, compHeap);
		}
	}

	public void ordena(int A[]) {
		int i, compHeap, temp;

		// Constroi o heap maximo do arranjo todo

		compHeap = A.length;

		constroiHeapMax(A);

		for (i = A.length - 1; i > 0; --i) {
			temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			// Diminui o heap, pois A[i] esta posicionado
			compHeap--;
			refazHeapMax(A, 0, compHeap);
		}
	}
}
