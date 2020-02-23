package depositos;

/**
	Classe que define um depósito, retirada ou saldo.
*/
public class Deposito {
	/** Valor depositado */
    private double valor;
    
    /** Data do depósito */
    private Data data;

	/**
		Constrói um objeto de depósito
	*/
	public Deposito(double valor, Data data) {
        this.valor = valor;
        this.data = data;
	}
	
	/**
		@return Valor do depósito
	*/
	public double getValor() {
		return(this.valor);
	}
	
	/**
		@return Data do depósito
	*/
	public Data getData() {
		return(this.data);
	}
}
