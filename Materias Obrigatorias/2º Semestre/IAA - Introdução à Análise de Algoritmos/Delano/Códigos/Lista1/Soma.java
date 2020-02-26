public class Soma {

	int soma(int[] v, int n) {
		if (n == 2) {
			return v[0] + v[1];
		} else {
			return v[n - 1] + soma(v, n - 1);
		}
	}

	int soma(int[] v, int ini, int fim) {
		if (ini == fim) {
			return v[ini];
		} else if (ini >= fim - 1) {
			return v[ini] + v[fim];
		} else {
			int meio = (ini + fim) / 2;
			int soma1 = soma(v, ini, meio);
			int soma2 = soma(v, meio + 1, fim);
			return soma1 + soma2;
		}
	}

	public static void main(String[] args) {
		Soma obj = new Soma();

		int[] a = new int[] { 1, 3, 5, 7, 2, 4, 6, 8, -30 };

		int somaInc = obj.soma(a, a.length);
		System.out.println("Soma = " + somaInc);

		int somaDevCon = obj.soma(a, 0, a.length - 1);
		System.out.println("Soma = " + somaDevCon);

	}

}