import java.util.Calendar;
import java.util.Date;


public class PasseioCavalo 
{
	final int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };  // posições possíveis de movimento na coordenada x
	final int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 }; // posições possíveis de movimento na coordenada y
	final int _num;  // n número de linhas e colunas
	final int _numSqr; // n ao quadrado
	int[][] table;  // matriz que representa tabuleiro
	long totalMovimentos = 0;
	long numDeOperacoes = 0;

	public PasseioCavalo(int num) //construtor
	{
		_num = num;
		_numSqr = num * num;
		table = new int[num][num];
	}

	boolean ehAceitavel(int x, int y) //devolve true se todas as condições forem válidas
	{
		boolean result = (x >= 0 && x <= _num - 1);            //verificar número da coordenada x
		result = result && (y >= 0 && y <= _num - 1);          //verificar número da coordenada y
		result = result && (table[x][y] == 0);                //posição da tabela está válida
		return result;
	}

	boolean tentaMovimento(int i, int x, int y) 
	{
		// tenta fazer movimentos
		boolean termina = (i > _numSqr);        // termina quando i for maior que n ao quadrado
		int k = 0;
		int u, v;  // u,v: posicao de destino  -  x, y: posicao atual
		while (!termina && k < 8) 
		{ // 8 eh o numero de movimentos teoricamente possiveis
			numDeOperacoes++;
			u = x + dx[k];
			v = y + dy[k];
			if (ehAceitavel(u, v)) 
			{
				table[u][v] = i;
				totalMovimentos++;
				termina = tentaMovimento(i + 1, u, v); // tenta outro movimento
				if (!termina)
				{
					totalMovimentos++;
					table[u][v] = 0; // não sucedido. Descarta movimento
				}
			}
			k = k + 1;
		}
		return termina;
	}

	void resolverPasseio(int x, int y) // chama procedimento que faz movimento 
	{
		numDeOperacoes=0;
		totalMovimentos=0;
		table[x][y] = 1;
		Date inicio = Calendar.getInstance().getTime();
		System.out.println("Inicio = " + inicio);
		boolean problemaResolvido = tentaMovimento(2, x, y);
		Date fim = Calendar.getInstance().getTime();
		System.out.println("Fim    = " + fim);
		if (problemaResolvido)
		{
			System.out.println("Passeio resolvido em (" + ((fim.getTime() - inicio.getTime())/1000.0) + " s)");
			for (int i = 0; i < _num; i++)
			{
				for (int j = 0; j < _num; j++) {
					if (table[i][j]<100) System.out.print(" "); // apenas para deixar a impressao mais organizada 
					if (table[i][j]<10) System.out.print(" "); // apenas para deixar a impressao mais organizada
					System.out.print(table[i][j] + " ");
				}
				System.out.println();
			}
		} 
		else System.out.println("Passeio impossivel de ser realizado.");
		System.out.println("Numero total de movimentos do cavalo: " + totalMovimentos);
		System.out.println("Numero total de operacoes do algoritmo: " + numDeOperacoes);
	}

	public static void main(String[] args) {
	/*  Uso as seguintes linhas (comentadas abaixo) se nao quiser utilizar argumentos
	args = new String[3];
	args[0] = "8";
	args[1] = "0";
	args[2] = "0";
	*/
		if (args.length >= 3)
		{
			int n = Integer.parseInt(args[0]);
			int x = Integer.parseInt(args[1]);
			int y = Integer.parseInt(args[2]);
			new PasseioCavalo(n).resolverPasseio(x, y);
		}
		else System.out.println("Voce precisa passar 3 numeros como parametros de entrada: ordem_do_tabuleiro x0 y0");
	}
}
