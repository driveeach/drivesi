/************************************
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

	Implementacao de Grafos utilizando Listas de Adjacencia
	(vetor de ponteiros onde cada posicao indexa um vertice e
	 contem o ponteiro para a cabeca de sua lista de adjacencia)

*************************************/

#include<stdlib.h>
#include<stdio.h>


typedef double tpeso;
typedef int tvertice;

/*
  tipo estruturado taresta:
      vertice de terminacao, peso, ponteiro p/ prox. aresta
*/
typedef struct taresta {
  tvertice vertice;
  tpeso peso;
  struct taresta * prox;
}taresta;

typedef taresta* tapontador;

/*
  tipo estruturado grafo:
      vetor de listas de adjacencia (cada posicao contem o ponteiro
        para o inicio da lista de adjacencia do vertice)
      numero de vertices
*/
typedef struct {
  tapontador *ListaAdj;
  int num_vertices;
}tgrafo;

/********************

  Prototipos dos metodos sobre grafos

*********************/

/*
  InicializaGrafo(Grafo, nv): Cria um grafo com n vertices
  Aloca espaco para o vetor de apontadores de listas de adjacencias e,
  para cada vertice, inicializa o apontador de sua lista de adjacencia
*/
void InicializaGrafo(tgrafo *grafo, int nvertices);

/*
  ListaAdjVazia(v, Grafo): retorna 1 se a lista de adjacencia do vertice v
                           estiver vazia, 0 caso contrario.
*/
int ListaAdjVazia(tvertice v, tgrafo *grafo);

/*
  PrimeiroAdj(v, Grafo): retorna o endereco do primeiro vertice
  adjacente a v.
*/
tapontador PrimeiroAdj(tvertice v, tgrafo *grafo);

/*
  ProxAdj(v, p, Grafo): retorna o endereco do proximo vertice
  adjacente a v, partindo do endereco p.
*/
tapontador ProxAdj(tvertice v, tapontador p, tgrafo *grafo);

/*
  FimLista(v, p, Grafo): indica se o ponteiro atual p estah chegou ao
        fim da lista de adjacencia de v.
*/
int FimListaAdj(tvertice v, tapontador p, tgrafo *grafo);

/*
  RecuperaAdj(v, p, u, peso, grafo): dado um vertice v e um
      ponteiro para uma aresta da lista de adjacencia de v,
      devolve nas variaveis "peso" e "u" respectivamente o peso
      da aresta e o numero do vertice adjacente
*/
void RecuperaAdj(tvertice v, tapontador p, tvertice *u, tpeso *peso,
                 tgrafo *grafo);

/*
  RetiraPrimAresta(v, Grafo): retira a aresta do 1o vertice adjacente a v
*/
void RetiraPrimAresta(tvertice v, tgrafo *grafo);

/*
  LiberaGrafo(Grafo): Liberar o espaco ocupado por um grafo.
*/
void LiberaGrafo (tgrafo *grafo);

/*
  ExisteAresta(v1, v2,Grafo): Retorna 1 se existir a aresta (v1,v2),
  Se existir, devolve o endereco em p.
*/
int ExisteAresta(tvertice v1, tvertice v2, tapontador p, tgrafo *grafo);

/*
  ImprimeGrafo(Grafo): imprime os vertices e arestas do grafo no seguinte formato:
    v1: (adj11, peso11); (adj12, peso12); ...
    v2: (adj21, peso21); (adj22, peso22); ...
*/
void ImprimeGrafo(tgrafo *grafo);

/*
  InsereAresta(v,u,Peso, Grafo): Insere a aresta (v1,v2)
  no grafo atribuindo seu peso.
*/
void InsereAresta(tvertice v1, tvertice v2, tpeso peso, tgrafo *grafo);

/*
  LeGrafo(nomearq, Grafo)
  Le o arquivo nomearq e armazena na estrutura Grafo
  Lay-out:
    A 1a linha deve conter o número de vertices e o numero de arestas do grafo,
    separados por espaço.
    A 2a linha em diante deve conter a informacao de cada aresta, que consiste
    no indice do vertice de origem, indice do vertice de destino e o peso da
    aresta, tambem separados por espacos.
    Observações:
      Os vertices devem ser indexados de 0 a |V|-1
      Os pesos das arestas sao numeros racionais nao negativos.

  Exemplo: O arquivo abaixo contem um grafo com 4 vertices (0,1,2,3) e
  7 arestas.

  4 7
  0 3 6.3
  2 1 5.0
  2 0 9
  1 3 1.7
  0 1 9
  3 1 5.6
  0 2 7.2

  Codigo de saida:
    1: leitura bem sucedida
    0: erro na leitura do arquivo
*/
int LeGrafo(char* nomearq, tgrafo *grafo);

