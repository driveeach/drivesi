#include <stdio.h>
#include <stdlib.h>

#define NLINHAS 5
#define NCOLUNAS 6

/*
MATRIZ ESPARSA
- Problema: matriz com muitos zeros
- Solução: lista cruzada sem nó cabeça
*/

typedef struct {
	int valor;
} DADO;

typedef struct CELULA {
	int lin;
	int col;
	DADO dado;
	struct CELULA *direita;
	struct CELULA *abaixo;
} tCELULA;

typedef struct {
	tCELULA *linha[NLINHAS];
	tCELULA *coluna[NCOLUNAS];	
}MatrizEsparsa;

void criarMatriz (MatrizEsparsa *matriz) {
	int i;
	for (i=0; i<NLINHAS; i++) {
		matriz->linha[i] = NULL;
	}
	for (i=0; i<NCOLUNAS; i++) {
		matriz->coluna[i] = NULL;
	}
}

void limparMatriz (MatrizEsparsa *matriz) {
	int i;
	for (i=0; i<NLINHAS; i++) {
		tCELULA *aux = matriz->linha[i];
		while (aux != NULL) {
			tCELULA *prem = aux;
			aux = aux->direita;
			free(prem);
		}
	}
}

int main() {
	MatrizEsparsa *matriz = (MatrizEsparsa*) malloc(sizeof(MatrizEsparsa));
	criarMatriz(matriz);
	return 0;
}
