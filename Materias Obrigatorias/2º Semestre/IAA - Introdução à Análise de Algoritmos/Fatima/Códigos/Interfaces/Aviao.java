public class Aviao implements Voador, TransportadorDePessoas {
	
	public void voa() {
		System.out.println("Liga a turbina; recolhe o trem de pouso");
	}

	public void aterrissa() {
		System.out.println("Abaixo o trem de pouso e desce");
	}

	public void entramPessoas() {
		System.out.println("Procedimento de embarque");
	}

	public void saemPessoas() {
		System.out.println("Procedimento de desembarque");
	}
}