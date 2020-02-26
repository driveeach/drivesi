package recursao;

public class Hanoi {
	static public long numeroDeOperacoes = 0;
	static void move(char origem, char destino, char auxiliar, int nDiscos) {
		if (nDiscos == 1) {
			System.out.println("Movendo disco de " + origem + " para " + destino);
			numeroDeOperacoes++;
		}
		else {
			move(origem, auxiliar, destino, nDiscos - 1);
			move(origem, destino, auxiliar, 1);
			move(auxiliar, destino, origem, nDiscos - 1);
		}
	}
}
