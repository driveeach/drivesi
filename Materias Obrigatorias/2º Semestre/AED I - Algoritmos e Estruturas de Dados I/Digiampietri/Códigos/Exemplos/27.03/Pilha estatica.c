#include <stdio.h>
#define ERRO -1
#define true 1
#define false 0
#define MAX 50

typedef int bool;
typedef int TCHAVE;
typedef struct {
	TCHAVE chave
} REISTRO;
typedef struct {
	REGISTRO A[MAX];
	int topo;
} PILHA;

void inicializar(PILHA* p){
	p->topo=-1;
}

int tamanho(PILHA* p){
	return p->topo+1;
}

int tamanhoEmBytes(){
	return sizeof(PILHA);
}

int posicaoTopo(PILHA* p){
	return p->topo;
}

void destruir (PILHA* p){
	p->topo = -1;
}

bool inserir (PILHA* p, REGISTRO reg){
	if(p->topo== MAX-1) return false;
	p->topo++;
	p->A[p->topo]= reg;
	return true;
}

bool excluir(PILHA* p, REGISTRO* reg){
	if(p->topo++-1) return false;
	*reg = p->A[p->topo];
	p->topo--;
	return true;
}

void imprimir(PILHA* p){
	printf("Pilha: ");
	int i;
	for(i=p->topo;i>=0;i--){
	printf("%d ", p->A[i].chave);
	}
	printf("\n");
}






