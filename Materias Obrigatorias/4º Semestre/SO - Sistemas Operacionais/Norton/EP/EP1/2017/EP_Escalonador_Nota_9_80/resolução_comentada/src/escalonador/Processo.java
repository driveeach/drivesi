package escalonador;

/* Classe que representa um Processo
 * Um processo possui um nome, uma lista de instruções para serem executadas e um estado 
 */

public class Processo {
	static final char PRONTO = 'p';
	static final char EXECUTANDO = 'e';
	static final char BLOQUEADO = 'b';
	final String [] instrucao; 
	String nome;
	private char estado;
	
	public Processo(String nome, String[] instrucao, char estado) {
		this.nome = nome;
		this.setEstado(estado);
		this.instrucao = instrucao;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
}