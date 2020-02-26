import java.util.ArrayList;
import java.util.Random;

class Teclado {
	private int[] teclado;
	private ArrayList <Integer> digitos;
	private int[] possibilidade = new int [6];

	Teclado () {
		inicializaDigitos();
		inicializaTeclado();
	}

	public void inicializaDigitos () {
		digitos = new ArrayList<Integer>();
		for (int i = 0; i<10; i++) {
			digitos.add(i);
		}
	}

	public void inicializaTeclado () {
		teclado = new int [10];
		for (int i = 0; i<10; i++) {
			int posicao = sorteiaTeclas (10-i);
			teclado[i] = digitos.get(posicao);
			digitos.remove(posicao);
		}
		ordenaTeclado();
	}

	public int sorteiaTeclas (int tamanhoMax) {
		Random rand = new Random();
		int posicao = rand.nextInt(tamanhoMax);
		return posicao;
	}

	public void ordenaTeclado () {
		for (int i = 0; i<teclado.length; i+=2) {
			if (teclado[i]>teclado[i+1]) {
				int aux = teclado[i];
				teclado[i] = teclado[i+1];
				teclado[i+1] = aux;
			}
		}
	}

	public void imprimeTeclado () {
		for (int i = 0; i<teclado.length; i++) {
			System.out.print(teclado[i] + " ");
		}
		System.out.println();
	}

	public int[] getTeclado() {
		return teclado;
	}

	public int[] getPossibilidade () {
		return possibilidade;
	}

	public void setPossibilidade (int[] possibilidade) {
		this.possibilidade = possibilidade;
	}
}