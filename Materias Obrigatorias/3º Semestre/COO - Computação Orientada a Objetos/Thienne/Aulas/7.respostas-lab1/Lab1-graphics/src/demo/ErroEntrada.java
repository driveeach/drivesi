package demo;

class ErroEntrada extends Exception {
	
	public ErroEntrada() {
		super("Excecao - Dados de Entrada Invalidos");
	}

}

class ValorZero extends Exception {
	
	public ValorZero() {
		super("Exceção - Valor de entrada Zero");
	}

}

class NumeroNegativo extends Exception {
	
	public NumeroNegativo() {
		super("Exceção - Numero Negativo");
	}

}

