public class Ornitorrinco implements Animal {

	double peso;

	Ornitorrinco(double p) {
		peso = p;
	}

	public double peso() {
		return peso;
	}

	public void nasca() {
		System.out.println("Quebra o ovo para sair");
	}

	public void passeiePelaTela() {
		System.out.println("Anda e nada de um lado para o outro");
	}

	public void durma() {
		System.out.println("Dorme dento de tuneis durante o dia");
	}
}