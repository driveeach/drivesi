/******************************************************************************
//     usaDeque.c
// Este programa interage com o usuario para o gerenciamento de um deque.
******************************************************************************/
#include "dequeDinamico.c"
#include <stdio.h>

void inserir1(DEQUE *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  REGISTRO reg;
  reg.chave = ch;
  if (inserirDeque1(reg,l)) printf("Elemento %d inserido corretamente na entrada 1.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void inserir2(DEQUE *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  REGISTRO reg;
  reg.chave = ch;
  if (inserirDeque2(reg,l)) printf("Elemento %d inserido corretamente na entrada 2.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void buscar(DEQUE *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  PONT posicao = buscaSeq(ch,l);
  if (posicao != NULL) printf("Elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}

void exibirPrimeiro1(DEQUE *l){
  TIPOCHAVE ch;
  PONT posicao = retornarPrimeiro1(l,&ch);
  if (posicao != NULL) printf("Primeiro elemento entrada 1: %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o primeiro elemento (lista vazia).\n");
}

void exibirPrimeiro2(DEQUE *l){
  TIPOCHAVE ch;
  PONT posicao = retornarPrimeiro2(l,&ch);
  if (posicao != NULL) printf("Primeiro elemento entrada 2: %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar o primeiro elemento (lista vazia).\n");
}

void excluir1(DEQUE *l){
  TIPOCHAVE ch;
  if (excluirElemDeque1(l,&ch)) printf("Elemento %d excluido corretamente da entrada 1.\n",ch);
  else printf("Nao foi possivel excluir elemento entrada 1 - deque vazio.\n");
}

void excluir2(DEQUE *l){
  TIPOCHAVE ch;
  if (excluirElemDeque2(l, &ch)) printf("Elemento %d excluido corretamente da entrada 2.\n",ch);
  else printf("Nao foi possivel excluir elemento entrada 2 - deque vazio.\n");
}


void exibir1(DEQUE *l){
  exibirDeque1(l);
}

void exibir2(DEQUE *l){
  exibirDeque2(l);
}

void meuLog(DEQUE *l){
  printf("Numero de elementos no deque: %d.\n",tamanho(l));
  printf("Tamanho do deque (em bytes): %d.\n",tamanhoEmBytes(l));
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1, na entrada 1\n");
  printf("   j <chave1>: inserir elemento com chave=chave1, na entrada 2\n");
  printf("   e : excluir elemento com chave=chave1, na entrada 1\n");
  printf("   f : excluir elemento com chave=chave1, na entrada 2\n");
  printf("   o : imprimir deque a partir da entrada 1\n");
  printf("   p : imprimir deque a partir da entrada 2\n");
  printf("   d : destruir (zerar) lista\n");
  printf("   l : exibir log de utilizacao do deque\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   1 : exibir a chave e o endereco do primeiro elemento, entrada 1\n");
  printf("   2 : exibir a chave e o endereco do primeiro elemento, entrada 2\n");
}

void destruir(DEQUE *l){
     destruirDeque(l);
     printf("Deque zerado.\n");
}

int main(){
  DEQUE deque;
  inicializarDeque(&deque);
  help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir1(&deque); break;
      case 'j' : inserir2(&deque); break;
      case 'e' : excluir1(&deque); break;
      case 'f' : excluir2(&deque); break;
      case 'o' : exibir1(&deque); break;
      case 'p' : exibir2(&deque); break;
      case 'd' : destruir(&deque); break;
      case 'l' : meuLog(&deque); break;
      case 'h' : help(); break;
      case '1' : exibirPrimeiro1(&deque); break;
      case '2' : exibirPrimeiro2(&deque); break;

      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
