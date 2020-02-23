class Um {
	static int tamanho (String palavra) {
		char [] tamanho = palavra.toCharArray();
		return tamanho.length;
	}
	public static void main (String [] args) {
		System.out.println (tamanho("Fernanda"));
	}
}