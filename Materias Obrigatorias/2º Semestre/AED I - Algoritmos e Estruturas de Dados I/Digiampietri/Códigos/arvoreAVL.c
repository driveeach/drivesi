/******************************************************************************/
/*              Este programa gerencia arvores AVL                            */
/*  Arvores AVL sao arvores balanceadas na altura.                            */
/*  O nome AVL vem de seus criadores Adelson Velsky e Landis, cuja primeira   */
/*    referência encontra-se no documento "Algoritmos para organização da     */
/*    informação" de 1962.                                                    */
/******************************************************************************/

#include <stdio.h>
#include <malloc.h>
#define true 1
#define false 0

typedef int bool;
typedef int TIPOCHAVE;

typedef struct aux {
  TIPOCHAVE chave;
  struct aux *esq;
  struct aux *dir;
  int bal; // fator de balanceamento (0, -1 ou +1)
} NO, *PONT;

/* cria um novo (aloca memoria e preenche valores) no com chave=ch e retorna 
   seu endereco */
PONT criarNovoNo(TIPOCHAVE ch){
   PONT novoNo = (PONT)malloc(sizeof(NO));
   novoNo->esq = NULL;
   novoNo->dir = NULL;
   novoNo->chave = ch;
   novoNo->bal = 0;
   return novoNo;
}


// Retorna o maior valor entre dois inteiros
int max(int a, int b){
  if (a>b) return a;
  return b;
}

// Retorna a altura de uma (sub-)arvore
int altura(PONT p){
  if (!p) return 0;
  else return 1 + max(altura(p->esq),altura(p->dir));
}

// Verifica se árvore é AVL
bool ehAVL(PONT p){
  int e,d;
  bool ok = true;
  if(p){
    ok = ehAVL(p->esq);
    if(ok) ok = ehAVL(p->dir);
    if(ok){
      e = altura(p->esq);
      d = altura(p->dir);
      if(abs(e-d) <= 1) ok = true;  
      else ok = false;
    }
  }
  return(ok);
}


// Rotações à direita (LL e LR)
void rotacaoL(PONT *raiz, PONT *p, bool *alterou){
  PONT u, v;
  u = (*p)->esq;
  if(u->bal == -1) {   // LL
    (*p)->esq = u->dir;
    u->dir = *p;
    (*p)->bal = 0;
    if(*p == *raiz) *raiz = u;
    *p = u;
  } else {  // LR
    v = u->dir;
    u->dir = v->esq;
    v->esq = u;
    (*p)->esq = v->dir;
    v->dir = *p;
    if(v->bal == -1)(*p)->bal = 1;
    else (*p)->bal = 0;
    if(v->bal == 1) u->bal = -1;
    else u->bal = 0;
    if(*p == *raiz) *raiz = v;
    *p = v;
  }
  (*p)->bal = 0; // balanço final da raiz (p) da subarvore
  *alterou = false;
}

// Rotações à esquerda (RR e RL)
void rotacaoR(PONT *raiz, PONT *p, bool *alterou){
  PONT u, v;
  u = (*p)->dir;
  if(u->bal == 1) {   // RR
    (*p)->dir = u->esq;
    u->esq = *p;
    (*p)->bal = 0;
    if(*p == *raiz) *raiz = u;
    *p = u;
  } else {  // RL
    v = u->esq;
    u->esq = v->dir;
    v->dir = u;
    (*p)->dir = v->esq;
    v->esq = *p;
    if(v->bal == -1)(*p)->bal = 1;
    else (*p)->bal = 0;
    if(v->bal == 1) u->bal = -1;
    else u->bal = 0;
    if(*p == *raiz) *raiz = v;
    *p = v;
  }
  (*p)->bal = 0; // balanço final da raiz (p) da subarvore
  *alterou = false;
}



// Inserção AVL: p é inicializado com raiz e *alterou com false
// O retorno da função é o nó inserido, mas não precisa ser usado em main()
PONT inserirAVL(PONT *raiz, TIPOCHAVE ch, PONT p, bool *alterou){
  if(!p){
    p = criarNovoNo(ch);
    *alterou = true;
    if(*raiz == NULL) *raiz =p;
  } else {
    if(ch < (p)->chave) {
      p->esq = inserirAVL(raiz, ch, p->esq, alterou);
      if(*alterou)
      switch (p->bal) {
        case 1 : (p)->bal = 0;
          *alterou = false;
        break;
        case 0 : (p)->bal = -1;
        break;
        case -1: rotacaoL(raiz, &p, alterou);
        break;
      }
    } else {
      p->dir = inserirAVL(raiz, ch, (p)->dir, alterou);
      if(*alterou)
      switch (p->bal) {
        case -1: p->bal = 0;
          *alterou = false;
        break;
        case 0 : p->bal = 1;
        break;
        case 1 : rotacaoR(raiz, &p, alterou);
        break;
      }
    }
  }
  return (p);
}








/* retorna o endereco do NO que contem chave=ch ou NULL caso a chave nao seja
   encontrada. Utiliza busca binaria recursiva                                */
PONT buscaBinaria(TIPOCHAVE ch, PONT raiz){
  if (raiz == NULL) return NULL;
  if (raiz->chave == ch) return raiz;
  if (raiz->chave<ch) 
    return buscaBinaria(ch,raiz->dir);
  return buscaBinaria(ch,raiz->esq);
}  


// Busca binária não recursiva devolvendo o nó pai
PONT buscaNo(PONT raiz, TIPOCHAVE ch, PONT *pai){
  PONT atual = raiz;
  *pai = NULL;
  while (atual) {
    if(atual->chave == ch)
    return(atual);
    *pai = atual;
    if(ch < atual->chave) atual = atual->esq;
    else atual = atual->dir;
  }
  return(NULL);
}


/* Exibe arvore Em Ordem         */
void exibirArvoreEmOrdem(PONT raiz){
  if (raiz == NULL) return;
  exibirArvoreEmOrdem(raiz->esq);
  printf("%d ",raiz->chave);
  exibirArvoreEmOrdem(raiz->dir);
}

/* Exibe arvore Pre Ordem         */
void exibirArvorePreOrdem(PONT raiz){
  if (raiz == NULL) return;
  printf("%d ",raiz->chave);
  exibirArvorePreOrdem(raiz->esq);
  exibirArvorePreOrdem(raiz->dir);
}

/* Exibe arvore Pos Ordem         */
void exibirArvorePosOrdem(PONT raiz){
  if (raiz == NULL) return;
  exibirArvorePreOrdem(raiz->esq);
  exibirArvorePreOrdem(raiz->dir);
  printf("%d ",raiz->chave);
}

/* Exibe arvore Em Ordem (com parenteses para os filhos)    */
void exibirArvore(PONT raiz){
  if (raiz == NULL) return;
  printf("%d",raiz->chave);
  printf("(");     
  exibirArvore(raiz->esq);
  exibirArvore(raiz->dir);
  printf(")");     
}

/* Exibe arvore Pre-Ordem indicando pai de cada no    */
void exibirArvore2(PONT raiz, TIPOCHAVE chavePai){
  if (raiz == NULL) return;
  printf("(%d,%d) ",chavePai,raiz->chave);
  exibirArvore2(raiz->esq,raiz->chave);
  exibirArvore2(raiz->dir,raiz->chave);
}

/* funcao auxiliar na destruicao (liberacao da memoria) de uma arvore */
void destruirAux(PONT subRaiz){
  if (subRaiz){
    destruirAux(subRaiz->esq);
    destruirAux(subRaiz->dir);
    free(subRaiz);
  }
}

/* libera toda memoria de uma arvore e coloca NULL no valor da raiz    */
void destruirArvore(PONT * raiz){
  destruirAux(*raiz);
  *raiz = NULL;
}


/* inicializa arvore: raiz=NULL */
void inicializar(PONT * raiz){
  *raiz = NULL;
}


void travessiaAux(PONT p, int *niv, TIPOCHAVE ch, bool *achou) {
  if(p) {
    (*niv)++;// *niv = *niv + 1;
    if(p->chave == ch) *achou = true;
    else {
      travessiaAux(p->esq, niv, ch, achou);
      if(!(*achou)) travessiaAux(p->dir, niv, ch, achou);
      if(!(*achou)) *niv = *niv - 1;
    }
  }
}

/* Retorna true e o nivel de uma chave (caso seja encontrada) e false caso 
   contrario.                                                                */
bool travessia(PONT p, int *niv, TIPOCHAVE ch) {
  *niv = 0;
  PONT temp = buscaBinaria(ch,p);
  if (temp){
    bool achou = false;
    travessiaAux(p, niv, ch, &achou);
    return true;
  }
  else return false;
}
   

