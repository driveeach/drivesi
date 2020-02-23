/* Escreva uma classe Ola com um unico metodo cumprimenta que, a cada chamada,
cumprimenta o usuario de uma entre 3 maneiras diferentes. Dica: use um atributo para,
dependendo de seu valor, escolher qual das maneiras sera usada; depois de imprimir a
mensagem, altere o valor do atributo.  */
class Dois {
	void cumprimenta (int maneira) {
		if (maneira == 1) System.out.println ("Oi");
		if (maneira == 2) System.out.println ("Ola");
		if (maneira == 3) System.out.println ("Hi");
	}
	public static void main (String [] args) {
		Dois ola = new Dois();
		ola.cumprimenta(2);
		ola.cumprimenta(1);
	}
}

