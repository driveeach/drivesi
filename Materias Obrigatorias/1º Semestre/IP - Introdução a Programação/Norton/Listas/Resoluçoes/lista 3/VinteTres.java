class VinteTres {
	static double cosseno;
		
	static int Fatorial (int n) {
		int produto = 1;
		for (int k = 1; k <= n; k++) {
			produto *= k;
		}
		return produto;
	}
	static void Cosseno (double x) {
		double somatoria = 0;
		for (int k = 2; k<= 20; k+=2) {
			somatoria += ((Math.pow (x, k)) / Fatorial (k));
		}
		cosseno = 1 - somatoria;
		System.out.println(cosseno);
	}
	public static void main (String [] args) {
		Cosseno (30);
	}
}

