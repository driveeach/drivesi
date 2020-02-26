/******************************************************************************
//     usaListaLigadaInterativoS.c
// Este programa interage com o usuario para o gerenciamento de uma lista.
******************************************************************************/
#include "listaLigadaSentinela.c"
#include <stdio.h>

void inserir(LISTA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  REGISTRO reg;
  reg.chave = ch;
  if (inserirElemListaOrd(reg,l)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void buscar(LISTA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  PONT posicao = buscaSeq(ch,l);
  if (posicao != NULL) printf("Elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}

void exibirPrimeiro(LISTA *l){
  TIPOCHAVE ch;
  PONT posicao = retornarPrimeiro(l,&ch);
  if (posicao != NULL) printf("Primeiro elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o primeiro elemento (lista vazia).\n");
}

void exibirUltimo(LISTA *l){
  TIPOCHAVE ch;
  PONT posicao = retornarUltimo(l,&ch);
  if (posicao != NULL) printf("Ultimo elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o ultimo elemento (lista vazia).\n");
}

void excluir(LISTA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  if (excluirElemLista(ch,l)) printf("Elemento %d excluido corretamente.\n",ch);
  else printf("Nao foi possivel excluir elemento %d.\n",ch);
}

void exibir(LISTA *l){
  exibirLista(l);
}

void meuLog(LISTA *l){
  printf("Numero de elementos na lista: %d.\n",tamanho(l));
  printf("Tamanho da lista (em bytes): %d.\n",tamanhoEmBytes(l));
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1\n");
  printf("   e <chave1>: excluir elemento com chave=chave1\n");
  printf("   p : imprimir lista\n");
  printf("   d : destruir (zerar) lista\n");
  printf("   l : exibir log de utilizacao da lista\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   b <chave1>: exibir posicao do elemento com chave=chave1\n");
  printf("   0 : exibir a chave e o endereco do primeiro elemento\n");
  printf("   u : exibir a chave e o endereco do ultimo elemento\n");
}

void destruir(LISTA *l){
     destruirLista(l);
     printf("Lista zerada.\n");
}

int main(){
  LISTA lista;
  inicializarLista(&lista);
  help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&lista); break;
      case 'e' : excluir(&lista); break;
      case 'p' : exibir(&lista); break;
      case 'd' : destruir(&lista); break;
      case 'l' : meuLog(&lista); break;
      case 'h' : help(); break;
      case 'b' : buscar(&lista); break;
      case '0' : exibirPrimeiro(&lista); break;
      case 'u' : exibirUltimo(&lista); break;

      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
