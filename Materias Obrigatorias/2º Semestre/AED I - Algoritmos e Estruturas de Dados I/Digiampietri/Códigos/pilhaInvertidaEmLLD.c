/******************************************************************************
     pilhaInvertidaEmLLD.c
  Este programa gerencia pilhas implementas em listas lineares ligadas 
    (implementacao dinamica). Chamamos de pilha invertida porque o topo da 
    pilha (nesta implementação) é o início da lista.    
  As pilhas gerenciadas podem ter um numero arbitrario de elementos.
  Não usaremos sentinela nesta estrutura.
******************************************************************************/
#include "listaLigadaD.c"

typedef LISTA PILHA;

/* Inicialização da pilha (a pilha jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarPilha(PILHA *l){
     inicializarLista(l);
} /* inicializarPilha */

/* Exibição da lista (pilha invertida) */
void exibirPilha(PILHA *l){
  exibirLista(l);
} /* exibirLista */ 

/* Retornar o tamanho da pilha (numero de elementos) */
int tamanhoPilha(PILHA *l) {
  return tamanho(l);
} /* tamanho */

/* Retornar o tamanho em bytes da pilha. Neste caso, isto depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytesPilha(PILHA *l) {
  return tamanhoEmBytes(l);
} /* tamanhoEmBytes */

/* Busca Pilha - retorna primeira chave da pilha */
PONT buscaPilha(PILHA *l){
  return l->inicio;
} /* buscaPilha */

/* Destruição da pilha
   libera a memoria de todos os elementos da pilha*/
void destruirPilha(PILHA *l) {
     destruirLista(l);
} /* destruirPilha */

/* inserirElementoPilha - insere elemento no "topo" da pilha   */
bool inserirElementoPilha(REGISTRO reg, PILHA *l){
     PONT novo = (PONT) malloc(sizeof(REGISTRO));
     *novo = reg;
     novo->prox = l->inicio;
     l->inicio = novo;
     return true;
} /* inserirElementoPilha */

/* excluirElementoPilha - retorna e exclui o topo da pilha (no caso, o primeiro
  elemento da lista. Retorna false se nao houver elemento a ser retirado */
bool excluirElementoPilha(PILHA *l, REGISTRO * reg){
   if (l->inicio == NULL) return false;
   PONT excluir = l->inicio;
   l->inicio = l->inicio->prox;
   *reg = *(excluir);
   free(excluir);
   return true;
} /* excluirElementoPilha */

PONT retornarTopoPilha(PILHA *l, TIPOCHAVE * ch){
     if (l->inicio == NULL) return NULL;
     *ch = l->inicio->chave;
     return l->inicio;
}
