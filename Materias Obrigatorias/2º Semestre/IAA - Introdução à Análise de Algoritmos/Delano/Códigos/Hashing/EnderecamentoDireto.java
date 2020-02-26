class Termo {
	int num;
	String descricao;

	Termo(int num, String descricao) {
		this.num = num;
		this.descricao = descricao;
	}

	void imprimeTermo() {
		System.out.println("chave:" + num + "; dado: " + descricao + ".");
	}
}

class ConjuntoDinamico {
	Termo cjto[] = new Termo[9];

	void insere(int num, String descricao) {
		if (chave(num) != 0) {
			cjto[chave(num)] = new Termo(num, descricao);
		}
	}

	void elimina(int num) {
		cjto[chave(num)] = null;
	}

	Termo busca(int num) {
		return cjto[chave(num)];
	}

	int chave(int num) {
		if (num > 0 && num < 9) {
			return num;
		} else {
			return 0;
		}
	}
}