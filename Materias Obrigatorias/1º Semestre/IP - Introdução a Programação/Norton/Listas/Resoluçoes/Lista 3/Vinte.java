class Vinte {
	static boolean Quadrado (int num) {
		int soma = 0;
		int impar = 1;
		while (soma < num) {
			soma += impar;
			impar += 2;
		}
		if (soma == num) return true;
		else return false;
	}

	public static void main (String [] args) {
		System.out.println(Quadrado (169));
	}
}