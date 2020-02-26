package recursao;

public class HanoiExecutavel {

	public static void main(String[] args) {
		Hanoi.move('o','d','t',4);
		System.out.println("\nNumero total de operacoes: " + Hanoi.numeroDeOperacoes);
	}

}
