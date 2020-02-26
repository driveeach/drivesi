import java.util.Calendar;
import java.util.Date;


public class KnightsTour {
	final int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	final int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	final int num;  // n (n�mero de linhas / colunas
	final int numSqr;
	int[][] table;
	long totalKnightMove = 0;
	long numOfOperations = 0;
	
	public KnightsTour(int num) {
		this.num = num;
		this.numSqr = num * num;
		this.table = new int[num][num];
	}

	boolean isAcceptable(int x, int y) {
		boolean result = (x >= 0 && x <= num - 1);
		result = result && (y >= 0 && y <= num - 1);
		result = result && (table[x][y] == 0);
		return result;
	}

	boolean tryMove(int i, int x, int y) {
		// Verifica a quantidade de movimentos
		boolean done = (i > numSqr);
		int k = 0;
		int u, v;  // u,v: posicao de destino  -  x, y: posicao atual
		while (!done && k < 8) { // 8 eh o numero de movimentos teoricamente possiveis
			numOfOperations++;
			u = x + dx[k];
			v = y + dy[k];
			if (isAcceptable(u, v)) {
				table[u][v] = i;
				totalKnightMove++;
				done = tryMove(i + 1, u, v); // tenta outro movimento
				if (!done) {
					totalKnightMove++;
					table[u][v] = 0; // n�o sucedido. Descarta movimento
				}
			}
			k = k + 1;
		}
		return done;
	}

	void resolverPasseio(int x, int y) {
		numOfOperations=0;
		totalKnightMove=0;
		table[x][y] = 1;
		Date inicio = Calendar.getInstance().getTime();
		System.out.println("Inicio = " + inicio);
		boolean problemaResolvido = tryMove(2, x, y);
		Date fim = Calendar.getInstance().getTime();
		System.out.println("Fim    = " + fim);
		if (problemaResolvido) {
			System.out.println("Passeio resolvido em (" + ((fim.getTime() - inicio.getTime())/1000.0) + " s)");
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (table[i][j]<100) System.out.print(" "); // apenas para deixar a impressao mais organizada 
					if (table[i][j]<10) System.out.print(" "); // apenas para deixar a impressao mais organizada
					System.out.print(table[i][j] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("Passeio impossivel de ser realizado.");
		}
		System.out.println("Numero total de movimentos do cavalo: " + totalKnightMove);
		System.out.println("Numero total de operacoes do algoritmo: " + numOfOperations);
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
			new KnightsTour(n).resolverPasseio(x, y);
		}else{
			System.out.println("Voce precisa passar 3 numeros como parametros de entrada: ordem_do_tabuleiro x0 y0");
		}
	}
}
