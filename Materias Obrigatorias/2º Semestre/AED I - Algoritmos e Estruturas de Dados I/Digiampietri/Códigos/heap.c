/******************************************************************************
                             heap.c
   Este programa implementa um heap MAXIMO em uma implementação estática de uma 
   árvore binária. Nao usa a posicao 0 (zero) do arranjo.
******************************************************************************/

#include <stdio.h>
#define true 1
#define false 0

typedef int bool;

typedef struct {
  int * A;
  int tamanhoAtual;
  int tamanhoMaximo;
} HEAP;

void inicializarHeap(HEAP * h, int tamanhoMax){
  h->A = (int*) malloc(sizeof(int)*(tamanhoMax+1));
  h->tamanhoAtual = 0;
  h->tamanhoMaximo = tamanhoMax;
}

void destruirHeap(HEAP * h){
  int tamanho = h->tamanhoMaximo;
  free(h->A);
  h->tamanhoMaximo=0;
  h->tamanhoAtual=0;
}


int pai(int i){
  return i/2;
}

int filhoEsquerda(int i){
  return 2*i;
}

int filhoDireita(int i){
  return 2*i + 1;
}

/* metodo auxiliar que pressupoe que o heap para qualquer j>i estah ordenado
   porem o elemento i nao eh necessariamente maior que seus filhos           */
void maxHeapify(HEAP * h, int i){
  int esq = filhoEsquerda(i);
  int dir = filhoDireita(i);
  int temp;
  int maior = i;
  if ((esq <= h->tamanhoAtual) && (h->A[esq]>h->A[i])) maior = esq;
  if ((dir <= h->tamanhoAtual) && (h->A[dir]>h->A[maior])) maior = dir;
  if (maior != i) {
     temp = h->A[i];
     h->A[i] = h->A[maior];
     h->A[maior] = temp;
     maxHeapify(h,maior);
  }
}

// Constroi heap a partir do arranjo usando o metodo maxHeapify
void construirHeapMaximo(HEAP * h){
  int i;
  int metadeTamanho = h->tamanhoAtual/2;
  for (i=metadeTamanho;i>0;i--) maxHeapify(h,i);
}

// Insere no final do arranjo do heap
bool inserirForaDeOrdem(HEAP * h, int valor){
  if (h->tamanhoAtual < h->tamanhoMaximo){
    (h->tamanhoAtual)++;
    h->A[h->tamanhoAtual] = valor;
    return true;
  }
  return false;
}


// Imprime o arranjo (na ordem que estiver)
void imprimirArranjo(HEAP h){
  int tamanho = h.tamanhoAtual;
  int i;
  for (i=1;i<=tamanho;i++) printf("%d ",h.A[i]);
  printf("\n");
}


// Imprime elementos em ordem decrescente e esvazia o heap
void heapSort(HEAP * h){
  int tamanho = h->tamanhoAtual;
  int i, temp;
  construirHeapMaximo(h);  // se o arranjo jah for um heap, nao precisa desta linha
  for (i=tamanho;i>1;i--){
     temp = h->A[1];
     h->A[1] = h->A[i];
     h->A[i] = temp;
     //printf("%d ",temp);
     (h->tamanhoAtual)--;
     maxHeapify(h,1);
  }
  //printf("\n");
  h->tamanhoAtual = tamanho;;
}

bool inserirHeap(HEAP * h, int chave){
  int i, temp;
  if (h->tamanhoAtual == h->tamanhoMaximo) return false;
  (h->tamanhoAtual)++;
  i = h->tamanhoAtual;
  h->A[i] = chave;
  while ((i>1) && (h->A[pai(i)]<h->A[i])){
     temp = h->A[i];
     h->A[i] = h->A[pai(i)];
     h->A[pai(i)] = temp;       
     i = pai(i);
  }
  return true;
}

int main(){
  HEAP meuHeap;
  inicializarHeap(&meuHeap, 100);
  inserirForaDeOrdem(&meuHeap,10);
  inserirForaDeOrdem(&meuHeap,5);
  inserirForaDeOrdem(&meuHeap,3);
  inserirForaDeOrdem(&meuHeap,20);
  inserirForaDeOrdem(&meuHeap,8);
  inserirForaDeOrdem(&meuHeap,6);
  inserirForaDeOrdem(&meuHeap,15);
  inserirForaDeOrdem(&meuHeap,1);
  inserirForaDeOrdem(&meuHeap,18);
  inserirForaDeOrdem(&meuHeap,40);
  inserirForaDeOrdem(&meuHeap,17);
  inserirForaDeOrdem(&meuHeap,12);
  imprimirArranjo(meuHeap);
  construirHeapMaximo(&meuHeap);
  imprimirArranjo(meuHeap);
  heapSort(&meuHeap);  
  imprimirArranjo(meuHeap);
  destruirHeap(&meuHeap);


  printf("\nTestes parte 2\n");
  inicializarHeap(&meuHeap, 50);
  inserirHeap(&meuHeap,10);
  inserirHeap(&meuHeap,5);
  inserirHeap(&meuHeap,3);
  inserirHeap(&meuHeap,20);
  inserirHeap(&meuHeap,8);
  inserirHeap(&meuHeap,6);
  inserirHeap(&meuHeap,15);
  inserirHeap(&meuHeap,1);
  inserirHeap(&meuHeap,18);
  inserirHeap(&meuHeap,40);
  inserirHeap(&meuHeap,17);
  inserirHeap(&meuHeap,12);
  imprimirArranjo(meuHeap);
  construirHeapMaximo(&meuHeap);
  imprimirArranjo(meuHeap);
  heapSort(&meuHeap);  
  imprimirArranjo(meuHeap);

  system("pause");
  
  destruirHeap(&meuHeap);
  system("pause");

}
