public interface ComparadorDeFrutas {
	/**
	 * Retorna true se a "menor" que b; false, caso contrario. Interface n�o
	 * define o que significa "menor". Isto � deixado para cada classe concreta
	 * implementar.
	 */

	boolean ehMenor(Fruta a, Fruta b);
}
