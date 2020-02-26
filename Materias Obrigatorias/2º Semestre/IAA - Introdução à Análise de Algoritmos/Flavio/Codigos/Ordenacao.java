public class Ordenacao {

	public static void selectionSort(int [] a){

		// ao final de cada iteração do for 
		// externo o (i+1)-ésimo menor elemento 
		// do vetor estará em a[i].

		for(int i = 0; i < a.length - 1; i++){

			// o for interno faz a verredura dos valores do vetor
			// entre as posições i e (a.length -1), de modo a 
			// deterinar o menor valor dentro deste intervalo

			int menor = i;

			for(int j = i + 1; j < a.length; j++){

				if(a[j] < a[menor]) menor = j;
			}

			// o menor valor encontrado, que encontra-se
			// em a[menor], é colocado em a[i]

			int temp = a[i];
			a[i] = a[menor];
			a[menor] = temp;
		}
	}

	public static void insertionSort(int [] a){

		// ao final de cada iteração do for externo
		// o subvetor a[0 .. i] será um rearranjo
		// ordenado dos (i+1) primeiros elementos do
		// vetor original

		for(int i = 1; i < a.length; i++){

			// a[0 .. i-1] já está ordenado.
			// a[i] será inserido no local 
			// adequado de modo que a[0 .. i] 
			// também fique ordenado

			int j;
			int x = a[i];

			// o for interno faz a verradura dos valores
			// do vetor entre as posições (i - 1) e 0, a fim
			// de determinar em que posição do vetor x deve ser 
			// colocado. Ao mesmo tempo, o for também move os 
			// elementos maiores que x uma posição para a direita 
			// no vetor

			for(j = i - 1; j >= 0 && x < a[j]; j--){

				a[j + 1] = a[j];
			}
	
			a[j + 1] = x;
		}
	}

	public static void main(String [] args){

		int [] a = { 7, 2, 3, 1, 4, 9, 8 };
		int [] b = { 7, 2, 3, 1, 4, 9, 8, 5, 0 };

		selectionSort(a);	
		insertionSort(b);
	
		System.out.print("Vetor a ordenado:");
		for(int x : a) System.out.print(" " + x);
		System.out.println();

		System.out.print("Vetor b ordenado:");
		for(int x : b) System.out.print(" " + x);
		System.out.println();
	}
}
