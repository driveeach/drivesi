import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class Dijkstra{			//classe que calcula a rota e custo minimo para cada trajeto

	Grafo grafo;		//variavel do tipo Grafo, o qual sera o mapa utilizado pelo algoritmo
	int[] ant;			//array de inteiros para armazenar os antecessores de cada vertice
	double[] custoMin;	//array de double para armazenar os custos minimos de cada vertice, a partir da origem
	int size;			//variavel para armazenar o tamanho do grafo

	Dijkstra(Grafo grafo){		//construtor: atribui os valores para as variaveis globais e aloca os arrays
		this.grafo = grafo;
		size = grafo.getSize();
		ant = new int[size];
		custoMin = new double[size];
	}

	void calculaRota(int v0){				//metodo que calcula a melhor rota a partir de uma origem v0, usando o algoritmo de Dijkstra
		Queue fila = new PriorityQueue();	//cria uma fila de prioridade para guardar os elementos a serem vistos, ordenando-os pela distancia
		inicializa(v0, fila);	//executa o metodo que inicializa os valores para o algoritmo de Dijkstra
		Set definitivo = new TreeSet();		//cria um conjunto onde serao adicionados os vertices que ja possuem o melhor custo

		int u;
		while(!fila.isEmpty()){			//enquanto a fila ainda tiver elementos:
			u = (int) fila.remove();		//u recebe o elemento mais perto de v0, que ainda esta na fila(elemento sera removido)
			definitivo.add(u);				//adiciona esse elemento ao conjunto dos definitivos

			ListIterator it = grafo.g[u].listIterator();		//cria iterator para percorrer a lista de adjacentes de u
			while(it.hasNext()){					//enquanto ha elementos nao visitados na lista:
				Aresta ar = (Aresta) it.next();			//armazena a proxima aresta
				relaxamento(u, ar.vFim, ar.peso);		//executa o metodo de relaxamento
			}
		}
	}

	void inicializa(int v0, Queue fila){	//metodo que inicializa os valores para o algoritmo de Dijkstra
		for(int i = 0; i < size; i++){			//enquanto nao percorrer todos os vertices:
			ant[i] = -1;							//antecessor de i = -1
			custoMin[i] = Double.MAX_VALUE;			//custo minimo de i = infinito
			fila.add(i);							//adiciona o vertice i na fila de prioridade
		}
		custoMin[v0] = 0;					//custo minimo do vertice de origem receve 0
	}

	void relaxamento(int v1, int v2, double pesoAresta){		//metodo de relaxamento
		double peso = custoMin[v1] + pesoAresta;		//armazena em peso o custo ate v2 passando por v1
		if(custoMin[v2] > peso){						//se o custo atual for maior que o novo custo(peso)
			custoMin[v2] = peso;						//o custo minimo de v2 passa a ser o valor de peso
			ant[v2] = v1;								//antecessor de v2 passa a ser v1
		}
	}
}