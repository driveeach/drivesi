class Tres {
	static int posicao (String palavra, char teste) {
		char [] tamanho = palavra.toCharArray();
		for (int i = 0; i<tamanho.length; i++) {
			if (tamanho[i] == teste) return i;
		}
		return -1;
	}
	public static void main (String [] args) { 
		System.out.println (posicao ("Fernanda", 'a'));
	}
}