class VinteUm {
	static void Quadrado (int n) {
		char [][] mais = new char [n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mais[i][j] = ('+');
			}
		}
		for (int i = 0; i < n; i++) {
			mais[i][i] = (':');
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(mais[i][j]);
			}
			System.out.println();
		}		
	}
	public static void main (String [] args) {
	Quadrado (20);
	}
}