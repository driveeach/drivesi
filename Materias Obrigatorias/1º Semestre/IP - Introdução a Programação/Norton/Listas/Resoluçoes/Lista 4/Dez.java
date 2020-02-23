class Dez {
	public static void Indice (int [] num) {
		int [] indice = new int [num.length];
		int contador = 0;
		for (int i = 0; i<num.length; i++) {
			if (num[i] % 2 == 0) {
				indice [contador] = i + 1;
				contador ++;
			}
		}
		for (int c : indice) {
			if (c != 0) {
				System.out.print (c + " ");
			}
		}
	}
	public static void main (String [] args) {
		int [] num = {2,4,6,8,10,12,14};
		Indice (num);
	}
}