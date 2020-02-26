package escalonador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
	private int maxProcessos = 10;
	private int[] prioridades;
	private int numProcesso = 0; //saber em qual processo está acontecendo a leitura
	private int quantum;
	
	public Leitura() throws Exception {
		prioridades = new int[maxProcessos];
		lerPrioridades();
		lerArquivos();
		lerQuantum();
	}
	
	//abre o arquivo prioridades e coloca os valores em um arranjo
	private void lerPrioridades() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/processos/prioridades.txt")));
		for (int i = 0; i < maxProcessos; i++) {
			prioridades[i] = Integer.parseInt(br.readLine());
		}
		br.close();
	}
	
	//abre cada um dos arquivos de processos
	private void lerArquivos() throws IOException {
		for (int i = 1; i <= 10; i++) {
			FileReader arquivo = new FileReader(i != 10 ? "src/processos/0"+i+".txt" : "src/processos/10.txt");
			numProcesso = i-1;
			armazenaProcesso(arquivo);
		}
	}
	
	//le todo o arquivo do processo e cria um novo BCP
	private void armazenaProcesso (FileReader arquivo) throws IOException {
		BufferedReader br = new BufferedReader(arquivo);
		String[] instrucao = new String[21];
		String nome = br.readLine();
		int cont = 0;
		String aux = null;
		while (!"SAIDA".equals(aux) && cont<21) {
			aux = br.readLine();
			if (aux != null) {
				instrucao[cont] = aux;
				cont++;
			}
		}
		
		br.close();
		Processo processo = new Processo(nome, instrucao, Processo.PRONTO);
		criaBCP(processo);
	}

	//adiciona o BCP na lista de processos prontos
	private void criaBCP(Processo processo) {
		BCP bcp = new BCP(processo, prioridades[numProcesso]);
		TabelaProcessos.adicionaBlocoProntos(bcp);
	}
	
	//le o quantum do arquivo quantum.txt
	private void lerQuantum () throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/processos/quantum.txt")));
		this.quantum = Integer.parseInt(br.readLine());
		br.close();
	}

	public int getQuantum() {
		return quantum;
	}
}