public class Zebra implements Animal {

	double peso;
	int listas;

	public Zebra(int listas, double peso) {
		this.listas = listas;
		this.peso = peso;
	}

	public double peso() {
		return peso;
	}

	public void nasca() {
		System.out.println("Nasce mais uma zebra");
	}

	public void passeiePelaTela() {
		System.out.println("Galopa pelo campo");
	}

	public void durma() {
		System.out.println("Dorme em pé");
	}

	public void contaListas() {
		System.out.println("Esta zebra tem " + listas + " listas ");
	}

}