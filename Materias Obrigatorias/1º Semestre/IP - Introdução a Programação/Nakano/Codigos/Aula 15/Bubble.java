import java.util.Random;

class Bubble {
    public static final int NAO_ENCONTRADO=-1;

    static void printArray (int[] Array, String msg) {
		System.out.println (msg);
		for (int i=0;i<10;i++) {
			System.out.println (Array[i]);
		}
	}
	
	public static void main (String[] args) {
		/* Inicializações */
		Random RNG=new Random ();
		int[] Turma = new int[10];
		
		for (int i=0;i<10;i++) {
			Turma[i]=RNG.nextInt()%25;
		}
		
		printArray (Turma, "Desordenado");

		/* ordena bubble */
		for (int i=0;i<10;i++) {
			for (int j=9;j>i;j--) {
				if (Turma[j]<Turma[j-1]) {
					int alunoaux=Turma[j];
					Turma[j]=Turma[j-1];
					Turma[j-1]=alunoaux;
				}
			}
		}

		printArray (Turma, "Ordenado");
	}
}
