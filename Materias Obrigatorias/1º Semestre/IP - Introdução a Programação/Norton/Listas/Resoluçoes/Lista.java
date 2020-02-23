class Lista {
	static void um() {
		System.out.print("Estou testando o print");
	}
	
	static void dois() {
		System.out.println("\nEstou \ntestando \no \nprintln");
	}
	
	static void três() {
		System.out.println(10 + 3 * 4);
		System.out.println((10 + 3) * 4);
		System.out.println(10 + (3 * 4));
		System.out.println(23 / 4);
		System.out.println(4 / 23);
		System.out.println(23 % 4);
		System.out.println(4 % 23);
	}
	
	static void quatro() {
	System.out.println(6*(2+3)/(4+5));
	System.out.println((42/7)*5+6*3+4);
	}
	
	static void cinco() {
		int a = 3;
		int b = 5;
		int c = 8;
		int d = a * (b + c * 3) - 7;
		int e = a - b - c;
		System.out.println(a+" "+b+" "+c+" "+d+" "+e);
		a = a + 1;
		b = (4 * a + 1) / 10;
		c = (4 * a + 1) % 10;
		System.out.println(a+" "+b+" "+c+" "+d+" "+e);
	}
	
	static void seis() {
	double real; // valor do real
	double taxa; // taxa de conversão real-dólar
	double dolar; // valor dolar
	real = 1;
	taxa = 1.8;
	dolar = real * taxa;
	System.out.println (dolar);
	}
	
	static void sete() {
	double altura0; // altura inicial
	double altura; // altura final
	double gravidade;
	double tempo; // tempo de queda
	altura = 0;
	gravidade = 10;
	altura0 = 9;
	tempo = Math.sqrt((2*(altura - altura0)) / -gravidade);
	tempo = (int) (tempo * 100);
	tempo = (double) (tempo / 100);
	System.out.println(tempo);
	}
	
	static void oito() {
	double a;
	double b;
	double c;
	double raiz1;
	double raiz2;
	a = 1;
	b = 2;
	c = 1;
	raiz1 = (-b + Math.sqrt((b*b) - (4*a*c))) / 2*a;
	raiz2 = (-b - Math.sqrt((b*b) - (4*a*c))) / 2*a;
	System.out.println("Raiz 1 igual a " +raiz1);
	System.out.println("Raiz 2 igual a " +raiz2);
	}
	
	static void nove() {
	int time;
	int jogador; // número do jogador
	int vermelho; // número de cartões vermelhos
	int amarelo; // número de cartões amarelos
	time = 2;
	jogador = 26;
	vermelho = 2;
	amarelo = 3;
	System.out.println("O jogador número " +jogador+" do time " +time+ " recebeu multa de R$" +((amarelo * 1000.20) + (vermelho * 4523.75)));
	}
	
	static void dez() {
	int a; // primeiro dígito
	int b; // segundo dígito
	int c; // terceiro dígito
	a = 2;
	b = 3;
	c = 4;
	System.out.println("O inverso do número " +a+ +b+ +c+ "é " +c+ +b+ +a);
	}

	public static void main(String[] args) {
		um();
		dois();
		três ();
		quatro();
		cinco();
		seis();
		sete(); 
		oito();
		nove();
		dez();
	}
}