import java.util.*;
class Ex1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		boolean continuar = true;
		do {
			try {
				int [] list = new int [4];
				int x = 10;
				System.out.println("Insira um denominador");
				int y = sc.nextInt();
				int resp = x/y;
				list[resp] = resp;
				continuar = false;
			}
			catch (ArithmeticException arithmeticException) {
				System.err.printf ("Excecao: %s\n", arithmeticException);
				sc.nextLine();
				System.out.println("Nao existe divisao por 0, tente novamente");
			
			}
			catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
				System.err.printf ("Excecao: %s\n", arrayIndexOutOfBoundsException);
				sc.nextLine();
				System.out.println("Digite outro denominador");
			}
			catch (InputMismatchException inputMismatchException) {
				System.err.printf ("Excecao: %s\n", inputMismatchException);
				sc.nextLine();
				System.out.println("Digite apenas numeros");
			}
		} while (continuar);
	}
}