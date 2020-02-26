import java.util.*;
class Excecao {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		boolean continuar = true;
		do {
			try {
				System.out.print("Digite um numerador: ");
				int num = sc.nextInt();
				System.out.print("Digite um denominador: ");
				int den = sc.nextInt();
				int resp = num/den;
				System.out.println("Resultado de " +num+ "/" +den+ " = " +resp);
				continuar = false;
			}
			catch (InputMismatchException inputMismatchException){
				System.err.printf ("Excecao: %s\n", inputMismatchException);
				sc.nextLine();
				System.out.println("Voce tem que digitar numeros, tente novamente");
			}
			catch (ArithmeticException arithmeticException) {
				System.err.printf ("Excecao: %s\n", arithmeticException);
				sc.nextLine();
				System.out.println("Nao existe divisao por 0, tente novamente");
			}
		} while (continuar);
	}
}