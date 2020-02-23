/* alocacao estatica de array de tipo primitivo */
class StaticTwoDimension {
	public static void main (String args[]) {
		// declara e inicializa estaticamente o tamanho eh dado pelo número de elementos inicializados.
		int array[][]={{0,1,2,3},{4,5,6,7},{8,9,10,11}};           
		array[0][0]=15;
		array[1][0]=20;
		for (int i=0;i<array.length;i++) {
			//System.out.print (array[i] + " ");
			for (int j=0;j<array[i].length;j++) {
			   System.out.print (array[i][j]+" ");
			}
			System.out.println("");
		}
	}
}