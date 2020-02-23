class Quatro {
	static void imprime (String palavra) {
		char [] tamanho = palavra.toCharArray();
		for (int i = 0; i<tamanho.length; i++) {
			if (tamanho [i] == ' ') System.out.print (tamanho [i+1]);
		}
	}
	public static void main (String [] args) {
			imprime ("Minha String é assim!");
	}
}