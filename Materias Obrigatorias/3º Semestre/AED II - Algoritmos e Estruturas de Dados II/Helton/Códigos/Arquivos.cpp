# include <malloc.h>
# include <stdio.h>
# include <conio.h>
# include <stdlib.h>
# include <string.h>

# define infinito 1000

typedef struct estrutura
{
        int vertice;
        int peso;
        estrutura* prox;
} NO;

typedef struct
{
        NO inicio;
} LI;

int main()
{
    FILE *entrada;
    FILE *saida;
    entrada = fopen("grafo.txt", "r");
    saida = fopen("matriz.txt", "w");

    //le o numero de vertices
    int nroVertices;
    fscanf(entrada, "%i", &nroVertices);
    //getc(entrada); //pula linha
    printf("Numero de vertices: %d\n", nroVertices);
    
    //le os nomes dos vertices
    for (int i = 0; i < nroVertices; i++)
    {
        char vertice[10];
        fscanf(entrada, "%s", &vertice);
        printf("%s\n", vertice);
    }
    
    
    //cria a matriz de adjacencias
    int matrizAdjacencias[nroVertices][nroVertices];
    
    //preenche a matriz com valor
    for (int i = 0; i < nroVertices; i++)
    {
        for (int j = 0; j < nroVertices; j++)
        {
            matrizAdjacencias[i][j] = '-1';
        }
    }
    
    //le as arestas e seus pesos
    char teste = '0';
    while (teste != EOF)
    {
          int i, j, peso;
          //le as arestas
          fscanf(entrada, "%i %i %i", &i, &j, &peso);
          //atualiza o valor na matriz
          matrizAdjacencias[i][j] = peso;   
          teste = getc(entrada); //fim de linha ou de arquivo
    }
    
    //grava no arquivo
    for (int i = 0; i < nroVertices; i++)
    {
        for (int j = 0; j < nroVertices; j++)
        {
            fputc(matrizAdjacencias[i][j] + 48, saida); //+48: conversão para ASCII
            fputc('\t', saida);
        }
        fputc('\n', saida); //pula proxima linha
    }
    
    getch();
    
    fclose(entrada);
    fclose(saida);
    
    
    return 1;
}
