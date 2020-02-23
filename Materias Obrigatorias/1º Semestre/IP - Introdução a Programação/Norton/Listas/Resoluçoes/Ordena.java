import java.util.Scanner;
class Ordena {
	public static void ordenacao (int [] array) {
		for (int ult = array.length - 1; ult > 0; ult--) {
			for (int i = 0; i<ult; i++) {
				if (array[i] > array[i+1]) {
					int aux = array[i];
					array[i] = array[i+1];
					array [i+1] = aux;
				}
			}
		}
		imprime(array);
	}
	public static void ordenacao (int [] array, int i) {
				
	}
	public static void ordenacao (int [] array, String s) {

	}
	public static void imprime (int [] array){
		for (int c : array) System.out.print (c + " ");
	}
	public static void main (String [] args){
		int [] array = {3,9,4,1,7,6};
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite 1 se quiser ordenar por bubble sort \n Digite 2 se quiser ordenar por selecao \n Digite 3 se quiser ordenar por insercao");
		int escolha = sc.nextInt();
		if (escolha == 1) ordenacao (array);
		if (escolha == 2) ordenacao (array, 2);
		if (escolha == 3) ordenacao (array, "sim");
	}
}