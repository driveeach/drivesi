package ep2_2012;


public class Cidade{
	public String nome; // Nome da cidade
	public boolean cidadeVisitada = false; // atributo auxiliar que pode ser utilizado para marcar se uma cidade foi visitada durante o backtracking
	public Distancia[] vizinhas; // arranjo que contem as distancias das cidades vizinhas (cada objeto do tipo Distancia contem uma cidade e uma distancia)

	/* construtor da classe Cidade */
	Cidade(String nomeDaCidade){
		nome = nomeDaCidade;
	}
	
	/* metodo para complementar o contrutor, adicionando o arranjo de cidades vizinhas */
	void adicionarArranjoDeDistancias(Distancia[] distVizinhas){
		vizinhas = distVizinhas;
	}
	
	/* metodo para verificar se uma dada cidade eh vizinha da cidade atual */
	boolean ehVizinha(Cidade cidade1){
		for (int i=0;i<vizinhas.length;i++){
			if (vizinhas[i].cidade == cidade1) return true;
		}
		return false;
	}
}