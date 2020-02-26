// Swap1.c
#include <stdio.h>
#include <malloc.h>

/* Este programa funciona corretamente pois, ao passarmos os enderecos de c e d
   (ao inves dos valores), permitimos ao metodo swap trocar os valores (o conteudo
   em memoria) das variaveis c e d.   */

void swap (int *a, int *b){
  int temp;
  temp = *a;
  *a = *b;
  *b = temp;
  return;
}
int main(){
  int c,d;
  c = 3;
  d = 4;
  swap(&c, &d);
  printf("c=%d, d=%d", c,d);
  return 0;
}
