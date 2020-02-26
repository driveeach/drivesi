/******************************************************************************
//     usaArvoreDeBuscaBinaria2.c
// Este programa interage com o usuario para o gerenciamento de arvore binaria
   de busca.
******************************************************************************/
#include "arvoreDeBuscaBinaria2.c"
#include <stdio.h>

void inserir(PontNo *l){
  TIPOCHAVE2 ch;
  scanf("%d",&ch);
  if (inserirNo(l,ch)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void buscar(NO *l){
  TIPOCHAVE2 ch;
  scanf("%d",&ch);
  PontNo posicao = buscaBinaria(ch,l);
  if (posicao != NULL) printf("Elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}

void usaTravessia(NO *l){
  TIPOCHAVE2 ch;
  scanf("%d",&ch);
  int niv;
  if (travessia(l, &niv, ch)) printf("Elemento %d encontrado no nivel %d.\n",ch,niv);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}


void exibirEmOrdem(NO *l){
   printf("Em ordem: ");
   exibirArvoreEmOrdem(l); printf("\n");
}

void exibirPreOrdem(NO *l){
   printf("Pre ordem: ");
   exibirArvorePreOrdem(l); printf("\n");
}

void exibirEmNivel(NO *l){
   printf("Em nivel: ");
   exibirArvoreEmNivel(l); printf("\n");
}

void exibirPreOrdemNR(NO *l){
   printf("Pre ordem: ");
   preOrdemNaoRecursivo(l);
}


void exibirPosOrdem(NO *l){
   printf("Pos ordem: ");
   exibirArvorePosOrdem(l); printf("\n");
}


void excluir(PontNo *l){
  TIPOCHAVE2 ch;
  scanf("%d",&ch);
  if (excluirChave(l,ch)) printf("Elemento %d excluido corretamente.\n",ch);
  else printf("Nao foi possivel excluir elemento %d.\n",ch);
}


void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1\n");
  printf("   e <chave1>: excluir elemento com chave=chave1\n");
  printf("   d : destruir (zerar) NO\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   b <chave1>: exibir endereco do elemento com chave=chave1\n");
  printf("   t <chave1>: exibir o nivel do elemento com chave=chave1\n");
  printf("   0 : exibir arvore em ordem.\n");
  printf("   1 : exibir arvore em PRE-ordem.\n");
  printf("   2 : exibir arvore em POS-ordem.\n");
  printf("   3 : exibir arvore em PRE-ordem (nao recursivo).\n");
  printf("   9 : exibir arvore em NIVEL.\n");
}

void destruir(PontNo *l){
     destruirArvore(l);
     printf("Arvore zerada (toda memoria liberada).\n");
}

int main(){
  PontNo raiz;
  inicializar(&raiz);
  help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&raiz); break;
      case 'e' : excluir(&raiz); break;
      case 'd' : destruir(&raiz); break;
      case 'h' : help(); break;
      case 'b' : buscar(raiz); break;
      case 't' : usaTravessia(raiz); break;
      case '0' : exibirEmOrdem(raiz); break;
      case '1' : exibirPreOrdem(raiz); break;
      case '2' : exibirPosOrdem(raiz); break;
      case '3' : exibirPreOrdemNR(raiz); break;
      case '9' : exibirEmNivel(raiz); break;

      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
