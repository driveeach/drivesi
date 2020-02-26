/******************************************************************************
     matrizDiagonalSuperior.c
  Este programa gerencia matrizes diagonais superiores, onde numero
  de linhas=numero de colunas 
  (implementacao em arranjo unidimensional, declarado dinamicamente).
******************************************************************************/
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;

typedef struct {
  int linhas;
  int * A;
} MDS;

typedef MDS * PontMDS;

/* Inicialização da matriz diagonal (aloca memoria para o arranjo, apontado 
   por A, preenche todos os valores do arranjo com zero e atribui valor na 
   variavel linhas)                                                         */
void inicializarMatriz(int lin, PontMDS matriz){
   matriz->linhas = lin;
   matriz->A = (int*)malloc(sizeof(int)*lin);
   int i;
   for (i=0;i<lin;i++) matriz->A[i]=0;
} /* inicializarMatriz */


/* Exibição bidimensional da matriz                                         */
void exibirMatriz(PontMDS matriz){
  int lin = matriz->linhas;
  int i, j;
  int zero = 0;
  for (i=0;i<lin;i++){
      for (j=0;j<lin;j++){
          if (i!=j) printf("%3d ",zero);
          else printf("%3d ",matriz->A[i]);
      }
      printf("\n");
  }
return;
} /* exibirMatriz */ 

/* Retornar o tamanho da matriz (numero de elementos validos                 */
int tamanho(PontMDS mat) {
  return mat->linhas;
} /* tamanho */

/* Retornar o tamanho total em bytes ocupado pela matriz */
int tamanhoEmBytes(PontMDS mat) {
  return sizeof(int)*mat->linhas;
} /* tamanhoEmBytes */

/* Libera a memoria de todos os elementos da matriz (memoria do arranjo A) e
   zera a variavel linhas                                                    */
void liberarMatriz(PontMDS mat) {
     int i;
     int tam = tamanho(mat);
     for (i=0;i<tam;i++) free(&(mat->A[i]));
     mat->linhas=0;
     return;
} /* liberarMatriz */

/* Inserção na matriz => funcionara como substituir caso o valor jah exista */
bool AtribuiMatriz(int lin, int col, int val, PontMDS mat) {
  if ((lin<1) || (lin>mat->linhas) || (lin != col)) return false;
  mat->A[lin-1]=val;
  return true;
} /* AtribuiMatriz */

/* ValorMatriz - retorna o valor do elemento de posicao lin, col 
   e -1 caso elemento nao exista (posicao invalida)   */
int ValorMatriz(int lin, int col, PontMDS mat){
  if ((lin<1) || (lin>mat->linhas) || (col<1) || (col>mat->linhas))
     return -1;
  if (lin!=col) return 0;
  return mat->A[lin-1];
} /* ValorMatriz */


/* OrdemMatriz - coloca nos enderecos m e n a ordem da matriz */
void OrdemMatriz(PontMDS mat, int * m, int * n){
     *m = mat->linhas;
     *n = mat->linhas;
     return;
} /* OrdemMatriz */

PontMDS SomaMatriz(PontMDS a, PontMDS b) {
  int lin = a->linhas;
  if (b->linhas != a->linhas) return NULL;      
  PontMDS c = (PontMDS) malloc(sizeof(MDS));
  inicializarMatriz(lin,c);
  int x;
  for (x=0;x<lin;x++)
    c->A[x] = a->A[x] + b->A[x];
  return c;
}


/* Calcula a eficiência da representação matriz diagonal 
   comparação com a usual, em percentagem                                 */
float eficiencia(MDS a) {
  int m = a.linhas;
  if (m!=0)
    return 100.0*(m*sizeof(int))
                  /(m*m*sizeof(int));
  else
    return 0;
}
