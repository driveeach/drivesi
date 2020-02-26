typedef struct aux {
	int vertice; 
	int peso; 
	struct aux* prox; 
} aresta; 

typedef aresta* taresta; 

typedef struct {
	int num_vertices; 
	taresta* adj; 
} Grafo; 

#include <stdlib.h>
#include <stdio.h>

//1. Criar um grafo vazio
void inicializa_grafo (Grafo* g, int num_vertices) {
	g->num_vertices = num_vertices; 
	g->adj = (taresta*) calloc (num_vertices, sizeof(taresta)); 
	int i; 
	for (i = 0; i < num_vertices; i++) {
		g->adj[i] = NULL; 
	}
}

int listaAdjVazia (Grafo* g, int v) {
	return (g->adj[v] == NULL ? 1:0); 
}

taresta primeiro_lista (Grafo* g, int v) {
	return (g->adj[v]); 
}

//2. Inserir uma aresta no grafo
void insere_aresta (int v1, int v2, int peso, Grafo* grafo) {
	taresta p, aux; 
	if (listaAdjVazia (grafo, v1) || !existeAresta(v1,v2,p,grafo)) {
		p = (taresta) malloc (sizeof(aresta)); 
		p->vertice = v2; 
		p->peso = peso; 
		p->prox = grafo->adj[v1]; 
		grafo->adj[v1] = p; 
	}
}

//3. Verificar se determinada aresta existe no grafo
int existeAresta (int v1, int v2, taresta ant, taresta p, Grafo* g) {
	taresta q = primeiro_lista(g, v1); 
	int existe = 0; 
	ant = NULL;  
	
	while (q != NULL && !existe) {
		if (q->vertice == v2) {
			existe = 1; 
		}
		else {
			ant = q; 
			q = q->prox; 
		}
	}
	if (existe) {
		p = q; 
	}
	return (existe); 
}

//4. Obter a lista de vertices adjacentes a determinado vertice. 
void listaAdj (Grafo* g, int vertice) {
	taresta primeiro = primeiro_lista (g, vertice); 
	if (primeiro == NULL) {
		printf ("Nao existe lista de adjacencia para o vertice %d.", vertice);
	}
	else {
		printf ("Os vertices adjacentes a %d no grafo dado sao: ", vertice); 
		taresta q;
		for (q = primeiro; q != NULL; q = q->prox) {
			printf ("%d, ", q->vertice); 
		}		 
	}
}

//5. Retirar uma aresta do grafo 
void retirar_aresta (int v1, int v2, Grafo* g) {
	if (!listaAdjVazia (g, v1)) {
		taresta remove = primeiro_lista (g, v1); 
		taresta anterior = NULL; 
		while (remove != NULL) {
			if (remove->vertice == v2) {
				break; 
			}
			else {
				anterior = remove; 
				remove = remove->prox; 
			}
		}
		//se achou: 
		if (remove != NULL) {
			if (anterior == NULL) {
				g->adj[v1] = remove->prox;
				remove->prox = NULL; 
				free (remove); 
			}
			else {
				anterior->prox = remove->prox; 
				remove->prox = NULL; 
				free (remove); 
			}
		}
	}
} 

//6. Imprimir um grafo
void ImprimeGrafo (Grafo* grafo) {
	int u, v; 
	taresta p; 
	
	printf ("Numero de vertices: %d\n", grafo->num_vertices); 
	for (u = 0; u < grafo->num_vertices; u++) {
		printf ("%d: ", u); 
		for (p = primeiro_lista (grafo, u); p != NULL; p = p->prox) {
			v = p->vertice;  
			printf ("(%d %d); ", v, p->peso); 
		}
		printf ("\n"); 
	}
}

//7. Obter o numero de vertices do grafo 
int num_vertices (Grafo* g) {
	return (g->num_vertices); 
}

//8. Obter o transposto de um grafo direcionado
Grafo transposto (Grafo* g) {
	Grafo transposto; 
	inicializa_grafo (&transposto, g->num_vertices); 
	int i;
	taresta p; 
	
	for (i = 0; i < g->num_vertices; i++) {
		p = primeiro_lista (g, i); 
		while (p != NULL) {
			insere_aresta (p->vertice, i, p->peso, &transposto);
			p = p->prox; 
		}
	}
	
	return transposto; 
}

//9. Obter a aresta de menor peso de um grafo
taresta menor_peso (Grafo* g) {
	taresta primeirao = primeiro_lista (g,0); 
	int menor_peso = primeirao->peso; 
	taresta aresta = primeirao; 
	taresta p; 
	int i; 
	
	for (i = 0; i < g->num_vertices; i++) {
		p = primeiro_lista (g, i); 
		while (p != NULL) {
			if (p->peso < menor_peso) {
				aresta = p; 
				menor_peso = p->peso; 
			}
			p = p->prox; 
		}
	}	
	return aresta; 
}

void main () {
	Grafo g; 
	inicializa_grafo (&g, 6);
	insere_aresta (0,1,1,&g); 
	insere_aresta (1,2,2,&g);
	insere_aresta (2,0,3,&g); 
	insere_aresta (4,5,4,&g); 
	ImprimeGrafo (&g); 
	printf ("O menor peso do grafo eh %d.\n", menor_peso(&g)->peso); 
	Grafo result = transposto (&g); 
	ImprimeGrafo (&result);  
}