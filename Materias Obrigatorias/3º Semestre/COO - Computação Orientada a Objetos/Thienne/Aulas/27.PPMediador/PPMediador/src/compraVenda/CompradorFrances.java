package compraVenda;

public class CompradorFrances extends Comprador {

	public CompradorFrances(Mediator mediator) {
		super(mediator, "euros");
		this.mediator.registreFrancesComprador(this);
	}
}
