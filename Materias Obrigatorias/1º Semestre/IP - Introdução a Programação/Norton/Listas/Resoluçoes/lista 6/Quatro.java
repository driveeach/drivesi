/* Construa a classe Inteiro que representa um numero inteiro. Essa classe deve ter os
seguintes atributos e metodos:
Atributos:
- int valor - Valor do inteiro representado.

Metodos para interacao com o usuario da classe:
- void carregaValor(int v) { Muda o valor representado por este objeto. O novo
valor deve ser v.
- int devolveValor() { Devolve o valor representado por este objeto.
- int devolveValorAbsoluto() { Devolve o valor absoluto do valor representado
por este objeto.
- void imprime() { Imprime algo que representa este objeto. Sugest~ao: imprima
o seu valor.
- int soma(int v) { Soma v ao valor deste objeto (valor + v). Este objeto passa
a representar o novo valor, que tambem deve ser devolvido pelo metodo.
- int subtrai(int v) { Subtrai v do valor deste objeto (valor - v). Este objeto
passa a representar o novo valor, que tambem deve ser devolvido pelo metodo.
- int multiplicaPor(int v) { Multiplica o valor deste objeto por v (valor * v).
Este objeto passa a representar o novo valor, que tambem deve ser devolvido
pelo metodo.
- int dividePor(int divisor) { Verifica se divisor é diferente de zero. Se n~ao,
imprime uma mensagem de erro e n~ao faz nada (devolve o valor inalterado).
Se for, divide o valor deste objeto por v (valor / divisor, divis~ao inteira). Este
objeto passa a representar o novo valor, que tambem deve ser devolvido pelo
metodo.*/ 
class Inteiro {
	int valor;
	void carregaValor (int v) {
		valor = v;
	}
	int devolveValor () {
		return valor;
	}
	int devolveValorAbsoluto () {
		return Math.abs(valor);
	}
	void imprime () {
		System.out.println (valor);
	}
	int soma (int v) {
		valor += v;
		return valor;
	}
	int subtrai (int v) {
		valor -= v;
		return valor;
	}
	int multiplicaPor (int v) {
		valor *= v;
		return valor;
	}
	int dividePor (int divisor) {
		if (divisor != 0) {
			valor /= divisor;
			return valor;
		}
		else {
			System.out.println ("erro, divisor igual a zero");
			return valor;
		}
	}
}

class Quatro {
	public static void main (String [] args) {
		Inteiro a = new Inteiro ();
		a.valor = 10;
		a.carregaValor(-10);
		System.out.println (a.devolveValor());
		System.out.println (a.devolveValorAbsoluto());
		a.imprime();
		System.out.println (a.soma (10));
		System.out.println (a.subtrai (10));
		System.out.println (a.multiplicaPor (10));
		System.out.println (a.dividePor (0));
	}
}