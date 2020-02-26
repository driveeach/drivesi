public class ComparaPreco implements ComparadorDeFrutas{

	public boolean ehMenor(Fruta a, Fruta b) {
		return (a.preco < b.preco);
	}

}
