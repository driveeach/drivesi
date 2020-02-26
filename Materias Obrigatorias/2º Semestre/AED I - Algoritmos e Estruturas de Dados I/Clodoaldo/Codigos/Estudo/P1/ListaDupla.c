#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int chave;
	int valor;
} ITEM;

typedef struct NO {
	ITEM item;
	struct NO* prox;
	struct NO* ant;
} tNO;

typedef struct {
	tNO *inicio;
	tNO *fim;
} lista;

int main() {
	
	return 0;
}

void criar (lista *l) {
	l->inicio = NULL;
	l->fim = NULL;
}

void apagarLista (lista *l) {
	if (!vazia(l)) {
		tNO *aux = l->inicio;
		while (aux != NULL) {
			tNO *apaga = aux;
			aux = aux->prox;
			free(apaga);
		}
	}
	l->inicio = NULL;
	l->fim = NULL;
}

int vazia (lista *l) {
	if (l->inicio == NULL) return 1;
	else return 0;
}

int inserirInicio (lista *l, ITEM *it) {
	tNO *aux = (tNO*)malloc(sizeof(tNO));
	if (aux != NULL) {
		aux->item = *it;
		aux->prox = l->inicio;
		aux->ant = NULL;
		if (l->inicio != NULL) {
			l->inicio->ant = aux;
		}
		else {
			l->fim = aux;
		}
		l->inicio = aux;
		return 1;
	}
	return 0;
}

int inserirFim (lista *l, ITEM *it) {
	tNO *aux = (tNO*)malloc(sizeof(tNO));
	if (aux != NULL) {
		aux->item = *it;
		aux->prox = NULL;
		aux->ant = l->fim;
		if (l->fim != NULL) {
			l->fim->prox = aux;
		}
		else{
			l->inicio = aux;
		}
		l->fim = aux;
		return 1;
	}
	return 0;
}

int removerInicio (lista *l) {
	if (!vazia(l)) {
		tNO *aux = l->inicio;
		if (l->inicio == l->fim) {
			l->inicio = NULL;
			l->fim = NULL;
		}
		else {
			l->inicio->prox->ant = NULL;
			l->inicio = l->inicio->prox;
		}
		free(aux);
		return 1;
	}
	return 0;
}

int removerFim (lista *l) {
	if (!vazia(l)) {
		tNO * aux = l->fim;
		if (l->inicio == l->fim) {
			l->inicio = NULL;
			l->fim = NULL;
		}
		else {
			l->fim->ant->prox = NULL;
			l->fim = l->fim->prox;
		}
		free(aux);
		return 1;
	}
	return 0;
}

int inserirPosicao (lista *l, ITEM *it, int pos) {
	tNO *aux = (tNO*)malloc(sizeof(tNO));
	if (aux != NULL) {
		aux->item = *it;
		if (pos == 0) {
			aux->prox = l->inicio;
			aux->ant = NULL;
			l->inicio->ant = aux;
			l->inicio = aux;
		}
		else {
			tNO *percorre = l->inicio;
			int i;
			for(i=0; i<pos; i++) {
				if (percorre != l->fim) {
					percorre = percorre->prox;
				}
				else return 0;
			}
			aux->prox = percorre;
			aux->ant = percorre->ant;
			percorre->ant->prox = aux;
			percorre->ant = aux;
		}
		return 1;
	}
	return 0;
}

int removerPosicao (lista *l, int pos) {
	if (!vazia(l)) {
		tNO *aux = l->inicio;
		int i;
		for (i=0; i<pos;i++) {
			if (aux != l->fim) {
				aux = aux->prox;
			}
			else return 0;
		}
		if (aux == l->inicio) {
			aux->prox->ant = NULL;
			l->inicio = aux->prox;
		}
		else if (aux == l->fim) {
			aux->ant->prox = NULL;
			l->fim = aux->ant;
		}
		else {
			aux->ant->prox = aux->prox;
			aux->prox->ant = aux->ant;
		}
		free(aux);
		return 1;
	}
	return 0;
}
