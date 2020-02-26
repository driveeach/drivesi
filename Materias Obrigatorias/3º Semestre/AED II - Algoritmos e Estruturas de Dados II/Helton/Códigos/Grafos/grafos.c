/************************************ 
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

  Modulo: grafos01.c
	Implementacao de Grafos utilizando Listas de Adjacencia
	(vetor de ponteiros onde cada posicao indexa um vertice e 
	 contem o ponteiro para a cabeca de sua lista de adjacencia) 

*************************************/

/* Leitura do header com estruturas e tipos especiais */
#include "grafos.h"  

// Descomente a linha abaixo se quiser usar a funcao main
#define _ALONE_ 

//#define _DEBUG_ 

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
  RecuperaAdj(v, p, u, peso, grafo): dado um vertice v e um 
      ponteiro para uma aresta da lista de adjacencia de v, 
      devolve nas variaveis "peso" e "u" respectivamente o peso 
      da aresta e o numero do vertice adjacente
*/
void RecuperaAdj(tvertice v, tapontador p, tvertice *u, tpeso *peso, 
                 tgrafo *grafo) {
  *u = p->vertice;
  *peso = p->peso;
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
  ExisteAresta(v1, v2,Grafo): Retorna 1 se existir a aresta (v1,v2),  
  Se existir, devolve o endereco em p.
*/
int ExisteAresta(tvertice v1, tvertice v2, tapontador p, tgrafo *grafo) {
  tapontador q;
  int existe = 0;
  
  for (q=PrimeiroAdj(v1, grafo); 
       !FimListaAdj(v1, q, grafo) && !existe; 
       q=ProxAdj(v1, q, grafo))
    if (q->vertice==v2) existe = 1;

  if (existe) p=q;
  return(existe);  
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

/* 
  InsereAresta(v,u,Peso, Grafo): Insere a aresta (v1,v2) 
  no grafo atribuindo seu peso.
*/
void InsereAresta(tvertice v1, tvertice v2, tpeso peso, tgrafo *grafo) {
  tapontador p, aux;
  
  if (ListaAdjVazia(v1, grafo) || !ExisteAresta(v1, v2, p, grafo)) {
    p = (tapontador) calloc(1, sizeof(taresta));
    p->vertice = v2;
    p->peso = peso;
    p->prox = grafo->ListaAdj[v1];
    grafo->ListaAdj[v1] = p;    
  }
}

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
int LeGrafo(char* nomearq, tgrafo *grafo) {
  FILE *fp;
  int nvertices, narestas;
  tvertice v1, v2;
  tpeso peso;
  int idar;
  
  fp = fopen(nomearq, "rt");
  if (fp==NULL)
     return(0);
  
  if (fscanf(fp, "%d %d", &nvertices, &narestas)!=2) 
    return(0);
        
  InicializaGrafo(grafo, nvertices);

#ifdef _DEBUG_
  std::cout << "vertices: " << nvertices << "; arestas: " << narestas << '\n';
#endif
  
  for (idar=1;idar<=narestas;idar++) {
    if (fscanf(fp, "%d %d %lf", &v1, &v2, &peso)!=3) {
      fclose(fp);
      LiberaGrafo(grafo);
      return(0);
    }

#ifdef _DEBUG_
    std::cout << idar << " - " << v1 << " - " << v2 << " - " << peso << '\n';
#endif
    if (v1<0 || v1>=nvertices || v2<0 || v2>=nvertices || peso<0) {
      fclose(fp);
      LiberaGrafo(grafo);
      return(0);
    }
    InsereAresta(v1,v2,peso,grafo);
  }  
  fclose(fp);
  
#ifdef _DEBUG_
  std::cout << "Carreguei o grafo\n"; 
#endif


  return(1);
}      

#ifdef _ALONE_ 
int main(int argc, char *argv[]) {
  tgrafo G;
  tapontador p;

  printf("Tentando ler arquivo %s\n", argv[1]);
  if (LeGrafo(argv[1], &G)) {
  
  //InicializaGrafo(&G,5);

  //InsereAresta(0,2,4,&G);
  //InsereAresta(3,4,22,&G);
  
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
      
