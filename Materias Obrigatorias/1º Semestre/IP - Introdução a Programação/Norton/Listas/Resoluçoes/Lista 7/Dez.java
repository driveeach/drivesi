import java.util.Scanner;

class Dez {
	static void ordenacao (String numero) {
		char [] teste = numero.toCharArray ();
		for (int i = 0; i<teste.length; i+=3) {
			if (teste[i] == teste[i+1] && teste[i+1] == teste[i+2]) {
				System.out.println ("Os números são iguais");
			}
			else { 
				if (teste[i] <= teste[i+1] && teste[i+1] <= teste[i+2]) {
					System.out.println ("Os números estão ordenados em ordem crescente"); 
				}
				else{	
					if (teste[i+2] <= teste[i+1] && teste[i+1] <= teste[i]) {
						System.out.println ("Os números estão ordenados em ordem decrescente");
					}
					else {
						System.out.println ("Os números estão desordenados");
					}
				}
			}
		}				
	}
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite os caracteres:");
		String numero = sc.nextLine ();
		ordenacao (numero);
	}
}