class Cinco {
	static boolean palindromo(String palavra) {
		char [] tamanho = palavra.toCharArray();
		boolean [] teste = new boolean [tamanho.length/2];
		int j = tamanho.length-1;
		for (int i = 0; i<tamanho.length /2; i++) {
			if (tamanho[i] == tamanho[j]) teste[i] = true;
			else teste[i] = false;
			j--;
		}
		for (int i = 0; i<teste.length; i++){
			if (teste[i] == false) return false;
		}
		return true;
	}
	public static void main (String [] args) {
		System.out.println (palindromo ("osso"));
	}
}