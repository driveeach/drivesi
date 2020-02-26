package ep2;

import java.util.LinkedList;

class RoundRobin {

	public static int escalonar(Cenario c, LinkedList<Processo> filaEntrada, LinkedList<Processo> filaProntos, int quantum) {
		while(!Simulacao.interrupcao){
			
			//primeira vez que o processo vai executar, atualiza tempo de resposta e tempo de espera
			if (filaProntos.element().tExecucao == 0){ 
				filaProntos.element().tResposta = (Simulacao.clock - filaProntos.element().tChegada);
				filaProntos.element().tEspera += ((Simulacao.clock - filaProntos.element().tChegada)-filaProntos.element().tResposta);
			}
			
			if (quantum == c.quantum) 
				Log.listaEvento.add(new Evento(4, filaProntos.element().pid, Simulacao.clock)); //processo obteve CPU
			
			quantum--;
			filaProntos.element().tExecucao += 1; //executa um ciclo
			Simulacao.clock++;
			
			if (filaProntos.element().burst == filaProntos.element().tExecucao){
				Processo p = filaProntos.remove();
				Log.listaEvento.add(new Evento(6, p.pid, Simulacao.clock, p.tChegada, p.tResposta, p.tEspera)); //processo concluido
				
				//atualiza o tempo de espera dos demais elementos prontos, se houver
				if (filaProntos.size() > 0){ 
					for (int i = 0; i < filaProntos.size(); i++)
						filaProntos.get(i).tEspera += c.quantum-quantum;
				}
				
				switch (c.opcao) {
					case 0: 
						Paginacao.desalocar(p);
						break;
					case 1: 
						Segmentacao.desalocar(p);
						break;
				}
				break;
			}
			if (quantum == 0){
				Processo p = filaProntos.remove();
				
				//atualiza o tempo de espera dos demais elementos prontos, se houver
				if (filaProntos.size() > 0){ 
					for (int i = 0; i < filaProntos.size(); i++)
						filaProntos.get(i).tEspera += c.quantum;
				}

				filaProntos.add(p);
				quantum = c.quantum; //restaura quantum
				Log.listaEvento.add(new Evento(5, p.pid, Simulacao.clock)); //processo preemptado
			}
			if (filaEntrada.size() > 0 && filaEntrada.element().tChegada == Simulacao.clock){
				Simulacao.interrupcao = true;
			}
		}
		return quantum; //guardar contexto
	}
}
