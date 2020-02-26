/* Hash com endereçamento aberto por sondagem linear */
class HashLinear {
	/* Elemento armazenado no hash */
	private class Elemento {
		int chave;
		String valor;
		Elemento(int chave, String valor) {
			this.chave = chave;
			this.valor = valor;
		}
	}

	/* Limite do hash */
	private int limite = 0;
	/* A tabela de hash */
	Elemento[] tab = null;
	
	HashLinear(int limite) {
		if (limite>0) {
			this.limite = limite;
			this.tab = new Elemento[limite];
		}
	}
	
	/* Função de hash auxiliar para chaves inteiras */
	private int hash1(int chave) {
		return(chave % limite);
	}
	
	/* Função de hash com sondagem linear */
	private int hash(int chave, int i) {
		return((hash1(chave) + i) % limite);
	}
	
	/* Inclusão de elemento no hash. Retorna a posição onde colocou, ou -1 se o hash estiver cheio */
	int incluiElemento(int chave, String valor) {
		// número da sondagem
		int i=0;
		// posição no hash onde a busca por local começará
		int j = this.hash(chave,i);
		
		while (i != this.limite) {
			if (this.tab[j] == null) {
				this.tab[j] = new Elemento(chave, valor);
				return(j);
			}
			i++;
			j = this.hash(chave,i);
		}
		return(-1);
	}
	
	/* Busca de elemento no hash. Retorna a posição do elemento, ou -1 caso não esteja lá*/
	int busca(int chave) {
		// número da sondagem
		int i=0;
		// posição no hash onde a busca por local começará
		int j = this.hash(chave,i);
		
		while ((i != this.limite) && (this.tab[j] != null)) {
			if (this.tab[j].chave == chave) {
				return(j);
			}
			i++;
			j = this.hash(chave,i);
		}
		return(-1);
	}
	
	/* Imprime hash */
	void imprimeHash() {
		for (int i=0; i<this.limite; i++) {
			System.out.print("tab["+i+"] = ");
			if (this.tab[i] != null) {
				System.out.print("["+this.tab[i].chave+" , "+this.tab[i].valor+"]");
			}
			System.out.println();
		}
	}

	/* Para testes */
	public static void main(String[] args) {
		HashLinear h = new HashLinear(6);

		// incluo elementos
		
		// ex: elementos com hash para a mesma posição inicial
		h.imprimeHash();
		System.out.println();
		h.incluiElemento(10, "Oba");
		h.imprimeHash();
		System.out.println();
		h.incluiElemento(4, "Oba");
		h.imprimeHash();
		System.out.println();
		h.incluiElemento(34, "Oba");
		h.imprimeHash();
		System.out.println();
		h.incluiElemento(16, "Oba");
		h.imprimeHash();
		System.out.println();
		h.incluiElemento(56, "Oba");
		h.imprimeHash();
		System.out.println();
		h.incluiElemento(64, "Oba");
		h.imprimeHash();
		
		System.out.println(h.busca(34));
	}
}
