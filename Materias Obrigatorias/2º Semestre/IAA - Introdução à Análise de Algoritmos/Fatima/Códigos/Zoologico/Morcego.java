public class Morcego implements Animal { 
	
	// implements -> obriga o programador escrever o codigo
	// de todos os metodos com as assinaturas definidas na
	// interface. Todos os metodos devem ser publicos.

	public void nasca() {
		System.out.println("Nasce um lindo morcego");
	}

	public void passeiePelaTela() {
		System.out.println("Voa de um lado para o outro");
	}

	public void durma() {
		System.out.println("Dorme de ponta cabeca");
	}

	public double peso() {
		return 4.5;
	}
}