class Oito {
	public static void maior (int[][]m1) {
		int soma = 0;
		int indice = 0;
		for (int i = 0; i<m1[0].length; i++) {
			int soma1= 0;
			for (int j = 0; j<m1.length;j++) {
				soma1 += m1[j][i];
			}
			if (soma1>soma) {
				soma=soma1;
				indice = i;
			}
		}
		System.out.print (indice);
	}
	public static void main (String [] args) {
		int [][] m1 = {{2,5,4,10},
					   {-3,8,5,1},
					   {4,0,11,7}};
		maior (m1);
   }
}