package ep2_2012;


import java.util.Random;

public class Mapa {
	Cidade[] cidadesDoMapa; // arranjo com todas as cidades do mapa

	/* construtor da classe Mapa */
	Mapa (Cidade[] cids){
		cidadesDoMapa = cids;
	}
	
	/* metodo para retornar o numero de cidades do mapa atual */
	public int numeroDeCidades(){
		return cidadesDoMapa.length;
	}
	
	/* dado um caminho, calcula a distancia total deste caminho */
	static double calcularDistanciaDoCaminho(Cidade[] caminho){
		double distancia = 0;
		for (int i=1;i<caminho.length;i++){
			if (caminho[i-1] == null || caminho[i] == null){
				System.err.println("Ha elementos vazios (null) no arranjo de cidades.");
			}else if (caminho[i-1].ehVizinha(caminho[i])){
				distancia += distanciaDuasCidades(caminho[i-1],caminho[i]);
			}else{
				System.err.println("Caminho irregular: as cidades " + caminho[i].nome + " e "+ caminho[i].nome + " nao sao vizinhas.");
			}
		}
		return distancia;
	}

	/* retorna a distancia entre duas cidades, caso sejam vizinhas, ou -1 caso contrario
	 * Dica: eh possivel (e recomendavel) implementar uma boa solucao sem utilizar este metodo 
	 */
	static double distanciaDuasCidades(Cidade cidade1, Cidade cidade2){
		double distancia = -1;
		Distancia temp;
		for (int i=0;i<cidade1.vizinhas.length;i++){
			temp = cidade1.vizinhas[i];
			if (temp.cidade == cidade2) return temp.distancia;
		}
		System.err.println("As cidades " + cidade1 + " e " + cidade2 + " nao sao vizinhas.");
		return distancia;
	}
	
	/* metodo que imprime as cidades e distancias entre as cidades de
	 * um dadao caminho 
	 */
	static void imprimirCaminho(Cidade[] caminho){
		System.out.println("Imprimindo caminho ("+caminho.length+" cidades):");
		for (int i=0;i<caminho.length;i++){
			if (caminho[i] == null){
				System.err.println("Ha elementos vazios (null) no arranjo de cidades.");
			}else if (i==0){
				System.out.print(caminho[i].nome);
			}else if (caminho[i-1].ehVizinha(caminho[i])){
				System.out.print(" ["+Mapa.distanciaDuasCidades(caminho[i-1], caminho[i]) + "] "+ caminho[i].nome);
			}else{
				System.err.println("Caminho irregular: as cidades " + caminho[i].nome + " e "+ caminho[i].nome + " nao sao vizinhas.");
			}
		}
		System.out.println();
	}
	
	/* metodo que gera mapas aleatorios, dados o numero de cidades e o numero de
	 * ligacoes (estradas) entre as cidades
	 */
	static Mapa geradorDeMapa(int cidades, int ligacoes){
		if (cidades < 2) cidades = 2;
		Mapa mapa = new Mapa(new Cidade[cidades]);
		for (int c=0;c<cidades;c++){
			mapa.cidadesDoMapa[c] = new Cidade("cidade" + (c+1));
		}
		int cont=0;
		if (ligacoes > (cidades-1)*(cidades-1)/2) ligacoes = (cidades-1)*(cidades-1)/2;
		int[] numVizinhas = new int[cidades];
		int[][] ads = new int[cidades][cidades];
		Random r = new Random();
		int distancia;
		for (int i=0;i<cidades-1;i++){
			numVizinhas[i] = 2;
			ads[i][i+1] = 100;
			ads[i+1][i] = 100;
		}
		numVizinhas[cidades-1] = 2;
		ads[cidades-1][0] = 100;
		ads[0][cidades-1] = 100;
		cont = cidades;
		while (cont<ligacoes){
			int id1 = r.nextInt(cidades);
			int id2 = r.nextInt(cidades);
			if (id1 != id2 && ads[id1][id2]==0){
				distancia = r.nextInt(100)+1;
				cont++;
				ads[id1][id2] = distancia;
				ads[id2][id1] = distancia;
				numVizinhas[id1]++;
				numVizinhas[id2]++;
			}
		}
		int indice;
		for (int c=0;c<cidades;c++){
			indice = 0;
			mapa.cidadesDoMapa[c].vizinhas = new Distancia[numVizinhas[c]];
			for (int v=0;v<cidades;v++){
				if (ads[c][v]>0){
					mapa.cidadesDoMapa[c].vizinhas[indice] = new Distancia(mapa.cidadesDoMapa[v],(double)ads[c][v]);
					indice++;
				}
			}
		}
		return mapa;
	}
	
}
