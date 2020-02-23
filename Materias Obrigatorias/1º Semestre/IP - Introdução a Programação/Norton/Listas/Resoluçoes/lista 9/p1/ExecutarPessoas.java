package p1;

public class ExecutarPessoas extends Aluno {
	public static void main(String[] args) {
		Pessoa p1 = new Pessoa ();
		Pessoa p2 = new Aluno ();
		Aluno a1 = new Aluno ();
		Aluno a2;
		System.out.println("Contador A1: " + a1.contador);
		System.out.println("Contador Pessoa: " + Pessoa.contador);
		// ERRO a2 = new Pessoa();
		// ERRO p2.estudar();
		p2.comer();
		p2.caminhar();
		// ERRO p2.correr();
	}
}
