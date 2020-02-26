#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int chave;
	int valor;
}ITEM;

typedef struct NO {
	ITEM item;
	struct NO *prox;
}tNO;

typedef struct {
	tNO *inicio;
	tNO *fim;
}fila;

int main() {
	
	return 0;
}

int vazia (fila *f) {
	if (f->inicio == NULL) return 1;
	else return 0;
}

int inserir (fila *f, ITEM *it) {
	tNO *novo = (tNO*)malloc(sizeof(tNO));
	if (novo != NULL) {
		novo->item = *it;
		novo->prox = NULL;
		if(f->fim != NULL) {
			f->fim->prox = novo;
		}
		else {
			f->inicio = novo;
		}
		f->fim = novo;
		return 1;
	}
	return 0;
}

int remover (fila *f) {
	if (!vazia(f)) {
		tNO *aux = f->inicio;
		if (f->fim == f->inicio) {
			f->fim = NULL;
			f->inicio = NULL;
		}
		else {
			f->inicio = f->inicio->prox;
		}
		free(aux);
		return 1;
	}
	return 0;
}


