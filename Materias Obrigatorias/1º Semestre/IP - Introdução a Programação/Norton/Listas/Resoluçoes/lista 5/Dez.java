class Dez {
	public static boolean simetrico (int[][]m1) {
		boolean [] verifica = new boolean [m1.length];
		for (int i = 0; i<m1.length; i++) {
			for (int j=0; j<m1.length; j++) {
				if (m1[i][j] == m1[j][i]) verifica [i] = true;
				else verifica [i] = false;
			}
		}
		for (int i =0; i<verifica.length; i++) {
			if (verifica[i] == false) return false;
		}
		return true;
	}
	public static void main (String [] args) {
		int [][] m1 = {{1,2,3},
			       {2,3,4},
			       {3,4,5}};
		System.out.println (simetrico (m1));
	}
}