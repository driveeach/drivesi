package heranca;

class Exemplo1Super {
	public int a;
	protected int b;
	int c;
	private int d;

	Exemplo1Super(int w, int x, int y, int z) {
		a = w;
		b = x;
		c = y;
		d = z;  
	}
	
	void imprime() {
		System.out.println("Metodo imprime da super-class Exemplo1Super.");
		System.out.println("A = " + a);
		System.out.println("B = " + b);
		System.out.println("C = " + c);
		System.out.println("D = " + d);
	}

	public int getA() {
		return a;
	}

	protected int getB() {
		return b;
	}

	int getC() {
		return c;
	}

	private int getD() {
		return d;
	}
	
}