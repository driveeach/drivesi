package complexidade;

public class MaximoMinimo {
	public static int comparacoesIterativo = 0;
	public static int comparacoesIndFraca = 0;
	public static int comparacoesIndForte = 0;
	/* Dado um arranjo de tamanho n, este metodos faz 2*(n-1) comparações para
	 * encontrar o maior e o menor valor do arranjo (no pior caso).
	 */
	
	// METODOS ITERATIVO
	public static int[] maximoMinimoIterativo(int[] A){
		int[] res = new int[2];
		res[0] = A[0]; // res[0] contem o maximo
		res[1] = A[0]; // res[1] contem o minimo
		for (int i=1;i<A.length;i++){
			comparacoesIterativo++;
			if (A[i]>res[0]) res[0] = A[i];
			else {
				comparacoesIterativo++;
				if (A[i]<res[1]){
				   res[1] = A[i];
				}
			}
		}
		return res;
	}

	// METODO RECURSIVO (INDUÇÃO FRACA)
	public static int[] maximoMinimoIndFraca(int[] A,int elementos){
        // Criterio de parada: soh sobrou um numero a ser avaliado
		if (elementos==1){	
        	int[] res = new int[2];
        	res[0] = A[0]; // res[0] contem o maximo
        	res[1] = A[0]; // res[1] contem o minimo
        	return res;
		}else{
			elementos--;
        	int[] res = maximoMinimoIndFraca(A,elementos);
        	comparacoesIndFraca++;
        	if (A[elementos]>res[0]){
        		res[0] = A[elementos];
        	}else{
        		comparacoesIndFraca++;
        		if (A[elementos]<res[1]){
        			res[1] = A[elementos];
        		}
        	}
        	return res;
		}
	}
	
	// METODO RECURSIVO (INDUÇÃO FORTE)
	public static int[] maximoMinimoIndForte(int[] A,int ini, int fim){
        // Criterio de parada: soh sobrou um numero a ser avaliado
		if (ini==fim){	
        	int[] res = new int[2];
        	res[0] = A[ini]; // res[0] contem o maximo
        	res[1] = A[fim]; // res[1] contem o minimo
        	return res;
		}else{
			int meio = (ini+fim)/2;
        	int[] res = maximoMinimoIndForte(A,ini,meio);
        	int[] res2 = maximoMinimoIndForte(A,meio+1,fim);
        	comparacoesIndForte++;
        	if (res2[0]>res[0]) res[0] = res2[0];
        	comparacoesIndForte++;
        	if (res2[1]<res[1]) res[1] = res2[1];
        	return res;
		}
	}
	
	
}
