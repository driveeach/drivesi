#ifndef DIC_H
#define DIC_H

#include<stdlib.h>
#include<stdio.h>
#include<limits.h>
#include<string.h>

typedef int tvalor;
typedef char* tchave;

#define VALORNULO INT_MIN

typedef struct tnodic{
  tchave chave;
  tvalor valor;
  struct tnodic *prox;
}tnodic;

typedef tnodic* tdic;

/* Inicializa o dicionario */
void InicializaDic(tdic *D);

int comparachave(const tchave k1, const tchave k2);


/* Busca a chave k no dicionario D; se encontrar, retorna o valor correspondente,
   senao retorna a constante VALORNULO */
tvalor BuscaDic(tdic *D, const tchave k);

/* Insere uma nova chave k com valor v no dicionario D. Se a chave jah estava
   no dicionario, simplesmente atualiza o valor */
void InsereDic(tdic *D, const tchave k, tvalor x);

/* Imprime todas as chaves e seus respectivos valores do dicionario D */
void ImprimeDic(tdic *D);

/* Libera o espaco alocado pelo dicionario */
void LiberaDic(tdic *D);


#endif
