public class Exercicios {

	/* Exercicio 1a */

	long fatorial(long n) {
		if (n == 0) {
			return 1;
		} else {
			return n * fatorial(n - 1);
		}
	}

	/* Exercicio 1b */

	int fibonacci(int n) {
		if (n < 2) {
			return n;
		} else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	/* Exercicio 1c */

	int binaria(int valor, int[] vetor, int esq, int dir) {

		if (esq > dir) {
			return -1;
		} else {
			int meio = (esq + dir) / 2;

			if (valor == vetor[meio]) {
				return meio;
			} else {
				if (valor > vetor[meio]) {
					esq = meio + 1;
				} else {
					dir = meio - 1;
				}
				return binaria(valor, vetor, esq, dir);
			}
		}
	}

	/* Exercicio 3 */

	int somaPositivos(int[] v, int n) {
		int valor = 0;
		if (n == 1) {
			if (v[0] > 0) {
				valor = v[0];
			}
			return valor;
		} else {
			if (v[n - 1] > 0) {
				valor = v[n - 1];
			}
			return valor + somaPositivos(v, n - 1);
		}
	}

	/* Exercicio 4 */
	
	int[] maxmin(int[] v, int n) {
		int[] maxmin;
		if (n == 2) {
			maxmin = new int[2];
			if (v[0] > v[1]) {
				maxmin[0] = v[0];
				maxmin[1] = v[1];
			} else {
				maxmin[0] = v[1];
				maxmin[1] = v[0];
			}
		} else {
			maxmin = maxmin(v, n - 1);
			if (maxmin[0] < v[n-1]) {
				maxmin[0] = v[n-1];
			} else if (maxmin[1] > v[n-1]) {
				maxmin[1] = v[n-1];
			}
		}
		return maxmin;
	}

	/* Exercicio 5 */

	int somaPositivos(int[] v, int ini, int fim) {
		int valor = 0;
		if (ini == fim - 1) {
			if (v[ini] > 0) {
				valor = v[ini];
			}
			return valor;
		} else {
			if (v[fim - 1] > 0) {
				valor = v[fim - 1];
			}
			return valor + somaPositivos(v, ini, fim - 1);
		}
	}

	/* Exercicio 6 */

	int somaDigitos(int n) {
		if (n == 0) {
			return 0;
		} else {
			return n % 10 + somaDigitos(n / 10);
		}
	}

	/* Exercicio 7 */

	int onde(int[] v, int x, int n) {
		if (n == 1) {
			if (v[0] == x) {
				return 0;
			} else {
				return -1;
			}
		} else {
			int index = onde(v, x, n - 1);
			if (index < 0) {
				if (v[n - 1] == x) {
					index = n - 1;
				}
			}
			return index;
		}
	}

	/* Exercicio 8 */

	int onde(int[] v, int x, int ini, int fim) {
		if (ini == fim - 1) {
			if (v[ini] == x) {
				return ini;
			} else {
				return ini - 1;
			}
		} else {
			int index = onde(v, x, ini, fim - 1);
			if (index < ini) {
				if (v[fim - 1] == x) {
					index = fim - 1;
				}
			}
			return index;
		}
	}
}
