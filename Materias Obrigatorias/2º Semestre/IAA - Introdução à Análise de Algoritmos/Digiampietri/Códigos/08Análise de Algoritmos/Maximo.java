package complexidade;

public class Maximo {

	/* Dado um arranjo de tamanho n, este metodos faz n-1 comparações para
	 * encontrar o maior valor do arranjo.
	 */
	static int maximoArranjo(int[] A){
		int max = A[0];
		for (int i=1;i<A.length;i++){
			if (max<A[i]) max = A[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] ARRANJ = new int[10];
		ARRANJ[0] = 12;
		ARRANJ[1] = 11;
		ARRANJ[2] = 32;
		ARRANJ[3] = 25;
		ARRANJ[4] = 23;
		ARRANJ[5] = 62;
		ARRANJ[6] = 44;
		ARRANJ[7] = 53;
		ARRANJ[8] = 41;
		ARRANJ[9] = 21;
		System.out.println("O maior valor eh: " + maximoArranjo(ARRANJ));
	}

}
