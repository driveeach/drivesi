/******************************************************************************
     matrizTriangularSuperior.c
  Este programa gerencia matrizes triangulares superiores, onde numero
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
} MTS;

typedef MTS * PontMTS;

/* Inicialização da matriz triangluar (aloca memoria para o arranjo, apontado 
   por A, preenche todos os valores do arranjo com zero e atribui valor na 
   variavel linhas)                                                         */
void inicializarMatriz(int lin, PontMTS matriz){
   int numeroDeElementos = (lin*lin+lin)/2;
   matriz->linhas = lin;
   matriz->A = (int*)malloc(sizeof(int)*numeroDeElementos);
   int i;
   for (i=0;i<numeroDeElementos;i++) matriz->A[i]=0;
} /* inicializarMatriz */


/* Exibição bidimensional da matriz                                         */
void exibirMatriz(PontMTS matriz){
  int lin = matriz->linhas;
  int i, j;
  for (i=1;i<=lin;i++){
      for (j=1;j<=lin;j++) printf("%3d ",ValorMatriz(i,j,matriz));
      printf("\n");
  }
return;
} /* exibirMatriz */ 

void exibirArranjo(PontMTS matriz){
  int tam = tamanho(matriz);
  int i;
  for (i=0;i<tam;i++){
    printf("%3d ",matriz->A[i]);
  }
return;
}

/* Retornar o tamanho da matriz (numero de elementos validos                 */
int tamanho(PontMTS mat) {
  int lin = mat->linhas;
  return (lin*lin+lin)/2;
} /* tamanho */

/* Retornar o tamanho total em bytes ocupado pela matriz */
int tamanhoEmBytes(PontMTS mat) {
  int lin = mat->linhas;
  return (lin*lin+lin)/2*sizeof(int);
} /* tamanhoEmBytes */

/* Libera a memoria de todos os elementos da matriz (memoria do arranjo A) e
   zera a variavel linhas                                                    */
void liberarMatriz(PontMTS mat) {
     int i;
     int tam = tamanho(mat);
     for (i=0;i<tam;i++) free(&(mat->A[i]));
     mat->linhas=0;
     return;
} /* liberarMatriz */

/* Dados indices validos (lin, col) de uma matriz diagonal retorna a posicao no
   arranjo que esse elemento deve ocupar                                       */
int retornaPosicao(int lin, int col){
  return (col*col-col)/2+lin-1;
} /* retornaPosicao */

/* Inserção na matriz => funcionara como substituir caso o valor jah exista */
bool AtribuiMatriz(int lin, int col, int val, PontMTS mat) {
  if ((lin<1) || (lin>mat->linhas) || (col<1) || (col>mat->linhas) ||(lin>col)) return false;
  int posicao = retornaPosicao(lin,col);
  mat->A[posicao]=val;
  return true;
} /* AtribuiMatriz */

/* ValorMatriz - retorna o valor do elemento de posicao lin, col 
   e -1 caso elemento nao exista (posicao invalida)   */
int ValorMatriz(int lin, int col, PontMTS mat){
  if ((lin<1) || (lin>mat->linhas) || (col<1) || (col>mat->linhas))
     return -1;
  if (lin>col) return 0;
  return mat->A[retornaPosicao(lin,col)];
} /* ValorMatriz */


/* OrdemMatriz - coloca nos enderecos m e n a ordem da matriz */
void OrdemMatriz(PontMTS mat, int * m, int * n){
     *m = mat->linhas;
     *n = mat->linhas;
     return;
} /* OrdemMatriz */

PontMTS SomaMatriz(PontMTS a, PontMTS b) {
  int lin = a->linhas;
  if (b->linhas != a->linhas) return NULL;      
  PontMTS c = (PontMTS) malloc(sizeof(MTS));
  inicializarMatriz(lin,c);
  int x;
  int tam = tamanho(a);
  for (x=0;x<tam;x++)
    c->A[x] = a->A[x] + b->A[x];
  return c;
}


/* Calcula a eficiência da representação matriz diagonal 
   comparação com a usual, em percentagem                                 */
float eficiencia(MTS a) {
  int m = a.linhas;
  if (m!=0)
    return 100.0*((tamanho(&a))*sizeof(int))
                  /(m*m*sizeof(int));
  else
    return 0;
}
