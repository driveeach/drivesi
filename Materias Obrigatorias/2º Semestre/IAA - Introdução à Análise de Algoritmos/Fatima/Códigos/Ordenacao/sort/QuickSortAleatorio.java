package sort;

public class QuickSortAleatorio extends QuickSort {

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