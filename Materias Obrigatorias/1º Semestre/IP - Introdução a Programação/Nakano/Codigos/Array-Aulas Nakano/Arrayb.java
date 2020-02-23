class Arrayb{
	public static void main(String[]args){
	
		double[][] A;
		A= new double[50][];    // 50 referencias para double[]
		
		for(int i=0;i<50;i++){
			A[i]=new double[500];
			for(int j=0;j<500;j++){
				
				System.out.print(A[i][j]+" ");
				}				
				System.out.println(" ");			
			
		}
	}
}




