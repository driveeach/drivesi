class Doze {
	public static int filialMais (double[][] tabela) {
		double soma = 0;
		double somafinal = 0;
		int filial = 0;
		for (int i = 0; i<tabela.length; i++) {
			for (int j = 0; j<tabela.length; j++) {
				soma += tabela[i][j];
			}
			if (soma > somafinal) {
				somafinal = soma;
				filial = i;
			}
		}
		return filial;
	}
	public static int mes (double[][] tabela) {
		int mes = 0;
		double soma = 0;
		double somafinal = 0;
		for (int i = 0; i<tabela.length; i++) {
			soma = 0;
			for (int j = 0; j<tabela.length; j++) {
				soma += tabela[j][i];
			}
			if (soma>somafinal) {
				somafinal = soma;
				mes =  i;
			}
		}
		return mes;
	}	
	public static void main (String [] args) {
		double[][] tabela = {{0,0,0,1}, //aqui
				  {15453, 5300,  42000,   135832,  18580.36, 85200}, //la
				  {14320, 55800, 12356,   2563,    100000,   62325}, //acola 
				  {12345, 44823, 15863.25,56483.55,93000,    4852.33}};//ali
		System.out.println (filialMais (tabela));
		System.out.println (mes (tabela));
	}
}
	