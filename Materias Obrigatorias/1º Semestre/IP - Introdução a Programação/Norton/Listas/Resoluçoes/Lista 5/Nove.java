class Nove {
	public static void transposta (int[][]m1) {
		int [][] t = new int [m1[0].length][m1.length];
		for (int i = 0; i<m1.length; i++) {
			for (int j = 0; j<m1[0].length;j++) {
				t [j][i] = m1 [i][j];
			}
		}
		for (int i = 0; i<t.length; i++) {
			for (int j = 0; j<t[0].length;j++) {
				System.out.print (t[i][j] + " ");
			}
			System.out.println ();
		}
	}
	public static void main (String [] args) {
		int [][] m1 = {{1,2,3},
					   {1,2,3},
					   {1,2,3}};
		transposta (m1);
   }
}