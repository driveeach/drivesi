package construtores;

class ContaSemConstrutor {
	String titular;
	double saldo;
	
	void imprime(){
		System.out.println("O cliente: "+ titular + " tem saldo " + saldo);
	}
	
	public static void main(String[]args){
		ContaSemConstrutor c1 = new ContaSemConstrutor();
		c1.imprime();
		c1.titular = "Joao";
		c1.saldo = 250;
		c1.imprime();
	}
}


/* RESULTADO DA EXECUCAO DO PROGRAMA:

O cliente: null tem saldo 0.0
O cliente: Joao tem saldo 250.0
	
 */ 