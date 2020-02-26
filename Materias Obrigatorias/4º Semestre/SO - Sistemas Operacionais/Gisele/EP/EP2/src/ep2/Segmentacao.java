package ep2;

import java.util.Collections;
import java.util.LinkedList;

class Segmentacao {
	static LinkedList<Segmento> tabelaSegmento = new LinkedList<Segmento>();
	
	public static void criarTabelaSegmento(int tamanhoMemoria){
		tabelaSegmento.add(new Segmento(false, 0, -1, -1, tamanhoMemoria));
	}
	
	public static String gerarMapa(){
		String s = "-->Memoria: ";
		for (int i = 0; i < tabelaSegmento.size(); i++){
			s += tabelaSegmento.get(i).detalhes();
			if (i < tabelaSegmento.size()-1)
				s += ",";
			else s += ".";
		}
		return s;
	}
	
	public static boolean alocar(Processo p){
		int secao = 0;
		
		for (int i = 0; i < tabelaSegmento.size() && secao < 3; i++){
			Segmento atual = tabelaSegmento.get(i);
			
			if (atual.alocado == false){
				if (atual.tamanho >= p.tabelaSecao[secao][1]){
					p.tabelaSecao[secao][0] = atual.base; //armazena a base
					
					if (atual.tamanho == p.tabelaSecao[secao][1]){
						atual.alocado = true;
						atual.pid = p.pid;
						atual.secao = secao;
					}
					else{
						atual.alocado = true;
						atual.pid = p.pid;
						atual.secao = secao;
						Segmento restante = new Segmento(false, (atual.base+p.tabelaSecao[secao][1]), -1, -1, atual.tamanho-p.tabelaSecao[secao][1]);
						atual.tamanho = p.tabelaSecao[secao][1];
						tabelaSegmento.add(restante);
						Collections.sort(tabelaSegmento);
					}
					secao++;
				}
			}
		}
		
		if (secao == 3){
			Collections.sort(tabelaSegmento);
			return true;
		}
		else{
			desalocar(p);
			return false;
		}
	}
	
	public static void desalocar(Processo p){
		
		for (int i = 0; i < tabelaSegmento.size(); i++){
			Segmento atual = tabelaSegmento.get(i);
			
			if (atual.pid == p.pid){
				p.tabelaSecao[atual.secao][0] = -1; //remove a base
				atual.alocado = false;
				atual.pid = -1;
				atual.secao = -1;
			}
		}
		
		int i = 0;
		while (i < tabelaSegmento.size()-1){
			Segmento atual = tabelaSegmento.get(i);
			Segmento prox = tabelaSegmento.get(i+1);
			
			if (!atual.alocado){
				if (!prox.alocado){
					atual.tamanho = atual.tamanho + prox.tamanho;
					tabelaSegmento.remove(i+1);
				}
				else {
					i += 2;
				}
			}
			else{
				i++;
			}
		}
	}
}