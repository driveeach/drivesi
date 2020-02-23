class VinteSete {
	static int Multiplicar (int a, int b) {
		int soma = 0;
		for (int k = 0; k < b; k++) {
			soma += a;
		}
		return soma;
	}
	
	static int Dividir (int a, int b) {
		int subtração = a;
		int vezes = 0;
		while (subtração >= b) {
			subtração -= b;
			vezes++;
		}
		return vezes;
	}
	static int Resto (int a, int b) {
		int subtração = a;
		int vezes = 0;
		while (subtração >= b) {
			subtração -= b;
			vezes++;
		}
		return subtração;
	}
	
	public static void main (String [] args) {
		System.out.println (Multiplicar (4 ,6));
		System.out.println (Dividir (20 ,4));
		System.out.println (Resto (21 ,4));
		
	}
}