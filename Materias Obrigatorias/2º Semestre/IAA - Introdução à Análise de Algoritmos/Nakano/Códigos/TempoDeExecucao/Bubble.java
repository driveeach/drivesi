import java.util.*;
import java.lang.Math;

class Bubble {

    static void printArray (int[] Array, String msg) {
		System.out.println (msg);
		for (int i=0;i<Array.length;i++) {
			System.out.println (Array[i]);
		}
	}

	static int[] geraProblema (int n, int max) {
		Random RNG=new Random ();
		int[] Turma = new int[n];
		
		for (int i=0;i<n;i++) {
			Turma[i]=RNG.nextInt()%max;
		}
		return Turma;
	}

	public static void main (String[] args) {
		/* Inicializacoes */
		Random RNG=new Random ();
		int[] Turma;
		long tinicio, tfinal;
		for (int l=1;l<=6; l++) {
			int size=(int) Math.pow (2,l);
			for (int nprob=0;nprob<(int) Math.sqrt(size);nprob++) {
				Turma=geraProblema (size, 500);
				if (args.length==0) printArray (Turma, "Desordenado");

				tinicio=System.nanoTime();
				/* ordena bubble */
				for (int i=0;i<Turma.length;i++) {
					for (int j=Turma.length-1;j>i;j--) {
						if (Turma[j]<Turma[j-1]) {
							int alunoaux=Turma[j];
							Turma[j]=Turma[j-1];
							Turma[j-1]=alunoaux;
						}
					}
				}
				tfinal=System.nanoTime();
				if (args.length==0) printArray (Turma, "Ordenado em " + (tfinal-tinicio));
				else System.out.println (size + ";" + (tfinal-tinicio));
			}
		}
	}
}

