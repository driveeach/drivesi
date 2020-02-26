package compraVenda;

public class CompradorBrasileiro extends Comprador {

	public CompradorBrasileiro(Mediator mediator) {
		super(mediator, "reais");
		this.mediator.registreBrasileiroComprador(this);
	}
}
