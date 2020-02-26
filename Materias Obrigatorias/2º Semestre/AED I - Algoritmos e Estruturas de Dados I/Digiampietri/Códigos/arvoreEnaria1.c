/******************************************************************************
     arvoreEnaria1.c
  Este programa gerencia arvores n-arias (cada noh pode ter um numero ilimitado
  de filhos. Esta estrutura usa uma lista ligada de filhos.
******************************************************************************/
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;
typedef char TIPOCHAVE;

typedef struct {
  TIPOCHAVE chave;
  void * filhos;
} NO;

typedef NO* PONT;

typedef struct auxF {
  PONT no;
  struct auxF * prox; 
}FILHOS;



/* retorna o endereco do NO que contem chave=ch ou NULL caso a chave nao seja
   encontrada.                                                                */
PONT buscarChave(TIPOCHAVE ch, PONT raiz){
  if (raiz == NULL) return NULL;
  if (raiz->chave == ch) return raiz;
  FILHOS* aux = (FILHOS*)raiz->filhos;
  while (aux){
    PONT temp = buscarChave(ch, aux->no);
    if (temp) return temp;
    aux = aux->prox;
  }
  return NULL;
}  

PONT criarNovoNo(TIPOCHAVE ch){
   PONT novoNo = (PONT)malloc(sizeof(NO));
   novoNo->filhos = NULL;
   novoNo->chave = ch;
   return novoNo;
}

bool inserirFilho(PONT raiz, TIPOCHAVE novaChave, TIPOCHAVE chavePai){
       PONT pai = buscarChave(chavePai,raiz);
       if (!pai) return false;
       PONT novo = criarNovoNo(novaChave);
       FILHOS* novoFilho = (FILHOS*)malloc(sizeof(FILHOS));
       novoFilho->no = novo;
       novoFilho->prox = NULL;
       FILHOS* ultimoFilho = (FILHOS*)pai->filhos;
       if (ultimoFilho == NULL) pai->filhos = novoFilho;       
       else {
         while (ultimoFilho->prox != NULL) ultimoFilho = ultimoFilho->prox;
         ultimoFilho->prox = novoFilho;
       }
     return true;
}

void exibirArvore(PONT raiz){
  if (raiz == NULL) return;
  printf("%c",raiz->chave);
  FILHOS* filhos = (FILHOS*)raiz->filhos;
  if (filhos == NULL) return;
  printf("(");
  while (filhos != NULL){
    exibirArvore(filhos->no);
    filhos = filhos->prox;
  }
  printf(")");     
}

PONT criarRaiz(TIPOCHAVE novaChave){
    return criarNovoNo(novaChave);
} 
  
int main(){
  PONT raiz = criarRaiz('a');

  inserirFilho(raiz,'b','a');
  inserirFilho(raiz,'c','a');
  inserirFilho(raiz,'d','a');
  inserirFilho(raiz,'e','b');
  inserirFilho(raiz,'f','b');
  inserirFilho(raiz,'g','b');
  inserirFilho(raiz,'h','d');
  inserirFilho(raiz,'i','h');
  inserirFilho(raiz,'j','h');
  
  
  exibirArvore(raiz);

  char fim;
  printf("\nPressione <ENTER> para terminar.\n");
  scanf("%c",&fim);

  return 0;
}
  
