#include "grafos.h"

// exemplo de funcao para criar um grafo vazio (para testes)
// vertices sao numerados de 1 ate n
VERTICE *criaGrafo (int n)
{
        int i;
        VERTICE *resp = (VERTICE*) malloc(sizeof(VERTICE)*(n+1));
        for (i=1; i<=n; i++) {
            resp[i].inicio = NULL;
            resp[i].aberto = true;
        }
        return (resp);
}

bool arestaExiste(VERTICE* g, int a, int b)
{
    NO* p = g[a].inicio;
    while(p)
    {
        if (p->v == b) return (true);
        p = p->prox;
    }
    return (false);
}

void insereAresta(VERTICE* g, int a, int b)
{
    if (arestaExiste(g, a, b)) return;
    NO* novo = (NO*) malloc(sizeof(NO));
    novo->v = b;
    novo->prox = g[a].inicio;
    g[a].inicio = novo;
}

void printG(VERTICE *g, int n)
{
    int i;
    for (i = 1; i <= n; i++)
    {
        NO*p = g[i].inicio;
        printf("[%0.2d.", i);
        printf("%d.", g[i].flag);
        if (g[i].aberto == true) printf("A]->");
        else printf("X]->");
        while(p)
        {
            printf("%d->", p->v);
            p = p->prox;
        }
        printf("NIL\n");
    }
}

void print(NO* p)
{
    printf("|");
    if (p == NULL) printf("NIL");
    while (p)
    {
        printf("->%d", p->v);
        p = p->prox;
    }
    if (p == NULL) printf("|\n");
}

//========================================================================
// use main() para criar as rotinas de teste do seu programa
// mas observe entretanto que todo codigo necessario para executar
// a funcao caminho() deve estar em trabalho.cpp, e nao aqui, pois
// o modulo main() nao faz parte da entrega.
int main()
{

    VERTICE *g = criaGrafo(18);
    NO *p;
    int chave;
    
    /** TESTES 1 **
    insereAresta(g, 1, 2);
    insereAresta(g, 2, 1);
    insereAresta(g, 2, 6);
    insereAresta(g, 6, 2);
    insereAresta(g, 1, 3);
    insereAresta(g, 3, 1);
    insereAresta(g, 4, 5);
    insereAresta(g, 5, 4);
    insereAresta(g, 2, 7);
    insereAresta(g, 7, 2);
    insereAresta(g, 2, 8);
    insereAresta(g, 8, 2);

    g[6].aberto = false;
    g[8].aberto = false;
    g[3].aberto = false;
    chave = 7;
    p = caminho(g, 1, 2, chave);
    print(p);
    p = caminho(g, 2, 1, chave);
    print(p);
    p = caminho(g, 1, 3, chave);
    print(p);
    p = caminho(g, 3, 1, chave);
    print(p);
    p = caminho(g, 4, 1, chave);
    print(p);
    p = caminho(g, 6, 2, chave);
    print(p);
    p = caminho(g, 2, 6, chave);
    print(p);
    p = caminho(g, 6, 3, chave);
    print(p);
    p = caminho(g, 6, 8, chave);
    print(p);
    p = caminho(g, 8, 6, chave);
    print(p);
    p = caminho(g, 4, 5, chave);
    print(p);
    p = caminho(g, 5, 4, chave);
    print(p);
    p = caminho(g, 5, 3, chave);
    print(p);
    p = caminho(g, 3, 7, chave);
    print(p);
    p = caminho(g, 7, 3, chave);
    print(p);
    p = caminho(g, 7, 8, chave);
    print(p);
    p = caminho(g, 8, 7, chave);
    print(p);
    
    **/
    
    /** TESTES EP **/
    insereAresta(g, 1, 2);
    insereAresta(g, 2, 1);
    
    insereAresta(g, 1, 3);
    insereAresta(g, 3, 1);
    
    insereAresta(g, 2, 7);
    insereAresta(g, 7, 2);
    
    insereAresta(g, 3, 7);
    insereAresta(g, 7, 3);

    insereAresta(g, 2, 6);
    insereAresta(g, 6, 2);

    insereAresta(g, 7, 6);
    insereAresta(g, 6, 7);
    
    insereAresta(g, 6, 8);
    insereAresta(g, 8, 6);

    insereAresta(g, 6, 5);
    insereAresta(g, 5, 6);

    insereAresta(g, 8, 5);
    insereAresta(g, 5, 8);

    insereAresta(g, 8, 13);
    insereAresta(g, 13, 8);

    insereAresta(g, 12, 13);
    insereAresta(g, 13, 12);
    
    insereAresta(g, 5, 14);
    insereAresta(g, 14, 5);
    
    insereAresta(g, 14, 17);
    insereAresta(g, 17, 14);
    
    insereAresta(g, 17, 16);
    insereAresta(g, 16, 17);
    
    insereAresta(g, 15, 16);
    insereAresta(g, 16, 15);
    
    insereAresta(g, 11, 14);
    insereAresta(g, 14, 11);

    insereAresta(g, 11, 10);
    insereAresta(g, 10, 11);
    
    insereAresta(g, 11, 9);
    insereAresta(g, 9, 11);

    insereAresta(g, 10, 9);
    insereAresta(g, 9, 10);

    insereAresta(g, 10, 4);
    insereAresta(g, 4, 10);
    
    insereAresta(g, 9, 4);
    insereAresta(g, 4, 9);

    insereAresta(g, 3, 4);
    insereAresta(g, 4, 3);
    
    chave = 5;
    g[1].aberto = false;
    g[13].aberto = false;
    g[16].aberto = false;
    
    //printG(g, 20);
    
    p = caminho(g, 11, 4, chave);
    print(p);
    p = caminho(g, 11, 2, chave);
    print(p);
    p = caminho(g, 11, 1, chave);
    print(p);
    p = caminho(g, 11, 13, chave);
    print(p);
    p = caminho(g, 11, 12, chave);
    print(p);
    p = caminho(g, 11, 17, chave);
    print(p);
    p = caminho(g, 11, 16, chave);
    print(p);
    p = caminho(g, 11, 15, chave);
    print(p);
    p = caminho(g, 15, 16, chave);
    print(p);
    p = caminho(g, 18, 11, chave);
    print(p);
    
    system("pause");
}
