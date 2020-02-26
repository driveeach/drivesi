#include <stdio.h>
#include <stdlib.h>
#define MAX 10
typedef struct {
	int valor;
} ITEM;

typedef struct {
	ITEM itens [MAX];
	int topo;
} PILHA;

int main() {
	PILHA p;
	cria(&p);
	insereTopo(&p, 2);
	insereTopo(&p, 3);
	insereTopo(&p, 4);
	removerTopo(&p);
	imprimir(&p);

	return 0;
}

int cria (PILHA *p) {
	p->topo = -1;
}

int vazia (PILHA *p) {
	if (p->topo == -1) return 1;
	else return 0;
}

int cheia (PILHA *p) {
	if (p->topo == MAX -1) return 1;
	else return 0;
}

ITEM obtemTopo (PILHA *p) {
	return p->itens[p->topo];
}

//push
int insereTopo (PILHA *p, int valor) {
	if (!cheia(p)) {
		(p->topo)++;
		p->itens[p->topo].valor = valor;
		
	}
}
//pop
int removerTopo (PILHA *p) {
	if (!vazia(p)) {
		p->itens[p->topo].valor = 0;
		p->topo--;
	}
}

int imprimir (PILHA *p) {
	int i;
	for (i = 0; i <= p->topo; ++i) {
		printf("%d\n", p->itens[i].valor);
	}
}
