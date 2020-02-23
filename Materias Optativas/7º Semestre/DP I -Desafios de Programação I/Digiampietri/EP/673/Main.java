import java.util.Scanner;
import java.util.Stack;

class Main {

	public static final char ABRE_PARENTESES = '(';
	public static final char FECHA_PARENTESES = ')';
	public static final char ABRE_COLCHETES = '[';
	public static final char FECHA_COLCHETES = ']';

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int quantidade = scan.nextInt();
		String sentenca = scan.nextLine();
		Stack<Character> pilha;

		for (int i = quantidade; i > 0; i--) {
			pilha = new Stack<Character>();
			sentenca = scan.nextLine();
			for (int j = 0; j < sentenca.length(); j++) {
				if (sentenca.charAt(j) == ABRE_PARENTESES) {
					pilha.push(sentenca.charAt(j));
				} else if (sentenca.charAt(j) == ABRE_COLCHETES) {
					pilha.push(sentenca.charAt(j));
				} else if (sentenca.charAt(j) == FECHA_PARENTESES) {
					if (pilha.isEmpty() || pilha.pop() != ABRE_PARENTESES) {
						pilha.push(sentenca.charAt(j));
						break;
					}
				} else if (sentenca.charAt(j) == FECHA_COLCHETES) {
					if (pilha.isEmpty() || pilha.pop() != ABRE_COLCHETES) {
						pilha.push(sentenca.charAt(j));
						break;
					}
				}
			}
			if (pilha.isEmpty()) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
