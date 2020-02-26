package ep2;

class Processo implements Comparable<Processo>{
	int pid, tChegada, burst, espacoEnd;
	int tabelaSecao[][] = new int[3][2]; //0=texto, 1=dados, 2=pilha (base, tamanho)
	int tabelaPagina[];
	
	//estatisticas do processo
	int tamanhoPagina;
	int tExecucao = 0;
	int tResposta = 0;
	int tEspera = 0;
	

	Processo(int pid, int tChegada, int burst, int tamSecao1, int tamSecao2, int tamSecao3, int tamanhoPagina) {
		this.pid = pid;
		this.tChegada = tChegada;
		this.burst = burst;
		this.tamanhoPagina = tamanhoPagina;
		
		this.tabelaSecao[0][0] = -1;
		this.tabelaSecao[0][1] = tamSecao1;
		this.tabelaSecao[1][0] = -1;
		this.tabelaSecao[1][1] = tamSecao2;
		this.tabelaSecao[2][0] = -1;
		this.tabelaSecao[2][1] = tamSecao3;
		
		this.espacoEnd = tamSecao1 + tamSecao2 + tamSecao3;
		
		//calcula o nro de paginas
		int nroPag = (espacoEnd%tamanhoPagina == 0) ? espacoEnd/tamanhoPagina : (espacoEnd/tamanhoPagina)+1;
		
		this.tabelaPagina = new int[nroPag];
		for (int i = 0; i < tabelaPagina.length; i++){
			tabelaPagina[i] = -1; //nao alocada ainda
		}
	}

	String imprimePagina(){
		String s = "  *Espaco de Enderecamento: " + espacoEnd + " bytes. Paginas: ";
		for (int i = 0; i < tabelaPagina.length; i++){
			if (i < tabelaPagina.length-1)
				s += tabelaPagina[i] + ", ";
			else
				s += tabelaPagina[i] + ". ";
		}
		s += "Fragmentacao interna: " + ((tabelaPagina.length*tamanhoPagina)-espacoEnd) + " bytes.";
		return s;
	}
	
	String imprimeSegmento(){
		return "  *Secoes: (texto) base " + tabelaSecao[0][0] + ", limite " + (tabelaSecao[0][0]+tabelaSecao[0][1]) + "; (dados) base " + tabelaSecao[1][0] + ", limite " + (tabelaSecao[1][0]+tabelaSecao[1][1]) + "; (pilha) base " + tabelaSecao[2][0] + ", limite " + (tabelaSecao[2][0]+tabelaSecao[2][1]) + ".";
	}
	
	public int compareTo(Processo p) {
		return this.tChegada-p.tChegada;
	}
}
