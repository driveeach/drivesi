class Dois {
	public static void Soma (int [][] matriz) {
		int soma = 0;
		int j = matriz.length - 1;
		for (int i = 0; i < matriz.length; i++) {
			soma += matriz[i][j];
			j--;
		}
		System.out.println (soma);
	}
	public static void main (String [] args) {
		int [][] matriz = {{2,5,10,4},
						   {-3, 8,1,5},
						   {4,0,7,11},
						   {3,-4,1,2}};
		Soma (matriz);
	}
}