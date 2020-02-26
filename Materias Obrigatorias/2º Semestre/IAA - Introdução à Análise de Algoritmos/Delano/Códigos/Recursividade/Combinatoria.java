public class Combinatoria {

	void printSubset() {
		int[] table = new int[] { 1, 2, 3, 4, 5 };

		for (int i = 1; i <= table.length; i++)
			printSubRecursive("", table, i, 0);
	}

	void printSubRecursive(String res, int[] t, int size, int pos) {
		if (t.length < size + pos)
			return;
		if (size > 0) {
			printSubRecursive(res + t[pos] + " ", t, size - 1, pos + 1);
			printSubRecursive(res, t, size, pos + 1);
		} else
			System.out.println(res);
	}

	int C(int m, int n) {
		if (n == 0 || m == n) {
			return 1;
		} else {
			return C(m - 1, n) + C(m - 1, n - 1);
		}
	}

	void imprimeNumeroCombinacoes(int n) {
		for (int i = n; i >= 1; i--) {
			for (int j = 0; j <= i; j++) {
				System.out.println("C" + i + "," + j + " = " + C(i, j));
			}
		}
	}
}
