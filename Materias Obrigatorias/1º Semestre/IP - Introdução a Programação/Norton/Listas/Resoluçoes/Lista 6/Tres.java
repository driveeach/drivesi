/*Escreva uma classe Simples contendo um metodo que recebe um array de inteiros como
parametro e que inicializa todos os elementos do array com um valor, tambem dado
como parametro. O metodo deve devolver o tamanho do array. A assinatura do metodo
deve ser a seguinte:
int inicializaArray ( int [] a , int v );
Escreva agora um metodo que recebe um array de inteiros como par^ametro e imprime o
seu conteudo:
void imprimeArray ( int[] a ) ;
Crie agora um metodo que, dado um inteiro, verifica se ele esta presente no array.
boolean estaNoArray ( int[] a , int v ) ;
Finalmente, escreva um programa que cria um array, cria um objeto Simples e chama
os seus tres metodos.*/
class Simples {
	int inicializaArray (int [] array, int valor) {
		for (int i = 0; i<array.length; i++) {
			array[i] = valor;
		}
		return array.length;
	}
	void imprimeArray (int[] array) {
		for (int c : array) System.out.print (c + " ");
		System.out.println ();
	}
	boolean estaNoArray (int[] array, int valor) {
		for (int i = 0; i<array.length; i++) {
			if (i == valor) return true;
		}
		return false;
	}
}
class Tres {
	public static void main (String [] args){
		int [] array = {1,2,3,4,6};
		Simples a = new Simples ();
		System.out.println (a.inicializaArray (array, 6));
		a.imprimeArray (array);
		System.out.println (a.estaNoArray (array, 5));
	}
}