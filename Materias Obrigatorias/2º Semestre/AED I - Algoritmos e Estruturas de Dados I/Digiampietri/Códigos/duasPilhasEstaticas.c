/******************************************************************************
//     duasPilhasEstaticas.c
// Este programa gerencia duas pilhas implementadas em um arranjo
   (implementacao estatica).
// As pilhas gerenciadas podem ter um numero de no maximo MAX elementos (juntas).
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
  int topo1;
  int topo2;
  REGISTRO A[MAX];
} PILHADUPLA;


/* Inicialização da PILHADUPLA (a PILHADUPLA jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarPilhaDupla(PILHADUPLA *l){
  l->topo1 = -1;
  l->topo2 = MAX;
} /* inicializarPilhaDupla */


/* Retornar o tamanho da PILHADUPLA (numero de elementos das duas somadas) */
int tamanhoPilhaDupla(PILHADUPLA *p) {
  int tamanho = p->topo1+1 + MAX-p->topo2;
  return tamanho;
} /* tamanhoPilhaDupla */

/* Retornar o tamanho de uma das pilhas */
int tamanhoUmaPilha(PILHADUPLA *p, int pilha) {
  int tamanho;
  if (pilha == 1) tamanho = p->topo1+1;
  else tamanho = MAX-p->topo2;
  return tamanho;
} /* tamanhoUmaPilha */

/* Exibição de uma das pilhas */
void exibirUmaPilha(PILHADUPLA *p, int pilha){
  printf("Pilha %d: \" ", pilha);
  int i;
  if (pilha == 1) for (i=p->topo1;i>=0;i--) printf("%d ", p->A[i].chave);
  else for (i=p->topo2;i<MAX;i++) printf("%d ", p->A[i].chave);
  printf("\"\n");
} /* exibirPilha */ 

/* Retornar o tamanho em bytes da pilha. Neste caso, isto nao depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytesPilha(PILHADUPLA *p) {
  return sizeof(PILHADUPLA);
} /* tamanhoEmBytes */

/* Destruição da pilha */
void destruirPilha(PILHADUPLA *l) {
     inicializarPilhaDupla(l);
} /* destruirPilha */

/* inserirElementoPilha - insere elemento no fim da pilha   */
bool inserirElementoPilha(REGISTRO reg, PILHADUPLA *l, int pilha){
     if (l->topo1 + 1 == l->topo2) return false;
     if (pilha == 1) {
       l->topo1 = l->topo1+1;
       l->A[l->topo1] = reg;
     }else{
       l->topo2 = l->topo2-1;
       l->A[l->topo2] = reg;        
     }
     return true;
} /* inserirElementoPilha */

/* excluirElementoPilha - retorna e exclui topo da pilha 
retorna false se nao houver elemento a ser retirado */
bool excluirElementoPilha(PILHADUPLA *l, REGISTRO * reg, int pilha){
   if (pilha == 1){
     if (l->topo1 == -1) return false;
       *reg = l->A[l->topo1];
       l->topo1 = l->topo1-1;
   }else{
     if (l->topo2 == MAX) return false;
       *reg = l->A[l->topo2];
       l->topo1 = l->topo2+1;      
   }
   return true;
} /* excluirElementoPilha */

/* retornarPrimeiroPilha
retorna a posicao do primeiro (topo) elemento da pilha e o valor de sua chave no 
conteudo do endereco ch. Retorna -1 caso a lista esteja vazia */
int retornarPrimeiroPilha(PILHADUPLA *p, TIPOCHAVE * ch, int pilha){
  if (pilha == 1){
    if (p->topo1==-1)return -1;
    *ch = p->A[p->topo1].chave;
    return p->topo1;
  }
  if (p->topo2==MAX)return -1;
  *ch = p->A[p->topo2].chave;          
  return p->topo2;
}
