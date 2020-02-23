/* alocacao dinamica de array de tipo primitivo */
class SingleDimension {

	public static void main (String args[]) {
		int[] array;           // declara a referência
		array = new int[10];   // aloca os objetos
		array[0]=15;
		array[1]=20;
		for (int i=0;i<array.length;i++) {
			System.out.println (array[i]);
		}
	}
}