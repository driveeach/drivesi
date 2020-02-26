package compraVenda;

public class Mediator {

	Comprador brasileiroComprador;
	Comprador francesComprador;
	VendedorAmericano americanoVendedor;
	ConversorDolar conversorDolar;

	public Mediator() {
	}

	public void registreBrasileiroComprador(CompradorBrasileiro brasileiroComprador) {
		this.brasileiroComprador = brasileiroComprador;
	}

	public void registreFrancesComprador(CompradorFrances francesComprador) {
		this.francesComprador = francesComprador;
	}

	public void registreAmericanoVendedor(VendedorAmericano americanoVendedor) {
		this.americanoVendedor = americanoVendedor;
	}

	public void registreConversorDolar(ConversorDolar conversorDolar) {
		this.conversorDolar = conversorDolar;
	}

	public boolean fazOferta(float oferta, String unidadeMoeda) {
		float valorEmDolar = conversorDolar.converteMoedaParaDolar(oferta, unidadeMoeda);
		return americanoVendedor.foiAceitaOferta(valorEmDolar);
	}
}
