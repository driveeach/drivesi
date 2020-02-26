// #define _ALONE_

#include<stdlib.h>
#include<stdio.h>
#include<limits.h>
#include<string.h>
#include"dicionario.h"

/* Inicializa o dicionario */
void InicializaDic(tdic *D){
  *D = NULL;
}

int comparachave(const tchave k1, const tchave k2) {
  return(strcmp(k1,k2));
}

/* Busca a chave k no dicionario D; se encontrar, retorna o valor correspondente,
   senao retorna a constante VALORNULO */
tvalor BuscaDic(tdic *D, const tchave k) {
  tnodic *p;

  // varre a lista do dicionario ateh o fim ou ateh encontrar a chave
  for (p=*D; p!=NULL && comparachave(p->chave,k)!=0; p=p->prox);

  if (p!=NULL)
    return(p->valor);
  else
    return(VALORNULO);
}

/* Insere uma nova chave k com valor v no dicionario D. Se a chave jah estava
   no dicionario, simplesmente atualiza o valor */
void InsereDic(tdic *D, const tchave k, tvalor x) {
  tnodic *p;

  // varre o dicionario ateh o fim ou ateh encontrar a chave
  for (p=*D; p!=NULL && comparachave(p->chave,k)!=0; p=p->prox);

  if (p!=NULL)
    p->valor = x;
  else {
    p = (tnodic*) calloc(1,sizeof(tnodic));
    p->chave = (tchave) calloc(strlen(k)+1,sizeof(char));
    strcpy(p->chave, k);
    p->valor = x;
    p->prox = *D;
    *D = p;
  }
}

/* Imprime todas as chaves e seus respectivos valores do dicionario D */
void ImprimeDic(tdic *D) {
  tnodic *p;

  printf("Conteudo Dicionario:\n");
  // varre o dicionario ateh o fim
  for (p=*D; p!=NULL; p=p->prox) {
    printf("(%s %ld)\n", p->chave, p->valor);
  }
}

/* Libera o espaco alocado pelo dicionario */
void LiberaDic(tdic *D) {
  tnodic *p;
  tchave q;

  // varre o dicionario ateh o fim
  for (p=*D; p!=NULL; p=*D) {
    *D = p->prox;
    p->prox = NULL;
    q = (tchave) p->chave;
    free(q);
    printf("liberou chave\n");
    free(p);
    printf("Liberou no\n");
  }

}


#ifdef _ALONE_
int main(int argc, char *argv[]) {
  tdic D;

  InicializaDic (&D);

  InsereDic(&D, "Butantan", 4);
  InsereDic(&D, "Pinheiros", 10);
  InsereDic(&D, "Perdizes", 9);

  InsereDic(&D, "Pinheiros", 20);

  printf("Valor Pinheiros: %ld \n", BuscaDic(&D, "Pinheiros"));

  ImprimeDic(&D);

  LiberaDic(&D);

  ImprimeDic(&D);

  return(1);
}
#endif
