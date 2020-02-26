package compraVenda;

public class VendedorAmericano {

	Mediator mediator;
	float precoEmDolar;

	public VendedorAmericano(Mediator mediator, float precoEmDolar) {
		this.mediator = mediator;
		this.precoEmDolar = precoEmDolar;
		this.mediator.registreAmericanoVendedor(this);
	}

	public boolean foiAceitaOferta(float ofertaEmDolar) {
		if (ofertaEmDolar >= precoEmDolar) {
			System.out.println("Vendedor aceita a oferta de " + ofertaEmDolar + " dolares\n");
			return true;
		} else {
			System.out.println("Vendedor rejeita a oferta de " + ofertaEmDolar + " dolares\n");
			return false;
		}
	}

}
