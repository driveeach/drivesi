//Lista Ligada

#include <stdio.h>
#define MAX 50
#define NUL -1
#define ERRO -1
#define true 1
#define false 0

typedef int bool;
typedef int TCHAVE;
typedef struct{
	TCHAVE chave;
	int prox;
} REGISTRO;
typedef struct {
	REGISTRO A[MAX]
	int inicio;
	int dispo;
} LISTA;
void inicializar(LISTA*L){
	l->inicio=NUL;
	l->dispo=0;
	int i;
	for(i=0; i<MAX-1; i++){
		l->A[i].prox=i+1;	
	}
	l->A[MAX-1].prox=NUL;
}
void exibirLISTA(LISTA*l){
	int i=l->inicio;
	while(i!=NUL){
	printf("%d", l->A[i].chave);
	i=l->A[i].prox;	
	}
	printf("\n");
}
int tamanho(LISTA*l){
	int cont=0;
	int i=l->inicio;
	while(i!=NUL){
		cont++;
		i=l->A[i}.prox;
	}
	return cont;
}
int tamanhoEmBytes(){
	return sizeof(LISTA);
}

int buscaSeqExc(LISTA*l, TCHAVE ch, int*ant){
	*ant=NUL;
	int i=l->inicio;
	while((i!=NUL)&&(l->A[i].chave<ch)){
		*ant=i;
		i->l->A[i].prox;
	}
	if(i==NUL) return NUL;
	if(l->A[i].chave==ch) return i;
	return NUL;
}

bool excluir(LISTA*l, TCHAVE ch){
	int i, ant;
	i=buscaSEqExc(l,ch,&ant);
	if(i==NUL) return false;
	if(ant==NUL){
		l->inicio=l->inicio->prox;	
	}
	else{
		ant->prox=l->A[i].prox;
	}
	l->A[i].prox=l->dispo;
	l->dispo=i;
	return true;
}

bool inserirOnd(LISTA*l, REGISTRO reg){
	if(l->dispo==NUL) return false;
	int i, ant;
	i= buscaSeqExc(l,reg.chave,&ant);
	if(i!=NUL) return false;
	l->A[l->dispo]=reg;
	
	i=l->dispo;
	l->dispo=l->A[l->dispo].prox;
	l->A[i]=reg;
	if(ant==NUL){
		l->A[i].prox=l->inicio;
		l->inicio=i;
	}
	else{
		l->A[i].prox=l->A[ant].prox;
		l->A[ant].prox=i;
	}
	return true;
}





























