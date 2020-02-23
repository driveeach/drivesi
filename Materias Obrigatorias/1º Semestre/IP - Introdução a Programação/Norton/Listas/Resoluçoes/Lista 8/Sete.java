class Sete {
	static int [] ordena (int [] a1, int [] a2) {
		int [] resultado = new int [a1.length + a2.length];
		for (int i = 0; i<a1.length; i++) {
			resultado [i] = a1[i]; 
		}
		int aux = 0;
		for (int i = 0; i<a2.length; i++) {
			int contador = a1.length-1+i;
			while (a2[i] < resultado[contador] && contador >= 0) {
				aux = resultado[contador] ;
				resultado [contador] = resultado [contador-1];
				resultado [contador+1] = aux;
				contador--;
			}
			resultado[contador+1] = a2[i];
		}
		return resultado;
	}
	public static void main (String [] args) {
		int [] a1 = {1,3,5,8,11};
		int [] a2 = {2,6,9,13,15};
		int [] resultado = ordena (a1,a2);
		for (int c : resultado) System.out.print (c + " ");
	}
}