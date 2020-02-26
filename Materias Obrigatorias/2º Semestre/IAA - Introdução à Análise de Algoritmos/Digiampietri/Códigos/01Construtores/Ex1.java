package construtores;

class Ex1{
	int a;
	double d;
	String s;
	boolean b;

	void imprime(){
		System.out.println("o inteiro vale " + a);
		System.out.println("o real vale " + d);
		System.out.println("a String vale " + s);
		System.out.println("o boolean vale " + b);
	}
	
	public static void main(String[] args)
	{
		/* Uso do construtor padrao criado automaticamente pelo Java, na
		 * ausencia de um construtor definido pelo programador. Note que as 
		 * variaveis globais apresentam valor padrao.
		 */
		Ex1 e = new Ex1();
		e.imprime();	
	}
}

/* RESULTADO DA EXECUCAO DO PROGRAMA:
	
	o inteiro vale 0
	o real vale 0.0
	a String vale null
	o boolean vale false
	
 */ 