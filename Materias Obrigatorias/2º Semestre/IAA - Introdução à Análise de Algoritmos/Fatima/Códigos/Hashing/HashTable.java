import java.util.LinkedList;

class Elemento {
	String nome;
	String descricao;

	Elemento(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	void imprimeTermo() {
		System.out.println("chave:" + nome + "; dado: " + descricao + ".");
	}
}

interface IHash {

	static final int SIZE = 503;

	void remove(String nome);

	Elemento busca(String nome);

	void insere(String nome, Elemento elemento);
}

class LinkedListHashTable implements IHash {

	LinkedList<Elemento>[] dic = new LinkedList[SIZE];

	public LinkedListHashTable() {
		for (int i = 0; i < dic.length; i++) {
			dic[i] = new LinkedList<Elemento>();
		}
	}

	private int hash(String nome) {
		int somaValCar = 0;
		int i;

		// Soma os valores dos caracteres

		for (i = 0; i < nome.length(); i++)
			// nome contem somente letras e nome.length <= 8
			somaValCar += nome.charAt(i);

		return (somaValCar % SIZE);
	}

	public void insere(String nome, Elemento elemento) {
		int hash = hash(nome);
		LinkedList<Elemento> lista = dic[hash];
		Elemento aux = this.busca(nome);
		if (aux != null) {
			lista.remove(aux);
		}
		
		// Lista ligada para tratar colisao.
		lista.addFirst(elemento);
	}

	public void remove(String nome) {
		// Lista ligada para tratar colisao.

		LinkedList<Elemento> lista = dic[hash(nome)];

		for (int i = 0; i < lista.size(); i++) {
			Elemento e = lista.get(i);
			if (e.nome.equals(nome)) {
				lista.remove(e);
				break;
			}
		}
	}

	public Elemento busca(String nome) {
		// Lista ligada para tratar colisao.

		LinkedList<Elemento> lista = dic[hash(nome)];

		for (int i = 0; i < lista.size(); i++) {
			Elemento e = lista.get(i);
			if (e.nome.equals(nome)) {
				return e;
			}
		}
		return null;
	}
}

class ELinearHashTable implements IHash {

	private static final Elemento ELIMINADO = new Elemento("NIL", "NIL");

	private Elemento[] dic;

	public ELinearHashTable() {
		dic = new Elemento[SIZE];
	}

	private int hash(String nome, int i) {
		return (hash(nome) + i) % SIZE;
	}

	private int hash(String nome) {
		int somaValCar = 0;
		int i;

		// Soma os valores dos caracteres

		for (i = 0; i < nome.length(); i++)
			// nome contem somente letras e nome.length <= 8
			somaValCar += nome.charAt(i);

		return (somaValCar % SIZE);
	}

	public void insere(String nome, Elemento elemento) {
		int i = 0;
		int j = hash(nome, i);
		while (i != SIZE) {
			if (dic[j] == null || dic[j] == ELIMINADO) {
				dic[j] = elemento;
				break;
			}
			i = i + 1;
			j = hash(nome, i);
		}
	}

	public void remove(String nome) {
		int i = 0;
		int j = hash(nome, i);
		while ((i != SIZE) && (dic[j] != null)) {
			if (dic[j].nome.equals(nome)) {
				dic[j] = ELIMINADO;
				break;
			}
			i = i + 1;
			j = hash(nome, i);
		}
	}

	public Elemento busca(String nome) {
		int i = 0;
		int j = hash(nome, i);
		while ((i != SIZE) && (dic[j] != null)) {
			if (dic[j].nome.equals(nome)) {
				return dic[j];
			}
			i = i + 1;
			j = hash(nome, i);
		}
		return null; // Não foi encontrada a chave k
	}
}
