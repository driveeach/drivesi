package heranca;

public class AlunoRegular extends Aluno{
	/* Já que aluno não tem construtor sem parâmetros é necessário criar um construtor aqui */
	AlunoRegular(String nome, int RG, int NUSP){
		super(nome,RG,NUSP);
	}
	
	void estudarMuito(){
		System.out.println("Estudando muito!");
	}
	
	// PRECISA implementar todos os métodos abstratos da super-classe
	void estudar(){
		System.out.println("Aluno Regular Estudando");
	}
}
