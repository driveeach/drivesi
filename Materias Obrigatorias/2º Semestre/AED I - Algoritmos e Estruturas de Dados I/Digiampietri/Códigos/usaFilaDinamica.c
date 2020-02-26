/******************************************************************************
//     usaFilaDinamica.c
// Este programa interage com o usuario para o gerenciamento de uma fila.
******************************************************************************/
#include "filaDinamica.c"
#include <stdio.h>

void inserir(FILA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  REGISTRO reg;
  reg.chave = ch;
  if (inserirNaFila(reg,l)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void buscar(FILA *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  PONT posicao = buscaSeq(ch,l);
  if (posicao != NULL) printf("Elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}

void exibirPrimeiro(FILA *l){
  TIPOCHAVE ch;
  PONT posicao = retornarPrimeiro(l,&ch);
  if (posicao != NULL) printf("Primeiro elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o primeiro elemento (fila vazia).\n");
}

void exibirUltimo(FILA *l){
  TIPOCHAVE ch;
  PONT posicao = retornarUltimo(l,&ch);
  if (posicao != NULL) printf("Ultimo elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o ultimo elemento (fila vazia).\n");
}

void excluir(FILA *l){
  TIPOCHAVE ch;
  REGISTRO excluido;
  if (excluirDaFila(l,&excluido)) printf("Elemento %d excluido corretamente.\n",excluido.chave);
  else printf("Nao foi possivel excluir elemento %d.\n",ch);
}

void exibir(FILA *l){
  exibirFila(l);
}

void meuLog(FILA *l){
  printf("Numero de elementos na fila: %d.\n",tamanho(l));
  printf("Tamanho da fila (em bytes): %d.\n",tamanhoEmBytes(l));
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1 no final da fila\n");
  printf("   e : exclui primeiro elemento da fila\n");
  printf("   p : imprimir fila\n");
  printf("   d : destruir (zerar) fila\n");
  printf("   l : exibir log de utilizacao da fila\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   b <chave1>: exibir posicao do elemento com chave=chave1\n");
  printf("   0 : exibir a chave e o endereco do primeiro elemento\n");
  printf("   u : exibir a chave e o endereco do ultimo elemento\n");
  printf("   q : sair (quit)\n");
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
      case 'b' : buscar(&fila); break;
      case '0' : exibirPrimeiro(&fila); break;
      case 'u' : exibirUltimo(&fila); break;

      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
