/******************************************************************************
     usaPilhaInvertidaEmLLD.c
  Este programa interage com o usuario para o gerenciamento de uma 
    pilha invertida (onde o inicio da lista contem o topo da pilha).
******************************************************************************/
#include "pilhaInvertidaEmLLD.c"
#include <stdio.h>

void inserir(PILHA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  REGISTRO reg;
  reg.chave = ch;
  if (inserirElementoPilha(reg,l)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void exibirTopoPilha(PILHA *l){
  TIPOCHAVE ch;
  PONT posicao = retornarTopoPilha(l,&ch);
  if (posicao != NULL) printf("Topo da pilha elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o topo da pilha (pilha vazia).\n");
}


void excluir(PILHA *l){
  REGISTRO reg;
  if (excluirElementoPilha(l,&reg)) printf("Elemento %d excluido corretamente.\n",reg.chave);
  else printf("Nao foi possivel excluir elemento - pilha vazia.\n");
}

void exibir(PILHA *l){
  exibirPilha(l);
}

void meuLog(PILHA *l){
  printf("Numero de elementos na pilha: %d.\n",tamanhoPilha(l));
  printf("Tamanho da pilha (em bytes): %d.\n",tamanhoEmBytesPilha(l));
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1 no topo da pilha\n");
  printf("   e : excluir o ultimo elemento da pilha\n");
  printf("   p : imprimir lista (pilha invertida)\n");
  printf("   d : destruir (zerar) pilha\n");
  printf("   l : exibir log de utilizacao da pilha\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   t : exibir a chave e o endereco do ultimo elemento (topo da pilha)\n");
}

void destruir(PILHA *l){
     destruirPilha(l);
     printf("Pilha zerada.\n");
}

int main(){
  PILHA pilha;
  inicializarPilha(&pilha);
  help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&pilha); break;
      case 'e' : excluir(&pilha); break;
      case 'p' : exibir(&pilha); break;
      case 'd' : destruir(&pilha); break;
      case 'l' : meuLog(&pilha); break;
      case 'h' : help(); break;
      case 't' : exibirTopoPilha(&pilha); break;
      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
