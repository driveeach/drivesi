/*********************************************/
/** ACH2001 - Introducao a Programacao 		**/
/** EACH-USP - Primeiro Semestre de 2014    **/
/** <Matutino> - <Fabio Nakano>             **/
/**                                         **/
/** Segundo Exercicio-Programa              **/
/** Arquivo: <Juros.java>                   **/
/**                                         **/
/** <Lucas Saccumann Miranda> <8921687>     **/
/** 									    **/
/** <28/07/2014>                            **/
/*********************************************/
import lista.ListaDepositos;
import depositos.Deposito;


/*
	VOCÊ PODE IMPORTAR AS CLASSES QUE DESEJAR AQUI
*/
import lista.ListaSimples;
import lista.No;
import depositos.Data;


class Juros {
	
	/*
		VOCÊ PODE INCLUIR OS MÉTODOS E ATRIBUTOS QUE QUISER, DESDE QUE OS JUROS SEJAM CALCULADOS COM UMA ÚNICA CHAMADA A "juros".
	*/
	/**
		Calcula os juros pagos, a partir de uma seqüência de depósitos e um saldo final, com a precisão indicada. Usa o método de Newton-Raphson para tal.
		
		@param depositos Lista de depósitos (não incluindo o saldo final)
		@param saldo Saldo final da aplicação
		@param epsilon Precisão do cálculo (0 < epsilon < 1)
		
		@return Valor dos juros, ou NAN em caso de erro (epsilon fora dos limites, lista de depósitos sem depósitos, saldo null)
	*/
	public static double juros(ListaDepositos depositos, Deposito saldo, double epsilon) {
		// SEU CÓDIGO DEVE VIR AQUI
		//Declaração das variaveis do metodo
		double f;
		double fd;
		double jurosA=0.5;
		double jurosB=0;
		double jurosC=0;
		int deltaD;
		
		if ((saldo != null)&&(depositos != null)&&(0<epsilon)&&(epsilon<1)){ /*Este if impede que as próximas ações sejam executadas 
		com uma lista e saldo nulos, e garante que a precisão epsilon esteja entre 0 e 1 */
			do {
				int i = depositos.elementos(); // conta o número de nós
				f = 0;
				fd = 0;
				No no =  depositos.getInicio();
				for(int b=0;b<i;b++){	// Calcula a função de "Newton-Raphson" enquanto houver nós
					deltaD = no.getDeposito().getData().mesesEntre(saldo.getData());
					f = f + no.getDeposito().getValor() *(Math.pow((jurosA+1),deltaD));
					fd = fd + deltaD * (no.getDeposito().getValor() *(Math.pow((jurosA+1),(deltaD - 1))));
					no = no.getProx();
				}
				f = f - saldo.getValor();
				jurosB = jurosA - (f / fd);  // J[k+1] = J[k] - f(J[k])/f'(J[k])  como na fórmula 
				jurosC = Math.abs(jurosB - jurosA); // atribuindo o valor de |J[k+1] - J[k]|
				jurosA = jurosB;
			} 
			while ( jurosC >= epsilon ); // condição da execução
			return jurosB; // Retorna juros procurado
		}
		return Double .NaN;
		
	}
	public static void main(String[] args) {
		// para seus testes
	}

	
}

