#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <malloc.h>
#include <string.h>
#define V 20

// elemento da lista de adjacencias e também da lista de respostas da funcao caminho()
typedef struct estr
{
        int v;
        estr *prox;
} NO;

// vertice do grafo
typedef struct 
{
       int flag; // para uso na busca em largura ou profundidade, se necessario
       bool aberto; // vale true se a sala em questao esta aberta
       NO* inicio;
} VERTICE;

NO *caminho(VERTICE *g, int inicio, int fim, int chave);
char *aluno1();
char *aluno2();
char *nrousp1();
char *nrousp2();
char *turma();
