public class Merge {

	int[] merge(int[] a, int[] b) {
		int posa = 0, posb = 0, posc = 0;
		int[] c = new int[a.length + b.length];
		// Enquanto nenhuma das seqüências está vazia...
		while (posa < a.length && posb < b.length) {
			// Pega o menor elemento das duas seqüências
			if (b[posb] <= a[posa]) {
				c[posc] = b[posb];
				posb++;
			} else {
				c[posc] = a[posa];
				posa++;
			}
			posc++;
		}

		// Completa com a seqüência que ainda não acabou
		while (posa < a.length) {
			c[posc] = a[posa];
			posc++;
			posa++;
		}
		while (posb < b.length) {
			c[posc] = b[posb];
			posc++;
			posb++;
		}
		return c; // retorna o valor resultado da intercalação
	}

	Menores menoresVetores(int[][] conjunto, int n) {
		int primeiro, segundo;

		if (conjunto[0].length < conjunto[1].length) {
			primeiro = 0;
			segundo = 1;
		} else {
			primeiro = 1;
			segundo = 0;
		}

		for (int i = 2; i < conjunto.length; i++) {
			if (conjunto[primeiro].length > conjunto[i].length) {
				segundo = primeiro;
				primeiro = i;
			} else if (conjunto[segundo].length > conjunto[i].length) {
				segundo = i;
			}
		}

		return new Menores(primeiro, segundo);

	}

	int[][] removeVetores(int[][] conjunto, Menores menores) {
		int pos = 0;
		int[][] novo = new int[conjunto.length - 1][];
		for (int i = 0; i < conjunto.length; i++) {
			if (i != menores.getPrimeiro() && i != menores.getSegundo()) {
				novo[pos++] = conjunto[i];
			}
		}
		return novo;
	}

	int[] merge(int[][] conjunto) {

		int tam = conjunto.length;
		int numCmp = 0;

		do {
			/* escolhe os dois menores vetores A e B (seleção gulosa) */
			Menores menores = menoresVetores(conjunto, tam);
			int prim = menores.getPrimeiro();
			int seg = menores.getSegundo();
			int[] A = conjunto[prim];
			int[] B = conjunto[seg];

			/* V = V - { A, B }; */
			conjunto = removeVetores(conjunto, menores);

			/* C = Intercala(A, B); */
			int[] C = merge(A, B);

			/* V = V + { C } */
			conjunto[tam - 2] = C;

			numCmp = numCmp + A.length + B.length - 1;

			tam = tam - 1;
		} while (tam > 1);
		System.out.println("Foram feitas " + numCmp + " Comparações");
		return conjunto[0];
	}

	public static void main(String[] args) {
		int[][] conjunto = new int[][] {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5 } };
		Merge merge = new Merge();
		int[] merged = merge.merge(conjunto);
		for (int i = 0; i < merged.length; i++) {
			System.out.print(merged[i] + " ");
		}
		System.out.println();
	}
}

class Menores {
	private int primeiro;
	private int segundo;

	public Menores(int primeiro, int segundo) {
		this.primeiro = primeiro;
		this.segundo = segundo;
	}

	public int getPrimeiro() {
		return primeiro;
	}

	public int getSegundo() {
		return segundo;
	}
}