// Soma.c
#include <stdio.h>

/* Este programa chama o metodo soma para somar dois inteiros. O metodo recebe 3
   parametros: os dois inteiros que serão somados e o endereço onde a resposta
   será colocada */

void soma (int a, int b, int *c){
  *c = a + b;
}

int main(){
  int x, y, resultado;
  x = 10;
  y = 13;
  printf("resultado: %d (variavel nao calculada e nao inicializada)\n",resultado);
  printf("endereco : %d (endereco da variavel resultado)\n",&resultado);
  soma(x,y,&resultado); // aqui passamos o endereço do resultado
  printf("resultado: %d\n",resultado);
  return 0;
}
