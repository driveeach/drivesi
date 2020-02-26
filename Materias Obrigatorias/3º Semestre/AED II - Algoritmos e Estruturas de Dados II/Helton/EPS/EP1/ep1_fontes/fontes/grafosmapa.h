/************************************
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

	Implementacao de Grafos utilizando Listas de Adjacencia
	(vetor de ponteiros onde cada posicao indexa um vertice e
	 contem o ponteiro para a cabeca de sua lista de adjacencia)

	Este modulo eh uma versao extendida para o problema de selecao
  otima de rotas via algoritmo de Dijkstra.
  Basicamente, acrescentamos algumas informacoes adicionais em
  cada aresta: nome da rua, numero inicial, numero final

*************************************/

#ifndef GRAFOSMAPA_H
#define GRAFOSMAPA_H

#include<stdlib.h>
#include<stdio.h>

#include "dicionario.h"

typedef double tpeso;
typedef int tvertice;

#define MAXSTRING 50

/*
  tipo estruturado taresta:
      vertice de terminacao, peso, nome da rua,
      numero no inicio do quarteirao, numero no fim do quarteirao
      ponteiro p/ prox. aresta
*/
typedef struct taresta {
  tvertice vertice;
  tpeso peso;
  char *nomerua;
  long nrini, nrfim;
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

  /* dicionario de ruas: a chave eh o nome da rua e
     o numero do vertice correspondente ao inicio da rua */
  tdic Ruas;
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
  RecuperaAdj(v, p, u, peso, nrini, nrfim, nomerua, grafo): dado um vertice v e um
      ponteiro para uma aresta da lista de adjacencia de v,
      devolve o peso da aresta, o vertice adjacente, o nome da rua e o numero
      inicial e final no segmento da rua
*/
void RecuperaAdj(tvertice v, tapontador p, tvertice *u, tpeso *peso,
                 long *nrini, long *nrfim, char **nomerua,
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
  ExisteAresta(v1, v2,Grafo): Retorna o ponteiro para a aresta (v1,v2),
  ou NULL caso ela nao exista.
*/
tapontador BuscaAresta(tvertice v1, tvertice v2, tgrafo *grafo);

/*
  BuscaRuaVert(v, nomerua, Grafo): Se a rua <nomerua> passar pelo vertice v, retorna
    o ponteiro para a aresta correspondente. Caso contrario, retorna NULL
*/
tapontador BuscaRuaVert(tvertice v, char* nomerua, tgrafo *grafo);

/*
  BuscaArestaRua(nomerua, numero, v, p, Grafo):
  Dado um endereco (nomerua, numero), esta funcao percorre todas as arestas
  contidas na rua especificada, ateh encontrar a aresta em que se localiza
  o numero desejado. Sao devolvidos o indice do vertice de onde
  ao segmento da rua onde se encontra o numero especificado. Devolve tambem
  o vertice v de onde sai a aresta p. Se o numero informado for maior do que
  o numero maximo da rua, retorna NULL
*/
void BuscaArestaRua(char* nomerua, long numero, tvertice *v, tapontador *p, tgrafo *grafo);

/*
  ImprimeGrafo(Grafo): imprime os vertices e arestas do grafo no seguinte formato:
    v1: (adj11, peso11); (adj12, peso12); ...
    v2: (adj21, peso21); (adj22, peso22); ...
*/
void ImprimeGrafo(tgrafo *grafo);

/* ImprimeRuas(tgrafo *grafo): lista as ruas do grafo e seus respectivos vertices
   iniciais */
void ImprimeRuas(tgrafo *grafo);


/*
  InsereAresta(v,u,nomerua,nrini,nrfim,peso, Grafo): Insere a aresta (v1,v2)
  no grafo atribuindo seu peso, o nome da rua correspondente e os numeros
  do inicio e final do quarteirao.
*/
void InsereAresta(tvertice v1, tvertice v2, char *nomerua,
                  long nrini, long nrfim, tpeso peso, tgrafo *grafo);

/*
  LeGrafo(nomearq, Grafo)
  Le o arquivo nomearq e armazena na estrutura Grafo
  Lay-out:
    A 1a linha deve conter o número de vertices e o numero de arestas do grafo,
    separados por espaço.
    A 2a linha em diante deve conter a informacao de cada aresta, que consiste em:
     1. identificador da aresta (no caso do roteamento, nome da rua), sem espacos
     2. vertice inicial
     3. vertice final
     4. numero inicial no segmento de rua
     5. numero final no segmento de rua
     6. custo da aresta
    Observações:
      Os vertices devem ser indexados de 0 a |V|-1
      Os pesos das arestas sao numeros racionais nao negativos.

  Codigo de saida:
    1: leitura bem sucedida
    0: erro na leitura do arquivo
*/
int LeGrafo(char* nomearq, tgrafo *grafo);

#endif

