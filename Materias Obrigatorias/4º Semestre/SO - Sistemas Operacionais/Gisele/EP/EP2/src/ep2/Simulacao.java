package ep2;

import java.util.LinkedList;

public class Simulacao {
	
	public static int clock;
	public static boolean interrupcao = false;

	public static void main(String[] args) {
		
		Cenario cenario = new Cenario();
		LinkedList<Processo> filaEntrada = cenario.filaEntrada;
		LinkedList<Processo> filaProntos = new LinkedList<Processo>();
		LinkedList<Processo> filaMemoria = new LinkedList<Processo>();
		
		//prepara o simulador para a chegada do primeiro processo
		clock = filaEntrada.element().tChegada;
		int quantum = cenario.quantum;
		boolean terminado = false;
		
		while(!terminado){
			//chegada de processo, solicita memoria
			while (filaEntrada.size() > 0 && filaEntrada.element().tChegada == clock){
				Processo p = filaEntrada.remove(); //hora de sair da lista de entrada
				Log.listaEvento.add(new Evento(0, p.pid, clock));
				
				if (p.espacoEnd > cenario.tamanhoMemoria){ //processo abortado por exceder os requisitos de memoria
					Log.listaEvento.add(new Evento(8, p.pid, p.espacoEnd, cenario.tamanhoMemoria));
					continue;
				}
				
				switch (cenario.opcao){
				case 0:
					if (Paginacao.alocar(p)){
						filaProntos.add(p); //entra na fila de prontos
						Log.listaEvento.add(new Evento(1, p.pid, clock)); //evento obteve memoria
						Log.listaEvento.add(new Evento(7, clock, p.imprimePagina())); //imprime tabela de paginas do processo
					}
					else {
						filaMemoria.add(p);
						Log.listaEvento.add(new Evento(2, p.pid, clock));
					}
					break;
				case 1:
					if (Segmentacao.alocar(p)){
						filaProntos.add(p); //entra na fila de prontos
						Log.listaEvento.add(new Evento(1, p.pid, clock)); //evento obteve memoria
						Log.listaEvento.add(new Evento(7, clock, p.imprimeSegmento())); //imprime tabela de segmentos do processo
					}	
					else {
						filaMemoria.add(p);
						Log.listaEvento.add(new Evento(2, p.pid, clock));
					}
					break;
				}
			}
			
			
			if (filaProntos.size() > 0){
				if (interrupcao){
					interrupcao = false;
					
					if (cenario.opcao == 0) 
						Log.listaEvento.add(new Evento(7, clock, Paginacao.gerarMapa()));
					else 
						Log.listaEvento.add(new Evento(7, clock, Segmentacao.gerarMapa()));
					
					quantum = RoundRobin.escalonar(cenario, filaEntrada, filaProntos, quantum);
				}
				else {
					if (cenario.opcao == 0) 
						Log.listaEvento.add(new Evento(7, clock, Paginacao.gerarMapa()));
					else 
						Log.listaEvento.add(new Evento(7, clock, Segmentacao.gerarMapa()));
					
					quantum = RoundRobin.escalonar(cenario, filaEntrada, filaProntos, cenario.quantum);
				}
			}
			else clock++;
			
			

			if (filaMemoria.size() > 0){
				boolean alocou = false;
				int cont = 0;
				switch (cenario.opcao){
				case 0:
					while(!alocou && cont < filaMemoria.size()){
						if (Paginacao.alocar(filaMemoria.element())){
							Processo p = filaMemoria.remove();
							filaProntos.add(p);
							Log.listaEvento.add(new Evento(1, p.pid, clock));
							Log.listaEvento.add(new Evento(7, clock, p.imprimePagina())); //imprime tabela de paginas do processo
							alocou = true;
						}
						else{
							filaMemoria.add(filaMemoria.remove());
							cont++;
						}
					}
					break;
				case 1:
					if (Segmentacao.alocar(filaMemoria.element())){
						Processo p = filaMemoria.remove();
						filaProntos.add(p);
						Log.listaEvento.add(new Evento(1, p.pid, clock));
						Log.listaEvento.add(new Evento(7, clock, p.imprimeSegmento())); //imprime tabela de segmentos do processo
					}
					break;
				}
			}
			
			if (filaEntrada.isEmpty() && filaProntos.isEmpty()){ 
				terminado = true;
			}
		}
		Log.gerar(cenario, clock);
	}
}
