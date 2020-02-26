import java.util.LinkedList;
import java.util.Scanner;

interface ICharacterHash {
	void remove(Character c);

	Character busca(Character nome);

	void insere(Character c);
}

@SuppressWarnings("unchecked")
class CharacterHashTable implements ICharacterHash {

	private LinkedList<Character>[] dic;

	private int tam;

	public CharacterHashTable(int tam) {
		this.tam = tam;
		this.dic = new LinkedList[tam];
		for (int i = 0; i < dic.length; i++) {
			dic[i] = new LinkedList<Character>();
		}
	}

	private int hash(char c) {
		return (c - 'A') % tam;
	}

	public void insere(Character c) {
		// Lista ligada para tratar colisao.
		LinkedList<Character> lista = dic[hash(c)];
		if (!lista.contains(c)) {
			dic[hash(c)].addLast(c);
		} else {
			int posicao = lista.indexOf(c);
			lista.add(posicao, c);
		}
	}

	public void remove(Character c) {
		// Lista ligada para tratar colisao.

		LinkedList<Character> lista = dic[hash(c)];

		for (int i = 0; i < lista.size(); i++) {
			Character e = lista.get(i);
			if (e.equals(c)) {
				lista.remove(e);
				break;
			}
		}
	}

	public Character busca(Character c) {
		// Lista ligada para tratar colisao.

		LinkedList<Character> lista = dic[hash(c)];

		for (int i = 0; i < lista.size(); i++) {
			Character e = lista.get(i);
			if (e.equals(c)) {
				return e;
			}
		}
		return null;
	}

	public void imprime() {
		for (int i = 0; i < tam; i++) {
			System.out.print("(" + i + ")");
			LinkedList<Character> lista = dic[i];
			for (int j = 0; j < lista.size(); j++) {
				if (j == 0) {
					System.out.print(" " + lista.get(j));
				} else {
					System.out.print(" -> " + lista.get(j));
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CharacterHashTable cht;
		char[] v;

		CharacterHashTable hashTable = new CharacterHashTable(11);

		while (scanner.hasNext()) {
			cht = new CharacterHashTable(7);

			String value = scanner.next();

			v = new char[7];
			for (int i = 0; i < 7; i++) {
				v[i] = value.charAt(i);
			}

			for (int i = 0; i < v.length; i++) {
				cht.insere(v[i]);
			}

			cht.imprime();

		}
	}
}
