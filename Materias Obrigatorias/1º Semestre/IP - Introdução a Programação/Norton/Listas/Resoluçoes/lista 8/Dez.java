class Dez {
	public static int [] buscaBinariaBidimensional (int [][] a1, int num) {
		int [] posicao = {-1,-1}; 
		int inicio =0;
		int fim = a1.length-1;
		if (num < a1[0][0]) return posicao;
		while (inicio <= fim){
			if (inicio == fim) {
				if (num >= a1[inicio][0]){
					posicao[0] = inicio;
					break;				
				}
				else {
					posicao[0] = inicio -1;
					break;
				}
			}
			int meio = (int)((inicio+fim)/2);
			if (num > a1[meio][0]) {
				posicao[0] = inicio;
				inicio = meio + 1;
			}
			else {
				if (num < a1[meio][0]){
					posicao[0] = inicio - 1;
					fim = meio - 1;
				}
				else {
					posicao [0] = meio;
					posicao [1] = 0;
					return posicao;
				}
			}
		}
		inicio = 0;
		fim = a1[0].length-1;
		while (inicio <= fim) {
			if (inicio == fim && inicio == a1.length-1) {
				if (a1[posicao[0]][inicio] == num){
					posicao[1] = inicio;
					return posicao;
				}
				else break;
			}
			int meio = (int)((inicio+fim)/2);
			if (num > a1[posicao[0]][meio]) {
				posicao[1] = meio;
				inicio = meio + 1;
			}
			else {
				if (num < a1[posicao[0]][meio]){
					posicao[1] = meio;
					fim = meio - 1;
				}
				else {
					posicao [1] = meio;
					return posicao;
				}
			}
		}
		posicao[0] = -1;
		posicao[1] = -1;
		return posicao;
	}
	public static void main (String [] args) {
		int [][] a1 = { {-55,-50,-42,-33,-30},
						{-30,-21, -4,  0,  0},
						{ 3,   5, 18, 33, 34},
						{39,  45, 59, 87,122}};
		int [] resposta = buscaBinariaBidimensional (a1, -54);
		for (int c : resposta) System.out.print (c + " ");
	}
}
