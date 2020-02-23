import java.util.Random;

class Insertion {
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

		/* ordena insertion  */
		for (int i=1;i<10;i++) {
			int ElementToInsert=Turma[i];
			int j=i;
			while ((j>0)&&(Turma[j-1]>ElementToInsert)) {
				Turma[j]=Turma[j-1];
				j--;
			}
			Turma[j]=ElementToInsert;
		}
		printArray (Turma, "Ordenado");
	}
}
