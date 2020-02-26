package ep2;

/**
	Classe para envio de mensagens ao usuário, a ser usada sempre que o robô executar um movimento.
*/
public class Mensageiro {
	/** Define a ação Busca */
	public static final int BUSCA = 0;
	/** Define a ação Retorno */
	public static final int RETORNO = 1;
	/** Define a ação Obstáculo encontrado */
	public static final int OBSTACULO = 2;
	/** Define a ação Captura de Bloco */
	public static final int CAPTURA = 3;
	/** Define a ação Armazenagem de Bloco */
	public static final int ARMAZENAGEM = 4;
	
	/**
		Escreve mensagem na tela, descrevendo ação do robô.
		
		@param tipo Tipo de ação, conforme definida nas constantes de 
		@param x Coordenada x a ser incluída na mensagem
		@param y Coordenada y a ser incluída na mensagem
	*/
	public static void mensagem(int tipo, int x, int y) {
		switch (tipo) {
			case BUSCA: System.out.println("Busca em ("+x+","+y+")");
					  break;
			case RETORNO: System.out.println("Retorno a ("+x+","+y+")");
					  break;
			case OBSTACULO: System.out.println("Obstáculo detectado em ("+x+","+y+")");
					  break;
			case CAPTURA: System.out.println("Bloco recolhido em ("+x+","+y+")");
					  break;
			case ARMAZENAGEM: System.out.println("Bloco armazenado em ("+x+","+y+")");
					  break;
			default : System.out.println("Erro em ("+x+","+y+")"+" - Tipo de movimento inválido.");
		}
	}
	
	/**
		Mensagem enviada caso o robô não encontre algum bloco
	*/
	public static void msgNaoAchou() {
		System.out.println("Mais nenhum bloco foi encontrado.");
	}
	
	/**
		Mensagem enviada caso o termine encontrando os 4 blocos
	*/
	public static void msgFim() {
		System.out.println("Busca terminada. Blocos recuperados.");
	}
}
