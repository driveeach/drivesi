// Interfaces: Util para implementar o conceito de abstracao/encapsulamento.
// Abstracao: informacao mais operacoes para representar um conceito --> Tipos Abstratos de Dados (TAD).
// Encapsulamento: atributos da classe e implementacao dos metodos nao sao visiveis para o
// usuario da classe.

// Conhecendo apenas a interface da classe, i.e., seus metodos, pode-se utiliza-la sem
// conhecer seus detalhes de implementacao.

interface Animal {
	void nasca();

	void passeiePelaTela();

	void durma();

	double peso();
}
