#include <stdio.h>
#include <stdlib.h>
#define MAX 10

typedef struct {
	int valor;
}ITEM;

typedef struct {
	ITEM item [MAX];
	int inicio;
	int fim;
}deque;

void imprime (deque *d) {
	int aux = d->inicio;
	while ((aux%MAX)!=d->fim) {
		printf("%d\n", d->item[aux%MAX].valor);
		aux++;
	}
}

int main() {
	deque *d = (deque*)malloc(sizeof(deque));
	criar(d);
	ITEM *it = (ITEM*)malloc(sizeof(ITEM));
	it->valor = 2;
	inserirInicio(d,it);
	ITEM *it2 = (ITEM*)malloc(sizeof(ITEM));
	it2->valor = 3;
	inserirInicio(d, it2);
	imprime (d);
	return 0;
}

int cheia (deque *d) {
	if ((d->fim+1)%MAX == d->inicio) return 1;
	else return 0;
}

int vazia (deque *d) {
	if (d->fim == d->inicio == 0) return 1;
	else return 0;
}

int criar (deque *d) {
	d->inicio = 0;
	d->fim = 0;
}

int inserirInicio (deque *d, ITEM *it){
	if (!cheia(d)) {
		d->inicio = d->inicio-1;
		if (d->inicio < 0) d->inicio = MAX-1;
		d->item[d->inicio] = *it;
		return 1;
	}
	return 0;
}

int removerFim (deque *d) {
	if (!vazia(d)) {
		d->fim = d->fim-1;
		if (d->fim < 0) d->fim = MAX-1;
		return 1;
	}
	return 0;
}
