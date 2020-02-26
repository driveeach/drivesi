class HashEncadeado {
	/* Limite do hash */
	private int limite = 0;
	/* A tabela de hash */
	ListaLigada[] tab = null;
	
	HashEncadeado(int limite) {
		if (limite>0) {
			this.limite = limite;
			this.tab = new ListaLigada[limite];
			for (int i=0; i<limite; i++) this.tab[i] = new ListaLigada();
		}
	}
	
	/* Função de hash para chaves inteiras */
	private int hash(int chave) {
		return(chave%limite);
	}
	
	/* Inclusão de elemento no hash */
	void incluiElemento(int chave, String valor) {
		// descubro a posição no hash onde o elemento será colocado
		int pos = this.hash(chave);
		// incluo o elemento na lista ligada desta posição
		this.tab[pos].adiciona(chave,valor);
	}
	
	/* Busca de elemento no hash. True = está lá, False = não está*/
	boolean busca(int chave) {
		// vejo em qual lista buscar
		int pos = this.hash(chave);
		// vejo se está na lista correspondente
		return(this.tab[pos].buscaElemento(chave) != -1);
	}
	
	/* Eliminação de elemento do hash */
	void eliminaElemento(int chave) {
		// vejo em qual lista estaria o elemento
		int pos = this.hash(chave);
		// elimino
		this.tab[pos].removeElemento(chave);
	}	
	
	/* Imprime hash */
	void imprimeHash() {
		for (int i=0; i<this.limite; i++) {
			System.out.print("tab["+i+"] = ");
			this.tab[i].imprimeLista();
		}
	}

	/* Para testes */
	public static void main(String[] args) {
		HashEncadeado h = new HashEncadeado(5);

		// incluo elementos
		h.incluiElemento(10, "Oba");
		h.incluiElemento(3, "Eba");
		h.incluiElemento(15, "Ovo");
		h.incluiElemento(14, "Ova");
		
		h.imprimeHash();
		
		System.out.println();
		
		h.eliminaElemento(10);
		h.imprimeHash();
	}
}
