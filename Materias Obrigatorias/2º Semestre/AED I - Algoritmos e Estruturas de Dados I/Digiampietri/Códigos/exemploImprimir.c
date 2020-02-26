// exemploImprimir.c
#include "exemploImprimir.h"

void imprimirA(int a){
  printf("%d ",a);
  a--;
  if (a>0) imprimirB(a);
}

void imprimirB(int a){
  printf("%d ",a);
  a--;
  if (a>0) imprimirA(a);
}
