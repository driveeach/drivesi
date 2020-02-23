import java.util.Scanner;
class Quinze{
	static void mostraprimo(int n){
		System.out.print("\t1\t2\t");
		boolean a = false;
		for(int i =2;n>0;i++){
			a = false;
			for(int j =2;j<i;i++){
				if((i%j )!= 0)break;
				else a =true;
			}
			if(a) System.out.print("\t"+i+"\t");
			n--;
		}    
	} 
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a qtde de primos");
		mostraprimo(sc.nextInt());
	}
}
