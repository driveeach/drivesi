class Dezoito {
	public static void Inverte (double [] n) {
		double a = 0;
		double b = 0;
		for (int i = 0; i < n.length / 2; i++) {
			a = n[i];
			b = n[n.length - 1 - i];
			n[i] = b;
			n[n.length - 1 - i] = a;
		}
		for (double c : n) System.out.println (c);
	}
	public static void main (String [] args) {
		double [] n = {1,2,3};
		Inverte (n);
	}
}