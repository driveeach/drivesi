class Vetor{
	public static void main (String[]args){
		int[] A,B;
		A = new int[10];
		B = new int[10];
		int c = 0;
		
		for (int i=0; i < A.length; i++){
			A[i]= i*2;
			B[i]=i+1;
			c+=A[i]*B[i];
		}
		
		System.out.println(c);
		/* for (int i=0; i<A.length; i++){
		c= A[i]*B[i];
		System.out.println(c);
		} */
		//System.out.println("A*B" + " = " + c);
	}
}