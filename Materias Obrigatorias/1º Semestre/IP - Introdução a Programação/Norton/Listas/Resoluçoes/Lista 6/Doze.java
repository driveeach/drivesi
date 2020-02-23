/*Escreva uma classe MediaAlunoDisciplina que tenha os atributos numero do aluno, nome
da disciplina, peso de trabalhos, peso de provas, media de trabalhos, media de provas,
media ponderada, com as seguintes especificacoes:
o numero do aluno, o nome da disciplina devem ser fornecidos no momento da
criacao do objeto;
os pesos de provas e trabalhos sao variaveis (atributos) da classe. A soma desses
atributos sempre devera resultar no valor 10;
o nome da disciplina tambem é uma variavel da classe;
Crie os seguintes metodos (faca as consideracoes necessarias sobre tipos de atributos,
metodos e especificadores de acesso):
calculaMediaPonderada() { calcula a media do aluno, multiplicando as medidas de
trabalhos e provas pelos seus respectivos pesos e altera o atributo que armazena a
media ponderada. Este atributo nunca podera ser maior que 10;
imprimeDados() { imprime todos os atributos; */
class MediaAlunoDisciplina {
	int numeroAluno;
	String nomeDisciplina;
	double pesoTrabalho;
	double pesoProva;
	double mediaTrabalho;
	double mediaProva;
	double mediaPonderada;
	MediaAlunoDisciplina (int numeroAluno, String nomeDisciplina){
		this.numeroAluno = numeroAluno;
		this.nomeDisciplina = nomeDisciplina;
	}
	double calculaMediaPonderada (double pesoTrabalho, double pesoProva, double mediaTrabalho, double mediaProva) {
		this.pesoTrabalho = pesoTrabalho;
		this.pesoProva = pesoProva;
		this.mediaTrabalho = mediaTrabalho;
		this.mediaProva = mediaProva;
		if (pesoTrabalho + pesoProva != 10) return -1;
		mediaPonderada = ((pesoTrabalho * mediaTrabalho) + (pesoProva * mediaProva)) / (pesoTrabalho + pesoProva);
		if (mediaPonderada <= 10) return mediaPonderada;
		else return -1;
	}
	void imprimeDados () {
		System.out.println ("O aluno é o número: " +numeroAluno);
		System.out.println ("A disciplina é: " + nomeDisciplina);
		System.out.println ("O trabalho tem peso: " +pesoTrabalho);
		System.out.println ("A prova tem peso: " +pesoProva);
		System.out.println ("A media do trabalho é: " +mediaTrabalho);
		System.out.println ("A media da prova é: " +mediaProva);
		System.out.println ("A media ponderada é: " +mediaPonderada);
	}
}
class Doze {
	public static void main (String [] args) {
		MediaAlunoDisciplina a = new MediaAlunoDisciplina (1, "matematica");
		a.calculaMediaPonderada(7,3,8,9);
		a.imprimeDados();
	}
}