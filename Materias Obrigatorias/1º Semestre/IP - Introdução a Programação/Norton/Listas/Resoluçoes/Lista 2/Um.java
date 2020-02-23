class Um {
	public static void Um () {
		if (5 < 5) System.out.println("sim");
		System.out.println(" é a resposta.\n");
	}
	
	public static void Dois () {
	int var1 = 3;
	int var2 = 6;
	if ((var1 + 2) < var2) System.out.println("oba");
	if ((var1 + 4) < var2) System.out.println("abo");
	if ((var1 + 4) > var2) System.out.println("aob");
	System.out.println("ola\n");
	}
	
	public static void Três () {
	int a = 10;
	int b = 20;
	int c;
	if (a < b) c = a;
	else c = b;
	System.out.println(a+" "+b+" "+c);
	}
	
	public static void Quatro () {
	int a = 10;
	int b = 20;
	int c = (a < b) ? a : b;
	System.out.println(a+" "+b+" "+c);
	}
	
	public static void Cinco () {
	int a = 3;
	int b = 4;
	boolean continua = true && ((a + b) < (2 * a));
	if (continua) System.out.println("alto");
	else System.out.println("baixo");
	}
	
	public static void Seis () {
		int a = 5;
		int b = 3;
		int c = 9;
		int aux;
		
		if (a>b) {
		aux = a;
		a = b;
		b = aux;
		}

		if (b>c) {
		aux = b;
		b = c;
		c = aux;
		}
		
		if (a>b) {
		aux = a;
		a = b;
		b = aux;
		}
		System.out.println(c+" "+b+" "+a);
	}
	
	public static void main(String[] args) {
		Um ();
		Dois ();
		Três ();
		Quatro ();
		Cinco ();
		Seis ();
	}
}