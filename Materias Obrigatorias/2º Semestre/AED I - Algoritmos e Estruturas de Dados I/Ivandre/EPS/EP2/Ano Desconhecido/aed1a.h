# include <iostream>
# include <stdio.h>
# include <stdlib.h>
# include <malloc.h>
# include <time.h>

// atencao: nao edite este modulo pois ele sera substituido pelo que testa o seu ep.
// portanto todas as alteracoes feitas aqui serao perdidas

# define MAX 100

typedef enum {linhas, colunas} direcao;

using namespace std;

typedef struct estrutura {
        int lin;
        int col;
     	int chave;
     	estrutura *proxL;
        estrutura *proxC;
} NO;

typedef struct {
          NO* L[MAX+1];
          NO* C[MAX+1];
} MATRIZ;

bool inserir(MATRIZ *m, int lin, int col, int  ch);
int valor(MATRIZ m, int lin, int col);
int colunaCoincidente(MATRIZ m, int lin1, int lin2);
void copiarColuna(MATRIZ *m, int colOrigem, int colDestino);
char *turma();
char *aluno();
char *nrousp();
