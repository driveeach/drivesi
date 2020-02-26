public class Polinomio {

	private double potencia(double x, int n) {
		double potencia = 1;
		for (int i = 0; i < n; i++) {
			potencia *= x;
		}
		return potencia;
	}

	double polinomio(double x, double[] a, int n) {
		if (n == 0) {
			return a[0];
		} else {
			double p = polinomio(x, a, n - 1);
			double xn = potencia(x, n);
			return p + a[n] * xn;
		}
	}

	double horner(double x, double[] a, int k, int n) {
		if (n == 0) {
			return a[0];
		} else {
			double p = horner(x, a, k + 1, n - 1);
			return x * p + a[k];
		}
	}

	public static void main(String[] args) {
		Polinomio p = new Polinomio();
		double[] a = new double[] { 1, 4, 6, 4, 1 };
		System.out.println(p.polinomio(4, a, a.length - 1));
		System.out.println(p.horner(4, a, 0, a.length - 1));
	}
}
