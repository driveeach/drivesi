class Sete {
	public static void determinante (int[][]m1) {
		int determinante = m1[0][0] * m1[1][1] * m1[2][2] +
						   m1[1][0] * m1[2][1] * m1[0][2] +
						   m1[0][1] * m1[1][2] * m1[2][0] -
						   m1[0][2] * m1[1][1] * m1[2][0] -
						   m1[2][1] * m1[1][2] * m1[0][0] -
						   m1[1][0] * m1[0][1] * m1[2][2];
		System.out.print (determinante);
	}
	public static void main (String [] args) {
		int [][] m1 = {{2,2,3},
					   {1,4,3},
					   {3,2,3}};
		determinante (m1);
   }
}