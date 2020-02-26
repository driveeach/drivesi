package construtores;

class Conta {
	String titular;
	double saldo;
	String RG;
	
	Conta(String s, double val){
		titular = s;
		saldo = val;
	}

	/* Construtor que recebe apenas um parametro e chama o construtor anterior,
	 * passando 0 (zero) como segundo parametro.
	 */
	Conta(String s){
		this(s,0);
	}

	/* Este construtor produz o mesmo resultado do anterior, porem os dois nao 
	 * podem ser usados concomitantemente pois os dois recebem apenas um 
	 * parametro que eh uma String (LEMBRE-SE nao eh possivel, numa mesma 
	 * classe a existencia de duas funcoes ou dois construtores que recebam
	 * exatamente os mesmos tipos de parametros e na mesma ordem).
	 */
	/*
	Conta(String s){
		titular = s;
		saldo = 0;
	}
	*/
	
	Conta(String s, double val, String RG){
		titular = s;
		saldo = val;
		this.RG = RG;
	}
	
	void imprime(){
		System.out.println("O cliente: "+ titular + " tem saldo " + saldo);
	}
	
	public static void main(String[]args){
		Conta c1 = new Conta("José",100);
		Conta c2 = new Conta("Maria",1000,"33333333-3");
		Conta c3 = new Conta("Carla");
		c1.imprime();
		c2.imprime();
		c3.imprime();
	}
}

/* RESULTADO DA EXECUCAO DO PROGRAMA:

O cliente: José tem saldo 100.0
O cliente: Maria tem saldo 1000.0
O cliente: Carla tem saldo 0.0

*/ 