package recursao;

public class MatExecutavel {

	/**
	 * Esta classe testa a clase {@link MatRecursivoEIterativo}.
	 * @see MatRecursivoEIterativo
	 */
	public static void main(String[] args) {
		System.out.println("O soma dos 12 primeiros inteiros eh: (recursivo) " + MatRecursivoEIterativo.somaDosNInteirosRec(12)+ "    (iterativo) " + MatRecursivoEIterativo.somaDosNInteirosIter(12));
		System.out.println("O 12o numero de Fibonacci eh: (recursivo) " + MatRecursivoEIterativo.fibonacciRec(12)+ "    (iterativo) " + MatRecursivoEIterativo.fibonacciIter(12));
	}

}
