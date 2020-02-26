public class Fruta {
	double peso;
	double preco;
	String nome;

	public Fruta(String n, double v, double p) {
		nome = n;
		preco = v;
		peso = p;
	}

	void imprime() {
		System.out.println(nome + " pesa " + peso + " gramas e custa " + preco
				+ " reais");
	}
}