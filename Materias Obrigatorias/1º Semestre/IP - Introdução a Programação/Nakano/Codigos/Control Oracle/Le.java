import java.util.*;  //

class Le {
	public static void main (String[] args) {
		Scanner sc;      // declara a variavel que será 
						 // usada como scanner
		int a;
		sc=new Scanner(System.in);  
						   /* cria um objeto da classe Scanner
						   / que usa a entrada do sistema
						   / (teclado)
						   / e atribui à variavel sc
						   / dá para usar Scanner para ler de
						   / arquivo. */

		a=sc.nextInt();    // invoca o método nextInt() do
						   // Scanner sc. O método espera que
						   // um int seja digitado.

		System.out.println (a);  // imprime o int.
	}
}

