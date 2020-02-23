class Onze {
	public static boolean magico (int[][]m1) {
		int [] verifica = new int [(m1.length * 2) + 2];
		int soma = 0;
		int contador = 0;
		for (int i = 0; i<m1.length; i++) {
			soma = 0;
			for (int j=0; j<m1.length; j++) {
				soma += m1[i][j];	
			}
			verifica [contador] = soma;
			contador++;
		}
		for (int i = 0; i<m1.length; i++) {
			soma = 0;
			for (int j=0; j<m1.length; j++) {
				soma += m1[j][i];	
			}
			verifica [contador] = soma;
			contador++;
		}
		soma = 0;
		for (int i = 0; i<m1.length; i++){
			soma += m1[i][i];
		}
		verifica [contador] = soma;
		contador++;
		verifica [contador] = m1[0][2] + m1[1][1] + m1[2][0];
		for (int i =0; i<verifica.length; i++) {
			if (verifica[i] == soma);
			else return false;
		}
		return true;
	}
	public static void main (String [] args) {
		int [][] m1 = {{8,0,7},
			       {4,5,6},
			       {3,10,2}};
		System.out.println (magico (m1));
	}
}