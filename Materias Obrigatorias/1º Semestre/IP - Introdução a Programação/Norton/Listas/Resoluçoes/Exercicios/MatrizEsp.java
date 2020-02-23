public class MatrizEsp{
	public static void main(String[] args){
		int A [][] = new int [25][25];
		int cont = 1;
		int sup = 0;
		int dir = 0;
		int inf = 0;
		int esq = 0;
		int metade = A.length / 2;
		for(int passadas = 0; passadas < metade; passadas++) {	
			for(int i = sup; i< A.length - dir; i++){
				
				A[esq][i] = cont;
				cont++;
			} 
			for(int i = sup + 1; i< A.length - inf; i++){
		
				A[i][A.length - dir - 1] = cont;
				cont++;
			}
			
			for(int i = A.length - dir - 2; i >= esq; i--) {
				A[A.length-1-inf][i] = cont;
				cont++;
			}
			for(int i = A.length - 2 - inf; i > sup; i--) {
				A[i][esq] = cont;
				cont++;
			}
			sup++;
			dir++;
			inf++;
			esq++;
		}
		if (A.length % 2!=0); {
			A[metade][metade] = cont;
		}
		//Imprime a matriz
		for(int i = 0; i< A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				System.out.print (A[i][j] + "\t");
			}
			System.out.println();
		}
	}
}