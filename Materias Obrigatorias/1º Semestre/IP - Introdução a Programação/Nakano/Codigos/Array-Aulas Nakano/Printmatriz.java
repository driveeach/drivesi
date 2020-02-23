class Printmatriz{
	public static void main(String[] args){
		double[][] A;
		A= new double[5][];   	
		for(int i=0;i<A.length;i++){
			A[i]=new double[5];
			imprimeVetor(A[i]);	
		}	 
	}
	static void imprimeVetor(double[] V){
		for( int j=0;j<V.length;j++){
			System.out.print(V[j]+" ");			
		}	
		System.out.println(" ");		
	}
}