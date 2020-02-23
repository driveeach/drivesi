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
		double [] r={0};    // inicializa, para este dummy rodar
		// solucao
		return r;
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
		M.le ("teste3.m");
		M.imprime ();
		System.out.println ("Solucao para teste3.m");
		r= M.resolve ();              // nao faz nada
		if (r!=null) {
			for (int i=0;i<r.length;i++) {
				System.out.println (r[i]);
			}
		}
	}
}

