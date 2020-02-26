/******************************************************************************
     usaDuasPilhasEstaticas.c
  Este programa interage com o usuario para o gerenciamento de duas pilhas
  alocadas num mesmo arranjo.
******************************************************************************/
#include "multiplasPilhasEstaticas.c"
#include <stdio.h>

void inserir(PILHAMULTIPLA *l){
  int pilha;
  scanf("%d",&pilha);
  TIPOCHAVE ch;
  scanf("%d",&ch);
  if (pushK(ch,l,pilha)) printf("Elemento %d inserido corretamente na pilha %d.\n",ch,pilha);
  else printf("Nao foi possivel inserir elemento %d na pilha %d.\n",ch,pilha);
}

void exibirPrimeiro(PILHAMULTIPLA *l){
  int pilha;
  scanf("%d",&pilha);
  TIPOCHAVE ch;
  int posicao = retornarElemento(l,pilha,&ch);
  if (posicao != -1) printf("Topo da pilha %d: %d encontrado no endereco %d.\n",pilha,ch,posicao);
  else printf("Nao foi possivel encontrar o topo da pilha %d (pilha vazia).\n", pilha);
}

void excluir(PILHAMULTIPLA *l){
  int pilha;
  scanf("%d",&pilha);
  TIPOCHAVE reg;
  if (popK(l,&reg,pilha)) printf("Elemento %d excluido corretamente da pilha %d.\n",reg,pilha);
  else printf("Nao foi possivel excluir elemento pilha %d - pilha vazia.\n", pilha);
}

void meuLog(PILHAMULTIPLA *l){
  printf("Numero de elementos nas pilhas: %d.\n",tamanhoTotalPilhas(*l));
  printf("Tamanho da pilha (em bytes): %d.\n",tamanhoEmBytesPilhas(*l));
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <pilha_><chave_>: inserir elemento com chave=chave_, na pilha=pilha_\n");
  printf("   e <pilha_>: excluir elemento da pilha=pilha_\n");
  printf("   d : destruir (zerar) pilhas\n");
  printf("   l : exibir log de utilizacao do pilha\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   p <pilha_>: exibir a chave e o endereco do topo, pilha=pilha_\n");
}

void destruir(PILHAMULTIPLA *l){
     destruirPilhaMultipla(l);
     printf("Pilhas zeradas.\n");
}

int main(){
  PILHAMULTIPLA pilha;
  inicializarPilhaMultipla(&pilha);
  help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&pilha); break;
      case 'e' : excluir(&pilha); break;
      case 'd' : destruir(&pilha); break;
      case 'l' : meuLog(&pilha); break;
      case 'h' : help(); break;
      case 'p' : exibirPrimeiro(&pilha); break;

      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
