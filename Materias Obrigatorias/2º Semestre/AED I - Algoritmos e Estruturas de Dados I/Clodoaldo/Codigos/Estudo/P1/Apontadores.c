#include <stdio.h>
#include <stdlib.h>

typedef int elem;
typedef struct no {
	elem dado;
	struct no* prox;
}tno;
typedef tno* lista;

int main() {
	lista l;
	criar(&l);
	imprime(&l);
	inserirFim(&l, 2);
	inserirFim(&l, 3);
	inserirMeio(&l, 4, 1);
	remover(&l, 2);
	imprime(&l);
	return 0;
}

int criar(lista *l) {
	l = NULL;
}

/*inserir lista vazia ou no fim*/
int inserirFim (lista *l, elem valor) {
	lista novo = (lista)(malloc(sizeof(lista)));
	if (novo != NULL) {
		novo->dado = valor;
		novo->prox = NULL;
		if (*l == NULL) {		
			*l = novo;
		}
		else {
			lista aux = *l;
			while (aux->prox != NULL) {
				aux = aux->prox;
			}
			aux->prox = novo;
		}
	}
}

int inserirMeio (lista *l, elem valor, int pos) {
	if (*l == NULL) return 0;
	lista novo = (lista)(malloc(sizeof(tno)));
	lista aux = *l;
	lista proximo;
	novo->dado = valor;
	int cont = 0;
	while(cont!=pos-1 && aux != NULL) {
		aux = aux->prox;
		cont++;
	}
	proximo = aux->prox;
	aux->prox = novo;
	novo->prox = proximo;
}

int remover (lista *l, int pos) {
	if (*l == NULL || pos<0) return 0;
	lista aux = *l;
	lista rem;
	int cont = 0;
	while (cont != pos-1 && aux != NULL) {
		aux = aux->prox;
		cont++;
	}
	rem = aux->prox;
	aux->prox = rem->prox;
	free(rem);
}

int imprime (lista *l){
	if (l != NULL){ 
		lista aux = *l;
		while (aux != NULL) {
			printf("%d\n", aux->dado);
			aux = aux -> prox;
		}
	}
}
