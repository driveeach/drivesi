class Um {
	public static void Maior (int [][] matriz) {
		int maior = matriz[0][0];
		int [] pos = new int [2];
		int linha = 0;
		int coluna = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz [i][j] > maior) {
					maior = matriz[i][j];
					linha = i+1;
					coluna = j+1;
				}
			}
		}
		System.out.println ("O maior numero é " +maior+ " e está na linha " +linha+ " e na coluna " +coluna);
	}
	public static void main (String [] args) {
		int [][] matriz = {{1,25,3},{6,5,4}};
		Maior (matriz);
	}
}