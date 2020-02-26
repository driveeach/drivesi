#include <stdio.h>
#include <stdlib.h>

#define TAM 100

/*
Fila ordenada: inserção (O(n)), remoção (O(1)), próximo (O(1))
Fila não-ordenada: inserção (O(1)), remoção (O(n)), próximo (O(n))

*/

typedef struct {
	int chave;
	int valor;
} ITEM;

typedef struct {
	ITEM itens [TAM];
	int fim;
} HEAPMAXIMO;

void criar (HEAPMAXIMO *heap) {
	heap->fim = -1;
}

int vazia (HEAPMAXIMO *heap) {
	if (heap->fim == -1) return 1;
	else return 0;
}

int cheia (HEAPMAXIMO *heap) {
	if (heap->fim == TAM-1) return 1;
	else return 0;
}

void bubblingUp (HEAPMAXIMO *heap) {
	int indice = heap->fim;
	int pai = (indice-1)/2;
	while (indice>0 && heap->itens[indice].chave > heap->itens[pai].chave) {
		ITEM aux = heap->itens[indice];
		heap->itens[indice] = heap->itens[pai];
		heap->itens[pai] = aux;
		indice = pai;
		pai = (pai-1)/2;
	}
}

int inserirHeap (HEAPMAXIMO *heap, ITEM *item) {
	if (!cheia(heap)) {
		heap->fim++;
		heap->itens[heap->fim] = *item;
		bubblingUp (heap);
		return 1;
	}
	return 0;
}

void bubblingDown (HEAPMAXIMO *heap) {
	int indice = 0;
	while (indice < heap->fim/2) {
		int filhoesq = 2*indice + 1;
		int filhodir = 2*indice + 2;
		int maiorfilho;
		if (filhodir <= heap->fim && heap->itens[filhodir].chave > heap->itens[filhoesq].chave) {
			maiorfilho = filhodir;
		}
		else {
			maiorfilho = filhoesq;
		}
		if (heap->itens[indice].chave >= heap->itens[maiorfilho].chave) break;
		ITEM aux = heap->itens[indice];
		heap->itens[indice] = heap->itens[maiorfilho];
		heap->itens[maiorfilho] = aux;
		indice = maiorfilho;
	}
}

int remover (HEAPMAXIMO *heap, ITEM *item) {
	if (!vazia(heap)) {
		*item = heap->itens[0];
		heap->itens[0] = heap->itens[heap->fim];
		heap->fim--;
		bubblingDown (heap);
		return 1;
	}
	return 0;
}

int main() {
	HEAPMAXIMO *heap = (HEAPMAXIMO*) malloc(sizeof(HEAPMAXIMO));
	criar(heap);
	ITEM *item = (ITEM*) malloc(sizeof(ITEM));
	item->chave = 1;
	item->valor = 1;
	inserirHeap (heap, item);
	item->chave = 2;
	item->valor = 2;
	inserirHeap(heap, item);
	return 0;
}
