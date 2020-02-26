# include <iostream>
# include <stdio.h>
# include <stdlib.h>
# include <conio.h>
# include <malloc.h>

# define MAX 100

using namespace std;

typedef struct estrutura {
     	int chave;
     	estrutura *esq;
        estrutura *dir;
} NO;


void manutencao(NO* *raiz, int ch);
NO *sucessor(NO *raiz, int n);
int folhaMaisProxima(NO *raiz);

char *aluno();
char *nrousp();
char *turma();
