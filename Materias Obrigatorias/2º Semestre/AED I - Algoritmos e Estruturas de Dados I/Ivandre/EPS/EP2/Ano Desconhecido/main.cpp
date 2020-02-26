#include "aed1a.h"
#include <cstdlib>
#include <iostream>
#include <conio.h>


void mostraRes(MATRIZ m, int lin, int col)
{
     printf("Pesquisando %d, %d. Valor encontrado: %d.\n", lin, col, valor(m,lin,col));
}

void exibeColSequencia(MATRIZ m, int col)
{
     NO *p = m.C[col];
     printf("Coluna %d: ", col);
     while (p)
     {
           printf("%d,%d/", p->lin, p->chave);
           p = p->proxC;
     }
     printf("/\n");
}
void exibeLinSequencia(MATRIZ m, int lin)
{
     NO *p = m.L[lin];
     printf("Linha %d: ", lin);
     while (p)
     {
           printf("%d,%d/", p->col, p->chave);
           p = p->proxL;
     }
     printf("/\n");
}

void exibeLinZeros(MATRIZ m, int lin, int tam)
{
     NO *p = m.L[lin];
     int k = 1;
     printf("Linha %d: ", lin);
     while (p)
     {
           if (p->col > tam) break;
           for (k; k < p->col; k++) printf("xx-");
           printf("%02d-", p->chave);
           k++;
           p = p->proxL;
     }
     for (k; k < tam; k++) printf("xx-");
     printf(".\n");
}
void exibeColZeros(MATRIZ m, int col, int tam)
{
     NO *p = m.C[col];
     int k = 1;
     printf("Coluna %d: ", col);
     while (p)
     {
           if (p->lin > tam) break;
           for (k; k < p->lin; k++) printf("xx-");
           printf("%02d-", p->chave);
           k++;
           p = p->proxC;
     }
     for (k; k < tam; k++) printf("xx-");
     printf(".\n");
}


//refazer se necessario
void exibirM(MATRIZ m, int tamL, int tamC)
{
     int i;
     int k;
     NO* p;
     
     if (tamL <= 0) tamL = 1;
     if (tamC <= 0) tamC = 1;
     //cabecalho
     printf("Visualizacao: %dx%d\n", tamL, tamC);
     printf(" 00-");
     for (i = 1; i <= tamC; i++) printf("-%02d-", i);
     
     for (i = 1; i <= tamL; i++)
     {
         p = m.L[i];
         k = 1;
         printf("\n %02d:", i);
         
         while (p)
         {
               if (p->col > tamC) break;
               for (k; (k < p->col); k++) printf(" xx ");
               printf(" %02d ", p->chave);
               k = k+1;
               p = p->proxL;
         }
         if (k <= tamC)
         {
            for (k; k <= tamC; k++) printf(" xx "); 
         }
     }
     printf("\n");
}

void exibirCoinc(MATRIZ m, int lin1, int lin2)
{
     if (lin1 == lin2)
     {
        printf("Linhas iguais!\n\n");
     }
     else
     {
         int resp = colunaCoincidente(m, lin1, lin2);
         if (lin1 > 0 && lin2 > 0 && lin1 < MAX && lin2 < MAX)
         {
            exibeLinZeros(m, lin1, 10);
            exibeLinZeros(m, lin2, 10);
         }
         if (resp == 0) printf("Linhas %d e %d nao coincidem.\n\n", lin1, lin2);
         else printf("Linhas %d e %d coincidem na posicao %d.\n\n", lin1, lin2, resp);
     }
}

int main(int argc, char *argv[])
{
    MATRIZ m;
    
    //inicialização
    int i;
    for (i = 1; i <= MAX; i++) m.L[i] = NULL;
    for (i = 1; i <= MAX; i++) m.C[i] = NULL;
    

    bool teste;
    // coloque aqui o codigo que necessitar para testar as funcoes implementadas
    // em trabalho.cpp
    // mas lembre-se de que main() nao faz parte da entrega do trabalho
    // portanto nao inclua aqui nada que seja necessario ao funcionamento das
    // operacoes basicas solicitadas
    
    inserir(&m, 1, 1, 1);
    inserir(&m, 1, 2, 1);
    inserir(&m, 2, 2, 2);
    inserir(&m, 1, 3, 1);
    exibeLinSequencia(m, 1);
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    inserir(&m, 1, 2, 0);
    inserir(&m, 1, 1, 0);
    inserir(&m, 1, 2, 0);
    inserir(&m, 1, 2, 0);
    inserir(&m, 2, 2, 0);
    exibeLinSequencia(m, 1);
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    inserir(&m, 1, 1, 6);
    exibeLinSequencia(m, 1);
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    exibeColSequencia(m, 3);


    //insersoes
    inserir(&m, 1, 1, 10);//
    inserir(&m, 1, 2, 10);
    inserir(&m, 1, 3, 10);
    inserir(&m, 1, 4, 10);
    inserir(&m, 1, 5, 10);
    inserir(&m, 2, 1, 10);
    inserir(&m, 2, 2, 10);
    inserir(&m, 2, 3, 10);
    inserir(&m, 2, 4, 10);
    inserir(&m, 2, 5, 10);
    inserir(&m, 1, 1, 0);//
    inserir(&m, 1, 2, 0);
    inserir(&m, 1, 3, 0);
    inserir(&m, 1, 4, 0);
    inserir(&m, 1, 5, 0);
    inserir(&m, 3, 1, 10);
    inserir(&m, 3, 2, 10);
    inserir(&m, 3, 3, 10);
    inserir(&m, 3, 4, 10);
    inserir(&m, 3, 5, 10);
    inserir(&m, 1, 1, 10); //0k
    inserir(&m, 1, 2, 10);
    inserir(&m, 1, 3, 10); //0k
    inserir(&m, 1, 4, 10); //0k
    inserir(&m, 1, 5, 10); //0k
    inserir(&m, 2, 1, 0); //0k
    inserir(&m, 2, 2, 0); //0k
    inserir(&m, 2, 3, 0); //0k
    inserir(&m, 2, 4, 0); //0k
    inserir(&m, 2, 5, 0);
    inserir(&m, 3, 5, 0);
    inserir(&m, 3, 4, 0);
    inserir(&m, 3, 3, 0);
    inserir(&m, 3, 2, 0);
    inserir(&m, 3, 1, 0);
    inserir(&m, 4, 5, 55);
    inserir(&m, 4, 2, 55);
    inserir(&m, 4, 3, 55);
    inserir(&m, 4, 4, 55); //0k
    inserir(&m, 4, 1, 55); //0k
    inserir(&m, 4, 2, 12); //0k
    inserir(&m, 5, 2, 55); //0k
    inserir(&m, 4, 2, 0); //0k
    inserir(&m, 1, 2, 0); //0k
    inserir(&m, 1, 1, 16); //0k
    inserir(&m, 1, 1, 20); //0k
    inserir(&m, 2, 2, 666); //0k
    inserir(&m, 3, 3, 16); //0k
    inserir(&m, 4, 4, 16); //0k
    inserir(&m, 5, 5, 16); //0k
    inserir(&m, 2, 1, 16);
    
    
    exibeLinSequencia(m, 1);
    exibeLinSequencia(m, 2);
    exibeLinSequencia(m, 3);
    exibeLinSequencia(m, 4);
    exibeLinSequencia(m, 5);
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    exibeColSequencia(m, 3);
    exibeColSequencia(m, 4);
    exibeColSequencia(m, 5);
    
    mostraRes(m,4,5);
    mostraRes(m,1,2);
    mostraRes(m,0,0);
    mostraRes(m,20,20);

    exibirM(m, 5, 5);
    


    inserir(&m, 1, 2, 16);
    inserir(&m, 2, 3, 11);
    inserir(&m, 3, 4, 05);
    inserir(&m, 1, 100, 100);
    exibirM(m, 3, 10);

    mostraRes(m,3,4);
    mostraRes(m,1,2);
    mostraRes(m,-3,2);
    mostraRes(m,3,-4);
    mostraRes(m,0,0);
    mostraRes(m,50,50);
    mostraRes(m,4,4);
    mostraRes(m,2,3);
    mostraRes(m,1,2);
    mostraRes(m,2,3);
    mostraRes(m,3,4);
    mostraRes(m,1,100);
    mostraRes(m,101,101);
    printf("Teste da resposta:\n");
    inserir(&m, 51, 51, 51);
    mostraRes(m,51,51);
    inserir(&m, 51, 51, 0);
    mostraRes(m,51,51);
    
    //teste de coincidencias
    exibirCoinc(m, 1, 2);
    exibirCoinc(m, 0, 0);
    exibirCoinc(m, -1, -4);
    exibirCoinc(m, 1000, 5);
    exibirCoinc(m, 4, 4);
    teste = inserir(&m, 1, 1, 15);
    teste = inserir(&m, 2, 1, 16);
    teste = inserir(&m, 2, 2, 17);
    teste = inserir(&m, 1, 3, 0);
    teste = inserir(&m, 2, 3, 0);
    exibirCoinc(m, 62, 64); //ambas com zero
    
    teste = inserir(&m, 62, 72, 18);
    teste = inserir(&m, 64, 37, 18);
    exibirCoinc(m, 62, 64); //ambas com elemento bem longe de 1

    
    exibirM(m, 8, 8);
    exibirCoinc(m, 1, 2);
    
    inserir(&m, 1, 1, 1);
    inserir(&m, 1, 2, 2);
    inserir(&m, 1, 3, 3);
    inserir(&m, 1, 4, 4);
    inserir(&m, 1, 5, 5);
    inserir(&m, 1, 6, 6);
    inserir(&m, 1, 7, 7);
    inserir(&m, 1, 8, 8);
    inserir(&m, 2, 1, 9);
    inserir(&m, 4, 2, 10);
    inserir(&m, 2, 3, 11);
    inserir(&m, 4, 4, 12);
    inserir(&m, 4, 5, 13);
    inserir(&m, 4, 6, 12);
    inserir(&m, 4, 7, 15);
    inserir(&m, 4, 8, 16);
    
    exibirM(m, 4, 8);
    exibirCoinc(m, 1, 2);
    exibirCoinc(m, 1, 3);
    exibirCoinc(m, 1, 4);
    exibirCoinc(m, 2, 3);
    exibirCoinc(m, 2, 4);
    exibirCoinc(m, 3, 4);
    exibirCoinc(m, 1, 1);
    exibirCoinc(m, 3, 3);
    exibirCoinc(m, 1, 5);
    
    inserir(&m, 40, 1, 0);
    inserir(&m, 41, 1, 0);
    inserir(&m, 40, 5, 10);
    inserir(&m, 41, 9, 10);
    exibirCoinc(m, 40, 41);
    
    inserir(&m, 1, 2, 0);
    system("pause");
    copiarColuna(&m, 1, 5);
    exibirM(m, 4, 8);
    

    

    exibirM(m, 8, 8);
    inserir(&m, 2, 2, 16);
    inserir(&m, 7, 4, 11);
    inserir(&m, 8, 8, 05);
    inserir(&m, 4, 2, 77);
    exibirCoinc(m, 2, 4);
    exibirM(m, 8, 8);
    copiarColuna(&m, 2, 4);
    copiarColuna(&m, 8, 1);
    exibirM(m, 8, 8);
    inserir(&m, 2, 1, 33);
    inserir(&m, 4, 1, 66);
    inserir(&m, 2, 2, 77);
    exibirCoinc(m, 2, 4);

    exibirM(m, 8, 8);
    inserir(&m, 1, 1, 33);
    exibirM(m, 8, 8);
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    exibeColSequencia(m, 3);
    exibeColSequencia(m, 4);
    exibeColSequencia(m, 5);
    exibeColSequencia(m, 6);
    exibeColSequencia(m, 7);
    exibeColSequencia(m, 8);
    exibeLinSequencia(m, 1);
    exibeLinSequencia(m, 2);
    exibeLinSequencia(m, 3);
    exibeLinSequencia(m, 4);
    exibeLinSequencia(m, 5);
    exibeLinSequencia(m, 6);
    exibeLinSequencia(m, 7);
    exibeLinSequencia(m, 8);
    exibirM(m, 8, 8);
    system("cls");
    
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    copiarColuna(&m, 1, 2);
    exibeColSequencia(m, 2);
    exibeColSequencia(m, 3);
    copiarColuna(&m, 1, 2);
    copiarColuna(&m, 1, 3);
    copiarColuna(&m, 8, 4);
    copiarColuna(&m, 6, 1);
    copiarColuna(&m, 3, 7);
    exibeColSequencia(m, 1);
    exibeColSequencia(m, 2);
    exibeColSequencia(m, 3);
    exibirM(m, 8, 8);
    inserir(&m, 2, 1, 0);
    inserir(&m, 2, 3, 0);
    exibirCoinc(m, 1, 2);
    exibirCoinc(m, 7, 8);
    exibirCoinc(m, 1, 4);

    for (i = 1; i <= MAX; i++) m.L[i] = NULL;
    for (i = 1; i <= MAX; i++) m.C[i] = NULL;
    inserir(&m, 1, 1, 16);
    exibirM(m, 8, 8);
    copiarColuna(&m, 1, 4);
    copiarColuna(&m, 4, 3);
    inserir(&m, 2, 3, 16);
    inserir(&m, 2, 3, 16);
    exibirCoinc(m, 1, 2);
    exibirM(m, 8, 8);

    if (teste) printf("\nSucesso.");
    else printf("\nErro.");
    
    getch();
    
    return EXIT_SUCCESS;
}
