package Simulador;

public class Processo {

	private String nome;
	private int tempo, prioridade;
	
	public Processo(String nome, int tempo, int prioridade) {
		this.nome = nome;
		this.tempo = tempo;
		this.prioridade = prioridade;
	}

	public String getNome() {
		return nome;
	}

	public int getTempo() {
		return tempo;
	}

	public int getPrioridade() {
		return prioridade;
	}	
}
