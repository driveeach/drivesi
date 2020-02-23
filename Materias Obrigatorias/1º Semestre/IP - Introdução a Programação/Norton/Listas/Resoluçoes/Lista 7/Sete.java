class Sete {
	static void copia (String palavra) {
		String copia;
		char [] aux = palavra.toCharArray ();
		copia = String.valueOf (aux);
		System.out.println (palavra);
		System.out.println (copia);
		copia = "moraes";
		System.out.println (palavra);
		System.out.println (copia);
	}
	public static void main (String [] args) {
		copia ("fernanda");
	}
}