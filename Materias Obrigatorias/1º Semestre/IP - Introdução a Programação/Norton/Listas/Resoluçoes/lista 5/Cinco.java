class Cinco {
	public static void soma(int[][]m1, int[][]m2) {
		int[][] matriz = new int [m1.length][m1.length];
		for (int i = 0; i<m1.length; i++) {
			for (int j = 0; j<m1.length; j++) {
				matriz [i][j] = m1[i][j] + m2[i][j];
			}
		}
		for (int i = 0; i<matriz.length; i++) {
			for (int j = 0; j<matriz.length; j++){
				System.out.print (matriz [i][j] + " ");
			}
			System.out.println ();
		}
	}
	public static void main (String [] args) {
		int [][] m1 = {{1,2,3},
						{3,4,5},
						{5,6,7}};
		int [][] m2 = {{1,2,3},
						{3,4,5},
						{5,6,7}};
		soma (m1, m2);
	}
}