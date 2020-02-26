package sort;

public class Eleicao {

	public static void main(String[] args) {
		final int n = 10000;
		final int m = 9999999;
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = (int) (1000000 + ((m - 1000000) * java.lang.Math.random()));
		}
		HeapSort sort = new HeapSort();
		sort.ordena(A);
		Main.imprimeArranjo(A);
		int contador = 1;
		int prev = A[0];
		int index = 0;
		int max = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] == prev) {
				contador++;
			} else {
				if (contador > max) {
					max = contador;
					index = i - 1;
				}
				prev = A[i];
				contador = 1;
			}
		}
		System.out.print("candidato " + A[index] + " com " + max + " votos");
	}

}
