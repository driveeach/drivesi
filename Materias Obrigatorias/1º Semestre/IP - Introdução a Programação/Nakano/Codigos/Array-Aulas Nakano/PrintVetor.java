public class PrintVetor{

	public static void main(String[]args){
		double[] A;
		A= new double [20];
		
		for (int i=0;i<A.length;i++){
			A[i]=3.7*i;
			}
			imprimeVetor (A); 
			System.out.println(A[20]);
		}
	static void imprimeVetor(double[] v){
		v[1]=3.1416;
		for (int i=0;i<v.length;i++){
		System.out.println(v[i]);
		}		
	}
}