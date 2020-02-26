/******************************************************************************
//     multiplasPilhasEstaticas.c
// Este programa gerencia NP pilhas implementadas em um arranjo
   (implementacao estatica).
// Cada pilha tem tamanho inicial MAX/NP, que pode ser alterado. A soma total
   dos elementos das pilhas eh limitado por MAX.
// Usaremos arranjos para guardarem a base e o topo das pilhas.
******************************************************************************/
#include <stdio.h>
#define ERRO -1
#define true 1
#define false 0
#define MAX 50 // tamanho do vetor A
# define NP 5 // nro. de pilhas compartilhando o vetor (numeradas de 1..NP)

typedef int bool;
typedef int TIPOCHAVE;

typedef struct {
  int base[NP+1]; // pilhas [0..NP-1] + pilha[NP] auxiliar (a "pilha[NP]" facilitará a verificação se a pilha NP-1 está completa)
  int topo[NP+1];
  TIPOCHAVE A[MAX];
} PILHAMULTIPLA;

// Inicializacao da pilha múltipla
void inicializarPilhaMultipla(PILHAMULTIPLA *p){
  int i;
  for(i = 0; i <= NP ; i++){
    p->base[i] = ( i * (MAX / NP) ) - 1;
    p->topo[i] = p->base[i];
  }
}

// Destruicao da pilha múltipla
void destruirPilhaMultipla(PILHAMULTIPLA *p){
  inicializarPilhaMultipla(p);
}

// Quantos elementos existem na pilha k
int tamanhoPilhaK(PILHAMULTIPLA p, int k) {
  return(p.topo[k] - p.base[k]);
}

// Tamanho em bytes total ds pilhas
int tamanhoEmBytesPilhas(PILHAMULTIPLA p) {
  return sizeof(PILHAMULTIPLA);
}

// Quantos elementos existem na soma das pilhas
int tamanhoTotalPilhas(PILHAMULTIPLA p) {
  int tamanho = 0;
  int i;
  for (i=0;i<NP;i++) tamanho = tamanho + tamanhoPilhaK(p,i);
  return tamanho;
}

// A pilha k esta cheia ?
bool pilhaKcheia(PILHAMULTIPLA *p, int k) {
  if(p->topo[k] == p->base[k + 1]) return(true);
  else return(false);
}

// Desloca pilha k uma posição para a direita, se possivel
bool paraDireita(PILHAMULTIPLA *p, int k) {
  int i;
  if( (k < 1) || (k > NP-1) ) return (false); // índice inválido
  if( (p->topo[k] < p->base[k + 1])){
    for(i = p->topo[k] + 1; i > p->base[k]; i--) p->A[i] = p->A[i-1];
    p->topo[k]++;
    p->base[k]++;
    return(true);
  }
  return(false);
}

// Desloca pilha k uma posição para a esquerda, se possivel
bool paraEsquerda(PILHAMULTIPLA *p, int k) {
  int i;
  if( (k < 1) || (k > NP-1) ) return (false); // índice inválido
  if((p->topo[k-1] < p->base[k])){
    for(i = p->base[k]+1; i <= p->topo[k]; i++) p->A[i-1] = p->A[i];
    p->topo[k]--;
    p->base[k]--;
    return(true);
  }
  return(false);
}

// Inserir um novo item no topo da pilha k
bool pushK(TIPOCHAVE ch, PILHAMULTIPLA *p, int k) {
  int j;
  if (pilhaKcheia(p,k)){
    // desloca p/direita todas as pilhas de [k+1..NP-1] em ordem reversa
    if(k < NP-1)
         for( j = NP-1; j > k; j--) paraDireita(p, j);
    if((pilhaKcheia(p, k)) && (k > 0)) // se a pilha continua cheia
        // desloca p/esquerda todas as pilhas de [1..k] (mas não a pilha 0)
         for( j = 1; j <= k; j++) paraEsquerda(p, j);
    if(pilhaKcheia(p, k)) return false;
  }
  p->topo[k]++;
  p->A[p->topo[k]] = ch;
  return true;
}

// Retirar um item da pilha k, ou -1
bool popK(PILHAMULTIPLA *p, TIPOCHAVE * ch, int k){
  if((p->topo[k] > p->base[k])) {
    *ch = p->A[p->topo[k]];
    p->topo[k]--;
    return true;
  } 
  return false;
}

// Retorna posicao do topo da pilha (e chave no endereco passado por ch), ou -1
int retornarElemento(PILHAMULTIPLA *p, int k, TIPOCHAVE *ch){
  if((p->topo[k] > p->base[k])) {
    *ch =p->A[p->topo[k]];
    return p->topo[k];
  }
  return -1;
}
