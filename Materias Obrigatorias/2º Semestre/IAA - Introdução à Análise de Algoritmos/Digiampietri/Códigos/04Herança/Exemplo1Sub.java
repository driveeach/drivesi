package heranca;

class Exemplo1Sub extends Exemplo1Super{
	
	int e;
	
	public Exemplo1Sub(int w, int x, int y, int z, int e) {
		super(w, x, y, z);
		this.e = e;
	}

	int getE(){
		return e;
	}

	void acessaCampos() {
		System.out.println("Metodo acessaCampos da sub-classe Exemplo1Sub.");
		System.out.println("A = " + getA());
		System.out.println("B = " + getB());
		System.out.println("C = " + getC());
		// System.out.println("D = " + getD()); // NAO FUNCIONA pois getD() eh private
		System.out.println("E = " + getE());
	}
	
	void imprime(){
		System.out.println("Metodo imprime da sub-classe Exemplo1Sub.");
		super.imprime();
		System.out.println("E = " + getE());
	}

	void novoImprime(){
		System.out.println("Metodo novoImprime da sub-classe Exemplo1Sub.");
	}
	
}