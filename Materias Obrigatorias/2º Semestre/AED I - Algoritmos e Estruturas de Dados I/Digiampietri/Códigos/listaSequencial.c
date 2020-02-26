/******************************************************************************
//     listaSequencia.c
// Este programa gerencia listas lineares sequenciais.
// As listas gerenciadas podem ter MAX elementos (posicoes de 0 a MAX-1 no
//   arranjo A). Alocamos MAX+1 posicoes no arranjo para possibilitar a criacao
//   de um elemento sentinela.
******************************************************************************/
#include <stdio.h>
#define MAX 50
#define ERRO -1
#define true 1
#define false 0

typedef int bool;

typedef int TIPOCHAVE;

typedef struct{
TIPOCHAVE chave;
// outros campos...
} REGISTRO;

typedef struct {
  REGISTRO A[MAX+1];
  int nroElem;
} LISTA;

/* Inicialização da lista sequencial (a lista jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarLista(LISTA *l){
  l->nroElem = 0;
} /* inicializarLista */

/* Exibição da lista seqüencial */
void exibirLista(LISTA *l){
  int i;
  printf("Lista: \" ");
  for (i=0; i < l->nroElem; i++)
    printf("%d ", l->A[i].chave); // soh lembrando TIPOCHAVE = int
  printf("\"\n");
} /* exibirLista */ 

/* Retornar o tamanho da lista (numero de elementos "validos") */
int tamanho(LISTA *l) {
  return(l->nroElem);
} /* tamanho */

/* Retornar o tamanho em bytes da lista. Neste caso, isto nao depende do numero
   de elementos que estao sendo usados, pois a alocacao de memoria eh estatica.
   A priori, nao precisariamos do ponteiro para a lista, vamos utiliza-lo apenas
   porque teremos as mesmas funcoes para listas ligadas.   
*/
int tamanhoEmBytes(LISTA *l) {
  return(sizeof(LISTA));
} /* tamanhoEmBytes */

/* Retornar a chave do primeiro elemento da lista sequencial (caso haja) e ERRO
   caso a lista esteja vazia */
TIPOCHAVE primeiroElem(LISTA *l){
  if(l->nroElem > 0) return(l->A[0].chave);
  else return(ERRO); // lista vazia
} /* primeiroElem */

/* Retornar a chave do ultimo elemento da lista seqüencial (caso haja) e ERRO
   caso a lista esteja vazia */
TIPOCHAVE ultimoElem(LISTA *l) {
  if(l->nroElem > 0) return(l->A[l->nroElem-1].chave);
  else return(ERRO); // lista vazia
} /* ultimoElem */

/* Retornar a chave do enesimo elemento da LISTA. Lembre-se que as posicoes do
   arranjo A vao de 0 a MAX-1, entao, por exemplo, o 2o elemento estah na 
   posicao 1 (ou seja, posicao n-1)  */
TIPOCHAVE enesimoElem(LISTA *l, int n) {
  if(( n>0 ) && (n <= l->nroElem)) return(l->A[n-1].chave);
  else return(ERRO);
} /* enesimoElem */

/* Destruição da lista sequencial */
void destruirLista(LISTA *l) {
  l->nroElem = 0;
} /* destruirLista */

/* Inserção "direta" na iésima posição (posicao i-1 do arranjo A).
   Funciona da mesma maneira de um insertioSort: deve-se deslocar todos os
   elementos a partir da iesima posicao e entao se insere o novo elemento. */
bool inserirElemLista(REGISTRO reg, int i, LISTA *l){
  int j;
  if ((l->nroElem >= MAX) || (i < 1) || (i > (l->nroElem+1))) 
     return(false); // lista cheia ou índice inválido
  if((l->nroElem > 0) && (i < l->nroElem+1))
  for (j = l->nroElem; j >= i; j--)
     l->A[j] = l->A[j-1];
  l->A[i-1] = reg;
  l->nroElem++;
  return(true);
} /* inserirElemLista */

/* Busca seqüencial em lista ordenada ou não SEM SENTINELA */
int buscaSeq(TIPOCHAVE ch, LISTA *l) {
  int i = 0;
  while (i < l->nroElem){
    if(ch == l->A[i].chave) return(i); // achou
    else i++;
  }
  return(ERRO); // não achou
} /* buscaSeq */

/* Busca sequencial em lista COM SENTINELA (vetor criado com MAX+1 posições) */
int buscaSent(TIPOCHAVE ch, LISTA *l) {
  int i = 0;
  l->A[l->nroElem].chave = ch; // sentinela
  while(l->A[i].chave != ch) i++;
  if (i > l->nroElem -1) return ERRO; // não achou
  else return(i);
} /* buscaSent */

/* Busca binaria em lista ordenada */
int buscaBin(TIPOCHAVE ch, LISTA *l){
  int inf, sup, meio;
  inf = 0;
  sup = l->nroElem-1;
  while(inf <= sup) {
    meio = ((inf + sup) / 2);
    if(l->A[meio].chave == ch) return(meio); // achou
    else {
      if(l->A[meio].chave < ch) inf = meio + 1;
      else sup = meio - 1;
    }
  }
  return(ERRO);
} /* buscaBin */

/* Inserção em lista ordenada COM SENTINELA sem duplicação */
bool inserirElemListaOrd(REGISTRO reg, LISTA *l) {
  int i = 0;
  TIPOCHAVE ch = reg.chave;
  if(l->nroElem >= MAX) return(false); // lista cheia
  l->A[l->nroElem].chave = ch; // sentinela
  while(l->A[i].chave < ch) i++;
  if((l->A[i].chave == ch) && (i < l->nroElem))
    return(false);
  else return(inserirElemLista(reg, i+1, l)); // i = posicao no arranjo, i+1 significa o iesimo elemento
} /* inserirElemListaOrd */

/* Exclusão do elemento cuja chave seja igual a ch */
bool excluirElemLista(TIPOCHAVE ch, LISTA *l)
{ 
  int pos, j;
  pos = buscaSeq(ch, l);
  if(pos == ERRO) return(false); // não existe
  for(j = pos; j < l->nroElem-1; j++) l->A[j] = l->A[j+1];
    l->nroElem--;
  return(true);
} /* excluirElemLista */
