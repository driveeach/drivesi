public class ZoologicoVirtual {

	static public void cicloDeVida(Animal animal) {
		animal.nasca();
		animal.passeiePelaTela();
		animal.durma();
	}

	static public void main(String[] args) {
		Animal[] bicharada = new Animal[4];

		bicharada[0] = new Zebra(102, 99); 
		bicharada[1] = new Zebra(101, 107);
		bicharada[2] = new Morcego();
		bicharada[3] = new Ornitorrinco(25);

		for (int i = 0; i < bicharada.length; i++) {
			cicloDeVida(bicharada[i]);
		}

	}
}
