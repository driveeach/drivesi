public class Hanoi {

	void move(char origem, char destino, char auxiliar, int nDiscos) {
		if (nDiscos == 1) {
			System.out.println("Movendo Disco de " + origem + " para " + destino);
		}
		else {
			move(origem, auxiliar, destino, nDiscos - 1);
			move(origem, destino, auxiliar, 1);
			move(auxiliar, destino, origem, nDiscos - 1);
		}
	}
}
