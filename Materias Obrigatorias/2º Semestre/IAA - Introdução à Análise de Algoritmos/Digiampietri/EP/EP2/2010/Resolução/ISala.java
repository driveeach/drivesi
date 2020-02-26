package ep2;

/**
	Define a sala onde o robô será executado
*/
public interface ISala {
	////// CONSTANTES DE MAPEAMENTO DA SALA //////
	/** Tamanho da sala */
	public static final int TAMANHO_SALA = 10;
	/** Coordenada x do início da área de armazenagem (canto superior esquerdo) */
	public static final int X_INICIO_ARM = 0;
	/** Coordenada y do início da área de armazenagem (canto superior esquerdo) */
	public static final int Y_INICIO_ARM = 0;
	/** Coordenada x do fim da área de armazenagem (canto inferior direito) */
	public static final int X_FIM_ARM = 1;
	/** Coordenada y do fim da área de armazenagem (canto inferior direito) */
	public static final int Y_FIM_ARM = 1;
	
	
	////// CONSTANTES DE MARCAÇÃO DA SALA //////
	/** Define a marca de um bloco na sala */
	public static final int BLOCO_PRESENTE = 3;
	/** Define a marca de um obstáculo sala */
	public static final int OBSTACULO_PRESENTE = 2;
	/** Define a marca deixada pelo robô na sala */
	public static final int MARCA_PRESENTE = 1;
	/** Define a marca indicando que a posição está vazia */
	public static final int POSICAO_VAZIA = 0;
	
	
	/**
		Verifica se a posição de busca é válida. São consideradas inválidas apenas coordenadas que remetam a fora da área de busca.
		
		@param x Coordenada x da posição
		@param y Coordenada y da posição
		
		@return true se a posição for válida para busca, false se não.
	*/
	public boolean posicaoBuscaValida(int x, int y);
	
	/**
		Verifica se uma determinada posição da sala está marcada
		
		@param x Coordenada x da posição
		@param y Coordenada y da posição
		
		@return O marcador existente na posição
	*/
	public int marcadorEm(int x, int y);
	
	/**
		Marca uma determinada posição de busca com o marcador fornecido. Sobrescreve qualquer marcador que lá houvesse.
		
		@param x Coordenada x da posição
		@param y Coordenada y da posição
		@param marcador Marcador a ser inserido na posição
		
		@return false se as coordenadas não forem válidas para busca, true caso sejam
	*/
	public boolean marcaPosicaoBusca(int x, int y, int marcador);
	
	/**
		Marca uma determinada posição de armazenagem com um bloco.
		
		@param x Coordenada x da posição
		@param y Coordenada y da posição
		
		@return false se as coordenadas não forem válidas para armazenagem, ou então se a coordenada já estiver marcada, true caso consiga inserir o marcador BLOCO_PRESENTE na posição.
	*/
	public boolean marcaPosicaoArmazenagem(int x, int y);
	
	/**
		Remove o marcador de uma determinada posição da área de busca (exceto OBSTACULO_PRESENTE, que deixa intacto). Se a posição for inválida, nada faz.
		
		@param x Coordenada x da posição
		@param y Coordenada y da posição
	*/
	public void removeMarcador(int x, int y);
	
	/**
		@param x Coordenada x da posição verificada
		@param y Coordenada y da posição verificada
		
		@return true se as coordenadas estiverem dentro da área de armazenagem, false se não
	*/
	public boolean areaArmazenagem(int x, int y);
	
	/**
		Remove um determinado marcador de toda a área de busca da sala.
		
		@param marcador O marcador a ser removido
	*/
	public void removeMarcador(int marcador);
}
