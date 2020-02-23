class Seis {
	public static void multiplicacao (int[][]m1, int[][]m2) {
		int [][] matriz = new int [m1.length][m1[0].length];
		for (int i = 0; i<m1.length; i++) {
			for (int j = 0; j<m1[0].length;j++) {
				for (int k =0; k<m1.length; k++) {
					matriz [i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		for (int i = 0; i<m1.length; i++) {
			for (int j = 0; j<m1[0].length;j++) {
				System.out.print (matriz[i][j] + " ");
			}
			System.out.println ();
		}
	}
	public static void main (String [] args) {
		int [][] m1 = {{1,2,3},
					   {1,2,3},
					   {1,2,3}};
		int [][] m2 = {{1,2,3},
					   {1,2,3},
					   {1,2,3}};
		multiplicacao (m1,m2);
   }
}