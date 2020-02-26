#include <stdio.h>
#include <stdlib.h>
#define TAM 100

typedef struct {
		int chave;
		int valor;
} ITEM;

typedef struct {
		ITEM itens [TAM];
		int fim;
} tlista;

void criar (tlista *lista) {
	lista -> fim = 0;
}

void imprimir (tlista *lista){
	int i;
	for (i = 0; i < lista -> fim; ++i) {
		printf("Chave: %d\t Valor: %d\n", lista -> itens[i].chave, lista -> itens[i].valor);
	}
}

int vazia (tlista *lista){
	if (lista -> fim == 0) return 1;
	else return 0; 
}

int cheia (tlista *lista) {
	if (lista -> fim == TAM) return 1;
	else return 0;
}

int inserir_fim (tlista *lista, ITEM *item) {
	if (lista -> fim < TAM && lista -> fim >= 0) {
		lista -> itens[lista -> fim].chave = item -> chave;
		lista -> itens[lista -> fim].valor = item -> valor;
		lista -> fim ++;
		return 1;
	}
	return 0;
}

int insere_posicao (tlista *lista, int pos, ITEM *item) {
	int i;
	if (lista -> fim >= 0 && lista -> fim < TAM && pos <= lista -> fim && pos >= 0) {
		for (i = lista -> fim; i > pos; i--) {
			lista -> itens[i] = lista -> itens[i-1];
		}
		lista -> itens[pos].chave = item -> chave;
		lista -> itens[pos].valor = item -> valor;
		lista -> fim ++;
		return 1;		
	}
	else return 0;
}

int remover (tlista *lista, int pos) {
	int i;
	if (pos >= 0 && pos < lista -> fim) {
		for (i = pos; i < lista -> fim - 1; i++) {
			lista -> itens[i] = lista -> itens[i+1];
		}
		lista -> fim --;
		return 1;
	}
	return 0;
}

int tamanho (tlista *lista) {
	return lista -> fim;
}

void limpa (tlista *lista){
	lista -> fim = 0;
}

int verifica_ordem (tlista *lista){ //0 - não ordenada; 1 - crescente; 2 - decrescente
	int aux = -1;
	int contcres = 0;
	int contdecres = 0;
	int i;
	for (i = 0; i < lista -> fim; i++){
		if (lista -> itens[i].valor <= lista -> itens[i+1].valor) {
			aux = 1;
			contcres++;
		}
		else if (lista -> itens[i].valor >= lista -> itens[i+1].valor){
			aux = 2;
			contdecres++;
		}
	}
	if (!(contcres == lista -> fim || contdecres == lista -> fim)) {
		aux = 0;
	} 
		return aux;
}

void concatena (tlista *lista1, tlista *lista2){
	int i;
	for (i = 0; i < lista2 -> fim; i++) {
		inserir_fim(lista1, &lista2 -> itens[i]);
	}
}

void concatena_sem_rep (tlista *lista1, tlista *lista2) {
	int i;
	for (i = 0; i < lista2 -> fim; i++) {
		if (tem_na_lista(lista1, &lista2 -> itens[i]) == 0){
			inserir_fim(lista1, &lista2 -> itens[i]);
		}
	}
}

int tem_na_lista (tlista *lista, ITEM *item) {
	int i;
	for (i = 0; i < lista -> fim; i++) {
		if (lista -> itens[i].valor == item -> valor) {
			return 1;
		}
	}
	return 0;
}

void inverte (tlista *lista) {
	int i;
	int final = lista -> fim - 1;
	ITEM aux;
	for (i = 0; i < lista -> fim / 2; i++)	{
		aux = lista -> itens[i];
		lista -> itens[i] = lista -> itens[final];
		lista -> itens[final] = aux;
		final--;
	}
}

tlista* intercalar (tlista *lista1, tlista *lista2) {
	tlista *aux = malloc (sizeof(tlista));
	criar(aux);

	int i;
	int cont1 = 0;
	int cont2 = 0; 
	for (i = 0; i < lista1 -> fim + lista2 -> fim - 1; i++) {
		if (cont1 < lista1 -> fim && cont2 < lista2 -> fim) {
			if (lista1 -> itens [cont1].valor <= lista2 -> itens[cont2].valor) {
				inserir_fim(aux, &lista1 -> itens[cont1]);
				cont1++;
			}
			else {
				inserir_fim(aux, &lista2 -> itens[cont2]);
				cont2++;
			}
		}
		else if (cont1 < lista1 -> fim) {
			inserir_fim(aux, &lista1 -> itens[cont1]);
			cont1++;
		}
		else if (cont2 < lista2 -> fim) {
			inserir_fim(aux, &lista2 -> itens[cont2]);
			cont2++;
		}
	}
	return aux;
}

void eliminar_ocorencia (tlista *lista1, ITEM *dado){
	int i;
	for (i = 0; i < lista1 -> fim; i++) {
		if (lista1 -> itens[i].valor == dado -> valor) {
			remover(lista1, i);
		}
	}
}
