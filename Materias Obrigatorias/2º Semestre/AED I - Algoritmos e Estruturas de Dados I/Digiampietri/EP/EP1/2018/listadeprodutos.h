#include <stdlib.h>
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;

typedef struct aux {
  int id;
  int tipo;
  int quantidade;
  int valorUnitario;
  struct aux* proxProd;
} REGISTRO, * PONT;

typedef struct {
  PONT cabeca;
} LISTADEPRODUTOS, * PLISTA;

PLISTA criarLista();

int tamanho(PLISTA l);

int consultarValorUnitario(PLISTA l, int id);

PONT buscarID(PLISTA l, int id);


bool inserirNovoProduto(PLISTA l, int id, int tipo, int quantidade, int valor);

bool atualizarValorDoProduto(PLISTA l, int id, int valor);

bool removerItensDeUmProduto(PLISTA l, int id, int quantidade);
