/******************************************************************************
//     pilhaEstatica.c
// Este programa gerencia PILHAs implementadas em arranjos 
   (implementacao estatica).
// As PILHAs gerenciadas podem ter um numero de no maximo MAX elementos.
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
  int topo;
  REGISTRO A[MAX];
} PILHA;


/* Inicialização da PILHA (a PILHA jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarPilha(PILHA *l){
  l->topo = -1;
} /* inicializarPILHA */


/* Retornar o tamanho da pilha (numero de elementos) */
int tamanhoPilha(PILHA *l) {
  int tamanho = l->topo+1;
  return tamanho;
} /* tamanho */

/* Exibição da pilha seqüencial */
void exibirPilha(PILHA *l){
  printf("Pilha: \" ");
  int i;
  for (i=l->topo;i>=0;i--){
    printf("%d ", l->A[i].chave); // soh lembrando TIPOCHAVE = int
  }
  printf("\"\n");
} /* exibirPilha */ 


/* Retornar o tamanho em bytes da pilha. Neste caso, isto nao depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytesPilha(PILHA *l) {
  return sizeof(PILHA);
} /* tamanhoEmBytes */

/* Busca Pilha - retorna posicao do primeiro elemento da pilha (topo) */
int buscaPilha(PILHA *l){
  return l->topo;
} /* buscaPilha */

/* Destruição da pilha */
void destruirPilha(PILHA *l) {
     inicializarPilha(l);
} /* destruirPilha */

/* inserirElementoPilha - insere elemento no fim da pilha   */
bool inserirElementoPilha(REGISTRO reg, PILHA *l){
     if (l->topo+1>= MAX) return false;
     l->topo = l->topo+1;
     l->A[l->topo] = reg;
     return true;
} /* inserirElementoPilha */

/* excluirElementoPilha - retorna e exclui 1o elemento da pilha 
retorna false se nao houver elemento a ser retirado */
bool excluirElementoPilha(PILHA *l, REGISTRO * reg){
   if (l->topo == -1) return false;
   *reg = l->A[l->topo];
   l->topo = l->topo-1;
   return true;
} /* excluirElementoPilha */

/* retornarPrimeiroPilha
retorna a posicao do primeiro (topo) elemento da pilha e o valor de sua chave no 
conteudo do endereco ch. Retorna -1 caso a lista esteja vazia */
int retornarPrimeiroPilha(PILHA *p, TIPOCHAVE * ch){
    if (p->topo==-1)return -1;
    *ch = p->A[p->topo].chave;
    return p->topo;
}
