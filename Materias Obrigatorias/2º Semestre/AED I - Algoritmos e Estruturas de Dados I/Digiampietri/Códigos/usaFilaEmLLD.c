/******************************************************************************
//     usaFilaEmLLD.c
// Este programa interage com o usuario para o gerenciamento de uma fila.
******************************************************************************/
#include "filaEmLLD.c"
#include <stdio.h>

void inserir(FILA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  REGISTRO reg;
  reg.chave = ch;
  if (inserirElementoFila(reg,l)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void exibirPrimeiroFila(FILA *l){
  TIPOCHAVE ch;
  PONT posicao = retornarPrimeiroFila(l,&ch);
  if (posicao != NULL) printf("Primeiro elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o primeiro elemento (fila vazia).\n");
}


void excluir(FILA *l){
  REGISTRO reg;
  if (excluirElementoFila(l,&reg)) printf("Elemento %d excluido corretamente.\n",reg.chave);
  else printf("Nao foi possivel excluir elemento - fila vazia.\n");
}

void exibir(FILA *l){
  exibirFila(l);
}

void meuLog(FILA *l){
  printf("Numero de elementos na fila: %d.\n",tamanhoFila(l));
  printf("Tamanho da fila (em bytes): %d.\n",tamanhoEmBytesFila(l));
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1 no final da fila\n");
  printf("   e : excluir o 1o elemento da fila\n");
  printf("   p : imprimir fila\n");
  printf("   d : destruir (zerar) fila\n");
  printf("   l : exibir log de utilizacao da fila\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   0 : exibir a chave e o endereco do primeiro elemento\n");
}

void destruir(FILA *l){
     destruirFila(l);
     printf("Fila zerada.\n");
}

int main(){
  FILA fila;
  inicializarFila(&fila);
  
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&fila); break;
      case 'e' : excluir(&fila); break;
      case 'p' : exibir(&fila); break;
      case 'd' : destruir(&fila); break;
      case 'l' : meuLog(&fila); break;
      case 'h' : help(); break;
      case '0' : exibirPrimeiroFila(&fila); break;
      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
