/******************************************************************************
//     filaEstatica.c
// Este programa gerencia filas implementadas em arranjos 
   (implementacao estatica).
// As filas gerenciadas podem ter um numero de no maximo MAX elementos.
// Não usaremos sentinela nesta estrutura.
******************************************************************************/
#include <stdio.h>
#define ERRO -1
#define true 1
#define false 0
#define MAX 50

typedef int bool;
typedef int TIPOCHAVE;

typedef struct {
  TIPOCHAVE chave;
} REGISTRO;

typedef struct {
  int inicio;
  int fim;
  REGISTRO A[MAX];
} FILA;

/* Inicialização da fila (a fila jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarFila(FILA *l){
  l->inicio=0;
  l->fim=-1;
} /* inicializarFila */


/* Retornar o tamanho da fila (numero de elementos) */
int tamanhoFila(FILA *l) {
  if (l->fim == -1) return 0;
  int tamanho = l->fim-l->inicio+1;
  if (tamanho <= 0) return MAX-tamanho;
  return tamanho;
} /* tamanho */

/* Exibição da fila seqüencial */
void exibirFila(FILA *l){
  printf("Fila: \" ");
   int i = l->inicio;
   if (l->fim != -1) {
     while (i!=l->fim){
      printf("%d ", l->A[i].chave); // soh lembrando TIPOCHAVE = int
      i = (i + 1) % MAX;
    }
    printf("%d ", l->A[i].chave);
  }
  printf("\"\n");
} /* exibirLista */ 


/* Retornar o tamanho em bytes da fila. Neste caso, isto nao depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytesFila(FILA *l) {
  return sizeof(FILA);
} /* tamanhoEmBytes */

/* Busca Fila - retorna posicao do primeiro elemento da fila */
int buscaFila(FILA *l){
  return l->inicio;
} /* buscaFila */

/* Destruição da fila */
void destruirFila(FILA *l) {
     inicializarFila(l);
} /* destruirFila */

/* inserirElementoFila - insere elemento no fim da fila   */
bool inserirElementoFila(REGISTRO reg, FILA *l){
     if (tamanhoFila(l) >= MAX) return false;
     TIPOCHAVE temp;
     l->fim = (l->fim+1)%MAX;
     l->A[l->fim] = reg;
     return true;
} /* inserirElementoFila */

/* excluirElementoFila - retorna e exclui 1o elemento da fila 
retorna false se nao houver elemento a ser retirado */
bool excluirElementoFila(FILA *l, REGISTRO * reg){
   if (l->fim == -1) return false;
   *reg = l->A[l->inicio];
   if (l->inicio == l->fim){
     l->inicio=0;
     l->fim=-1;
   } else l->inicio = (l->inicio+1)%MAX;
   return true;
} /* excluirElementoFila */

/* retornarPrimeiroFila
retorna a posicao do primeiro elemento da fial e o valor de sua chave no 
conteudo do endereco ch. Retorna -1 caso a lista esteja vazia */
int retornarPrimeiroFila(FILA *f, TIPOCHAVE * ch){
    if (f->fim==-1)return -1;
    *ch = f->A[f->inicio].chave;
    return f->inicio;
}
