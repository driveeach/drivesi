class Quatro {
	public static void altera (double[][] matriz) {
		for (int i = 0; i<matriz.length; i++) {
			double maior = 0;
			for (int j = 0; j<matriz.length; j++){
				if (matriz [j][i] > maior) maior = matriz [j][i];
			}
			for (int j = 0; j<matriz.length; j++) {
				matriz [j][i] /= maior;
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
		double [][] matriz = {{1,2,3},
							{2,3,4},
							{3,4,5}};
		altera (matriz);
	}
}