#include <stdio.h>
#include <stdlib.h>
#define MAX 10

typedef int elem;

typedef struct {
	elem v[MAX];
	int n;
} lista;

int main() {
	lista l;
	cria(&l);
	insere (&l, 0, 2);
	insere(&l, 1, 4);
	insereOrdenado(&l, 1);
	insere(&l, 3, 5);
	remover(&l, 1);
	//limpa(&l);
	printf("%d\n", buscaBinaria(&l, 10));
	imprime(&l);
	return 0;
}

int cria (lista *l) {
	l->n = 0;
}

int vazia (lista *l) {
	if (l->n == 0) return 1;
	else return 0;
}

int cheia (lista *l) {
	if (l->n == MAX - 1) return 1;
	else return 0;
}

int limpa (lista *l) {
	int i;
	for (i=0; i<=l->n; i++) {
		l->v[i] = 0;
		(l->n)--;
	}
}

int insere (lista *l, int pos, elem valor) {
	/*lista cheia ou posição inválida*/
	if ((l->n == MAX -1) || (pos > l->n+1) ) return 0;
	int i;
	for (i = l->n; i >= pos; i--) {
		l->v[i] = l->v[i-1];
	}
	l->v[pos] = valor;
	(l->n)++;
}

int insereOrdenado (lista *l, elem valor) {
	if (!cheia(l)) {
		int pos = l->n;
		while (valor < l->v[pos] && pos>0) {
			l->v[pos] = l->v[pos-1]; 
			pos--;
		}
		l->v[pos] = valor;
		(l->n)++;
	}
}

int imprime (lista *l){
	int i;
	for (i = 0; i < l->n; ++i) {
		printf("%d\n", l->v[i]);
	}
}

int remover (lista *l, int pos) {
	if ((pos >= l->n) || (pos<0)) return 0;
	int i;
	for(i = pos; i<l->n; i++) {
		l->v[i] = l->v[i+1];
	}
	(l->n)--;
	return 1;
}

int buscaSequencial (lista *l, elem valor) {
	int i;
	for (i = 0; i < l->n; i++) {
		if (l->v[i] == valor) return 1;
		else if (l->v[i] > valor) return 0;
	}
	return 0;
}

int buscaBinaria (lista*l, elem valor) {
	int esq, dir, meio;
	esq = 0;
	dir = l->n-1;
	while (esq<=dir) {
		meio = (esq + dir)/2;
		if (l->v[meio] == valor) return 1;
		else if (l->v[meio] > valor) dir = meio-1;
		else esq = meio + 1;
	}
	return 0;
}
