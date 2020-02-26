#include <stdlib.h>
#include <stdio.h>
#define NUMTIPOS 10
#define true 1
#define false 0

typedef int bool;

typedef struct aux {
  int id;
  int quantidade;
  int valorUnitario;
  struct aux* proxProd;
} REGISTRO, * PONT;


typedef struct {
  PONT LISTADELISTAS[NUMTIPOS];
} LISTADEPRODUTOS, * PLISTA;



PLISTA criarLista();

int tamanho(PLISTA l);

int consultarValorUnitario(PLISTA l, int id);

PONT buscarID(PLISTA l, int id);

PONT buscarIDTipo(PLISTA l, int id, int tipo);




bool inserirNovoProduto(PLISTA l, int id, int tipo, int quantidade, int valor);

bool atualizarValorDoProduto(PLISTA l, int id, int valor);

bool removerItensDeUmProduto(PLISTA l, int id, int quantidade);
