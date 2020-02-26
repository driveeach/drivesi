package hash;

class Tabela implements TipoTabela{
	private int[] tabela;
	int nroRegistros;

	Tabela(int tamanho){
		tabela = new int[tamanho];
		for (int i = 0; i < tamanho; i++){
			tabela[i] = -1;
		}
	}

	public int[] setTabela() {
		return tabela;
	}

	public long insere(int registro, Hash h, Rehash rh){
		if (tabela.length == nroRegistros) return -1;
		int j = 0;

		long tempo = System.nanoTime();

		int chave = h.hash(registro, tabela.length);
		while (tabela[chave] != -1){
			j++;
			chave = rh.rehash(chave, tabela.length, j);
		}
		if (tabela[chave] == -1) tabela[chave] = registro;

		tempo = System.nanoTime()-tempo;

		return tempo;
	}

	public long busca(int registro, Hash h, Rehash rh, boolean confirma){
		if (tabela.length == nroRegistros) return -1;
		int j = 0;

		long tempo = System.nanoTime();

		int chave = h.hash(registro, tabela.length);
		while (tabela[chave] != -1 && tabela[chave] != registro){
			j++;
			chave = rh.rehash(chave, tabela.length, j);
		}
		if (tabela[chave] == -1){
			confirma = false;
			return System.nanoTime() - tempo;            
		}

		tempo = System.nanoTime()-tempo;

		confirma = true;

		return tempo;
	}


	public long remove(int registro, Hash h, Rehash rh, boolean confirma){ 
		int j=1;
		
		long tempo = System.nanoTime();
		int chave = h.hash(registro, tabela.length);
		while (tabela[chave] != registro){
			chave = rh.rehash(chave, tabela.length, j);
			j++;
		}
		
		return System.nanoTime()-tempo;
	}
	
	public void removeColisao(int chave, Rehash rh, int j){
		int prox = rh.rehash(chave, tabela.length, j);
		j++;
		if (tabela[prox] == -1) tabela[chave] = -1; //eh o ultimo, apaga numa boa
		else{
			int temp = tabela[prox];
			removeColisao(tabela[prox], rh, j);
			tabela[chave] = temp;
		}
	}

}
