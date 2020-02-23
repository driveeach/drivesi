class A {
	double a(int meses,double taxa) {
		return Math.pow((taxa + 100) / 100, meses) -1;
	}
}

class B {
	final double TAXA = 1.2;
	void b(double valorEmprestado, int meses) {
		A a = new A();
		double valorDaDivida = valorEmprestado + (a.a(meses, TAXA) *
		valorEmprestado);
		System.out.println("Divida de "+valorDaDivida+" real(is)," +
		"calculada com taxa de "+TAXA+"% ao m^es.");
	}
}

class Um {
	public static void main (String [] args) {
		B b = new B();
		b.b(10,2);
	}
}