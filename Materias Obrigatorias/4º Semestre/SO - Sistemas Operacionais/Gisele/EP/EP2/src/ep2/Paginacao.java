package ep2;

class Paginacao {
	public static int tabelaQuadro[];
	static int paginaLivre; //quantidade de paginas disponiveis
	
	
	public static void criarTabelaQuadro(int tamanhoMemoria, int tamanhoPagina){
		tabelaQuadro = new int[tamanhoMemoria/tamanhoPagina];
		for (int i = 0; i < tabelaQuadro.length; i++){
			tabelaQuadro[i] = -1; //quadro livre
		}
		paginaLivre = tabelaQuadro.length;
	}
	
	public static String gerarMapa(){
		String s = "   Memoria: ";
		for (int i = 0; i < tabelaQuadro.length; i++){
			s += "[" + i + "]" + (tabelaQuadro[i] == -1 ? "[X]" : "[" + tabelaQuadro[i] + "]");
			if (i < tabelaQuadro.length-1)
				s += ", ";
			else s += ".";
		}
		return s;
	}
	
	public static boolean alocar(Processo p) {
		if (paginaLivre >= p.tabelaPagina.length){
			int j = 0;
			
			for (int i = 0; i < tabelaQuadro.length; i++){
				if (tabelaQuadro[i] == -1){
					tabelaQuadro[i] = p.pid;
					p.tabelaPagina[j] = i;
					paginaLivre--;
					j++;
				}
				if (j == p.tabelaPagina.length) break;
			}
			return true;
		}
		else return false;
	}

	public static void desalocar(Processo p) {
		for (int i = 0; i < p.tabelaPagina.length; i++){
			tabelaQuadro[p.tabelaPagina[i]] = -1;
			p.tabelaPagina[i] = -1;
			paginaLivre++;
		}
	}
}