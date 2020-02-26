/******************************************************************************
     usaEsparsasLinhas.c
  Este programa gerencia matrizes esparsas (implementacao em listas lineares).
  Haverá um nó cabeça (que armazena a ordem da matriz).
******************************************************************************/
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;

typedef struct tempNo{
  int linha;
  int coluna;
  float valor;
  struct tempNo * prox;
} NO;

typedef NO* PONT;

typedef void * MatrizAux;

typedef struct {
  PONT cabeca;
} MATRIZ, *ImplMatriz;

/* Inicialização da matriz esparsa (cria a matriz, o cabeca e retorna o endereco
  da matriz */
MATRIZ inicializarMatriz(int lin, int col){
   MATRIZ m;
   PONT cab = (PONT) malloc(sizeof(NO));
   m.cabeca = cab;
   cab->linha = lin;
   cab->coluna = col;
   cab->prox = NULL;
   return m;
} /* inicializarMatriz */

/* Exibição sequencial da Matriz no formato (linha, coluna : valor) */
void exibirMatriz(ImplMatriz m){
  PONT end = m->cabeca;
  printf("Matriz: %d por %d\n",end->linha, end->coluna);
  end = end->prox;
  while (end != NULL){
    printf("(%d, %d : %f\n", end->linha, end->coluna, end->valor);
    end = end->prox;
  }
} /* exibirMatriz */ 

/* Retornar o tamanho da matriz (numero de elementos validos (sem contar o cabeca) */
int tamanho(ImplMatriz m) {
  PONT end = m->cabeca->prox;
  int tam = 0;
  while (end != NULL){
    tam++;
    end = end->prox;
  }
  return tam;
} /* tamanho */

/* Retornar o numero total de nos da matriz (incluindo cabeca) */
int NumeroNos(MATRIZ m) {
  return tamanho(&m)+1;
} /* NumeroNos */

/* TamanhoNo - retorna o tamanho em bytes de um no */
int TamanhoNo(){
    return sizeof(NO);
} /* TamanhoNo */

/* Retornar o tamanho em bytes da matriz. Neste caso, isto depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytes(MATRIZ m) {
  return (NumeroNos(m)*sizeof(NO))+sizeof(MATRIZ);
} /* tamanhoEmBytes */

/* Busca sequencial - funcao de exclusao (retorna no endereço *ant o indice do
   elemento anterior ao elemento que está sendo buscado [ant recebe o elemento
   anterior independente do elemento buscado ser ou não encontrado]) 
   O elemento buscado eh caracterizado pela sua linha e coluna   */
PONT buscaSeqExc(int lin, int col, ImplMatriz m, PONT *ant){
  *ant = m->cabeca;
  PONT pos = m->cabeca->prox;
  while ((pos != NULL) && (pos->linha<lin)){
    *ant = pos;
    pos = pos->prox;
  }
  while ((pos != NULL) && (pos->linha==lin) && (pos->coluna<col)){
    *ant = pos;
    pos = pos->prox;
  }
  if ((pos != NULL) && (pos->linha==lin) && (pos->coluna==col)) return pos;
  return NULL;
} /* buscaSeqExc */

/* Exclusão do elemento de posico (linha e coluna) indicados */
bool excluirElemMatriz(int lin, int col, ImplMatriz m){
  PONT ant, i;
  i = buscaSeqExc(lin, col, m, &ant);
  if (i == NULL) return false;
  ant->prox = i->prox;
  free(i);
  return true;
} /* excluirElemMatriz */

/* Destruição da matriz libera a memoria de todos os elementos da matriz
   menos o no cabeca */
void destruirMatriz(ImplMatriz m) {
  PONT end = m->cabeca->prox;
  while (end != NULL){
    PONT apagar = end;
    end = end->prox;
    free(apagar);
  }
  m->cabeca->prox = NULL;
} /* destruirMatriz */

/* LiberaMatriz = destruirMatriz */
void LiberaMatriz(MATRIZ m){
     destruirMatriz(&m);
} /* LiberaMatriz */

/* Inserção na matriz => funcionara como substituir caso o no jah exista */
bool AtribuiMatriz(ImplMatriz m,int lin, int col, float val) {
  if ((lin<1) || (lin>m->cabeca->linha) || (col<1) || (col>m->cabeca->coluna))
     return false;
  PONT ant, i;
  i = buscaSeqExc(lin, col, m, &ant);
  if (val == 0) {    // nao se insere elementos valor zero (e remove caso existisse outro valor la => "substituicao por zero")
    excluirElemMatriz(lin,col,m);
    return true;
  }
  if (i != NULL) {  // substitui valor existente
        i->valor = val; 
        return true;
  }
  // cria novo NO (nao existia ninguem na posicao lin, col
  i = (PONT) malloc(sizeof(NO));
  i->linha = lin;
  i->coluna = col;
  i->valor = val;
  i->prox = ant->prox;
  ant->prox = i;
  return true;
} /* AtribuiMatriz */

/* ValorMatriz - retorna o valor do elemento de posicao lin, col 
   e -1 caso elemento nao exista   */
float ValorMatriz(MATRIZ m, int lin, int col){
  if ((lin<1) || (lin>m.cabeca->linha) || (col<1) || (col>m.cabeca->coluna))
     return -1;
  PONT ant, i;
  i = buscaSeqExc(lin, col, &m, &ant);
  if (i == NULL) return 0;
  return i->valor;
} /* ValorMatriz */


/* OrdemMatriz - coloca nos enderecos m e n a ordem da matriz */
void OrdemMatriz(MATRIZ a, int * m, int * n){
     *m = a.cabeca->linha;
     *n = a.cabeca->coluna;
} /* OrdemMatriz */

MATRIZ* SomaMatriz(MATRIZ a, MATRIZ b) {
  int lin = a.cabeca->linha;
  int col = a.cabeca->coluna;
  if ((b.cabeca->linha != lin) || (b.cabeca->coluna != col)) return NULL;      
  MATRIZ * c = (MATRIZ*) malloc(sizeof(MATRIZ));
  *c = inicializarMatriz(lin,col);
  int x;
  int y;
  for (x=1;x<=lin;x++)
    for (y=1;y<=lin;y++)
      AtribuiMatriz(c,x,y,ValorMatriz(a,x,y)+ValorMatriz(b,x,y));
  return c;
}
