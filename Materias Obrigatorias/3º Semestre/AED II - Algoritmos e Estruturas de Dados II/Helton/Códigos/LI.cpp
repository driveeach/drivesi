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
        NO* vertice[10];
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
    
    //criar lista de vertices
    LI lista;
    
    
    for (int i = 0; i < nroVertices; i++)
    {
        
        lista.vertice[i] = NULL;
    }
    
    
    NO* p;
    
    printf("passei");
    
    //le as arestas e seus pesos
    char teste = '0';
    while (teste != EOF)
    {
          int i, j, peso;
          //le as arestas
          fscanf(entrada, "%i %i %i", &i, &j, &peso);
          
          //posicionar no fim da lista
          p = lista.vertice[i];
          
          
          while (p->prox)
          {
                p = p->prox;
          }
          //cria o vertice adjacente e liga no fim da lista
          NO* novo = (NO *) malloc(sizeof(NO));
          novo->vertice = j;
          novo->peso = peso;
          novo->prox = NULL;
          p->prox = novo;
          
         
    
    
          teste = getc(entrada); //fim de linha ou de arquivo
    }
    
    
    //grava no arquivo
    for (int i = 0; i < nroVertices; i++)
    {
        NO* p = lista.vertice[i];
        while (p != NULL)
        {
              fputc(p->peso + 48, saida); //+48: conversão para ASCII
              fputc('\t', saida);
              p = p->prox;
              
        }
        fputc('\n', saida); //pula proxima linha
    }
    
    getch();
    
    fclose(entrada);
    fclose(saida);
    
    
    return 1;
}
