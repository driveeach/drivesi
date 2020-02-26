public class MochilaFracionaria {

	public static double[] mochilafracionaria(Item[] itens, double pesoTotal) {

		double[] x = new double[itens.length];

		for (int i = 0; i < itens.length && pesoTotal != 0; i++) {

			if (itens[i].getPeso() <= pesoTotal) {
				x[i] = 1;
				pesoTotal -= itens[i].getPeso();
			} else {
				x[i] = pesoTotal / itens[i].getPeso();
				pesoTotal = 0;
			}
		}
		return x;
	}

	public static void main(String[] args) {
		Item[] itens = new Item[] { new Item(40, 840), new Item(30, 600),
				new Item(20, 400), new Item(10, 100) };

		double[] x = mochilafracionaria(itens, 50);

		double peso = 0;
		double valor = 0;
		for (int i = 0; i < x.length; i++) {
			double p = itens[i].getPeso();
			double v = itens[i].getValor();
			String format = "%7.2f\t%7.2f\t%7.2f\t%7.2f\t%7.2f\n";
			System.out.printf(format, x[i], p, v, x[i] * p, x[i] * v);
			peso += x[i] * itens[i].getPeso();
			valor += x[i] * itens[i].getValor();
		}
		
		System.out.printf("\t\t\t%7.2f\t%7.2f\n", peso, valor);
	}
}

class Item {
	private double peso;
	private double valor;

	public Item(double peso, double valor) {
		super();
		this.peso = peso;
		this.valor = valor;
	}

	public double getPeso() {
		return peso;
	}

	public double getValor() {
		return valor;
	}
}