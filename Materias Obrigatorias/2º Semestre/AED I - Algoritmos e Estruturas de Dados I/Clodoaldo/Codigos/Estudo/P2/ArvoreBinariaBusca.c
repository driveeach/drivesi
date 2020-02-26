#include <stdio.h> 
#include <stdlib.h>

typedef struct {
	int chave;
	int valor;
} INFO;

typedef struct NO {
	INFO info;
	struct NO *fesq;
	struct NO *fdir;
} tNO;

typedef struct {
	tNO *raiz;
}ARVORE;

void criar (ARVORE *arv) {
	arv->raiz = NULL;
}

int criarRaiz (ARVORE *arv, INFO *info) {
	arv->raiz = (tNO*) malloc(sizeof(tNO));
	if (arv->raiz != NULL){
		arv->raiz->info = *info;
		arv->raiz->fesq = NULL;
		arv->raiz->fdir = NULL;
		return 1;
	}
	return 0;
}

tNO* inserirDireita (tNO *no, INFO *info) {
	if (no != NULL) {
		no->fdir = (tNO*) malloc(sizeof(tNO));
		if (no->fdir != NULL) {
			no->fdir->info = *info;
			no->fdir->fdir = NULL;
			no->fdir->fesq = NULL;
		}
	}
	return no->fdir;
}

tNO* inserirEsquerda (tNO* no, INFO *info) {
	if (no) {
		no->fesq = (tNO*) malloc(sizeof(tNO));
		if (no->fesq) {
			no->fesq->info = *info;
			no->fesq->fesq = NULL;
			no->fesq->fdir = NULL;
		}
	}
	return no->fesq;
}

int inserirAux (tNO *no, INFO *info) {
	if (info->chave < no->info.chave) {
		if (no->fesq == NULL) return (inserirEsquerda (no, info) != NULL);
		else return inserirAux (no->fesq, info);
	}
	else {
		if (no->fdir == NULL) return (inserirDireita(no, info) != NULL);
		else return inserirAux (no->fdir, info);
	}	
}

int inserir (ARVORE *arv, INFO *info) {
	if (arv->raiz == NULL) {
		return criarRaiz (arv, info);
	}
	else {
		return inserirAux (arv->raiz, info);
	}
}

void emOrdem (tNO* raiz) {
	if (raiz != NULL) {
		emOrdem(raiz->fesq);
		printf("%d ", raiz->info.chave);
		emOrdem (raiz->fdir);
	}
}

int buscar (tNO *raiz, INFO *info) {
	if (raiz == NULL) return 0;
	else {
		if (info->chave > raiz->info.chave) return buscar(raiz->fdir, info);
		else if (info->chave < raiz->info.chave) return buscar(raiz->fesq, info);
		else {
			info->valor = raiz->info.valor;
			return 1;
		}
	}
}

void trocarMaxEsq (tNO* remover, tNO* anterior, tNO* no) {
	if (no->fdir != NULL) {
		trocarMaxEsq (remover, no, no->fdir);
	}
	else {
		if (anterior != remover) anterior->fdir = no->fesq;
		else anterior->fesq = no->fesq;

		remover->info = no->info;
		free(no);
	}
}

int removerAux (ARVORE *arv, tNO *anterior, tNO *remover, int chave) {
	if (remover == NULL) return 0; //chave não encontrada
	else if (chave < remover->info.chave) removerAux (arv, remover, remover->fesq, chave);
	else if (chave > remover->info.chave) removerAux (arv, remover, remover->fdir, chave);
	else { //chave encontrada
		if (remover->fdir == NULL || remover->fesq == NULL) { //tem um ou nenhum filho
			tNO *proximo = (tNO*) malloc(sizeof(tNO));
			if (remover->fdir == NULL) proximo = remover->fesq;
			else proximo = remover->fdir;

			if (anterior == NULL) arv->raiz = proximo;
			else if (anterior->fdir == remover) anterior->fdir = proximo;
			else anterior->fesq = proximo;
			free(remover);
		}
		else { //tem os dois filhos
			trocarMaxEsq (remover, remover, remover->fesq);
		}
		return 1;
	}
}

int remover (ARVORE *arv, int chave) {
	if (arv->raiz != NULL) {
		return removerAux (arv, NULL, arv->raiz, chave);
	}
	return 0;
}

void gerarArvore (ARVORE *arv) {
	INFO *info = (INFO*) malloc (sizeof(INFO));
	info->chave = 10;
	info->valor = 10;
	criarRaiz (arv, info);
	info->chave = 5;
	info->valor = 5;
	inserir(arv, info);
	info->chave = 15;
	info->valor = 15;
	inserir(arv, info);
	info->chave = 8;
	info->valor = 8;
	inserir(arv, info);
	info->chave = 3;
	info->valor = 3;
	inserir(arv, info);
	info->chave = 12;
	info->valor = 12;
	inserir(arv, info);
}

int main() {
	ARVORE *arv = (ARVORE*) malloc (sizeof(ARVORE));
	gerarArvore(arv);
	emOrdem (arv->raiz);
	INFO *info = (INFO*) malloc(sizeof(INFO));
	info->chave = 12;
	info->valor = 10;
	printf("\nBusca: %d Valor: %d\n", buscar (arv->raiz, info), info->valor);
	remover(arv, 5);
	emOrdem (arv->raiz);
	return 0;
}
