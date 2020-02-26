#include "listadeprodutos.h"

PLISTA criarLista(){
  PLISTA res = (PLISTA) malloc(sizeof(LISTADEPRODUTOS));
  res->cabeca = (PONT) malloc(sizeof(REGISTRO));
  res->cabeca->id=-1;
  res->cabeca->tipo=-1;
  res->cabeca->quantidade=0;
  res->cabeca->valorUnitario=0;
  res->cabeca->proxProd=NULL;
  return res;
}

int tamanho(PLISTA l){
  PONT atual = l->cabeca->proxProd;
  int tam = 0;
  while (atual) {
    atual = atual->proxProd;
    tam++;
  }
  return tam;
}

PONT buscarID(PLISTA l, int id){
  PONT atual = l->cabeca->proxProd;
   while (atual) {
    if (atual->id == id) return atual;
    atual = atual->proxProd;
  }
  return NULL;
}

void exibirLog(PLISTA f){
  int numElementos = tamanho(f);
  printf("Log lista [elementos: %i]\n    ", numElementos);
  PONT atual = f->cabeca->proxProd;
  while (atual){
    printf(" [%i;%i;%i;%i;$%i]", atual->id, atual->tipo, atual->quantidade, atual->valorUnitario, atual->quantidade*atual->valorUnitario);
    atual = atual->proxProd;
  }
  printf("\n\n");
}

int consultarValorUnitario(PLISTA l, int id){
  PONT atual = l->cabeca->proxProd;
   while (atual) {
    if (atual->id == id) return atual->valorUnitario;
    atual = atual->proxProd;
  }
  return 0;
}


int precoTotal( PONT produto )
{
  return produto->quantidade * produto->valorUnitario;
}

bool inserirNovoProduto(PLISTA l, int id, int tipo, int quantidade, int valor)
{
  if ( id <= 0 || tipo <= 0 || quantidade <= 0 || valor <= 0 ) return false;

  PONT ant, atual;
  ant = l->cabeca;
  atual = l->cabeca->proxProd;

  //TODO: Dá para otimizar essa função

  PONT inserir = (PONT)malloc( sizeof( REGISTRO ) );
  inserir->id = id;
  inserir->quantidade = quantidade;
  inserir->tipo = tipo;
  inserir->valorUnitario = valor;

  while ( atual != NULL && precoTotal( atual ) < precoTotal( inserir ) )
  {
    ant = atual;
    atual = atual->proxProd;
  }
  if ( atual != NULL && atual->id == id ) return false;

  inserir->proxProd = atual;
  ant->proxProd = inserir;

  return true;
}

bool removerItensDeUmProduto(PLISTA l, int id, int quantidade)
{
  if( quantidade <= 0 ) return false;
  PONT ant, atual;
  ant = l->cabeca;
  atual = l->cabeca->proxProd;

  while( atual != NULL )
  {
    if( atual->id == id )
    {
      if( atual->quantidade >= quantidade )
      {
        if ( precoTotal( ant ) > ( atual->quantidade - quantidade ) * atual->valorUnitario )
        {
          ant->proxProd = atual->proxProd;
          int auxiliar = atual->id;
          atual->id = -1;
          inserirNovoProduto( l, auxiliar, atual->tipo, atual->quantidade - quantidade, atual->valorUnitario );
          free( atual );
        }
        else
        {
          atual->quantidade -= quantidade;
        }
        return true;
      }
      return false;
    }
    ant = atual;
    atual = atual->proxProd;
  }
  return false;
}

bool atualizarValorDoProduto(PLISTA l, int id, int valor)
{
  if( valor <= 0 ) return false;
  PONT ant, atual;
  ant = l->cabeca;
  atual = l->cabeca->proxProd;

  while( atual != NULL )
  {
    if( atual->id == id )
    {
      if ( precoTotal( ant ) > atual->quantidade * valor || atual->quantidade * valor > precoTotal( atual->proxProd ) )
      {
        ant->proxProd = atual->proxProd;
        int auxiliar = atual->id;
        atual->id = -1;
        inserirNovoProduto( l, auxiliar, atual->tipo, atual->quantidade, valor );
        free( atual );
      }
      else
      {
        atual->valorUnitario = valor;
      }
      return true;
    }
    ant = atual;
    atual = atual->proxProd;
  }

  return false;
}
