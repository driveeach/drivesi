/******************************************************************************
//     usaArvoreAVL.c
// Este programa interage com o usuario para o gerenciamento de arvores AVL.
******************************************************************************/
#include "arvoreAVL.c"
#include <stdio.h>

void verificaEhAVL(PONT l){
     if (ehAVL(l)) printf("Arvore eh AVL.\n");
     else printf("PROBLEMA: a arvore nao eh mais AVL.\n");
}

void inserir(PONT *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  bool alterou;
  if (inserirAVL(l,ch,*l,&alterou)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void buscar(NO *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  PONT posicao = buscaBinaria(ch,l);
  if (posicao != NULL) printf("Elemento %d encontrado no endereco %d.\n",ch,posicao);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}

void usaTravessia(NO *l){
  TIPOCHAVE ch;
  scanf("%d",&ch);
  int niv;
  if (travessia(l, &niv, ch)) printf("Elemento %d encontrado no nivel %d.\n",ch,niv);
  else printf("Nao foi possivel encontrar elemento %d.\n",ch);
}


void exibirEmOrdem(NO *l){
   printf("Em ordem: ");
   exibirArvoreEmOrdem(l); printf("\n");
}

void exibirArvoreX(NO *l){
   printf("Arvore AVL: ");
   exibirArvore2(l,-1); printf("\n");
}

void exibirPreOrdem(NO *l){
   printf("Pre ordem: ");
   exibirArvorePreOrdem(l); printf("\n");
}


void exibirPosOrdem(NO *l){
   printf("Pos ordem: ");
   exibirArvorePosOrdem(l); printf("\n");
}



void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1\n");
  printf("   d : destruir (zerar) NO\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   b <chave1>: exibir endereco do elemento com chave=chave1\n");
  printf("   0 : exibir arvore EM ordem.\n");
  printf("   1 : exibir arvore PRE-ordem.\n");
  printf("   2 : exibir arvore POS-ordem.\n");
  printf("   9: verifica se arvore eh AVL.\n");
}

void destruir(PONT *l){
     destruirArvore(l);
     printf("Arvore zerada (toda memoria liberada).\n");
}

int main(){
  PONT raiz;
  inicializar(&raiz);
  help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&raiz); break;
      case 'd' : destruir(&raiz); break;
      case 'h' : help(); break;
      case 'b' : buscar(raiz); break;
      case '0' : exibirEmOrdem(raiz); break;
      case '1' : exibirPreOrdem(raiz); break;
      case '2' : exibirPosOrdem(raiz); break;
      case '5' : exibirArvoreX(raiz); break;
      case '9' : verificaEhAVL(raiz); break;
      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
