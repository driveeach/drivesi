class Dezenove {
	static void Tabuada (int n, int m) {
		int [][] tab = new int[n][m];
		int a = 1;
		int num = 1;
		for (int i = 0; i < n; i++) {
		num = 1;	
			for (int j = 0; j < m; j++) {
				tab[i][j] = num * a;
				num++;
			}
		a++;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print (tab[i][j] + "\t");
			}
		System.out.println ();	
		}
	}
	public static void main (String [] args) {
	Tabuada (10,10);
	
	}
}