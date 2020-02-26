package compraVenda;

public class Comprador {

	Mediator mediator;
	String unidadeMoeda;

	public Comprador(Mediator mediator, String unidadeMoeda) {
		this.mediator = mediator;
		this.unidadeMoeda = unidadeMoeda;
	}

	public boolean tentativaCompra(float oferta) {
		System.out.println("Comprador tenta a oferta de " + oferta + " " + unidadeMoeda);
		return mediator.fazOferta(oferta, unidadeMoeda);
	}
}
