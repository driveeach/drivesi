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
}fila;

int main() {
	
	return 0;
}

int criar (fila *f) {
	f->inicio = 0;
	f->fim = 0;
}

int vazia (fila *f) {
	if (f->inicio == f->fim) return 1;
	else return 0;
}

int cheia (fila *f) {
	if ((f->fim+1)%MAX == f->inicio) return 1;
	else return 0;
}

int inserir (fila *f, ITEM *it) {
	if(!cheia(f)) {
		f->item[f->fim] = *it;
		f->fim = (f->fim+1)%MAX;
		return 1;
	}
	return 0;
}

int remover (fila *f, ITEM *it) {
	if (!vazia(f)) {
		*it = f->item[f->inicio];
		f->inicio = (f->inicio+1)%MAX;
		return 1;
	}
	return 0;
}

int contar (fila *f) {
	if (f->fim >= f->inicio) return (f->fim - f->inicio);
	else return (MAX - (f->inicio - f->fim));
}
