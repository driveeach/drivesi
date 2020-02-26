package hash;

import java.util.LinkedList;

public class TabelaEncadeada implements TipoTabela{

	private LinkedList<Integer> [] tabela;
	int tamanho;
	
	TabelaEncadeada(int tamanho){		
		tabela = new LinkedList[tamanho];
		this.tamanho = tamanho;
		for (int i = 0; i < tamanho; i++){
			tabela[i] = null;
		}
	}
	
	
	public long insere(int registro, Hash h, Rehash rh) {
		
		long tempo = System.nanoTime();
		
		int chave = h.hash(registro, tamanho);
		if(tabela[chave] == null){
			tabela[chave] = new LinkedList<Integer>();
			tabela[chave].addLast(registro);
		}
		else
			tabela[chave].addFirst(registro);
		
		tempo = System.nanoTime() - tempo;
		
		return tempo;
	}

	
	public long busca(int registro, Hash h, Rehash rh, boolean confirma) {
		long tempo = System.nanoTime();
		
		int chave = h.hash(registro, tamanho);
		
		if(tabela[chave] == null){
			confirma = false;
		}
		else
			confirma = tabela[chave].contains(registro);
		
		tempo = System.nanoTime() - tempo;
		
		return tempo;
	}

	
	public long remove(int registro, Hash h, Rehash rh, boolean confirma) {
		long tempo = System.nanoTime();
		
		int chave = h.hash(registro, tamanho);
		
		tabela[chave].removeFirstOccurrence(registro);
		
		tempo = System.nanoTime() - tempo;
		
		return tempo;
	}

	
	
}
