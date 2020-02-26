package compraVenda;

public class Aplicativo {

	public static void main(String[] args) {

		Mediator mediator = new Mediator();

		Comprador brasileiroComprador = new CompradorBrasileiro(mediator);
		Comprador francesComprador = new CompradorFrances(mediator);
		float precoVendaEmDolar = 10.0f;
		VendedorAmericano americanoVendedor = new VendedorAmericano(mediator, precoVendaEmDolar);
		ConversorDolar conversorDolar = new ConversorDolar(mediator);

		float brasileiroOfertaEmReal = 12.0f;
		while (!brasileiroComprador.tentativaCompra(brasileiroOfertaEmReal)) {
			brasileiroOfertaEmReal += 3.0f;
		}

		float francesOfertaEmEuros = 3.0f;
		while (!francesComprador.tentativaCompra(francesOfertaEmEuros)) {
			francesOfertaEmEuros += 1.5f;
		}

	}

}
