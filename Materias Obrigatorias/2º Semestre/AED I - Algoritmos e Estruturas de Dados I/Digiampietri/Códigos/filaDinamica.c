/******************************************************************************
//     filaDinamica.c
// Este programa gerencia filas lineares ligadas (implementacao dinamica).
// As filas gerenciadas podem ter um numero arbitrario de elementos.
// Não usaremos sentinela ou cabeça nesta estrutura.
******************************************************************************/
#include <stdio.h>
#include <malloc.h>
#define ERRO -1
#define true 1
#define false 0
typedef int bool;
typedef int TIPOCHAVE;

typedef struct tempRegistro{
  TIPOCHAVE chave;
  struct tempRegistro *prox;
// outros campos...
} REGISTRO, *PONT;

typedef struct {
  PONT inicio;
  PONT fim;
} FILA;

/* Inicialização da fila ligada (a fila jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarFila(FILA *f){
  f->inicio = NULL;
  f->fim = NULL;
} /* inicializarFila */

/* Retornar o tamanho da fila (numero de elementos) */
int tamanho(FILA *f) {
  PONT end = f->inicio;
  int tam = 0;
  while (end != NULL){
    tam++;
    end = end->prox;
  }
  return tam;
} /* tamanho */

/* Retornar o tamanho em bytes da fila. Neste caso, isto depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytes(FILA *f) {
  return(tamanho(f)*sizeof(REGISTRO))+sizeof(FILA); // sizeof(FILA) = sizeof(PONT) pois a "FILA" eh um ponteiro para o 1o elemento
} /* tamanhoEmBytes */


/* Destruição da fila 
   libera a memoria de todos os elementos da fila*/
void destruirFila(FILA *f) {
  PONT end = f->inicio;
  while (end != NULL){
    PONT apagar = end;
    end = end->prox;
    free(apagar);
  }
  f->inicio = NULL;
} /* destruirFila */


/* retornarPrimeiro - retorna o endereco do primeiro elemento da fila e (caso
   a fila nao esteja vazia) retorna a chave desse elemento na memoria 
   apontada pelo ponteiro ch */
PONT retornarPrimeiro(FILA *f, TIPOCHAVE *ch){
  if (f->inicio != NULL) *ch = f->inicio->chave;
  return f->inicio;
} /* retornarPrimeiro */

/* retornarUltimo - retorna o endereco do ultimo elemento da fila e (caso
   a fila nao esteja vazia) retorna a chave desse elemento na memoria 
   apontada pelo ponteiro ch */
PONT retornarUltimo(FILA *f, TIPOCHAVE *ch){
  PONT ultimo = f->inicio;
  if (f->inicio == NULL) return NULL;
  while (ultimo->prox != NULL) ultimo = ultimo->prox;
  *ch = ultimo->chave;
  return ultimo;
} /* retornarUltimo */


/* Inserção no fim da fila */
bool inserirNaFila(REGISTRO reg, FILA *f) {
  PONT novo = (PONT) malloc(sizeof(REGISTRO));
  *novo = reg;
  novo->prox = NULL;
  if (f->inicio==NULL){
     f->inicio = novo;
  }else{
     f->fim->prox = novo;
  }
  f->fim = novo;
  return true;
} /* inserir */

/* Excluir  */
bool excluirDaFila(FILA *f, REGISTRO *reg) {
  if (f->inicio==NULL){
    return false;                     
  }
  *reg = *(f->inicio);
  PONT apagar = f->inicio;
  f->inicio = f->inicio->prox;
  free(apagar);
  if (f->inicio == NULL){
    f->fim = NULL;
  }
  return true;
} /* inserir */


/* Exibição da fila seqüencial */
void exibirFila(FILA *f){
  PONT end = f->inicio;
  printf("Fila: \" ");
  while (end != NULL){
    printf("%d ", end->chave); // soh lembrando TIPOCHAVE = int
    end = end->prox;
  }
  printf("\"\n");
} /* exibirFila */ 

/* Busca sequencial */
PONT buscaSeq(TIPOCHAVE ch, FILA *f){
  PONT pos = f->inicio;
  while (pos != NULL){
    if (pos->chave == ch) return pos;
    pos = pos->prox;
  }
  return NULL;
} /* buscaSeq */
