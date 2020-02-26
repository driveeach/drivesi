#include <stdio.h>
#include <malloc.h>

/* Este programa contem toda a logica usado nos programas Swap1, Swap2 e Swap3.
  A funcao troca FUNCIONA, enquanto que a funcao trocaNaoFunciona NAO FUNCIONA
  pois a troca ocorre apenas nos atributos temporarios criados dentro da funcao.
*/

void troca(int* x, int *y){
     int temp = *x;
     *x = *y;
     *y = temp;
} 

void trocaNaoFunciona(int x, int y){
     int temp = x;
     x = y;
     y = temp;
}

int main(){
    int a = 5;
    int b = 10;
    
    trocaNaoFunciona(a,b);
    printf("(1) A: %i, B: %i\n",a,b);
    
    troca(&a,&b);
    printf("(2) A: %i, B: %i\n",a,b);
    
    int *f = (int*) malloc(sizeof(int));
    *f = 25;
    int *g = (int*) malloc(sizeof(int));
    *g = 33;
    troca(f,g);
    printf("(3) A: %i, B: %i\n",*f,*g);    
    
    system("pause"); 
   
    return 0;
} 

/*  SAÍDA DO PROGRAMA
Press any key to continue . . . 
(1) A: 5, B: 10
(2) A: 10, B: 5
(3) A: 33, B: 25
*/
