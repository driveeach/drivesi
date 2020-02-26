package escalonador;

import static escalonador.TabelaProcessos.bloqueados;
import static escalonador.TabelaProcessos.prontos;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Escalonador {

	private static int quantum; // quantidade de quantum para cada processo
	private static int interrompido = 0; // contador para a média de processos interrompidos
	private static int instrucaoQuantum = 0; // contador para a média do número de instruções por quantum
	private static int numQuantum = 0;// contador geral de quantuns, em relação a todos os processos
	private final static int totalProcessos = 10; //total de processos a serem executados

	public static void main(String[] args) throws Exception {
		Leitura leitura = new Leitura(); //lê todos os arquivos e adiciona os processos e prioridades na Tabela de Processos
		
		quantum = leitura.getQuantum(); //armazenando o valor do quantum 
		
		// criando o arquivo LOG
		if (quantum < 10 && quantum > 0) {
			System.setOut(new PrintStream(new FileOutputStream("log0" + quantum + ".txt")));
		} else {
			System.setOut(new PrintStream( new FileOutputStream("log"+quantum + ".txt")));
		}

		// carregando os processos após serem ordenados conforme sua prioridade
		for (BCP bcp : prontos) {
			System.out.println("Carregando " + bcp.processo.nome);
		}

		// enquanto a fila de processos prontos ou a fila de processos bloqueados forem maiores que 0
		while (prontos.size() > 0 || bloqueados.size() > 0) {
			if (prontos.size() > 0) { // Se tiver algum processo pronto
				
				int cont = 0; //contador para não deixar passar o valor do quantum
				boolean saida = false; // booleano para saber se o processo chegou ao final (SAIDA)
				boolean parou = false; // booleano que serve para saber se o processo parou antes do quantum
				
				BCP atual = TabelaProcessos.removePrimeiroProntos(); // o primeiro bcp da lista de prontos é removido
				atual.decrementaCredito(); // o crédito de sua prioridade é decrementado
				
				atual.processo.setEstado(Processo.EXECUTANDO);
				System.out.println("Executando " + atual.processo.nome);
				
				while (atual != null && cont < quantum) {// enquanto ainda puder rodar dentro do quantum
					String instrucao = atual.processo.instrucao[atual.contadorDePrograma]; // instrução que será rodada agora
					
					if ("E/S".equals(instrucao)) { // se for uma instrução de entrada e saída
						if (atual.flag == 0) { //se a flag for 0, ou seja, se ainda não tiver sido executada a instrução
							System.out.println("E/S iniciada em " +atual.processo.nome);
							entradaSaida(atual);
							parou = true; //processo irá parar antes do quantum terminar
							break; //para esse processo
						} else {
							atual.flag = 0; //se já foi executada, volta a flag para 0
						}
					}
					
					else if ("SAIDA".equals(instrucao)) { // se a instrução for "SAIDA" indica que tal processo chegou ao seu fim
						System.out.println(atual.processo.nome + " terminado. X=" + atual.registradorX + ". Y=" + atual.registradorY);
						prontos.remove(atual); //remove o processo da lista de prontos
						parou = true; //processo irá parar
						saida = true; //chegou ao final
						break;
					}
					
					else if (instrucao.contains("X=")) { // se a instrução for atribuindo um valor ao registrador X
						atual.registradorX = Integer.parseInt(instrucao.substring(2));
					}
					
					else if (instrucao.contains("Y=")) { // se a instrução for atribuindo um valor ao registrador Y
						atual.registradorY = Integer.parseInt(instrucao.substring(2));
					}

					atual.contadorDePrograma++; // incrementa o contador de programa do processo atual
					cont++; // incrementa o contador de instruções
				}
				
				if (!saida) { //Se o processo atual não acabou (não chegou na SAIDA), portanto ele foi interrompido
					if (cont == 0 || cont == 1) {
						System.out.println("Interrompendo "	+ atual.processo.nome + " após " + cont	+ " instrução");
					} else {
						System.out.println("Interrompendo "	+ atual.processo.nome + " após " + cont	+ " instruções");
					}
				}
				
				interrompido++; // contador de interrompido é incrementado
				numQuantum++;// incrementa o quantum
				instrucaoQuantum += cont; //conta o número de instruções a cada quantum
				
				//se o processo não foi para a lista de bloqueados, ele volta para a lista de prontos
				if (!parou) {
					atual.processo.setEstado(Processo.PRONTO);
					TabelaProcessos.adicionaBlocoProntos(atual);
				}
			}
			
			decrementaBloqueados(); //decrementa a espera de cada processo da lista de bloqueados
			verificaZeroEspera(); //verifica se tem algum processo na lista de bloqueados com 0 de espera 
			verificaZeroCreditosProntos(); //verifica se todos os créditos da lista de prontos é 0, se sim já seta com a prioridade
		}
		
		System.out.println("media de trocas: " + mediaTrocas(interrompido));
		System.out.println("media de instruções: " + mediaInstrucao(instrucaoQuantum, numQuantum));
		
		System.out.println("quantum: " + quantum);
	}
	
	private static double mediaTrocas (double interrompido) {
		return interrompido / totalProcessos;
	}
	
	private static double mediaInstrucao (double instrucaoQuantum, double numQuantum) {
		return instrucaoQuantum / numQuantum;
	}

	private static void entradaSaida(BCP processo) {
		processo.processo.setEstado(Processo.BLOQUEADO);
		TabelaProcessos.adicionaBlocoBloqueados(processo); //adiciona o processo à lista de bloqueados
		int index = bloqueados.indexOf(processo);
		bloqueados.get(index).espera = 2; //atualiza a espera do processo para 2
		bloqueados.get(index).flag = 1; //atualiza o flag para 1, ou seja, a instrução já foi executada
	}

	private static boolean verificaZeroCreditosProntos() {
		for (BCP p : prontos) {
			if (p.credito != 0)	return false; //se algum crédito for diferente de 0, já retorna falso
		}
		//se todos os créditos forem iguais a 0, atualiza todos os créditos à sua prioridade correspondente
		for (BCP p : prontos) {
			p.credito = p.prioridade;
		}
		return true;
	}

	private static void decrementaBloqueados() {
		for (BCP b : bloqueados) {
			if (b.espera > 0) b.espera--; //diminui 1 da espera
		}
	}

	private static void verificaZeroEspera() {
		int cont = 0;
		
		//conta quantos processos tem a espera igual a zero
		for (BCP b : bloqueados) {
			if (b.espera == 0) {
				b.processo.setEstado(Processo.PRONTO);
				TabelaProcessos.adicionaBlocoProntos(b);
				cont++;
			} else break;
		}
		
		//remove todos os bloqueados com espera igual a zero
		for (int i = 0; i < cont; i++) {
			TabelaProcessos.removePrimeiroBloqueados();
		}
	}
}