public class Busca {

	int sequencial(int valor, int[] v, int n) {
		if (n == 1) {
			if (v[0] == valor) {
				return 0;
			} else {
				return -1;
			}
		} else {
			int index = sequencial(valor, v, n - 1);
			if (index < 0) {
				if (v[n - 1] == valor) {
					index = n - 1;
				}
			}
			return index;
		}
	}

	int binaria(int valor, int[] vetor, int esq, int dir) {
		int meio = (esq + dir) / 2;
		if (esq <= dir) {
			if (valor > vetor[meio]) {
				esq = meio + 1;
				return binaria(valor, vetor, esq, dir);
			} else if (valor < vetor[meio]) {
				dir = meio - 1;
				return binaria(valor, vetor, esq, dir);
			} else {
				return meio;
			}
		} else {
			return -1;
		}
	}
	public static void main (String[] args) {
		int[] Arr={9, 56, 33, 4, 98, 100, 7};
		Busca B=new Busca();
		System.out.println (B.sequencial(98, Arr, Arr.length));
		System.out.println (B.binaria(98, Arr, 0, Arr.length));
	}
}
