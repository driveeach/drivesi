import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Random;

class Senha {
	public static ArrayList<Teclado> teclados;
	public static int maxDigitos = 6;
	public static Scanner sc = null;
	public static int numSenhas;
	public static int[][] senhas = null;

	public static void main(String[] args) {
		Teclado novoTeclado;	

		try {
			sc = new Scanner(new File ("senha.txt"));
		}
		catch (FileNotFoundException e) {
			System.err.print(e);
		}
 
		numSenhas = sc.nextInt();
		senhas = new int [numSenhas][maxDigitos];
		inicializaSenhas();

		comecaEscreveArquivo();
	
		for (int i = 0; i<senhas.length; i++) {
			teclados = new ArrayList<Teclado>();
			int cont = 0;
			boolean adivinhou = false;
			while (!adivinhou) {
				novoTeclado = new Teclado ();
				digitaSenha(i, novoTeclado);
				adivinhou = adivinhaSenha();
			}
			escreveTecladoArquivo(teclados);
		}

	}	

	public static void inicializaSenhas () {
		for (int i = 0; i<senhas.length; i++) {
			for (int j = 0; j<senhas[0].length; j++) {
				senhas[i][j] = sc.nextInt();
			}
		}
	}

	public static void digitaSenha (int linha, Teclado novoTeclado){
		int[] possibilidade = new int [6];
		for (int i = 0; i<senhas[linha].length; i++) {
			possibilidade[i] = pesquisaTecla (senhas[linha][i], novoTeclado);
		}
		novoTeclado.imprimeTeclado();
		novoTeclado.setPossibilidade(possibilidade);
		teclados.add(novoTeclado);
	}

	public static int pesquisaTecla (int digito, Teclado novoTeclado) {
		int cont = 1;
		for (int i = 0; i<novoTeclado.getTeclado().length; i+=2) {
			if (digito == novoTeclado.getTeclado()[i] || digito == novoTeclado.getTeclado()[i+1]){
				return cont;
			}
			cont++;
		}
		return cont;
	}

	public static boolean adivinhaSenha () {
		boolean[] descobrir = new boolean [6];
		for (int i = 0; i<descobrir.length; i++) {
			HashMap <Integer, Integer> aux = new HashMap <Integer,Integer>();
			inicializaHash (aux);
			for (int j = 0; j<teclados.size(); j++) {
				int possib = teclados.get(j).getPossibilidade()[i];
				int dig1 = teclados.get(j).getTeclado()[(possib*2)-2];
				int dig2 = teclados.get(j).getTeclado()[(possib*2)-1];
				int valor;
				valor = aux.get(dig1);
				aux.put(dig1, valor+1);
				valor = aux.get(dig2);
				aux.put(dig2, valor+1);
			}
			descobrir [i] = acharDigito(aux);
		}
		return conferirTodosDig(descobrir);		
	}

	public static void inicializaHash (HashMap <Integer, Integer> aux) {
		for (int i = 0; i<10; i++) {
			aux.put(i, 0);
		}
	}

	public static boolean acharDigito(HashMap<Integer,Integer> aux) {
		int chaveMax = -1;
		int valorMax = -1;
		for (int i = 0; i<aux.size(); i++) {
			if (valorMax < aux.get(i)) {
				chaveMax = i;
				valorMax = aux.get(i);
			}
		}
		int cont = 0;
		for (int i = 0; i<aux.size(); i++) {
			if (aux.get(i) == valorMax) cont++;
		}		
		if (cont == 1) return true;
		else return false;
	}

	public static boolean conferirTodosDig (boolean [] descobrir) {
		for (int i = 0; i<descobrir.length; i++) {
			if (descobrir[i] == false) return false;
		}
		return true;
	}

	public static void comecaEscreveArquivo (){
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter ("teclados.txt"));
			bw.write(numSenhas+"");
			bw.newLine();
			bw.close();
		}
		catch (IOException e) {
			System.err.print(e);
		}
	}

	public static void escreveTecladoArquivo (ArrayList<Teclado> teclados) {
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter ("teclados.txt", true));
			
			bw.write (teclados.size()+"\n");
			bw.newLine();
			for (int i = 0; i<teclados.size(); i++) {
				int[] tecladoAux = teclados.get(i).getTeclado();
				for (int j =0; j<tecladoAux.length; j+=2) {
					bw.write(tecladoAux[j]+""+tecladoAux[j+1]+" ");
				}
				int[] possibAux = teclados.get(i).getPossibilidade();
				for (int j =0; j<possibAux.length; j++) {
					bw.write(possibAux[j]+" ");
				}
				bw.newLine();
			}

			bw.close();
		}
		catch (IOException e) {
			System.err.print(e);
		}	
	}
}



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