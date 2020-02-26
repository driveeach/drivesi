package ep2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JOptionPane;

class Log {
	static LinkedList<Evento> listaEvento = new LinkedList<Evento>();
	
	public static void gerar(Cenario cenario, int tempoTotal){
		try {
			File arquivo = new File(JOptionPane.showInputDialog("Entre com o nome do arquivo de saida"));
			PrintWriter saida = new PrintWriter(new FileOutputStream(arquivo));
			Collections.sort(listaEvento);
			
			saida.println("------------------------------------------");
			saida.println("Arquivo de entrada: " + cenario.arquivo);
			switch (cenario.opcao){
			case 0: 
				saida.println("Gerencia de memoria: Paginacao");
				break;
			case 1: 
				saida.println("Gerencia de memoria: Segmentacao pura");
				break;
			}
			saida.println("Tamanho da memoria: " + cenario.tamanhoMemoria + " bytes");
			if (cenario.opcao == 0){
				saida.println("Tamanho da pagina: " + cenario.tamanhoPagina + " bytes");
				saida.println("Numero de paginas: " + (Integer) (cenario.tamanhoMemoria/cenario.tamanhoPagina));
			}
			saida.println("Quantum de tempo: " + cenario.quantum + " ms");
			saida.println("------------------------------------------");
			
			for (int i = 0; i < listaEvento.size(); i++)
				saida.println(listaEvento.get(i).geraDescricao());
			
			saida.println("------------------------------------------");
			saida.close();
		} 
		catch (Exception e){
			System.out.println("Nao foi possivel gravar o arquivo.");
		}
	}
}
