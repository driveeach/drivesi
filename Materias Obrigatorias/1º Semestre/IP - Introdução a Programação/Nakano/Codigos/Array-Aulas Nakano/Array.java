class Array{
	public static void main(String[]args){
	
		double[] A = new double[100];		
		double[] B = null;
		B = A;
				
		/*double[] A;       array de double
		A = new double[100];       cria na memoria um espaco para um vetor ou array double de 99 posicoes  */
		
		A[0]=5;
		for(int i=1;i<100;i++){
		A[i]=A[i-1]*1.06;	
		//System.out.println(B[i]+" "+ i);     
		}
		
		/*B[5] = 300;
		System.out.println(A[5]);*/		
		
		for(int i=1;i<=100;i++){
		A[i]=A[i-1]*1.06;	
		System.out.println(B[i]+" "+ i);     
		
		}
	}
}