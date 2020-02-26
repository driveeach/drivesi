import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class Grafo{

	List[] g;		//cria um array de listas na quais serao inseridos os adjacentes de cada vertice
	private final int size;		//armazena o tamanho do array acima

	Grafo(int n){		//construtor
		size = n;			//atualiza o tamanho do array
		g = new List[n];	//aloca dinamicamente o array de listas
		inicializa();		//metodo que inicializa o grafo
	}

	int getSize(){		//metodo que retorna o tamanho do grafo(numero de vertices)
		return size;
	}

	void inicializa(){	//metodo que inicializa o array de listas 
		for(int i = 0; i < g.length; i++){		//para cada posicao do array
			g[i] = new LinkedList<Aresta>();	//instancia uma nova LinkedList
		}
	}

	void insereAresta(Aresta a){		//insere uma nova aresta nas listas de adjacencia
		if(buscaAresta(a.vInicio, a.vFim) == null){		//verifica se um sentido da aresta nao existe, se sim:
			if(buscaAresta(a.vFim, a.vInicio) == null){		//verifica se o outro sentido nao existe, se sim:
				Aresta novo = new Aresta(a.vFim, a.vInicio);	//cria uma nova aresta, trocando os vertices de inicio e fim
				g[a.vFim].add(novo);			//adiciona a aresta criada na lista de adjacentes do outro vertice
			}
			g[a.vInicio].add(a);			//adiciona a aresta na lista do primeiro vertice
		}
	}

	Aresta buscaAresta(int v1, int v2){		//metodo para buscar determinada aresta no grafo
		ListIterator it = g[v1].listIterator();		//cria o iterator para pescorrer a lista de adjacentes do primeiro vertice

		Aresta atual;			//cria uma variavel do tipo aresta
		while(it.hasNext()){		//enquanto existem mais Arestas na lista:
			atual = (Aresta) it.next();	//armazena a Aresta atual
			if(atual.vFim == v2){		//verifica se o vertice final da aresta atual e igual a v2, se sim:
				return atual; 				//retorna atual
			}
		}
		return null;		//caso nao seja encontrada nenhuma aresta, retorna null
	}

	void insereTodasArestas(Aresta[] a){	//metodo que insere todas as arestas carregadas
		int size = a.length;				//armazena o numero de arestas
		for(int i = 0; i < size; i++){		//enquanto todas as arestas nao forem percorridas,
			insereAresta(a[i]);				//executa o metodo para inserir a aresta i
		}
	}
}