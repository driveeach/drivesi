package ep3;

/*********************************************************************/
/** ACH 2002 - Introducao a Ciencia da Computacao II                **/
/** EACH-USP - Segundo Semestre de 2010                             **/
/**                                                                 **/
/** <turma> - <nome do professor>                                   **/
/**                                                                 **/
/** Terceiro Exercicio-Programa                                     **/
/**                                                                 **/
/** <nome do(a) aluno(a)> <numero USP>                              **/
/**                                                                 **/
/*********************************************************************/


/* COMENTARIOS GERAIS
 * Seguindo os criterios de selecao, um objeto so podera ser colocado na
 *   mochila caso a mochila suporte o total de peso.
 * O total de peso ao se "colocar" um objeto (do tipo Objeto) eh:
 *                  mochila.getPesoUsado() + objeto.getPeso()
 * Colocar um objeto na mochila significa alterar os seguintes campos
 *   da mochila: pesoUsado, valorDentroDaMochila e numObjetosNaMochila.
*/
public abstract class MetodosGulosos {

	/* Este metodo deve implementar um algoritmo guloso que selecione objetos
	 *   da listaDeObjetosDisponiveis a serem colocados na mochila de acordo
	 *   com o criterio: 'objetos de menor peso primeiro', caso dois objetos
	 *   tenham o mesmo peso, o criterio de desempate sera: 
	 *   'objetos de maior valor primeiro' (apenas para os empates em peso).
	 */
	public static Mochila utilizaMenorPeso(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis) {
		Mochila mochila = new Mochila(pesoMaximoDaMochila);

		// TODO
		
		return mochila;
	}

	
	/* Este metodo deve implementar um algoritmo guloso que selecione objetos
	 *   da listaDeObjetosDisponiveis a serem colocados na mochila de acordo
	 *   com o criterio: 'objetos de maior valor primeiro', caso dois objetos
	 *   tenham o mesmo valor, o criterio de desempate sera: 
	 *   'objetos de menor peso primeiro' (apenas para os empates em valor).
	 */
	public static Mochila utilizaMaiorValor(double pesoMaximoDaMochila,	Objeto[] listaDeObjetosDisponiveis) {
		Mochila mochila = new Mochila(pesoMaximoDaMochila);

		// TODO
		
		return mochila;
	}

	
	/* Este metodo deve implementar um algoritmo guloso que selecione objetos
	 *   da listaDeObjetosDisponiveis a serem colocados na mochila de acordo
	 *   com o criterio: 'objetos de maior valor/peso primeiro (valor dividido por
	 *   peso primeiro)', caso dois objetos tenham o mesmo valor/peso, o criterio 
	 *   de desempate sera: 'objetos de maior peso primeiro' (apenas para os empates).
	 */
	public static Mochila utilizaMaiorValorDivididoPorPeso(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis) {
		Mochila mochila = new Mochila(pesoMaximoDaMochila);

		// TODO

		return mochila;
	}

	
}
