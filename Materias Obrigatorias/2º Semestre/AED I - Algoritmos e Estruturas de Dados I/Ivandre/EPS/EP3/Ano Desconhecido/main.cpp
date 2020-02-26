#include "aed1b.h"
#include <cstdlib>
#include <iostream>

using namespace std;

void imprimir(NO* p)
{
     if (p)
     {
        imprimir(p->esq);
        printf("-%d", p->chave);
        imprimir(p->dir);
     }
}


int main()
{
    int valor;
    NO *raiz = NULL; //incializacao
    printf("Insira os valores...\n");
    
    while (valor != 0)
    {
          fflush(stdin);
          scanf("%d", &valor);
          if (valor > MAX) break;
          manutencao(&raiz, valor);
    }
    
    if (valor == 300)
    {
       fflush(stdin);
       printf("Sucessor: insira valor de 'n'\n");
       scanf("%d", &valor);
       NO * s = NULL;
       s = sucessor(raiz, valor);
       if (s) printf("Resposta: sucessor de %d eh %d!\n", valor, s->chave);
       else printf("Sucessor nao encontrado.\n");
    }
    
    if (valor == 666)
    {
       valor = folhaMaisProxima(raiz);
       printf("Resposta: %d\n", valor);
    }
    
    if (valor == 777)
    {
       printf("Impressao em-ordem:\n");
       imprimir(raiz);
    }

    printf("Saiu...\n");


    //codigo do professor
    system("PAUSE");
    return EXIT_SUCCESS;
}

