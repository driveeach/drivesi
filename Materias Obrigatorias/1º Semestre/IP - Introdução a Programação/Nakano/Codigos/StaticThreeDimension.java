/* alocacao estatica de array de tipo primitivo */
class StaticThreeDimension {
	public static void main (String args[]) {
		// declara e inicializa estaticamente o tamanho eh dado pelo número de elementos inicializados.
		int array[][][]={{{0,1,2,3},{4,5,6,7},{8,9,10,11}},{{10,11,12,13},{14,15,16,17},{18,91,110,111}},{{20,12,22,32},{42,52,62,72},{82,92,102,112}}};           
		array[0][0][2]=15;
		array[1][0][3]=20;
		for (int i=0;i<array.length;i++) {
			//System.out.print (array[i] + " ");
			for (int j=0;j<array[i].length;j++) {
				for (int k=0;k<array[i][j].length;k++) {
					System.out.print (array[i][j][k]+" ");
				}
				System.out.println("");
			}
			System.out.println("");
		}
	}
}