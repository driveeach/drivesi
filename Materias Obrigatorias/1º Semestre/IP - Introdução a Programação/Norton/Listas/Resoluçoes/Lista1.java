/* Programa utilizado para fazer as listas de exercícios */
class Lista1 {
	static void um() {
	System.out.println ("Estou testanto o println");
}

	static void dois() {
	System.out.println ("Estou \ntestando \no \nprintln");
}

	static void três() {
	System.out.println (10+3*4);
	System.out.println ((10+3)*4);
	System.out.println (10+(3*4));
	System.out.println (23/4);
	System.out.println (4/23);
	System.out.println (23%4);
	System.out.println (4%23);
}

	static void quatro() {
	System.out.println (6*(2+3)/(4+5));
	System.out.println ((42/7)*5+6*3+4);
}

	static void cinco() {
		int a = 3;
		int b = 5;
		int c = 8;
		int d = a*(b+c*3) -7;
		int e = a-b-c;
		System.out.println (a+" "+b+" "+c+" "+d+" "+e);
		a = a+1;
		b = (4*a+1) / 10;
		c = (4*a+1) % 10;
		System.out.println (a+" "+b+" "+c+" "+d+" "+e);
}

	static void seis() {
		double real; // valor em real
		double conversao; // taxa de conversão;
		double dolar; // valor em dólar
		conversao = 1.8018;
		real = 2;
		System.out.println (dolar = real * conversao);
}
	
	public static void main(String[] args) {
	um();
	dois();
	três();
	quatro();
	cinco();
	seis();
	}
}