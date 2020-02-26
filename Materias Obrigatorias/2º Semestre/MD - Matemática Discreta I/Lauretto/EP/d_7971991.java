import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;


class d_7971991 {
	public static ArrayList<Teclado> teclados; //arraylist que armazena todos os teclados gerados para descobrir uma senha
	public static int maxDigitos = 6; //numero maximo de digitos de uma senha
	public static Scanner sc = null;
	public static int numSenhas; //numero total de senhas a serem descobertas
	public static int[][] senhas = null; //arranjo que armazenara todas as senhas

	public static void main(String[] args) {
		Teclado novoTeclado; 	

		try {
			sc = new Scanner(new File ("senha.txt")); //abre arquivo das senhas para começar a ler
		}
		catch (FileNotFoundException e) { //tratar exceção caso o arquivo não exista
			System.err.print(e);
		}
 
		numSenhas = sc.nextInt(); //le o numero total de senhas do arquivo
		senhas = new int [numSenhas][maxDigitos];
		inicializaSenhas();

		comecaEscreveArquivo();
	
		for (int i = 0; i<senhas.length; i++) { //descobrir cada senha
			teclados = new ArrayList<Teclado>(); //cria um novo arraylist para armazenar os teclados utilizados para descobrir a senha
			//int cont = 0;
			boolean adivinhou = false; //utilizada para saber se a senha foi descoberta ou não
			while (!adivinhou) {
				novoTeclado = new Teclado (); //cria um novo teclado
				digitaSenha(i, novoTeclado);
				adivinhou = adivinhaSenha();
			}
			escreveTecladoArquivo(teclados);
		}
	}	

	public static void inicializaSenhas () { //método que le todas as senhas do arquivo e as armazena no array senhas
		for (int i = 0; i<senhas.length; i++) {
			for (int j = 0; j<senhas[0].length; j++) {
				senhas[i][j] = sc.nextInt();
			}
		}
	}

	public static void comecaEscreveArquivo (){ //método que abre o arquivo para escrita e escreve o número de senhas que serão descobertas
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

	public static void digitaSenha (int linha, Teclado novoTeclado){ //a partir de um teclado, seleciona as teclas que serão utilizadas
		int[] possibilidade = new int [6]; //arranjo que armazena a sequencia das teclas digitadas
		for (int i = 0; i<senhas[linha].length; i++) {
			possibilidade[i] = pesquisaTecla (senhas[linha][i], novoTeclado);
		}
		//novoTeclado.imprimeTeclado();
		novoTeclado.setPossibilidade(possibilidade);
		teclados.add(novoTeclado);
	}

	public static int pesquisaTecla (int digito, Teclado novoTeclado) { //pesquisa em qual tecla está o dígito pesquisado
		int cont = 1;
		for (int i = 0; i<novoTeclado.getTeclado().length; i+=2) {
			if (digito == novoTeclado.getTeclado()[i] || digito == novoTeclado.getTeclado()[i+1]){
				return cont;
			}
			cont++;
		}
		return cont;
	}

	public static boolean adivinhaSenha () { //descobre a senha
		boolean[] descobrir = new boolean [maxDigitos];
		for (int i = 0; i<descobrir.length; i++) { //percorre cada dígito da senha
			HashMap <Integer, Integer> aux = new HashMap <Integer,Integer>(); //arraylist que armazena os digitos de cada tecla
			inicializaHash (aux);
			for (int j = 0; j<teclados.size(); j++) { //percorre cada teclado
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

	public static boolean acharDigito(HashMap<Integer,Integer> aux) { //tenta achar o dígito da senha entre os digitos das teclas
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

	public static boolean conferirTodosDig (boolean [] descobrir) { //verifica se todos os digitos foram descobertos
		for (int i = 0; i<descobrir.length; i++) {
			if (descobrir[i] == false) return false;
		}
		return true;
	}

	public static void escreveTecladoArquivo (ArrayList<Teclado> teclados) { //método que escreve todos os teclados no arquivo
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

	public void inicializaDigitos () { //adiciona no arraylist os dígitos de 1 a 10
		digitos = new ArrayList<Integer>();
		for (int i = 0; i<10; i++) {
			digitos.add(i);
		}
	}

	public void inicializaTeclado () { //sorteia as teclas do arraylist digitos
		teclado = new int [10];
		for (int i = 0; i<10; i++) {
			int posicao = sorteiaTeclas (10-i);
			teclado[i] = digitos.get(posicao);
			digitos.remove(posicao);
		}
		ordenaTeclado();
	}

	public int sorteiaTeclas (int tamanhoMax) { //sorteia a posicao das teclas
		Random rand = new Random();
		int posicao = rand.nextInt(tamanhoMax);
		return posicao;
	}

	public void ordenaTeclado () { //deixa os digitos das teclas do teclado ordenados
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