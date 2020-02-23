class Seis {
	static String inverte (String palavra) {
		char [] inverte = palavra.toCharArray();
		int j = inverte.length - 1;
		for (int i = 0; i<inverte.length /2; i++) {
			char aux;
			aux = inverte[i];
			inverte[i] = inverte[j];
			inverte[j] = aux;
			j--;
		}
		palavra = String.valueOf(inverte);
		return palavra;
	}
	public static void main (String [] args) {
		System.out.println (inverte ("roma"));
	}
}