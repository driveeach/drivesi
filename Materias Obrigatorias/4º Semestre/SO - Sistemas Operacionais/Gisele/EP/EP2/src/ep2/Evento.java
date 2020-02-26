package ep2;

class Evento implements Comparable<Evento>{
	/* tipos de eventos:
	 * 0 -> entrada
	 * 1 -> obter memoria
	 * 2 -> entrar fila memoria
	 * 3 -> entrar fila prontos
	 * 4 -> obter CPU
	 * 5 -> preempcao
	 * 6 -> conclusao
	 * 7 -> mostrar mapa da memoria
	 * 8 -> processo rejeitado, requisitou mais memoria do que toda memoria disponivel
	 */
	int tipo, pid, tempo;
	int espacoEnd, memTotal;
	int tChegada, tResposta, tEspera;
	String alocacao;
	
	Evento(int tipo, int pid, int tempo){
		this.tipo = tipo;
		this.pid = pid;
		this.tempo = tempo;
	}
	
	Evento(int tipo, int pid, int tempo, int tChegada, int tResposta, int tEspera){
		this.tipo = tipo;
		this.pid = pid;
		this.tempo = tempo;
		this.tChegada = tChegada;
		this.tResposta = tResposta;
		this.tEspera = tEspera;
	}
	
	Evento(int tipo, int tempo, String alocacao){
		this.tipo = tipo;
		this.tempo = tempo;
		this.alocacao = alocacao;
	}
	
	Evento(int tipo, int pid, int espacoEnd, int memTotal){
		this.tipo = tipo;
		this.pid = pid;
		this.espacoEnd = espacoEnd;
		this.memTotal = memTotal;
	}
	
	String geraDescricao(){
		switch(tipo){
		case 0 : return tempo + " ms: processo " + pid + " chegou ao sistema.";
		case 1 : return tempo + " ms: processo " + pid + " obteve memoria.";
		case 2 : return tempo + " ms: processo " + pid + " entrou na fila de memoria.";
		case 3 : return tempo + " ms: processo " + pid + " entrou na fila de prontos.";
		case 4 : return tempo + " ms: processo " + pid + " executando na CPU.";
		case 5 : return tempo + " ms: processo " + pid + " preemptado.";
		case 6 : return tempo + " ms: processo " + pid + " concluido.\n  *Tempo de resposta: " + (tResposta) + " ms.\n  *Turnaround: " + (tempo-tChegada) + " ms.\n  *Tempo de Espera: " + (tEspera) + " ms."; 
		//tempo de espera -> soma dos periodos gastos aguardando na fila de espera 
		case 7 : return alocacao;
		case 8 : return "   Processo " + pid + " rejeitado. Requisita " + espacoEnd + " bytes, mas a memoria total é de " + memTotal + " bytes.";
		default: return null;
		}
	}

	public int compareTo(Evento e) {
		return this.tempo-e.tempo;
	}
}
