class Oito {
	static boolean verifica (int x, int [] a1) {
		int inicio = 0;
		int fim = a1.length-1;
		while (inicio <= fim) {
			int meio = (inicio+fim)/2;
			if (a1[meio] > x) fim = meio - 1;
			else {
				if (a1[meio] < x) inicio = meio + 1;
				else return true;
			}
		}
		return false;
	}
	public static void main (String [] args) {
		int [] a1 = {1,3,5,7,8,9};
		System.out.println (verifica (2, a1));
	}
}