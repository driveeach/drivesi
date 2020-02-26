package COO;

import java.io.Serializable;
import java.util.*;

class Palavra implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String palavra;
	private ArrayList <String> linhas;	
	private int quantidade;
	private String arquivo;
	
	Palavra() {		
		palavra = "";
		quantidade = 0;		
		linhas = new ArrayList <String>();
	}

	public void setPalavra(String p_palavra) {
		palavra = p_palavra;
	}
	
	public String getPalavra() {
		return palavra;
	}
	
	public void addLinha(String p_linha, int numLinha) {
		linhas.add(numLinha + ": " + p_linha);
	}
	
	public ArrayList<String> getLinhas() {
		return linhas;
	}
	
	public void addQuantidade() {
		quantidade++;
	}
	
	public int getQuantidade() {
		return quantidade;	
	}
	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

}

