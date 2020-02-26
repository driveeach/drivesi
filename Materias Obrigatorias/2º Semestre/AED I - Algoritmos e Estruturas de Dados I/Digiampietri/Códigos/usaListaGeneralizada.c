/******************************************************************************
       usaPONTGenerlizada.c
  Este programa interage com o usuario para o gerenciamento de uma PONT 
   generalizada.
******************************************************************************/
#include "listaGeneralizada.c"
#include <stdio.h>

void meuLog(PONT lista){
  int chaves, nos;
  chaves = contarChaves(lista);  
  nos = contarNosR(lista);
  printf("A lista contem %d chave(s) e %d no(s).\n",chaves,nos);
  exibir(lista);
  printf("\n");
  return;
}

int main(){
  PONT inicio;
  inicializarLista(&inicio);
  PONT p1 = criarNovoElementoLista(1,'a');
  inserir(NULL,p1,&inicio,true);
  inicio = p1;

  meuLog(inicio);
  
  PONT p2 = criarNovoSubLista(2);
  inserir(NULL,p2,&inicio,true);

  meuLog(inicio);

  PONT p3 = criarNovoSubLista(3);
  inserir(p2,p3,&inicio,false);

  meuLog(inicio);
  
  PONT p4 = criarNovoElementoLista(4,'b');
  inserir(p3,p4,&inicio,false);

  meuLog(inicio);
  
  PONT p5 = criarNovoElementoLista(5,'c');
  inserir(p2,p5,&inicio,false);

  meuLog(inicio);

  char fim;
  printf("Pressione <ENTER> para terminar.\n");
  scanf("%c",&fim);
  return 0;
}
