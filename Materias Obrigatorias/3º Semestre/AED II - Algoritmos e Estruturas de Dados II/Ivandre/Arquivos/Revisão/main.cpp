#include <iostream>
#include "revisao.h"
#define MAX 10

using namespace std;

int main()
{
    FILE *arq; //cria um ponteiro para um arquivo. 'arq' aponta para um byte especifico

    arq = fopen("entrada.txt", "r+"); //r, w, a, r+ 'abre arq para leitura/escrita, w+ 'cria arq para leitura/escrita', a+ 'permite ler mas adiciona no fim somente'
    FILE *arq2 = fopen("saida.txt", "w+");

    /*
    //MODO BYTE - pode ser usado para qualquer tipo de arquivo
    char c = 'X';
    putc(c, arq); //cada getc avanca 'arq' em 1 byte
    d = getc(arq);
    putc(d, arq); //cada getc avanca 'arq' em 1 byte

    while(!feof(arq)) //legado. codigo correspondente ao EOF(27) pode estar no meio de arquivo binario
    {
        c = getc(arq);
        printf("%c\n", c);
    }
    putc(c, arq);
    */

    fflush(arq); //deve ser usado quando muda de uma operacao de leitura para escrita e vice-versa
    //fscanf(arq, "%[^/]c%", testo[i]); le ate o delimitador, volta um caractere

    int n;
    char nomes[MAX][10];
    int notas[MAX];
    n = 0;
    int j = 0;
    char nome[10];

    //parte de leitura
    while (!feof(arq))
    {
        fgets(nomes[n], 10, arq);
        notas[n] = atoi(fgets(nome, 10, arq));
        n++;
    }


    //parte de escrita
    while (n < MAX)
    {
        fflush(stdin);
        printf("\nNome: ");
        fgets(nomes[n], 10, stdin); //ler do teclado os 1o.s nove caracteres
        if (strncmp("FIM", nomes[n], 3) == 0) break; //'strcncmp' compara os primeiros n caracteres de duas strings: strncmp("a","b",n)
        fflush(stdin);
        printf("\nNota: ");
        scanf("%d", &notas[n]); //nao esqueca do &
        n++;
    }

    int i;
    for (i = 0; i < n; i++)
    {
        fflush(stdin);
        fprintf(arq2, "%s", nomes[i]);
        fprintf(arq2, "%d\n", notas[i]);
    }

    fclose(arq);
    fclose(arq2);

    //cout << "Hello world!" << endl;
    return 0;
}
