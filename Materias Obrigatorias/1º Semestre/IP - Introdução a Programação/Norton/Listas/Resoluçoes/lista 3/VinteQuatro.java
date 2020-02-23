class VinteQuatro {
	static int Potencia (int num) {
		int expoente = 0;
		int resultado = num;
		while (resultado > 1 && resultado % 2 == 0) { 
			resultado /= 2;
			expoente++;
		}
		if (resultado == 1) return expoente;
		else return -1;
	}
	public static void main (String [] args) {
		System.out.println(Potencia (1024));
	}
}