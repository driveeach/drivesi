package heranca;

// A classe Aluno é abstrata (não pode ser instanciada)
abstract class Aluno extends Pessoa {
	String nome;
	int RG;
	int numeroUSP;
	
	/* construtores não podem ser final */
	Aluno(String n, int rg, int nUSP){
		super(n,rg);
		numeroUSP = nUSP;
	}
	
	void respirar(){
		System.out.println("Aluno respirando.");
	}

	final void fazerTrabalhos(){
		System.out.println("Trabalhando.");
	}
	
	// Método abstrato, precisa ser implementado nas sub-classes não abstratas
	// um método não pode ser abstrato e estático ao mesmo tempo
	abstract void estudar();
}
