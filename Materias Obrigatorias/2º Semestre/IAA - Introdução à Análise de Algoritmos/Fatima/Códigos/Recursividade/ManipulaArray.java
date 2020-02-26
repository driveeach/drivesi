public class ManipulaArray {

	public void imprime(int[] v) {
		imprime(v, v.length - 1);
		System.out.println();
	}
	
	private void imprime(int[] v, int n) {
		if (n >= 0) {
			imprime(v, n - 1);
			System.out.print(v[n] + " ");
		}
	}

	public void imprimeInverso(int[] v) {
		imprimeInverso(v, v.length - 1);
		System.out.println();
	}
	
	private void imprimeInverso(int[] v, int n) {
		if (n >= 0) {
			System.out.print(v[n] + " ");
			imprimeInverso(v, n - 1);
		}
	}

	public void inverteArray(int[] v) {
		inverteArray(v, 0, v.length - 1);
	}
	
	private void inverteArray(int[] v, int inicio, int fim) {
		if (inicio < fim) {
			inverteArray(v, inicio + 1, fim - 1);
			int temp = v[inicio];
			v[inicio] = v[fim];
			v[fim] = temp;
		}
	}

	/*
	 * reorganiza array de tal forma que todos os valores pares aparecem antes
	 * dos valores ímpares
	 */
	public void reorganiza(int[] v) {
		reorganiza(v, v.length - 1);
	}

	/* reorganiza e retorna o índice do último par */
	private int reorganiza(int[] v, int n) {
		if (n == 1) {
			if (v[0] % 2 == 0) {
				return 0;
			} else {
				return -1;
			}
		} else {
			int indice = reorganiza(v, n - 1);
			if (v[n] % 2 == 0) {
				int temp = v[n];
				v[n] = v[indice + 1];
				v[indice + 1] = temp;
				indice++;
			}
			return indice;
		}
	}

}
