class VinteSeis {
	static void Triangulo (int n) {
		int contador = 1;
		int coluna = 0;
		int [][] triangulo = new int [n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= coluna && j < n; j++) {
				triangulo [i][j] = contador;
				contador++;
			}
		coluna++;	
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print (triangulo [i][j] + "\t");
			}
		System.out.println ();
		}
	}
	public static void main (String [] args) {
		Triangulo (10);
	}
}