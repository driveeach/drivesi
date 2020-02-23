import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Locale;

public class Matriz {
	protected double M[][];
	
	/** Este método lê a matriz armazenada no arquivo (espera-se que
	  * ela seja única). Esta tem que 
	  * começar com dois inteiros que informam o número de linhas e
	  * o número de colunas da matriz. Os números que se seguem são
	  * são os elementos da matriz, do tipo double, cujo separador de
	  * casa decimal é o ponto.
	  * @param nomeArquivo nome do arquivo que contém a matriz (única).
	  */
	void le(String nomeArquivo) {
		int[] dim = new int[2];
		File arquivo = new File(nomeArquivo);
		try {

			Scanner sc = new Scanner(arquivo);
			sc.useLocale ( new Locale ("US"));
			int i=0;
			while (sc.hasNextInt() && (i<2)) {
				dim[i++] = sc.nextInt();
            }

			int contador=0;
			M = new double[dim[0]][dim[1]];
			while (sc.hasNextDouble() && (contador<(dim[0]*dim[1]))) {
				M[contador/dim[1]][contador%dim[1]] = sc.nextDouble();
				contador++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }
	}
	
	/** Sua solucao deve ser escrita dentro deste método. */
	public double[] resolve () {
		double[] r = {0};    // inicializa, para este dummy rodar
		// solucao
		
		escalonar(); 										//CHAMA METODO DE ESCALONAMENTO
		int tLinha = tamanho(0), tColuna = tamanho(1);		//DEFINE DUAS VARIAVEIS QUE RECEBEM OS VALORES DE TAMANHO DO ARRAY
		r = new double[tLinha];								//DEFINE NOVO TAMANHO DO ARRAY
		double n; 											//INICIA VARIAVEL PARA RECEBER A SOMA DOS TERMOS
		for(int i = tLinha - 1; i >= 0; i--){
			n = 0.0;
			for(int j = tColuna - 1; j >= 0; j--){
				while (j >= r.length) j--;					//EVITA ESTOURO DO ARRAY
				n += M[i][j] * r[j]; 						//CALCULA SOMA DOS TERMOS DA FORMULA DADA
			}
			r[i] = (M[i][tColuna - 1] - n) / M[i][i]; 		//CALCULA O "X" ATRAVES DA FORMULA DADA
		}
		return r;
	}
	
	public void escalonar(){ 								// FAZ ESCALONAMENTO
		int tLinha = tamanho(0), tColuna = tamanho(1);		//DEFINE DUAS VARIAVEIS QUE RECEBEM OS VALORES DE TAMANHO DO ARRAY
		double[][] P = new double[tLinha][tColuna]; 		//DEFINE UM NOVO ARRAY DE MESMO TAMANHO DA MATRIZ M
		for(int k = 0; k < M.length; k++){
			for(int i = k + 1; i < M.length; i++){
				P[i][k] = M[i][k] / M[k][k];				//CALCULA A CONSTANTE DA LINHA PELA FORMULA DADA
				for(int j = k; j < M[i].length; j++){
					M[i][j] = M[i][j] - (P[i][k] * M[k][j]);//CALCULA NOVO VALOR DA POSIÇAO PELA FORMUALA DADA															
				}
			}
		}
	}
	
	public int tamanho(int a){ 								//METODO QUE RETORNA VALOR DE LINHA OU COLUNA, ESCOLHA FEITA ATRAVES DE INT INSERIDO
		int[] dim = new int[2];								// BASEADO NO METODO "le" DA CLASSE MATRIZ
		File arquivo = new File("teste0.m");				/**--> ARQUIVO MUDA CONFORME O TESTE <-- */
		try {
			Scanner sc = new Scanner(arquivo);
			sc.useLocale ( new Locale ("US"));
			int i=0;
			while (sc.hasNextInt() && (i<2)) {
				dim[i++] = sc.nextInt();
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }
		if(a != 0){
			return dim[1];									//RETORNA TAMANHO DA COLUNA
		}
		return dim[0];										//RETORNA TAMANHO DA LINHA
	}
	
	/** Este método imprime a matriz armazenada em M no mesmo formato como é lida do arquivo. */
	public void imprime () {
		if ((M!=null) && (M.length>0) && (M[0].length>0)) {
			System.out.println (M.length + " " + M[0].length);
			for (int i=0;i<M.length;i++) {
				for (int j=0;j<M[i].length;j++) {
					System.out.print (M[i][j] + " ");
				}
				System.out.println ();
			}
		}
		else {
			System.out.println ("Matriz inexistente ou de tamanho zero.");
		}
	}

	public static void main (String[] args) {
		Matriz M= new Matriz ();
		double[] r;
		M.le ("teste0.m");
		M.imprime ();
		System.out.println ("Solucao para teste0.m");
		r= M.resolve ();              // nao faz nada por enquanto
		if (r!=null) {
			for (int i=0;i<r.length;i++) {
				System.out.println (r[i]);
			}
		}
	}
}

