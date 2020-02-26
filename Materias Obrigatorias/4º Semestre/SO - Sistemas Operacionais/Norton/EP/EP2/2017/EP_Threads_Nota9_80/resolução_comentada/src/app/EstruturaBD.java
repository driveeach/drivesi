package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EstruturaBD {
	private static ArrayList<String> backup; //arraylist que representa o que está no arquivo
	static ArrayList<String> bd; //arraylist que representa o bd que é utilizado no momento
	private static Scanner sc;
	private static NumeroAleatorio numAleatorio;
	
	public EstruturaBD() throws FileNotFoundException {
		backup = new ArrayList<>();
		numAleatorio =  new NumeroAleatorio();
	
		/*lendo o arquivo e colocando cada linha em uma posição do arraylist*/
		sc = new Scanner(new File("src/banco/bd.txt"));
		while (sc.hasNext()) {
			backup.add(sc.nextLine());
		}
	}
	
	/*atualizando o bd para recomeçar a utilizá-lo*/
	public void setup() throws FileNotFoundException {
		bd = backup;
	}

	/*quando o objeto é um leitor, faz 100 acessos ao bd e apenas salva o que lê em uma variável*/
	public static void acessosAleatoriosLeitor() {
		int size = bd.size();
		for (int i = 0; i < 100; i++) {
			String string = bd.get(numAleatorio.gera(size));
		}
	}

	/*quando o objeto é um escritor, faz 100 acessos ao bd e troca seu conteúdo para "MODIFICADO"*/
	public static void acessosAleatoriosEscritor() {
		int size = bd.size();
		for (int i = 0; i < 100; i++) {
			bd.set(numAleatorio.gera(size), "MODIFICADO");
		}
	}
}
