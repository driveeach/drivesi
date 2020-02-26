#include <stdio.h>
#include <stdlib.h>

#define TAM 500

//Nó da árvore de Huffman
typedef struct NO {
	int simbolo;
	int freq;
	struct NO *fesq;
	struct NO *fdir;
}tNO;

typedef struct {
	tNO *raiz;
	char codigo [TAM][TAM];
} ARVORE;

//Heap mínimo
typedef struct {
	tNO *itens [TAM];
	int fim;
}HEAP;

void inicializarArvore (ARVORE *arv) {
	int i;
	for (i=0; i<TAM; i++) {
		arv->codigo[i][0] = '\0';
	}
	arv->raiz = NULL;
}

void limparArvoreAux (tNO *raiz) {
	if (raiz != NULL) {
		limparArvoreAux (raiz->fesq);
		limparArvoreAux (raiz->fdir);
		free(raiz);
	}
}

void limparArvore (ARVORE *arv) {
	limparArvoreAux (arv->raiz);
	arv->raiz = NULL;
}

void criarArvore (ARVORE *arv, char *msg) {
	int i;
	int freq[TAM];
	for (i=0; i<TAM; i++) freq[i] = 0;
	for (i=0; msg[i] != '\0'; i++) freq[(int)msg[i]]++;

	HEAP heap;
	criarHeap(&heap);

	for (i=0; i<TAM; i++) {
		if (freq[i] > 0) {
			tNO *no = (tNO*) malloc(sizeof(tNO));
			no->simbolo = i;
			no->freq = freq[i];
			no->fesq = NULL;
			no->fdir = NULL;
			inserirHeap (&heap, no);
		}
	}
	while (tamanhoHeap (&heap) > 1) {
		tNO *pfesq = (tNO*)removerHeap (&heap);
		tNO *pfdir = (tNO*)removerHeap (&heap);
		tNO *novo = (tNO*) malloc(sizeof(tNO));
		novo->simbolo = '#';
		novo->freq = pfesq->freq + pfdir->freq;
		novo->fesq = pfesq;
		novo->fdir = pfdir;
		inserirHeap (&heap, novo);
	}
	arv->raiz = (tNO*)removerHeap(&heap);
}

void criarCodigoAux (ARVORE *arv, tNO *no, char *cod, int fim) {
	
}

void criarCodigo (ARVORE *arv) {
	char codigo [TAM];
	criarCodigoAux (arv, arv->raiz, codigo, -1);
}

int main() {
	
	return 0;
}
