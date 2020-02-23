/* Crie uma classe TestaInteiro que verifica o funcionamento correto da classe Inteiro em
diversas situacoes (criando objetos Inteiro e testando-os). Lembre-se de verificar casos
como a divis~ao por diversos valores. */

class Cinco {
	public static void main (String [] args) {
		Inteiro b = new Inteiro ();
		for (int i = 0; i<11; i++) {
			b.valor = i;
			b.carregaValor(i);
			System.out.println (b.devolveValor());
			System.out.println (b.devolveValorAbsoluto());
			b.imprime();
			System.out.println (b.soma (i));
			System.out.println (b.subtrai (i));
			System.out.println (b.multiplicaPor (i));
			for (int j = 0; j < 11; j++) {
				System.out.println (b.dividePor (j));
			}
		}
	}
}