/************************************
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

  Modulo: indheap.c: implementacao de heap indireto

  Ver indheap.h para detalhes da estrutura

*************************************/

#include<math.h>
#include<limits.h>
#include "indheap.h"

// Descomente a linha abaixo se quiser usar a funcao main
// #define _ALONE_


/*
  HeapVazio(key, into, outof, szheap): verifica se o heap estah vazio
*/
int HeapVazio(tpeso *key, int *into, tvertice *outof, int szheap) {
  return (szheap==0);
}

/*
  PaiHeap(j): calcula o indice no heap correspondente ao pai do noh j
              retorna -1 se o noh for a raiz
*/
int PaiHeap(int j) {
  return((int) floor((j+1.0)/2.0)-1);
}

/*
  FilhoEsqHeap(j): calcula o indice no heap correspondente ao filho esquerdo do noh j
*/
int FilhoEsqHeap(int j) {
  return((j+1)*2 - 1);
}

/*
   FilhoDirHeap(j): calcula o indice no heap correspondente ao filho direito do noh j
*/
int FilhoDirHeap(int j) {
  return((j+1)*2);
}


/*
   MenorElemHeap(key, into, outof, szheap): devolve o indice do menor
     elemento do heap, sem retira-lo
*/
int MenorElemHeap(tpeso *key, int *into, tvertice *outof, int szheap) {
  if(HeapVazio(key, into, outof, szheap))
    return(-1);
  else
    return(outof[0]);
}


/*
   TrocaPosHeap(int i, int j, tpeso *key, int *into, tvertice *outof,
      int szheap): troca os elementos das posicoes i e j no heap,
      atualizando os vetores into e outof
*/
void TrocaPosHeap(int id1, int id2, tpeso *key, int *into, tvertice *outof, int szheap) {
  tvertice outaux;
  int intoaux;

  intoaux = into[outof[id1]];
  into[outof[id1]] = into[outof[id2]];
  into[outof[id2]] = intoaux;

  outaux = outof[id1];
  outof[id1] = outof[id2];
  outof[id2] = outaux;
}


/*
   MenorFilho(i, key, into, outof, szheap): dado o elemento da posicao i do heap,
      retorna a posicao do filho com menor valor
      se o elemento nao tiver filhos, o indice resultante serah um
      valor maior do que szheap
*/
int MenorFilho(int idelem, tpeso *key, int *into, tvertice *outof, int szheap) {
  int filhoesq, filhodir;
  tpeso valoresq, valordir;

  filhoesq = FilhoEsqHeap(idelem);
  filhodir = FilhoDirHeap(idelem);

  // Se tiver filho esquerdo, armazena seu valor; senao, armazena INT_MAX
  valoresq = (filhoesq<szheap? key[outof[filhoesq]]: INT_MAX);

  // Se tiver filho direito, armazena seu valor; senao, armazena INT_MAX
  valordir = (filhodir<szheap? key[outof[filhodir]]: INT_MAX);

  if (valordir<valoresq)
    return(filhodir);
  else
    return(filhoesq);
}


/*
  DesceHeap(idelem, key, into, outof, szheap): faz o elemento da posicao idelem
      descer no heap (trocando de lugar com seus descendentes) ateh a
      propriedade de heap ser satisfeita
*/
void DesceHeap(int idelem, tpeso *key, int *into, tvertice *outof, int szheap) {
  int i, idmenorf;
  int condicaoheap = 0;  // flag indicando se a condicado de heap foi satisfeita

  i=idelem;
  while(i<szheap && !condicaoheap) {
    idmenorf = MenorFilho(i, key, into, outof, szheap);

    // se o noh tem filhos e seu valor eh maior do que o menor deles,
    // troca de posicao com com o menor e condinua;
    // senao, a condicao do heap jah estah satisfeita.
    if (idmenorf < szheap && key[outof[i]]>key[outof[idmenorf]]) {
      TrocaPosHeap(i, idmenorf, key, into, outof, szheap);
      i = idmenorf;
    } else
      condicaoheap=1;
  }
}


/*
  SobeHeap(idelem, key, into, outof, szheap): faz o elemento da posicao idelem
      subir no heap (trocando de lugar com seus ascendentes) ateh a
      propriedade de heap ser satisfeita
*/
void SobeHeap(int idelem, tpeso *key, int *into, tvertice *outof, int szheap) {
  int i, idpai;
  int condicaoheap = 0;  // flag indicando se a condicado de heap foi satisfeita

  i=idelem;
  while(i>0 && !condicaoheap) {
    idpai = PaiHeap(i);

    if (idpai > -1 && key[outof[i]]<key[outof[idpai]]) {
      TrocaPosHeap(i, idpai, key, into, outof, szheap);
      i = idpai;
    } else
      condicaoheap=1;
  }
}

/*
  RetiraMenorElemHeap(key, into, outof, szheap): retira o menor elemento do heap e devolve seu indice
*/
int RetiraMenorElemHeap(tpeso *key, int *into, tvertice *outof, int *szheap) {
  int idmenorelem;

  if(HeapVazio(key, into, outof, *szheap))
    return(-1);

  // retira o elemento da raiz do heap
  idmenorelem = outof[0];
  into[outof[0]]=-1;

  // coloca o ultimo elemento do heap na raiz
  (*szheap)--;
  outof[0] = outof[*szheap];
  into[outof[*szheap]]=0;

  // desce o elemento da raiz ateh a propriedade de heap ser satisfeita
  DesceHeap(0, key, into, outof, *szheap);

  return(idmenorelem);
}


/*
   InsertHeap(idkey, key, into, outof, szheap):
     insere o elemento key[idkey] no heap,
     atualizando os vetores into e outof
*/
void InsertHeap(tvertice idkey, tpeso *key, int *into, tvertice *outof, int *szheap) {

  // insere o elemento na ultima posicao do heap
  outof[*szheap] = idkey;
  into[idkey] = *szheap;
  (*szheap)++;

  // faz o elemento inserido subir no heap ateh a propriedade de heap ser satisfeita
  SobeHeap(*szheap-1, key, into, outof, *szheap);
}


/*
   AlteraValorHeap(idkey, novovalor key, into, outof, szheap):
     atualiza o vetor key[idkey]=novovalor, e atualiza a posicao desse elemento para que
     a propriedade de heap seja mantida
*/
void AlteraValorHeap(tvertice idkey, tpeso novovalor, tpeso *key, int *into,
        tvertice *outof, int szheap) {
  tpeso valorant = key[idkey];

  key[idkey] = novovalor;
  // se o novo valor eh menor, faz o elemento subir no heap; caso contrario, faz descer.
  if (novovalor < valorant)
    SobeHeap(into[idkey], key, into, outof, szheap);
  else
    DesceHeap(into[idkey], key, into, outof, szheap);
}


/*
   ImprimeHeap(key, into, outof, szheap):
     imprime os valores dos elementos do heap na forma de um arranjo
*/
void ImprimeHeap(tpeso *key, int *into, tvertice *outof, int szheap) {
  int i;

  for (i=0;i<szheap;i++)
    printf("%lf  ", key[outof[i]]);
  printf("\n\n");
}


/*
   ImprimeRamoHeap(idelem, key, into, outof, szheap):
     imprime a subarvore do heap com raiz em idelem
*/
void ImprimeRamoHeap(int idelem, tpeso *key, int *into, tvertice *outof, int szheap) {
  int i;
  char arestaramo;

  if (idelem<szheap) {
    ImprimeRamoHeap(FilhoDirHeap(idelem), key, into, outof, szheap);
    for (i=0;i<floor(log(idelem+1)/log(2));i++)
      printf("        ");

    // imprime / ou \ dependendo se for um filho direito ou esquerdo
    if (idelem==0)
      arestaramo = ' ';
    else
      arestaramo=(idelem==floor(idelem/2)*2? '/' : '\\');

    printf("%c%7.3lf\n", arestaramo, key[outof[idelem]]);
    ImprimeRamoHeap(FilhoEsqHeap(idelem), key, into, outof, szheap);
  }
}



#ifdef _ALONE_
int main(int argc, char *argv[]) {
  int into[100];
  tvertice outof[100];
  tpeso key[] = {9.5, 2, 13.1, 21.22, 29.12, 2.32, 6.12, 5.22, 22.1, 8.4, 2.4};

  int nvertices, szheap;
  int i, j;
  tpeso menorelem;

  szheap=0;
  nvertices = 11;

  for (i=0; i<nvertices; i++) {
    InsertHeap(i, key, into, outof, &szheap);
    ImprimeRamoHeap(0, key, into, outof, szheap);
    printf("\n\n");
  }

  menorelem = RetiraMenorElemHeap(key, into, outof, &szheap);
  menorelem = RetiraMenorElemHeap(key, into, outof, &szheap);
  menorelem = RetiraMenorElemHeap(key, into, outof, &szheap);

  ImprimeRamoHeap(0, key, into, outof, szheap);
  printf("\n\n");

  AlteraValorHeap(3, 1.5, key, into, outof, szheap);
  ImprimeRamoHeap(0, key, into, outof, szheap);
  printf("\n\n");

  AlteraValorHeap(9, 2.23, key, into, outof, szheap);
  ImprimeRamoHeap(0, key, into, outof, szheap);
  printf("\n\n");

  AlteraValorHeap(9, 17, key, into, outof, szheap);
  ImprimeRamoHeap(0, key, into, outof, szheap);
  printf("\n");

  return(1);
}
#endif
