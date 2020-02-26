#include <stdio.h>
#include "util_ep1.c"
#define true 1
#define false 0

typedef int bool;

typedef int TCHAVE;

/* Esta estrutura define os Registros que serao utilizados em nossa tabela hash.
   Cada registro sera um elemento de uma lista ligada, apontando para o proximo
   elemento da lista (campo prox).
   Cada registro contera a chave que foi inserida e um contador de quantas vezes
   essa chave foi inserida, isto eh, nossa tabela hash permite a insercao de uma
   chave mais de uma vez, porem devemos criar apenas um registro para essa chave
   e controlar no campo contador quantes vezes essa chave foi inserida/excluida
   sempre que o contador cair para zero, o registro da chave deverah ser
   excluido da lista e sua memoria liberada.
*/
typedef struct aux {
  TCHAVE chave;
  int contador;
  struct aux* prox;
} Registro, * Pont;


/* Esta estrutura define uma tabela hash que utilizara enderecamento aberto,
   ou seja, listas ligadas de elementos.
   o campo TabelaHash contem o ponteiro para a tabela hash propriamente dita,
      isto eh, um ponteiro para um arranjo de ponteiros para Registros
   o campo tamanhoDaTabela contem o tamanho do arranjo alocado para a TabelaHash
   o campo quantidadeTotalDeRegistros contem a quantidade total de registros
      que estao sendo utilizadas na nossa estrutura hash (todos os registros em
      todas as listas ligadas apontadas pelos ponteiros da nossa tabela hash
      propriamente dita).
*/
typedef struct {
  Pont* TabelaHash;
  int tamanhoDaTabela;
  int quantidadeTotalDeRegistros;
} HASH;

int quantidadeTotalDeChaves(HASH *h);
void inicializarHash(HASH *h, int tamanho);
int liberarMemoriaTabelaHash(HASH* h);
bool inserirElemento(HASH *h, TCHAVE ch);
bool excluirElemento(HASH *h, TCHAVE ch);
int quantidadeDeElementos(HASH *h, TCHAVE ch);
Pont retornaEnderecoDoRegistro(HASH *h, TCHAVE ch);
