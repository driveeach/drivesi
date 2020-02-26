package recursao;

public class MatRecursivoEIterativo {
	
	/**
	 * Este metodo recebe um numero inteiro n e soma os n inteiro de 1 ateh n.
	 * @param n: numero de inteiros que serao somados
	 * @return soma dos n inteiros de  1 ateh n
	 */
	// VERSAO RECURSIVA
	static int somaDosNInteirosRec(int n){
		if (n<=0) return 0;     // utilizei <= ao inves de == apenas para evitar n negativo
		else return n+somaDosNInteirosRec(n-1);
	}
	// VERSAO ITERATIVA
	static int somaDosNInteirosIter(int n){
		int resultado = 0;
		for (int i=n; i>0;i--) resultado += i;
		return resultado;
	}

	/**
	 * Este metodo recebe um numero inteiro n e retorna o n-esimo numero de Fibonacci.
	 * @param n: numero de Fibonacci desejado (n-esimo)
	 * @return n-esimo numero de Fibonacci
	 */
	// VERSAO RECURSIVA
	static int fibonacciRec(int n){
		if (n<=0) return 0;     // utilizei <= ao inves de == apenas para evitar n negativo
		if (n==1) return 1;
		else return fibonacciRec(n-2)+fibonacciRec(n-1);
	}
	// VERSAO ITERATIVA
	static int fibonacciIter(int n){
		if (n<=0) return 0;
		int resultado = 1;
		int temp_2 = 0;
		int temp_1 = 1;
		for (int i=1; i<n;i++) {
			resultado += temp_2;
			temp_2=temp_1;
			temp_1=resultado;
		}
		return resultado;
	}

	
}
