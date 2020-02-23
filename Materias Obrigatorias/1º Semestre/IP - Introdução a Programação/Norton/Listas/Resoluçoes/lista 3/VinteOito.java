class VinteOito {
	static int Cubo (int m) {
		int impar = 0;
		int cubo = 0;
		impar = m * (m-1) + 1;
		for (int k = 0; k < m; k++) {
			cubo += impar;
			impar +=2;
		}
		return cubo;
	}
	public static void main (String [] args) {
		System.out.println (Cubo (5));
	}
}