package heranca;

public class Exemplo1 {
	public static void main(String[] args)
	{
		Exemplo1Super e1 = new Exemplo1Super(1,2,3,4);
		Exemplo1Sub e2 = new Exemplo1Sub(6,7,8,9,10);
		Exemplo1Super e3 = new Exemplo1Sub(11,12,13,14,15); // Funciona pois toda sub-classe "também é" classe
		// Exemplo1Sub e4 = new Exemplo1Super(21,21,22,23); // Nao funciona pois uma super-classe não é classe
		e1.imprime();
		e2.imprime();
		e3.imprime();
		((Exemplo1Super)e3).imprime();
		// e3.novoImprime(); // Nao funciona pois, mesmo e3 contendo um objeto do tipo Exemplo1Sub, a variável é do tipo Exemplo1Super
		((Exemplo1Sub)e3).novoImprime(); // o 'cast' permite a execucao do metodo novoImprime, note que o programador precisa ter certeza que o conteudo de e3 é compatível com Exemplo1Sub
		// ((Exemplo1Sub)e1).novoImprime(); // Nao funciona pois o objeto que está em e1 não é compatível com o tipo Exemplo1Sub [porém não dará erro de compilação e sim de execução]
		
		
		/* Note que:
		 * 1. e3 eh uma variavel do tipo Exmeplo1Super e recebe um objeto do
		 *    tipo Exemplo1Sub;
		 * 2. a classe Exemplo1Sub nao tem acesso (visibilidade) ao atributo
		 *    d da super-classe, porem este atributo existe e eh impresso.
		 * 3. o cast da penultima linha significa "leia e3 como sendo do tipo Exemplo1Sub, garantindo
		 *    assim acesso ao metodo 'novoImprime()' da sub-classe Exemplo1Sub.
		 *    Isto funciona pois a instancia e3 contem um objeto do tipo Exemplo1Sub
		 */
	}
}


/* RESULTADO DA EXECUCAO DO PROGRAMA:

Metodo imprime da super-class Exemplo1Super.
A = 1
B = 2
C = 3
D = 4
Metodo imprime da sub-classe Exemplo1Sub.
Metodo imprime da super-class Exemplo1Super.
A = 6
B = 7
C = 8
D = 9
E = 10
Metodo imprime da sub-classe Exemplo1Sub.
Metodo imprime da super-class Exemplo1Super.
A = 11
B = 12
C = 13
D = 14
E = 15
Metodo imprime da sub-classe Exemplo1Sub.
Metodo imprime da super-class Exemplo1Super.
A = 11
B = 12
C = 13
D = 14
E = 15

*/