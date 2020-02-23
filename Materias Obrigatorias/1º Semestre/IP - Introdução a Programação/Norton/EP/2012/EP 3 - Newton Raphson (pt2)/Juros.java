import lista.ListaDepositos;
import depositos.Deposito;

/*
	VOCÊ PODE IMPORTAR AS CLASSES QUE DESEJAR AQUI
*/

class Juros {
	
	/*
		VOCÊ PODE INCLUIR OS MÉTODOS E ATRIBUTOS QUE QUISER, DESDE QUE OS JUROS SEJAM CALCULADOS COM UMA ÚNICA CHAMADA A "juros".
	*/
	
	/**
		Calcula os juros pagos, a partir de uma seqüência de depósitos e um saldo final, com a precisão indicada. Usa o método de Newton-Raphson para tal.
		
		@param depositos Lista de depósitos (não incluindo o saldo final)
		@param saldo Saldo final da aplicação
		@param epsilon Precisão do cálculo (0 < epsilon < 1)
		
		@return Valor dos juros, ou NAN em caso de erro (epsilon fora dos limites, lista de depósitos sem depósitos, saldo null)
	*/
	public static double juros(ListaDepositos depositos, Deposito saldo, double epsilon) {
		// SEU CÓDIGO DEVE VIR AQUI
		return(0); // essa linha existe apenas para compilar
	}
	
	public static void main(String[] args) {
		// para seus testes
	}
}

