package heranca2;

public class C3 extends C2 implements I1{

	// C3 não precisa ter construtor pois o construtor de C2 não tem parâmetros
	// então será chamado automaticamente pelo construtor padrão de C2
	
	// Note que o método imprimir existe em C2 e em I1
	// Por existir em I1 é necessário reimplementá-lo em C3 (não pode simplesmente herdá-lo)
	public void imprimir(){
		System.out.println("Imprimir C3");
	}
	
	void imprimirAlternativo(){
		super.imprimir();
		// super.super.imprimir();  NÃO FUNCIONA não podemos usar "super.super"
	}
	
	void imprimirAlternativo2(){
		((C1)this).imprimir(); // vai executar o imprimir de C3 (e não de C1) é a mesma coisa de "imprimir();"
	}

	// as implementações de métodos de interface precisam ser públicas
	public void metodoInterfaceI1() {
		System.out.println("Imprimindo valor da constante de I1: " + I1.constanteI1);
	}
	
}
