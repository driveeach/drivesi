/******************************************************************************
     arvoreEnaria2.c
  Este programa gerencia arvores n-arias (cada noh pode ter um numero ilimitado
  de filhos). Cada noh possui dois ponteiros um para seu ultimo filho e um para 
  o seu irmao a esquerda (essa lista de irmaos funciona como uma lista ligada).
******************************************************************************/
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;
typedef char TIPOCHAVE;

typedef struct auxNo{
  TIPOCHAVE chave;
  struct auxNo * ultimoFilho; 
  struct auxNo * ultimoIrmao; 
} NO, * PONT;


/* retorna o endereco do NO que contem chave=ch ou NULL caso a chave nao seja
   encontrada.                                                                */
PONT buscarChave(TIPOCHAVE ch, PONT raiz){
  if (raiz == NULL) return NULL;
  if (raiz->chave == ch) return raiz;
  PONT aux = buscarChave(ch,raiz->ultimoFilho);
  if (aux) return aux;
  return buscarChave(ch,raiz->ultimoIrmao);
}  

PONT criarNovoNo(TIPOCHAVE ch){
   PONT novoNo = (PONT)malloc(sizeof(NO));
   novoNo->ultimoFilho = NULL;
   novoNo->ultimoIrmao = NULL;
   novoNo->chave = ch;
   return novoNo;
}

bool inserirFilho(PONT raiz, TIPOCHAVE novaChave, TIPOCHAVE chavePai){
     PONT pai = buscarChave(chavePai,raiz);
     if (!pai) return false;
     PONT novo = criarNovoNo(novaChave);
     novo->ultimoFilho = NULL;
     novo->ultimoIrmao = pai->ultimoFilho;
     pai->ultimoFilho = novo;
     return true;
}

void exibirArvore(PONT raiz){
  if (raiz == NULL) return;
  printf("%c",raiz->chave);
  PONT filhos = raiz->ultimoFilho;
  if (filhos == NULL) return;
  printf("(");
  while (filhos != NULL){
    exibirArvore(filhos);
    filhos = filhos->ultimoIrmao;
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
  
