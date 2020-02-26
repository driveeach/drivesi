package app;

import java.io.FileNotFoundException;

public class MainThreads {
	private static Lock lock; //objeto que irá possibilitar bloquear e desbloquear a região crítica, no modelo sem Leitor/Escritor
	private static NumeroAleatorio numAleatorio; //objeto que ajudará a selecionar números aleatórios
	private static LeitorEscritor controlador; //objeto que bloqueará/desbloqueará a região crítica, utilizando o modelo com Leitor/Escritor
	private static EstruturaBD bd;
	
	private static final int todasProporcoes = 101; //número utilizado para fazer as proporções entre leitores e escritores
	private static final int vezesCadaProp = 50; //número utilizado para a quantidade de vezes que cada proporção rodará
	private static Thread[] threads; //arranjo que conterá as 100 threads
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		lock = new Lock();
		controlador = new LeitorEscritor();
		bd = new EstruturaBD();
		
		for (int k = 0; k < 2; k++) { //fazer os 2 tipos de implementação
			long inicioPrograma = System.currentTimeMillis(); //começando a contar o tempo total que uma implementação irá demorar
			System.out.println("Implementação: " + (k+1));
			for (int i = 0; i < todasProporcoes; i++) { //fazer todas as proporções de leitores e escritores
				int media = 0;
				for (int j = 0; j < vezesCadaProp; j++) { //fazer 50 vezes cada uma das proporções
					bd.setup(); //inicializando a estrutura de banco de dados
					numAleatorio = new NumeroAleatorio(); //gerando arranjo com números de 1 a 100 para pegar aleatórios
					newThreads(i, k+1);
					long tempoInicial = System.currentTimeMillis();
					startThreads();
					joinThreads();
					long tempoFinal = System.currentTimeMillis();
					media += tempoFinal - tempoInicial;
				}
				media /= vezesCadaProp;
				System.out.println("Média - " + i + " escritores e " + (100 - i) + " leitores - " + media);
			}
			long fimPrograma = System.currentTimeMillis();
			System.out.println("Demorou " + ((fimPrograma - inicioPrograma) / 60000) + " min");
		}
	}
	
	private static void newThreads(int proporcao, int implementacao) throws FileNotFoundException {
		threads = new Thread[100]; //inicializando array de threads
		/*gerando objetos da proporção em lugares aleatórios*/
		for (int i = 0; i < proporcao; i++) { 
			loop(new Escritor(0, controlador, lock, implementacao));
		}
		for (int i = 0; i < 100 - proporcao; i++) {
			loop(new Leitor(0, controlador, lock, implementacao));
		}
	}
	
	/*inicializando as threads*/
	private static void startThreads() { 
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	/*fazer com que o main espere as threads*/
	private static void joinThreads() throws InterruptedException { 
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
	
	/*procurando lugar aleatório para colocar o novo objeto*/
	private static void loop(Runnable obj) { 
		int randomNumber = numAleatorio.gera();
		threads[randomNumber] = new Thread(obj);
	}
}
