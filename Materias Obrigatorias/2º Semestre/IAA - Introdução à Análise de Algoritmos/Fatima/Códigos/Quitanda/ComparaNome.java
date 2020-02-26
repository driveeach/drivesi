public class ComparaNome implements ComparadorDeFrutas {

	public boolean ehMenor(Fruta a, Fruta b) {
		return (a.nome.compareTo(b.nome) < 0);
	}

}
