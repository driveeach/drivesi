package sort;

public class Select {

	public int select(int[] A, int k) {
		return select(A, 0, A.length - 1, k - 1);
	}

	private int select(int[] A, int ini, int fim, int k) {
		int q = particao(A, ini, fim);
		if (q == k) {
			return A[k];
		}
		if (q > k) {
			return select(A, ini, q - 1, k);
		} else {
			return select(A, q + 1, fim, k);
		}
	}

	protected int particao(int[] A, int ini, int fim) {

		int pivo, i, j, temp;

		pivo = A[fim];
		i = ini;
		j = fim - 1;

		while (i <= j) {
			if (A[i] <= pivo) {
				i++;
			} else if (A[j] > pivo) {
				j--;
			} else {
				temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				i++;
				j--;
			}
		}
		A[fim] = A[i];
		A[i] = pivo;
		return i;
	}
}

class SelectAleatorio extends Select {

	protected int particao(int[] A, int ini, int fim) {
		int i, temp;
		double f;

		// Escolhe um numero aleatorio entre ini e fim

		f = java.lang.Math.random(); // retorna um real f tal que 0 <= f < 1
		i = (int) (ini + (fim - ini) * f);

		// i eh tal que ini <= i < fim

		// Troca de posicao A[i] e A[fim]

		temp = A[fim];
		A[fim] = A[i];
		A[i] = temp;

		return super.particao(A, ini, fim);
	}
}