package ep2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

class Cenario {
	int opcao, quantum, tamanhoMemoria, tamanhoPagina;
	LinkedList<Processo> filaEntrada;
	File arquivo;
	
	Cenario(){
		try{
			arquivo = new File(JOptionPane.showInputDialog("Entre com o nome do arquivo"));
			
			while (!arquivo.exists() || !arquivo.isFile() || !arquivo.canRead()){
				JOptionPane.showMessageDialog(null, "Erro no arquivo. Verifique a digitacao e tente novamente.");
				arquivo = new File(JOptionPane.showInputDialog("Entre com o nome do arquivo"));
			}
			
			Scanner ler = new Scanner(arquivo); //scanner para ler do arquivo
			filaEntrada = new LinkedList<Processo>();
			
			tamanhoMemoria = ler.nextInt();
			
			if (!potenciaDeDois(tamanhoMemoria)){
				JOptionPane.showMessageDialog(null, "Erro no cenario. Memoria total nao eh potencia de 2.");
				System.exit(0);
			}
			ler.nextLine();
			
			tamanhoPagina = ler.nextInt();
			if (!potenciaDeDois(tamanhoPagina)){
				JOptionPane.showMessageDialog(null, "Erro no cenario. Tamanho da pagina nao eh potencia de 2.");
				System.exit(0);
			}
			
			ler.nextLine();
			
			quantum = ler.nextInt();
			ler.nextLine();
			
			opcao = ler.nextInt();
			ler.nextLine();
			
			ler.nextLine(); //comentarios
			ler.nextLine();
			ler.nextLine();
			
			//teste de valores validos
			if (tamanhoMemoria < 0) System.exit(0);
			if (tamanhoPagina < 0 || tamanhoPagina > tamanhoMemoria) System.exit(0);
			if (quantum < 0) System.exit(0);
			if (opcao < 0 || opcao >1) System.exit(0);
			
			switch (opcao){
			case 0 : 
				Paginacao.criarTabelaQuadro(tamanhoMemoria, tamanhoPagina);
				break;
			case 1 : 
				Segmentacao.criarTabelaSegmento(tamanhoMemoria);
				break;
			}
			
			while(ler.hasNext()){
				//adiciona um novo processo, lendo os atributos contidos na linha do arquivo de cenario
				filaEntrada.add(new Processo(ler.nextInt(), ler.nextInt(), ler.nextInt(), ler.nextInt(), ler.nextInt(), ler.nextInt(), tamanhoPagina));
			}
			Collections.sort(filaEntrada);
			
			
			
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Programa encerrado pelo usuário.");
			System.exit(0);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo nao encontrado. Possivelmente foi movido.");
			System.exit(0);
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel ler o cenario. Verifique a formatacao.");
			System.exit(0);
		}
	}
	
	boolean potenciaDeDois(int numero){
		while (numero%2 == 0 && numero > 2){
			numero = numero/2;
		}
		if (numero == 2) return true;
		else return false;
	}
}