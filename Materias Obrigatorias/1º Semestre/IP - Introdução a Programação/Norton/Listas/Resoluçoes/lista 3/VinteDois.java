class VinteDois {
	static int Fatorial (int n) {
		int produto = 1;
		for (int k = 1; k <= n; k++) {
			produto *= k;
		}
		return produto;
	}
	public static void main (String [] args) {
		System.out.println(Fatorial (2));

	}
}