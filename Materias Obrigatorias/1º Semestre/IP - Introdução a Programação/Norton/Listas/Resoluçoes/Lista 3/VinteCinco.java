class VinteCinco {
	static void Tabela (int n) {
		int[][] array = new int [n][n];
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
			array[i][j] = (i+1) * (j+1);
			}
		}
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
			System.out.print (array[i][j] + "\t");
			}
		System.out.println ();
		}
		
	}
	public static void main (String [] args) {
		Tabela (6);
	}
}