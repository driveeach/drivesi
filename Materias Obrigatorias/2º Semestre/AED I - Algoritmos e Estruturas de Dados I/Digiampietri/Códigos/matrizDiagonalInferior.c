/******************************************************************************
     matrizDiagonalInferior.c
  Este programa gerencia matrizes diagonais inferiores, onde numero
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
} MDI;

typedef MDI * PontMDI;

/* Inicialização da matriz diagonal (aloca memoria para o arranjo, apontado 
   por A, preenche todos os valores do arranjo com zero e atribui valor na 
   variavel linhas)                                                         */
void inicializarMatriz(int lin, PontMDI matriz){
   matriz->linhas = lin;
   matriz->A = (int*)malloc(sizeof(int)*lin);
   int i;
   for (i=0;i<lin;i++) matriz->A[i]=0;
} /* inicializarMatriz */


/* Exibição bidimensional da matriz                                         */
void exibirMatriz(PontMDI matriz){
  int lin = matriz->linhas;
  int i, j;
  int zero = 0;
  for (i=1;i<=lin;i++){
      for (j=1;j<=lin;j++){
          if (i+j!=lin+1) printf("%3d ",zero);
          else printf("%3d ",matriz->A[i-1]);
      }
      printf("\n");
  }
return;
} /* exibirMatriz */ 

/* Retornar o tamanho da matriz (numero de elementos validos                 */
int tamanho(PontMDI mat) {
  return mat->linhas;
} /* tamanho */

/* Retornar o tamanho total em bytes ocupado pela matriz */
int tamanhoEmBytes(PontMDI mat) {
  return sizeof(int)*mat->linhas;
} /* tamanhoEmBytes */

/* Libera a memoria de todos os elementos da matriz (memoria do arranjo A) e
   zera a variavel linhas                                                    */
void liberarMatriz(PontMDI mat) {
     int i;
     int tam = tamanho(mat);
     for (i=0;i<tam;i++) free(&(mat->A[i]));
     mat->linhas=0;
     return;
} /* liberarMatriz */

/* Inserção na matriz => funcionara como substituir caso o valor jah exista */
bool AtribuiMatriz(int lin, int col, int val, PontMDI mat) {
  if ((lin<1) || (lin>mat->linhas) || (lin+col-1!=mat->linhas)) return false;
  mat->A[lin-1]=val;
  return true;
} /* AtribuiMatriz */

/* ValorMatriz - retorna o valor do elemento de posicao lin, col 
   e -1 caso elemento nao exista (posicao invalida)   */
int ValorMatriz(int lin, int col, PontMDI mat){
  if ((lin<1) || (lin>mat->linhas) || (col<1) || (col>mat->linhas))
     return -1;
  if (lin+col-1!=mat->linhas) return 0;
  return mat->A[lin-1];
} /* ValorMatriz */


/* OrdemMatriz - coloca nos enderecos m e n a ordem da matriz */
void OrdemMatriz(PontMDI mat, int * m, int * n){
     *m = mat->linhas;
     *n = mat->linhas;
     return;
} /* OrdemMatriz */

PontMDI SomaMatriz(PontMDI a, PontMDI b) {
  int lin = a->linhas;
  if (b->linhas != a->linhas) return NULL;      
  PontMDI c = (PontMDI) malloc(sizeof(MDI));
  inicializarMatriz(lin,c);
  int x;
  for (x=0;x<lin;x++)
    c->A[x] = a->A[x] + b->A[x];
  return c;
}


/* Calcula a eficiência da representação matriz diagonal 
   comparação com a usual, em percentagem                                 */
float eficiencia(MDI a) {
  int m = a.linhas;
  if (m!=0)
    return 100.0*(m*sizeof(int))
                  /(m*m*sizeof(int));
  else
    return 0;
}
