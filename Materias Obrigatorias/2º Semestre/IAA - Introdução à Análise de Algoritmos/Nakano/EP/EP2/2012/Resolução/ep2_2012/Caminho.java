/****************************************************************/ 
/** ACH 2002 - Introducao a Ciencia da Computacao II           **/ 
/** EACH-USP - Segundo Semestre de 2012                        **/ 
/**                                                            **/ 
/** Turma 04 - Prof. Lucinao Digiampietri                      **/ 
/**                                                            **/ 
/** Segundo Exercicio-Programa                                 **/ 
/**                                                            **/ 
/** Ralph Ramos Pinheiro de Oliveira - 6411607		           **/ 
/**                                                            **/ 
/****************************************************************/ 


package ep2_2012;



public abstract class Caminho {
	static Mapa mainMap;
	static Cidade destinoFinal;
	static Cidade[] CaminhoMelhor;
	static Cidade[] CaminhoAtual;
	static int CidKey;
	
	public static Cidade[] encontrarMelhorCaminho(Mapa mapa, Cidade origem, Cidade destino){
		resetAttr();
		mainMap = mapa;
		destinoFinal = destino;
		CaminhoMelhor = new Cidade[mapa.numeroDeCidades()]; 
		CaminhoAtual = new Cidade[mapa.numeroDeCidades()];
		
		proxCidade(origem);
			
		return CaminhoMelhor;
	}
	

	
	private static void proxCidade(Cidade origem){
		
		// Adiciona a origem ao caminho atual
		addCidade(origem);
		
		// Se todo arranjo foi percorrido e chegamos ao destino final
		if (CidKey == mainMap.numeroDeCidades() && origem == destinoFinal){
			// Um caminho válido foi encontrado
			addCaminho();
		}else{
			// Busca a próxima cidade não visitada
			for (int i = 0 ; i< origem.vizinhas.length; i++){
					if (!visitada(origem.vizinhas[i].cidade)){				
						if(origem.vizinhas[i].cidade != destinoFinal || CidKey == mainMap.numeroDeCidades()-1){
							proxCidade(origem.vizinhas[i].cidade);
						}	
					}
				}	
		}
		
		voltaCid();
	}
	
	private static void addCidade(Cidade cidade){
		// Adiciona cidade ao caminho atual
		CaminhoAtual[CidKey] = cidade;
		CidKey++;
		
		// Marca no mapa como cidade visitada
		for(int i =0; i < mainMap.cidadesDoMapa.length; i++){
			if (mainMap.cidadesDoMapa[i] == cidade){
				mainMap.cidadesDoMapa[i].cidadeVisitada = true;
			}
		}
		
	}
	
	private static void addCaminho(){
		
		if (CaminhoMelhor[0]==null || 
			Mapa.calcularDistanciaDoCaminho(CaminhoAtual) < Mapa.calcularDistanciaDoCaminho(CaminhoMelhor)){
			setCaminhoMelhor(CaminhoAtual);
		}
		
	}
	
	
	private static Cidade voltaCid(){
		
		//BackTracking, volta para a cidade anterior
		
		CidKey--;
		Cidade cidade = CaminhoAtual[CidKey];
		CaminhoAtual[CidKey]= null;
				
		for(int i =0; i < mainMap.cidadesDoMapa.length; i++){
			if (mainMap.cidadesDoMapa[i] == cidade){
				mainMap.cidadesDoMapa[i].cidadeVisitada = false;
			
			}
		}
		
		return cidade;
		
	}
	
	private static void setCaminhoMelhor(Cidade[] caminho)
	{
		// Clona o valor da caminho para CaminhoMelhor
		for (int i = 0; i< caminho.length; i++)
		{
			CaminhoMelhor[i] = caminho[i];
		}
		
	}
	
	
	private static boolean visitada(Cidade cidade){
		//Verifica se a cidade já foi visitada
		for(int i =0; i < mainMap.cidadesDoMapa.length; i++){
			if (mainMap.cidadesDoMapa[i] == cidade){
				return mainMap.cidadesDoMapa[i].cidadeVisitada;
			}
		}
		
		return false;
	}
	
	private static void resetAttr(){
		// Inicializa atributos estáticos
		
		mainMap = null;
		destinoFinal = null;
		CaminhoAtual = null;
		CaminhoMelhor = null;
		CidKey = 0;
		
	}

}
