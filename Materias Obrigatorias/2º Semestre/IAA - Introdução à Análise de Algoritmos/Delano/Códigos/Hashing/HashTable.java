import java.util.LinkedList;

class TermoDicionario {
	String palavra;
	String descricao;

	Elemento(String palavra, String descricao) {
		this.palavra = palavra;
		this.descricao = descricao;
	}

	void imprimeTermo() {
		System.out.println("chave:" + palavra + "; dado: " + descricao + ".");
	}
}

interface TabelaHash 
{
	static final int SIZE = 503;
	void insere(String palavra, Termo termo);
	void remove(String palavra);
	Termo busca(String palavra);
}

@SuppressWarnings("unchecked")
class LinkedListHashTable implements TabelaHash {

	private LinkedList<Termo>[] dic;

	public LinkedListHashTable() {
		dic = new LinkedList[SIZE];
		for (int i = 0; i < dic.length; i++) {
			dic[i] = new LinkedList<Termo>();
		}
	}

	private int hash(String palavra) {
		int somaValCar = 0;
		int i;

		// Soma os valores dos caracteres

		for (i = 0; i < palavra.length(); i++)
			// nome contem somente letras e nome.length <= 8
			somaValCar += palavra.charAt(i);

		return (somaValCar % SIZE);
	}

	public void insere(String nome, Termo termo) {
		// Lista ligada para tratar colisao.
		dic[hash(palavra)].addFirst(termo);
	}

	public void remove(String palavra) {
		// Lista ligada para tratar colisao.

		LinkedList<Termo> lista = dic[hash(palavra)];

		for (int i = 0; i < lista.size(); i++) {
			Termo t = lista.get(i);
			if (t.palavra.equals(palavra)) {
				lista.remove(t);
				break;
			}
		}
	}

	public Termo busca(String palavra) {
		// Lista ligada para tratar colisao.

		LinkedList<Termo> lista = dic[hash(palavra)];

		for (int i = 0; i < lista.size(); i++) {
			Termo t = lista.get(i);
			if (t.palavra.equals(palavra)) {
				return t;
			}
		}
		return null;
	}
}