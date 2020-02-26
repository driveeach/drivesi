/************************************
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

  Modulo: indheap.h: implementacao de heap indireto

*************************************/

/*
  O Heap indireto eh util quando os elementos do heap precisam ter
  seus valores modificados (como eh o caso no algoritmo de Dijkstra)

  Um heap indireto eh composto por 3 vetores:
   key[1..m]: vetor externo contendo os valores a serem ordenados no heap
      (para o algoritmo de dijskstra, este vetor contem o custo do melhor
      caminho atual da origem ateh cada vetor)
   into[1..n]: indica em qual posicao do heap encontra-se o valor key[i]
   outof[1..n]: indica em qual posicao do vetor key encontra-se o j-esimo
           elemento do heap.

   O vetor key eh estatico, ou seja, uma vez que um elemento entra
   neste vetor, nao eh mais trocado de posicao. Os vetores into e
   outof sao dinamicos, isto eh, quando um elemento em key eh modificado,
   into e outof precisam ser alterados de forma a garantir que o novo
   valor key[i] fique posicionado corretamente no heap de modo a preservar
   a propriedade de heap.


    ex:                 17          indice   0   1   2   3   4   5   6   7
                     /     \          key   40  21  28  23  35  37  17  33
                   23       21        into   5   2   3   1   7   6   0   4
                  /  \     /  \       outof  6   3   1   2   7   0   5   4
                28   33  40    37
               /
             35

*/

#include<math.h>
#include<limits.h>
#include "grafosmapa.h"

/*
  HeapVazio(key, into, outof, szheap): verifica se o heap estah vazio
*/
int HeapVazio(tpeso *key, int *into, tvertice *outof, int szheap);

/*
  PaiHeap(j): calcula o indice no heap correspondente ao pai do noh j
              retorna -1 se o noh for a raiz
*/
int PaiHeap(int j);

/*
  FilhoEsqHeap(j): calcula o indice no heap correspondente ao filho esquerdo do noh j
*/
int FilhoEsqHeap(int j);

/*
   FilhoDirHeap(j): calcula o indice no heap correspondente ao filho direito do noh j
*/
int FilhoDirHeap(int j);

/*
   MenorElemHeap(key, into, outof, szheap): devolve o indice do menor
     elemento do heap, sem retira-lo
*/
int MenorElemHeap(tpeso *key, int *into, tvertice *outof, int szheap);


/*
   TrocaPosHeap(int i, int j, tpeso *key, int *into, tvertice *outof,
      int szheap): troca os elementos das posicoes i e j no heap,
      atualizando os vetores into e outof
*/
void TrocaPosHeap(int id1, int id2, tpeso *key, int *into, tvertice *outof, int szheap);

/*
   MenorFilho(i, key, into, outof, szheap): dado o elemento da posicao i do heap,
      retorna a posicao do filho com menor valor
      se o elemento nao tiver filhos, o indice resultante serah um
      valor maior do que szheap
*/
int MenorFilho(int idelem, tpeso *key, int *into, tvertice *outof, int szheap);


/*
  DesceHeap(idelem, key, into, outof, szheap): faz o elemento da posicao idelem
      descer no heap (trocando de lugar com seus descendentes) ateh a
      propriedade de heap ser satisfeita
*/
void DesceHeap(int idelem, tpeso *key, int *into, tvertice *outof, int szheap);


/*
  SobeHeap(idelem, key, into, outof, szheap): faz o elemento da posicao idelem
      subir no heap (trocando de lugar com seus ascendentes) ateh a
      propriedade de heap ser satisfeita
*/
void SobeHeap(int idelem, tpeso *key, int *into, tvertice *outof, int szheap);


/*
  RetiraMenorElemHeap(key, into, outof, szheap): retira o menor elemento do heap e devolve seu indice
*/
int RetiraMenorElemHeap(tpeso *key, int *into, tvertice *outof, int *szheap);


/*
   InsertHeap(idkey, key, into, outof, szheap):
     insere o elemento key[idkey] no heap,
     atualizando os vetores into e outof
*/
void InsertHeap(tvertice idkey, tpeso *key, int *into, tvertice *outof, int *szheap);


/*
   AlteraValorHeap(idkey, novovalor key, into, outof, szheap):
     atualiza o vetor key[idkey]=novovalor, e atualiza a posicao desse elemento para que
     a propriedade de heap seja mantida
*/
void AlteraValorHeap(tvertice idkey, tpeso novovalor, tpeso *key, int *into,
        tvertice *outof, int szheap);


