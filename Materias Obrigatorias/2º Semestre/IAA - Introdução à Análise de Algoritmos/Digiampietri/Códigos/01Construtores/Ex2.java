package construtores;

class Ex2 {
	int a;
	double d;
	String s;
	boolean b;

	Ex2(int i1, double d1,String s1, boolean b) {
		a=i1;
		d=d1;
		s=s1;
		this.b=b;
	}
	
	void imprime() {
		System.out.println("o inteiro vale "+a);
		System.out.println("o real vale "+d);
		System.out.println("a String vale "+s);
		System.out.println("o boolean vale "+b);
	}
	
	public static void main(String[]args) {
		// Ex2 e = new Ex2(); // ERRO não podemos mais usar o construtor padrão
		Ex2 obj1 = new Ex2(2,3.14,"Oi",true);
		Ex2 obj2 = new Ex2(1,1.0,"Tudo1",true);
		obj1.imprime();
		System.out.println();
		obj2.imprime();
	}
}

/* RESULTADO DA EXECUCAO DO PROGRAMA:
 
	o inteiro vale 2
	o real vale 3.14
	a String vale Oi
	o boolean vale true
	
	o inteiro vale 1
	o real vale 1.0
	a String vale Tudo1
	o boolean vale true

 */
