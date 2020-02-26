/************************************
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

  Modulo: grafos01.c
	Implementacao de Grafos utilizando Listas de Adjacencia
	(vetor de ponteiros onde cada posicao indexa um vertice e
	 contem o ponteiro para a cabeca de sua lista de adjacencia)

*************************************/

#include<math.h>
#include "grafosmapa.h"
#include "dicionario.c"
/* Leitura do header com estruturas e tipos especiais */
#include "grafosmapa.h"
//#include "dicionario.h"

// Descomente a linha abaixo se quiser usar a funcao main
// #define _ALONE_

#define _DEBUG_

/*
  InicializaGrafo(Grafo, nv): Cria um grafo com n vertices
  Aloca espaco para o vetor de apontadores de listas de adjacencias e,
  para cada vertice, inicializa o apontador de sua lista de adjacencia
*/
void InicializaGrafo(tgrafo *grafo, int nvertices) {
  int i;
  grafo->num_vertices = nvertices;
  //grafo->ListaAdj = (tapontador*) calloc(nvertices, sizeof(tapontador));
  grafo->ListaAdj = (tapontador*) calloc(nvertices, sizeof(tapontador));

  for (i=0;i<nvertices; i++)
    grafo->ListaAdj[i] = NULL;

  InicializaDic(&(grafo->Ruas));
}

/*
  ListaAdjVazia(v, Grafo): retorna 1 se a lista de adjacencia do vertice v
                           estiver vazia, 0 caso contrario.
*/
int ListaAdjVazia(tvertice v, tgrafo *grafo) {
  return (grafo->ListaAdj[v]==NULL?1:0);
}

/*
  PrimeiroAdj(v, Grafo): retorna o endereco do primeiro vertice
  adjacente a v.
*/
tapontador PrimeiroAdj(tvertice v, tgrafo *grafo) {
  return(grafo->ListaAdj[v]);
}

/*
  ProxAdj(v, p, Grafo): retorna o endereco do proximo vertice
  adjacente a v, partindo do endereco p.
*/
tapontador ProxAdj(tvertice v, tapontador p, tgrafo *grafo) {
  return(p->prox);
}

/*
  FimLista(v, p, Grafo): indica se o ponteiro atual p estah chegou ao
        fim da lista de adjacencia de v.
*/
int FimListaAdj(tvertice v, tapontador p, tgrafo *grafo) {
  return(p==NULL?1:0);
}

/*
  RecuperaAdj(v, p, u, peso, nrini, nrfim, nomerua, grafo): dado um vertice v e um
      ponteiro para uma aresta da lista de adjacencia de v,
      devolve o peso da aresta, o vertice adjacente, o nome da rua e o numero
      inicial e final no segmento da rua
*/
void RecuperaAdj(tvertice v, tapontador p, tvertice *u, tpeso *peso,
                 long *nrini, long *nrfim, char **nomerua,
                 tgrafo *grafo) {
  *u = p->vertice;
  *peso = p->peso;
  *nrini = p->nrini;
  *nrfim = p->nrfim;
  *nomerua = p->nomerua;  // nao eh feita copia do conteudo, apenas do endereco
}

/*
  RetiraPrimAresta(v, Grafo): retira a aresta do 1o vertice adjacente a v
*/
void RetiraPrimAresta(tvertice v, tgrafo *grafo) {
  tapontador p;

  if (!ListaAdjVazia(v, grafo)) {
    p = grafo->ListaAdj[v];
    grafo->ListaAdj[v] = p->prox;
    p->prox=NULL;
    free(p->nomerua);
    free(p);
  }
}

/*
  LiberaGrafo(Grafo): Liberar o espaco ocupado por um grafo.
*/
void LiberaGrafo (tgrafo *grafo) {
  tvertice v;
  // libera a lista de adjacencia de cada vertice
  for (v = 0; v < grafo->num_vertices; v++) {
    while (!ListaAdjVazia(v, grafo))  {
      RetiraPrimAresta(v, grafo);
    }
  }
  grafo->num_vertices=0;
  // Libera o vetor de ponteiros para as listas de adjacencia
  free(grafo->ListaAdj);
}

/*
  BuscaAresta(v1, v2,Grafo): Retorna o ponteiro para a aresta (v1,v2),
  ou NULL caso ela nao exista.
*/
tapontador BuscaAresta(tvertice v1, tvertice v2, tgrafo *grafo) {
  tapontador q;
  int existe = 0;

  for (q=PrimeiroAdj(v1, grafo); !FimListaAdj(v1, q, grafo) && !existe; )
    if (q->vertice==v2)
      existe = 1;
    else
      q=ProxAdj(v1, q, grafo);

  return(q);
}

/*
  BuscaArestaRua(nomerua, numero, v, p, Grafo):
  Dado um endereco (nomerua, numero), esta funcao percorre todas as arestas
  contidas na rua especificada, ateh encontrar a aresta em que se localiza
  o numero desejado. Sao devolvidos o indice do vertice de onde
  ao segmento da rua onde se encontra o numero especificado. Devolve tambem
  o vertice v de onde sai a aresta p. Se o numero informado for menor que
  o numero minimo da rua ou maior do que o numero maximo, retorna NULL
*/
void BuscaArestaRua(char* nomerua, long numero, tvertice *v, tapontador *p, tgrafo *grafo) {
  tapontador q, qmaior;
  long maiornum;
  int encontrou = 0;

  *v = BuscaDic(&(grafo->Ruas), nomerua);   // vertice inicial
  #ifdef _DEBUG_
  printf("\nEncontrando aresta do endereco %s, %d\n", nomerua, numero);
  printf("Vertices: %d, ", *v);
  #endif
  do {
    qmaior=NULL;
    maiornum=0;
    for (q=PrimeiroAdj(*v, grafo); !FimListaAdj(*v, q, grafo); q=ProxAdj(*v, q, grafo))
      if (strcmp(q->nomerua,nomerua)==0)
        if (q->nrfim > maiornum) {
          qmaior = q;
          maiornum = qmaior->nrfim;
        }
    *p = qmaior;
    if (*p!=NULL && (*p)->nrfim < numero) {
      *v = (*p)->vertice;
      #ifdef _DEBUG_
      printf("%d, ",*v);
      #endif
    }
  } while (*p!=NULL && (*p)->nrfim < numero);
  #ifdef _DEBUG_
  printf("\n");
  #endif

  if (*p==NULL) {
    printf("Erro: numero nao encontrado\n\n");
    exit(0);
  }
}


/*
  ImprimeGrafo(Grafo): imprime os vertices e arestas do grafo no seguinte formato:
    v1: (adj11, peso11); (adj12, peso12); ...
    v2: (adj21, peso21); (adj22, peso22); ...
*/
void ImprimeGrafo(tgrafo *grafo) {
  tvertice v, u;
  tapontador p;

  printf("Num vertices: %d\n", grafo->num_vertices);
  for (v=0; v<grafo->num_vertices; v++) {
    printf("%3d: ", v);
    for (p=PrimeiroAdj(v, grafo); !FimListaAdj(v, p, grafo); p=ProxAdj(v, p, grafo)) {
      printf("(%d, %lf); ", p->vertice, p->peso);
    }
    printf("\n");
  }
}

/* ImprimeRuas(tgrafo *grafo): lista as ruas do grafo e seus respectivos vertices
   iniciais */
void ImprimeRuas(tgrafo *grafo){
  ImprimeDic(&(grafo->Ruas));
}

/*
  InsereAresta(v,u,nomerua,nrini,nrfim,peso, Grafo): Insere a aresta (v1,v2)
  no grafo atribuindo seu peso, o nome da rua correspondente e os numeros do inicio e final do quarteirao.
*/
void InsereAresta(tvertice v1, tvertice v2, char *nomerua, long nrini, long nrfim, tpeso peso, tgrafo *grafo) {
  tvertice primvert;
  tapontador p, aux;

  if (ListaAdjVazia(v1, grafo) || BuscaAresta(v1, v2, grafo)==NULL) {
    p = (tapontador) calloc(1, sizeof(taresta));
    p->nomerua = (char*) calloc(strlen(nomerua)+1,sizeof(char));
    strcpy(p->nomerua, nomerua);
    p->vertice = v2;
    p->nrini = nrini;
    p->nrfim = nrfim;
    p->peso = peso;
    p->prox = grafo->ListaAdj[v1];
    grafo->ListaAdj[v1] = p;

    primvert = BuscaDic(&(grafo->Ruas), nomerua);
    if (primvert == VALORNULO) {
      InsereDic(&(grafo->Ruas), nomerua, v1);
      // printf("Nova rua no dicionario: %s - %ld\n", nomerua, v1);
    } else {

      for (aux=PrimeiroAdj(primvert, grafo); !FimListaAdj(primvert, aux, grafo) && strcmp(aux->nomerua,nomerua)!=0;
           aux=ProxAdj(primvert, aux, grafo));

      if (aux==NULL) {
        printf("Erro: A rua %s nao passa sobre o vertice %d\n", nomerua, primvert);
        exit(0);
      }
      if (aux->nrini > nrini) {
        InsereDic(&(grafo->Ruas), nomerua, v1);
        #ifdef _DEBUG_
        printf("Atualizacao de rua no dicionario: %s - %ld - %ld\n", nomerua, v1, nrini);
        #endif
      }
    }
  }
}

/*
  LeGrafo(nomearq, Grafo)
  Le do arquivo nomearq uma representacao de mapa, armazenando-a na estrutura Grafo
  Lay-out:
    A 1a linha deve conter o número de vertices e o numero de arestas do grafo,
    separados por espaço.
    A 2a linha em diante deve conter a informacao de cada aresta, que consiste em:
     1. nome da rua, sem espacos
     2. vertice inicial
     3. vertice final
     4. numero inicial no segmento de rua
     5. numero final no segmento de rua
     6. custo da aresta
    Observações:
      Os vertices devem ser indexados de 0 a |V|-1
      Os custos das arestas sao numeros racionais nao negativos.

  Codigo de saida:
    1: leitura bem sucedida
    0: erro na leitura do arquivo
*/
int LeGrafo(char* nomearq, tgrafo *grafo) {
  FILE *fp;
  int nvertices, narestas;
  tvertice v1, v2;
  long nrini, nrfim;
  tpeso peso;
  int idar;
  char nomerua[MAXSTRING];

  fp = fopen(nomearq, "rt");
  if (fp==NULL)
     return(0);

  if (fscanf(fp, "%d %d", &nvertices, &narestas)!=2)
    return(0);

  InicializaGrafo(grafo, nvertices);

  for (idar=1;idar<=narestas;idar++) {
    if (fscanf(fp, "%s %d %d %ld %ld %lf", nomerua, &v1, &v2, &nrini, &nrfim, &peso)!=6) {
      fclose(fp);
      LiberaGrafo(grafo);
      return(0);
    }
    if (v1<0 || v1>=nvertices || v2<0 || v2>=nvertices || peso<0) {
      fclose(fp);
      LiberaGrafo(grafo);
      return(0);
    }
    InsereAresta(v1,v2,nomerua,nrini,nrfim,peso,grafo);
    // printf("1o vertice da rua %s: %d\n", nomerua, BuscaDic(&(grafo->Ruas), nomerua));
  }

  fclose(fp);
  return(1);
}

#ifdef _ALONE_
int main(int argc, char *argv[]) {
  tgrafo G;
  tapontador p;

  printf("Tentando ler arquivo mapa01.txt \n");

  if (LeGrafo("mapa01.txt", &G)) {  //argv[1]

  //InicializaGrafo(&G,5);

  //InsereAresta(0,2,4,&G);
  //InsereAresta(3,4,22,&G);

    ImprimeRuas(&G);

    ImprimeGrafo(&G);

    RetiraPrimAresta(0, &G);
    RetiraPrimAresta(1, &G);
    RetiraPrimAresta(2, &G);
    RetiraPrimAresta(3, &G);

    ImprimeGrafo(&G);

    LiberaGrafo(&G);

    ImprimeGrafo(&G);

  }

  return(1);
}
#endif

