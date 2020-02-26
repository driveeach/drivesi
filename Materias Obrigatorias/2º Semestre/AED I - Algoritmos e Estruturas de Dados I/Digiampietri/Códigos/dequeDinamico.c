/******************************************************************************
     dequeDinamico.c
  Este programa gerencia deques (filas com duas pontas)- implementacao dinamica.
  Os deques gerenciados podem ter um numero arbitrario de elementos.
******************************************************************************/
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;
typedef int TIPOCHAVE;

typedef struct tempRegistro{
  TIPOCHAVE chave;
  struct tempRegistro *prox;
  struct tempRegistro *ant;
// outros campos...
} REGISTRO;

typedef REGISTRO* PONT;

typedef struct {
  PONT inicio1;
  PONT inicio2; // pode ser chamado de fim (corresponde a segunda extremidade
} DEQUE;

/* Inicialização do deque (o deque jah esta criado e eh apontado 
pelo endereco em d) */
void inicializarDeque(DEQUE *l){
  l->inicio1 = NULL;
  l->inicio2 = NULL;
} /* inicializarDeque */

/* Exibição do DEQUE a partir do inicio1 */
void exibirDeque1(DEQUE *l){
  PONT end = l->inicio1;
  printf("Deque entrada 1: \" ");
  while (end != NULL){
    printf("%d ", end->chave); // soh lembrando TIPOCHAVE = int
    end = end->prox;
  }
  printf("\"\n");
} /* exibirDeque1 */ 

/* Exibição do DEQUE a partir do inicio2 */
void exibirDeque2(DEQUE *l){
  PONT end = l->inicio2;
  printf("Deque entrada 2: \" ");
  while (end != NULL){
    printf("%d ", end->chave); // soh lembrando TIPOCHAVE = int
    end = end->ant;
  }
  printf("\"\n");
} /* exibirDeque2 */ 

/* Retornar o tamanho do deque (numero de elementos) */
int tamanho(DEQUE *l) {
  PONT end = l->inicio1;
  int tam = 0;
 while (end != NULL){
    tam++;
    end = end->prox;
  }
  return tam;
} /* tamanho */

/* Retornar o tamanho em bytes da lista. Neste caso, isto depende do numero
   de elementos que estao sendo usados.   */
int tamanhoEmBytes(DEQUE *d) {
  return((tamanho(d))*sizeof(REGISTRO))+sizeof(DEQUE); // sizeof(DEQUE) = 2*sizeof(PONT) pois a "DEQUE" eh composto por ponteiros
} /* tamanhoEmBytes */

/* Busca sequencial 
   NAO EH UMA OPERACAO PADRAO DE UM DEQUE  */
PONT buscaSeq(TIPOCHAVE ch, DEQUE *l){
  PONT pos = l->inicio1;
  while ((pos != NULL) && (pos->chave != ch)) pos = pos->prox;
  return pos;
} /* buscaSeq */

/* Exclusão do primeiro elemento, entrada 1 do deque 
   e colocar o valor da chave do elemento memoria apontada por ch*/
bool excluirElemDeque1(DEQUE *l, TIPOCHAVE * ch){
  if (l->inicio1 == NULL) return false;  // deque vazio
  PONT apagar = l->inicio1;
  *ch = apagar->chave;
  l->inicio1 = l->inicio1->prox;
  if (l->inicio1 != NULL) l->inicio1->ant = NULL;
  else l->inicio2 = NULL;
  free(apagar);
  return true;
} /* excluirElemDeque1 */

/* Exclusão do primeiro elemento, entrada 2 do deque
   e colocar o valor da chave do elemento memoria apontada por ch*/
bool excluirElemDeque2(DEQUE *l, TIPOCHAVE * ch){
  if (l->inicio2 == NULL) return false;  // deque vazio
  PONT apagar = l->inicio2;
  *ch = apagar->chave;
  l->inicio2 = l->inicio2->ant;
  if (l->inicio2 != NULL) l->inicio2->prox = NULL;
  else l->inicio1 = NULL;
  free(apagar);
  return true;
} /* excluirElemDeque2 */

/* Destruição do deque
   libera a memoria de todos os elementos do deque*/
void destruirDeque(DEQUE *l) {
  PONT end = l->inicio1;
  while (end != NULL){
    PONT apagar = end;
    end = end->prox;
    free(apagar);
  }
  l->inicio1 = NULL;
  l->inicio2 = NULL;
} /* destruirDeque */

/* Inserção no deque, entrada 1 */
bool inserirDeque1(REGISTRO reg, DEQUE *l) {
  PONT i = (PONT) malloc(sizeof(REGISTRO));
  *i = reg;
  i->ant = NULL;
  i->prox = l->inicio1;
  l->inicio1 = i;
  if (l->inicio2 == NULL) l->inicio2 = i;
  else l->inicio1->prox->ant = l->inicio1;
  return true;
} /* inserirDeque1 */

/* Inserção no deque, entrada 2 */
bool inserirDeque2(REGISTRO reg, DEQUE *l) {
  PONT i = (PONT) malloc(sizeof(REGISTRO));
  *i = reg;
  i->prox = NULL;
  i->ant = l->inicio2;
  l->inicio2 = i;
  if (l->inicio1 == NULL) l->inicio1 = i;
    else l->inicio2->ant->prox = l->inicio2;
  return true;
} /* inserirDeque2 */

/* retornarPrimeiro1 - retorna o endereco do primeiro elemento do deque 1 */
PONT retornarPrimeiro1(DEQUE *l, TIPOCHAVE *ch){
  if (l->inicio1 != NULL) *ch = l->inicio1->chave;
  return l->inicio1;
} /* retornarPrimeiro1 */

/* retornarPrimeiro2 - retorna o endereco do primeiro elemento do deque 2*/
PONT retornarPrimeiro2(DEQUE *l, TIPOCHAVE *ch){
  if (l->inicio2 != NULL) *ch = l->inicio2->chave;
  return l->inicio2;
} /* retornarPrimeiro2 */
