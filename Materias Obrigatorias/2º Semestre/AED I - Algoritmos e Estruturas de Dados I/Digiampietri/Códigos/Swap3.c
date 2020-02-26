// Swap3.c
#include <stdio.h>
#include <malloc.h>

/* Este programa funciona corretamente pois, ao passarmos os enderecos apontados 
   pelos ponteiros c e d, permitimos ao metodo swap trocar os valores (o conteudo
   em memoria) apontado pelos ponteiros c e d.  
   Note que o metodo swap utilizado aqui eh exatamente o mesmo do programa Swap1 */

void swap (int *a, int *b){
  int temp;
  temp = *a;
  *a = *b;
  *b = temp;
  return;
}
int main(){
  int *c, *d;
  c = (int*) malloc(sizeof(int));
  d = (int*) malloc(sizeof(int));
  *c = 3;
  *d = 4;
  // O metodo swap recebe os valores de c e d (que sao enderecos)
  swap(c, d);
  printf("c=%d, d=%d", *c, *d);
  return 0;
}
