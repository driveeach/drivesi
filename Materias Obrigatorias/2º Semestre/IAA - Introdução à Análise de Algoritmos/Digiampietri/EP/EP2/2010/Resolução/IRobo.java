package ep2;

/**
	Interface para definição das ações possíveis ao nosso Robô
*/
public interface IRobo {
	/** Define o número de blocos do sistema */
	public static final int N_BLOCOS = 4;
	
	/**
		Busca um bloco na área de busca da sala, usando busca em profundidade, a partir das coordenadas (x,y). A cada passo dado, o programa envia mensagem ao usuário avisando (via Mensageiro)
		
		@param x Coordenada x da área de busca
		@param y Coordenada y da área de busca
		
		@return True, caso encontre um bloco nessa busca. False se correr toda a área possível e não encontrar.
	*/
	public boolean buscaBloco(int x, int y);
	
	/**
		Procura uma posição vazia dentro da área de armazenagem, armazenando lá um bloco. O armazenamento do bloco é feito colocando-se um ISala.BLOCO_PRESENTE nessa posição. Despreze os movimentos do robô dentro da área de armazenagem.
		
		Após fazer a marcação, o programa envia mensagem ao usuário avisando (via Mensageiro)
		
		@return true se efetivamente armazenou algo, false se não pode armazenar (pela área estar cheia, por exemplo).
	*/
	public boolean guardaBloco();
	
	/**
		Adiciona um obstáculo à área de busca do robô. Se a coordenada passada não estiver na área de busca, não faz nada.
		
		@param x Coordenada x da área de busca
		@param y Coordenada y da área de busca
	*/
	public void adicionaObstaculo(int x, int y);
	
	/**
		Adiciona um bloco à área de busca do robô. Se a coordenada passada não estiver na área de busca, não faz nada.
		
		@param x Coordenada x da área de busca
		@param y Coordenada y da área de busca
	*/
	public void adicionaBloco(int x, int y);
	
	/**
		Prepara a sala para uma nova busca, removendo marcas MARCA_PRESENTE
	*/
	public void novaBusca();
	
	/**
		Executa a busca por todos os blocos espalhados pela sala.
	*/
	public void buscaBlocos();
}
