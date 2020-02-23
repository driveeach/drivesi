/* Defina uma classe Complexo e escreva metodos para efetuar a soma, subtracao, multiplicacao e 
divisao de numeros complexos. Escreva metodos tambem que, dado um
complexo, retornem seu conjugado e seu modulo.*/
class Complexo {
	int real1;
	int imag1;
	int real2;
	int imag2;
	
	void soma () {
		int rReal;
		int rImag;
		rReal = real1 + real2;
		rImag = imag1 + imag2;
		System.out.println ("A soma e igual a " +rReal+ " + " +rImag+ "i.");
	}
	void subtracao () {
		int rReal;
		int rImag;
		rReal = real1 - real2;
		rImag = imag1 - imag2;
		System.out.println ("A subtracao e igual a " +rReal+ " - " +rImag+ "i.");
	}
	void multiplicacao () {
		int rReal;
		int rImag;
		rReal = (real1 * real2) + (imag1 * imag2);
		rImag = (real1 * imag2) + (imag2 * real2);
		System.out.println ("A multiplicacao e igual a " +rReal+ " + " +rImag+ "i.");
	}
	void divisao () {
		int rReal;
		int rImag;
		rReal = ((real1 * real2) + (imag1 * imag2)) / ((real2*real2) + (imag2*imag2));
		rImag = ((real2 * imag1) - (real1 * imag2)) / ((real2*real2) + (imag2*imag2));
		System.out.println ("A divisao e igual a " +rReal+ " + " +rImag+ "i.");
	}
	void conjugado (int real, int imag) {
		imag *= -1;
		if (imag > 0) System.out.println ("O conjugado é " +real+ " + " +imag+ " i.");
		else System.out.println ("O conjugado é " +real+ " " + imag+ " i.");
	}
	void modulo (int real, int imag) {
		double modulo;
		modulo = Math.sqrt ((real*real) + (imag*imag));
		System.out.println (modulo);	
	}
}
class Sete {
	public static void main (String [] args) {
		Complexo a = new Complexo();
		a.real1 = 1;
		a.imag1 = 2;
		a.real2 = 1;
		a.imag2 = 2;
		a.soma();
		a.subtracao();
		a.multiplicacao();
		a.divisao();
		a.conjugado(3,4);
		a.modulo(3,4);
	}
}