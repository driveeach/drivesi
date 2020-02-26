/******************************************************************************
//     pilhaEmLLD.c
// Este programa gerencia pilhas implementas em listas lineares ligadas 
   (implementacao dinamica).
// As pilhas gerenciadas podem ter um numero arbitrario de elementos.
// Não usaremos sentinela nesta estrutura.
******************************************************************************/
#include "listaLigadaD.c"

typedef LISTA PILHA;

/* Inicialização da pilha (a pilha jah esta criada e eh apontada 
pelo endereco em l) */
void inicializarPilha(PILHA *l){
     inicializarLista(l);
} /* inicializarPilha */

/* Exibição da pilha seqüencial */
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
     TIPOCHAVE temp;
     PONT ultimo = retornarUltimo(l, &temp);
     PONT novo = (PONT) malloc(sizeof(REGISTRO));
     *novo = reg;
     novo->prox = NULL;
     if (ultimo != NULL) ultimo->prox = novo;
     else l->inicio = novo;
     return true;
} /* inserirElementoPilha */

/* excluirElementoPilha - retorna e exclui ultimo elemento da pilha 
retorna false se nao houver elemento a ser retirado */
bool excluirElementoPilha(PILHA *l, REGISTRO * reg){
   if (l->inicio == NULL) return false;
   PONT ant, ultimo;
   ultimo = l->inicio;
   ant = NULL;
   while (ultimo->prox != NULL) {
         ant = ultimo;
         ultimo=ultimo->prox;
   }
   *reg = *(ultimo);
   ant->prox = NULL;
   free(ultimo);
   return true;
} /* excluirElementoPilha */

PONT retornarTopoPilha(PILHA *l, TIPOCHAVE * ch){
     return retornarUltimo(l, ch);
}
