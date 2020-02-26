//Fernanda Moraes Bernardo 7971991

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
	tNO	 *raiz;
} ARVORE;

void criar (ARVORE *arv) {
	arv->raiz = NULL;
}

int criarRaiz (ARVORE *arv, INFO *info) {
	arv->raiz = (tNO*)malloc(sizeof(tNO));
	if (arv->raiz != NULL) {
		arv->raiz->fesq = NULL;
		arv->raiz->fdir = NULL;
		arv->raiz->info = *info;
		return 1;
	} 
	return 0;
}

tNO* inserirDireita (tNO* no, INFO *info) {
	no->fdir = (tNO*) malloc(sizeof(tNO));
	if (no->fdir != NULL) {
		no->fdir->fesq = NULL;
		no->fdir->fdir = NULL;
		no->fdir->info = *info;
	}
	return no->fdir;
}

tNO* inserirEsquerda (tNO* no, INFO *info) {
	no->fesq = (tNO*) malloc(sizeof(tNO));
	if (no->fesq != NULL) {
		no->fesq->fesq = NULL;
		no->fesq->fdir = NULL;
		no->fesq->info = *info;
	}
	return no->fesq;
}

int buscaIterativa (ARVORE *arv, INFO *info) {
	tNO *aux = arv->raiz;
	while (aux != NULL) {
		if (aux->info.chave > info->chave) aux = aux->fesq;
		else if (aux->info.chave < info->chave) aux = aux->fdir;
		else {
			info->valor = aux->info.valor;
			return 1;
		}
	}
	return 0;
}
