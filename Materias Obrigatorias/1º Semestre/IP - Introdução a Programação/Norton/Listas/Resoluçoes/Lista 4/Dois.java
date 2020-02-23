class Dois {
	public static void Multiplo (int [] array) {
		for (int i = 0; i<array.length; i++) {
			if (array[i] % 7 == 0) {
				System.out.println (array[i]);
			}
		}
	}
	public static void main (String[] args) {
		int [] numeros = {4,7,9,21, 14};
		Multiplo (numeros);
	}
}