#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int chave;
	char valor;
}INFO;

typedef struct NO {
	INFO info;
	struct NO *fdir;
	struct NO *fesq;
} tNO;

typedef struct {
	tNO *raiz;
} ArvoreBinaria;

void criararv (ArvoreBinaria *arv) {
	arv->raiz = NULL;
}

tNO* criarRaiz (ArvoreBinaria *arv, INFO *info) {
	arv->raiz = (tNO*) malloc(sizeof(tNO));
	if (arv->raiz != NULL) {
		arv->raiz->info = *info;
		arv->raiz->fdir = NULL;
		arv->raiz->fesq = NULL;
	}
	return arv->raiz;
}

tNO* insereDireita (tNO *no, INFO *info) {
	if (no) {
		no->fdir = (tNO*) malloc(sizeof(tNO));
		no->fdir->fdir = NULL;
		no->fdir->fesq = NULL;
		no->fdir->info = *info;
		return no->fdir;
	}
	return NULL;
}

tNO* insereEsquerda (tNO *no, INFO *info) {
	if (no) {
		no->fesq = (tNO*) malloc(sizeof(tNO));
		no->fesq->fdir = NULL;
		no->fesq->fesq = NULL;
		no->fesq->info = *info;
		return no->fesq;
	}
	return NULL;
}

tNO* gerarArvore (tNO *raiz) {
	tNO *aux = (tNO*) malloc(sizeof(tNO));
	INFO *info = (INFO*) malloc(sizeof(INFO));
	aux = raiz;
	info->chave = 2;
	info->valor = 'B';
	aux = (tNO*)insereEsquerda (aux, info);
	info->chave = 3;
	info->valor = 'D';
	aux = (tNO*)insereEsquerda (aux, info);
	info->chave = 4;
	info->valor = 'G';
	aux = (tNO*)insereDireita (aux, info);

	aux = raiz;
	info->chave = 5;
	info->valor = 'C';
	aux = (tNO*)insereDireita (aux, info);
	info->chave = 6;
	info->valor = 'E';
	aux = (tNO*)insereEsquerda (aux, info);
	info->chave = 7;
	info->valor = 'H';
	insereEsquerda (aux, info);
	info->chave = 8;
	info->valor = 'I';
	insereDireita (aux, info);
	aux = raiz->fdir;
	info->chave = 9;
	info->valor = 'F';
	aux = (tNO*)insereDireita (aux, info);
}

void preOrdem (tNO *raiz) {
	if (raiz != NULL) {
		printf("%c ", raiz->info.valor);
		preOrdem(raiz->fesq);
		preOrdem(raiz->fdir);
	}
}

void emOrdem (tNO *raiz) {
	if (raiz != NULL) {
		emOrdem(raiz->fesq);
		printf("%c ", raiz->info.valor);
		emOrdem(raiz->fdir);
	}
}

void posOrdem (tNO *raiz) {
	if (raiz != NULL) {
		posOrdem(raiz->fesq);
		posOrdem(raiz->fdir);
		printf("%c ", raiz->info.valor);
	}
}

int alturaArvore (tNO *raiz, int altura) {
	if(raiz){
		altura++;
		int esq = alturaArvore(raiz->fesq, altura);
		int dir = alturaArvore(raiz->fdir, altura);	
		if (esq >= dir) return esq;
		return dir;
	} else return altura;
}

void limparArvoreAux (tNO *raiz) {
	if (raiz != NULL) {
		limparArvoreAux(raiz->fesq);
		limparArvoreAux(raiz->fdir);
		free(raiz);
	}
}

void limparArvore (ArvoreBinaria *arv) {
	limparArvoreAux(arv->raiz);
	arv->raiz = NULL;
}

int tamanhoArvore (tNO *raiz) {
	if (raiz) {
		int esq = tamanhoArvore(raiz->fesq);
		int dir = tamanhoArvore(raiz->fdir);
		return esq+dir+1;	
	}
	return 0;
}

int maiorElemento (tNO *raiz, int maior) {
	if (raiz == NULL) return maior;
	if (raiz->fesq || raiz->fdir) {
		int dir = maiorElemento (raiz->fdir, maior);
		int esq = maiorElemento (raiz->fesq, maior);
		if (dir > maior) maior = dir;
		if (esq > maior) maior = esq;
		return maior;
	}
	return raiz->info.valor;
}

int menorElemento (tNO *raiz, int menor) {
	if (raiz == NULL) return;
	if (raiz->fesq || raiz->fdir) {
		int dir = menorElemento (raiz->fdir, menor);
		int esq = menorElemento (raiz->fesq, menor);
		if (dir < menor) menor = dir;
		if (esq < menor) menor = esq;
		return menor;
	}
	return raiz->info.valor;
}

int main() {
	ArvoreBinaria *arv = (ArvoreBinaria*) malloc(sizeof(ArvoreBinaria));
	criararv(arv);
	INFO *info = (INFO*) malloc(sizeof(INFO));
	info->chave = 1;
	info->valor = 'A';
	criarRaiz (arv, info);
	gerarArvore (arv->raiz);
	printf("Pré-ordem: ");
	preOrdem(arv->raiz);
	printf("\nEm-ordem: ");
	emOrdem(arv->raiz);
	printf("\nPós-ordem: ");
	posOrdem(arv->raiz);
	printf("\nAltura da Árvore: %d\n", alturaArvore (arv->raiz, -1));
	//limparArvore(arv);
	printf("Número de nós: %d\n", tamanhoArvore(arv->raiz));
	printf("Maior valor: %c\n", maiorElemento(arv->raiz, arv->raiz->info.valor));
	printf("Menor valor: %c\n", menorElemento(arv->raiz, arv->raiz->info.valor));

	return 0;
}
