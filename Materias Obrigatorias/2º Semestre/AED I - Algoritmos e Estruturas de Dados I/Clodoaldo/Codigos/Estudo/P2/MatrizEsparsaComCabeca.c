#include <stdio.h>
#include <stdlib.h>

#define NLINHAS 5
#define NCOLUNAS 6

/*
MATRIZ ESPARSA
- Problema: matriz com muitos zeros
- Solução: lista cruzada com nó cabeça
- Vantajoso: quando 1/5 dos valores forem NÃO-nulos
*/

/*
LISTA CRUZADA CIRCULAR
- Diferença: 
	typedef struct {
		tCELULA *A;
	}MatrizEsparsa;
- Vantagem: a matriz pode crescer dinamicamente
- Desvantagem: mais complexa de manipular
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
	tCELULA linha[NLINHAS];
	tCELULA coluna[NCOLUNAS];	
}MatrizEsparsa;

void criarMatriz (MatrizEsparsa *matriz) {
	int i;
	for (i=0; i<NLINHAS; i++) {
		matriz->linha[i].direita = NULL;
		matriz->linha[i].abaixo = NULL;
		matriz->linha[i].lin = -1;
		matriz->linha[i].col = -1;
	}
	for (i=0; i<NCOLUNAS; i++) {
		matriz->coluna[i].direita = NULL;
		matriz->coluna[i].abaixo = NULL;
		matriz->coluna[i].lin = -1;
		matriz->coluna[i].col = -1;	
	}
}

void limparMatriz (MatrizEsparsa *matriz) {
	int i;
	for (i=0; i<NLINHAS; i++){
		tCELULA *aux = matriz->linha[i].direita;
		while (aux != NULL) {
			tCELULA *prem = aux;
			aux = aux->direita;
			free(prem);
		}
	}
}

int inserir (MatrizEsparsa *matriz, int lin, int col, DADO *dado){
	if (lin < NLINHAS && col < NCOLUNAS) {
		tCELULA *aux = &matriz->linha[lin];
		while (aux->direita != NULL && aux->direita->col < col) {
			aux = aux->direita;
		}
		if (aux->direita != NULL && aux->direita->col == col) {
			aux->direita->dado = *dado;
		}
		else {
			tCELULA *novo = (tCELULA*) malloc(sizeof(tCELULA));
			novo->lin = lin;
			novo->col = col;
			novo->dado = *dado;
			novo-> direita = aux->direita;
			aux->direita = novo;
			aux = &matriz->coluna[col];
			while (aux->abaixo != NULL && aux->abaixo->lin < lin) {
				aux = aux->abaixo;
			}
			novo->abaixo = aux->abaixo;
			aux->abaixo = novo;
		}
		return 1;
	}
	return 0;
}

int remover (MatrizEsparsa *matriz, int lin, int col) {
	if (lin < NLINHAS && col < NCOLUNAS) {
		tCELULA *aux = &matriz->linha[lin];
		while (aux->direita != NULL) {
			if (aux->direita->col < col) aux = aux->direita; 
			else break;
		}
		if (aux->direita != NULL) { //bloco existe
			if (aux->direita->col == col) { //local correto
				tCELULA *prem = aux->direita;
				aux->direita = aux->direita->direita;

				aux = &matriz->coluna[col];
				while (aux->abaixo != NULL) {
					if (aux->abaixo->lin < lin) aux = aux->abaixo;
					else break;
				}
				aux->abaixo = aux->abaixo->abaixo;
				free(prem);
			}
			else return 0; //local incorreto
		}
		else return 0; //bloco não existe
		return 1;
	}
	return 0;
}

int consultar (MatrizEsparsa *matriz, int lin, int col, DADO *dado) {
	if (lin < NLINHAS && col < NCOLUNAS) {
		tCELULA *aux = matriz->linha[lin].direita;
		while (aux != NULL) {
			if (aux->col == col) {
				*dado = aux->dado;
				return 1;
			}
			aux = aux->direita;
		}
		dado->valor = 0;
		return 1;
	}
	return 0;
}

int main() {
	MatrizEsparsa *matriz = (MatrizEsparsa*) malloc(sizeof(MatrizEsparsa));
	criarMatriz(matriz);
	DADO *dado = (DADO*) malloc(sizeof(DADO));
	dado->valor = 3;
	inserir(matriz, 2, 3, dado);
	DADO *novo = (DADO*) malloc(sizeof(DADO));
	consultar(matriz, 2, 3, novo);
	printf("Linha = 2, Coluna = 3, Valor = %d\n", novo->valor);
	remover(matriz, 2, 3);
	limparMatriz(matriz);
	return 0;
}
