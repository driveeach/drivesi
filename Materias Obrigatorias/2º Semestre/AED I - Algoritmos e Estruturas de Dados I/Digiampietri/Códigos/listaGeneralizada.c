/******************************************************************************
     listaGenerlizada.c
  Este programa gerencia listas generalizadas.
******************************************************************************/
#include <stdio.h>
#define true 1
#define false 0
typedef int bool;
typedef char TIPOCHAVE;

typedef enum{elemLista, inicioLista} IDENT;
typedef struct aux {
  int id;
  IDENT tag;
  union {
    TIPOCHAVE chave;
    struct aux *sublista;
  };
  struct aux *prox;
} NO;
typedef NO* PONT;

// Inicialização
void inicializarLista(PONT *l){
  *l = NULL;
}

// Quantidade de chaves na lista
int contarChaves(PONT p){
  int chaves = 0;
  while (p) {
    if( p->tag == elemLista) chaves++;
    else chaves = chaves + contarChaves(p->sublista);
    p = p->prox;
  }
  return(chaves);
}

// Cria um novo no do tipo elemLista e retorna seu endereço
PONT criarNovoElementoLista(int pId, TIPOCHAVE pChave){
  PONT p1 = (PONT)malloc(sizeof(NO));
  p1->tag = elemLista;
  p1->id = pId;
  p1->chave = pChave;
  p1->prox = NULL;
  return p1;
}

// Cria um novo no do tipo inicioLista e retorna seu endereço
PONT criarNovoSubLista(int pId){
  PONT p1 = (PONT)malloc(sizeof(NO));
  p1->tag = inicioLista;
  p1->id = pId;
  p1->sublista = NULL;
  p1->prox = NULL;
  return p1;
}


// Quantidade de nos na lista
int contarNos(PONT p){
  int nos = 0;
  while (p) {
    nos++;
    if( p->tag == inicioLista) nos = nos + contarNos(p->sublista);
    p = p->prox;
  }
  return(nos);
}

// Quantidade de nos na lista com uma recursao dupla
int contarNosR(PONT p){
  if (!p) return 0;
  if (p->tag == elemLista) return 1+contarNosR(p->prox);
  else return 1+contarNosR(p->prox)+contarNosR(p->sublista);
}

// Exibir lista (recursivamente)
void exibir(PONT p){
  if (!p) return;
  if (p->tag == elemLista) {
    printf("%d(%c) ",p->id,p->chave);
    exibir(p->prox);
  }
  else {
    printf("%d ",p->id);
    exibir(p->prox);
    if (p->sublista) {
      printf("\n[%d]: ");
      exibir(p->sublista);
    }
  }
}



// Profundidade maxima da lista
int profundidade(PONT p){
  int maximo = 0;
  int resp;
  if(!p) return(maximo);
  while(p){
    if( p->tag == elemLista) resp = 0;
    else resp = profundidade(p->sublista);
    if(resp > maximo) maximo = resp;
    p = p->prox;
  }
  return(maximo + 1);
}

// copia uma lista inteira
PONT copiarListaGen(PONT p){
  PONT novo, abaixo, dir;
  IDENT tipo;
  novo = NULL;
  if (p){
    tipo = p->tag;
    if( tipo == inicioLista) abaixo = copiarListaGen(p->sublista);
    dir = copiarListaGen(p->prox);
    novo = (NO *) malloc(sizeof(NO));
    novo->tag = tipo;
    novo->prox = dir;
    if( tipo == elemLista) novo->chave = p->chave;
    else novo->sublista = abaixo;
  }
  return(novo);
}

// verifica se duas listas são identicas
bool listasIguais(PONT a, PONT b){
  bool resp = false;
  if((!a) && (!b)) return(true);
  if((a) && (b)) 
    if( a->tag == b->tag)
      if (a->id == b->id) {
        if( a->tag == elemLista) resp = (a->chave == b->chave);
        else resp = listasIguais(a->sublista, b->sublista);
        if(resp) resp = listasIguais(a->prox, b->prox);
  }
  return(resp);
}

/* recebe o endereço do nó "anterior" ao que será inserido. O no que sera 
   inserido vem n forma de um ponteiro para o novo no (jah alocado). Se o
   endereco anterior for NULL, insere no comeco da lista.                    
   Recebe um parametro bool inserirProx que, se sim, insere no ponteiro prox
   do no anterior, senao no ponteiro sublista                                */
bool inserir(PONT anterior, PONT novo, PONT * primeiro, bool inserirProx){
  if (anterior==NULL){ // insere no inicio
    novo->prox = *primeiro;
    *primeiro = novo;
    return true;
  }else{
    if (inserirProx){
      novo->prox = anterior->prox;
      anterior->prox = novo;
      return true;
    }else{
      if (anterior->tag != inicioLista) return false;
      else{
        novo->prox = anterior->sublista;
        anterior->sublista = novo;         
      }
    }
  }
}
