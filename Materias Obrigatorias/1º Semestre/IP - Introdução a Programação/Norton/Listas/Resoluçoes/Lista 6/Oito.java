/* Voce foi contratado por uma agencia de viagens para escrever uma classe em Java para
calcular a conversao de reais para dolar de acordo com a taxa de compra e a taxa
de venda. Para isso, escreva uma classe ConversorMonetario que inclua os seguintes
metodos:
defineTaxaCompra()
defineTaxaVenda()
imprimeTaxas(), que imprime o valor das 2 taxas de conversao;
vendeDolar(), que recebe uma quantia em dolares e devolve o valor correspondente
em reais
compraDolar(), que recebe uma quantia em dolares e devolve o valor correspondente
em reais
Em seguida, escreva uma classe TestaConversorMonetario que define diferentes taxas de
compra e venda de dolares e, para cada taxa de conversao, realiza operacoes de compra
e venda.*/
class ConversorMonetario {
	double taxaCompra;
	double taxaVenda;
	void defineTaxaCompra(double taxaCompra) {
		this.taxaCompra = taxaCompra;
	}
	void defineTaxaVenda (double taxaVenda) {
		this.taxaVenda = taxaVenda;
	}
	void imprimeTaxas () {
		System.out.println (taxaCompra);
		System.out.println (taxaVenda);
	}
	void vendeDolar (double valorDolar) {
		double reais;
		reais = valorDolar * taxaVenda;
		System.out.println ("$" +valorDolar+ " equivale a R$" +reais);
	}
	void compraDolar (double valorDolar) {
		double reais;
		reais = valorDolar * taxaCompra;
		System.out.println ("$" +valorDolar+ " equivale a R$" +reais);
	}
}
class TestaConversorMonetario {
	void teste () {
		ConversorMonetario a = new ConversorMonetario ();
		for (int i = 0; i< 10; i++) {
			a.defineTaxaCompra(i);
			a.defineTaxaVenda(i);
			a.imprimeTaxas(\nn);
			a.vendeDolar(i);
			a.compraDolar(i);
		}
	}
}
class Oito {
	public static void main (String [] args) {
		TestaConversorMonetario b = new TestaConversorMonetario();
		b.teste ();
	}
}