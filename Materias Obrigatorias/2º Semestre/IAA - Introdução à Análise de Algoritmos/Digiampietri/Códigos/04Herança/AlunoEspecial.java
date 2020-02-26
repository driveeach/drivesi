package heranca;

/* a classe AlunoEspecial nao pode estender Aluno e Digitador pois, em Java,
 * cada classe só pode extender uma classe.  */
public final class AlunoEspecial extends Aluno{
	/* Já que aluno não tem construtor sem parâmetros é necessário criar um construtor aqui */
	AlunoEspecial(String nome, int RG, int NUSP){
		super(nome,RG,NUSP);
	}
	
	void respirar(){
		super.respirar(); // NAO é possível usar, super.super.respirar (super.super não é permitido) 
		System.out.println("Aluno especial respirando.");
	}

	/*  Este metodo não pode ser implementado aqui por ser final na super-clase (Aluno)
	void fazerTrabalhos(){
		...
	}
	*/
	
	
	// PRECISA implementar todos os métodos abstratos da super-classe
	void estudar(){
		System.out.println("Aluno Especial Estudando");
	}
	
	
}
