public class ComparaPeso implements ComparadorDeFrutas {

	public boolean ehMenor(Fruta a, Fruta b) {
		return (a.peso < b.peso);
	}

}
