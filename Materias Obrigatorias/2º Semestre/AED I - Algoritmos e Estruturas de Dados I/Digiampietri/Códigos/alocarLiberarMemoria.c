#include <stdio.h>
#include <malloc.h>
#define MAX 9999999
/* No teste o uso de memoria iniciou com 212KB (simplesmente a execucao do programa)
   e apos a alocacao de memoria foi para 220KB. Apos o preenchimento da memoria
   o uso de memoria subiu para 78.496KB (para o sistema operacional ha diferenca
   entre alocar a memoria e ela estar efetivamente em uso).
   */

int main(){
    int i;
    printf("A memoria sera alocada.\n");
    system("pause");
    double *x = (double*) malloc(sizeof(double)*MAX);
    printf("Memoria alocada corretamente (\%d bytes)\n", sizeof(double)*MAX);
    system("pause");
    printf("Preenchendo a memoria alocada.\n");

    for (i=0;i<MAX;i++) x[i] = i;
    system("pause");        
    printf("A memoria sera liberada.\n");
    free(x);
    printf("Memoria liberada corretamente.\n");
    system("pause");
}
