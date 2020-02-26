package tentativaEErro;

public class PasseioDoCavalo {
	final int[] deslocamentoX = { 2, 1, -1, -2, -2, -1, 1, 2 };
	final int[] deslocamentoY = { 1, 2, 2, 1, -1, -2, -2, -1 };
	final int ordemDoTabuleiro;  // n (número de linhas / colunas
	final int totalDeCelulas;
	int[][] tabuleiro;
	long totalDeMovimentosDoCavalo = 0;
	long numeroDeOperacoes = 0;
	
	public PasseioDoCavalo(int ordemDoTabuleiro) {
		this.ordemDoTabuleiro = ordemDoTabuleiro;
		this.totalDeCelulas = ordemDoTabuleiro * ordemDoTabuleiro;
		this.tabuleiro = new int[ordemDoTabuleiro][ordemDoTabuleiro];
	}
	
	boolean jogadaEhAceitavel(int x, int y) {
		boolean resultado = (x >= 0 && x <= ordemDoTabuleiro - 1);
		resultado = resultado && (y >= 0 && y <= ordemDoTabuleiro - 1);
		resultado = resultado && (tabuleiro[x][y] == 0);
		return resultado;
	}
	
	boolean tentaMover(int i, int x, int y) {
		// Verifica a quantidade de movimentos
		boolean problemaResolvido = (i > totalDeCelulas);
		int k = 0;
		int u, v;  // u,v: posicao de destino  -  x, y: posicao atual
		while (!problemaResolvido && k < 8) { // 8 eh o numero de movimentos teoricamente possiveis
			numeroDeOperacoes++;
			u = x + deslocamentoX[k];
			v = y + deslocamentoY[k];
			if (jogadaEhAceitavel(u, v)) {
				tabuleiro[u][v] = i;
				totalDeMovimentosDoCavalo++;
				problemaResolvido = tentaMover(i + 1, u, v); // tenta outro movimento
				if (!problemaResolvido) {
					totalDeMovimentosDoCavalo++;
					tabuleiro[u][v] = 0; // não sucedido. Descarta movimento
				}
			}
			k = k + 1;
		}
		return problemaResolvido;
	}

	void resolverPasseio(int x, int y) {
		numeroDeOperacoes=0;
		totalDeMovimentosDoCavalo=0;
		tabuleiro[x][y] = 1;
		boolean problemaResolvido = tentaMover(2, x, y);
		if (problemaResolvido) {
			System.out.println("Passeio resolvido:");
			for (int i = 0; i < ordemDoTabuleiro; i++) {
				for (int j = 0; j < ordemDoTabuleiro; j++) {
					if (tabuleiro[i][j]<100) System.out.print(" "); // apenas para deixar a impressao mais organizada 
					if (tabuleiro[i][j]<10) System.out.print(" "); // apenas para deixar a impressao mais organizada
					System.out.print(tabuleiro[i][j] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("Passeio impossivel de ser realizado.");
		}
		System.out.println("Numero total de movimentos do cavalo: " + totalDeMovimentosDoCavalo);
		System.out.println("Numero total de operacoes do algoritmo: " + numeroDeOperacoes);
	}
	
	public static void main(String[] args) {
		/*  Uso as seguintes linhas (comentadas abaixo) se nao quiser utilizar argumentos
		args = new String[3];
		args[0] = "8";
		args[1] = "0";
		args[2] = "0";
		*/
		if (args.length >= 3){
			int n = Integer.parseInt(args[0]);
			int x = Integer.parseInt(args[1]);
			int y = Integer.parseInt(args[2]);
			new PasseioDoCavalo(n).resolverPasseio(x, y);
		}else{
			System.out.println("Voce precisa passar 3 numeros como parametros de entrada: ordem_do_tabuleiro x0 y0");
		}

	}
}
