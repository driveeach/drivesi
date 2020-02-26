// Swap2.c
#include <stdio.h>

/* Este programa NAO funciona pois ao chamarmos o metodo swap copiamos os valores de c e d
   e apenas modificamos as copias (variaveis a e b), sem invertermos os valores de c e d */

void swap (int a, int b){
  int temp;
  temp = a;
  a = b;
  b = temp;
  return;
}

int main(){
  int c,d;
  c = 3;
  d = 4;
  swap(c, d);
  printf("c=%d, d=%d", c,d);
  return 0;
}
