public class ManipulaInteiro {

	public int numDigitos(int n) {
		if (n < 10) {
			return 1;
		} else {
			return numDigitos(n / 10) + 1;
		}
	}

	public int inverte(int n) {
		return inverte(n, potencia(n));
	}

	private int potencia(int n) {
		if (n < 10) {
			return 1;
		} else {
			int valor = 10 * potencia(n / 10);
			return valor;
		}
	}

	private int inverte(int n, int potencia) {
		if (n == 0) {
			return 0;
		} else {
			return (n % 10 * potencia) + inverte(n / 10, potencia / 10);
		}

	}

	public Inverso inverte2(int n) {
		if (n < 10) {
			return new Inverso(n, 10);
		} else {
			Inverso tmp = inverte2(n / 10);
			int num = tmp.getNum();
			int potencia = tmp.getPotencia();
			return new Inverso(n % 10 * potencia + num, potencia * 10);
		}
	}

	public static void main(String[] args) {
		System.out.println(new ManipulaInteiro().inverte(123456789));
		System.out.println(new ManipulaInteiro().inverte2(123456789).getNum());
	}
}

class Inverso {
	private int num;
	private int potencia;

	public Inverso(int num, int potencia) {
		this.num = num;
		this.potencia = potencia;
	}

	public int getNum() {
		return num;
	}

	public int getPotencia() {
		return potencia;
	}

}