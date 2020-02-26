#include <stdio.h>
#include <stdlib.h>

#include "ep1.h"

/*Fernanda Moraes Bernardo*/

/*inicializa a lista*/
void inicializaLista(LISTA* l){
	l->fim = NULL;
}

/*Insere um elemento no final da lista*/
NO* insereElem(LISTA* l, char* nome){
	NO* pNovo = (NO*)malloc(sizeof(NO));
	if (pNovo != NULL) {
		/*int cont = 0;
		while (nome[cont] != '\0')	{
			pNovo->nome[cont] = nome[cont];
			cont++;
		}*/
		
		strcpy(pNovo->nome, nome);
		if (l->fim == NULL) {
			l->fim = pNovo;
			l->fim->prox = l->fim;
		}
		else {
			pNovo->prox = l->fim->prox;
			l->fim->prox = pNovo;
			l->fim = pNovo;
		}
		return pNovo;
	}
	return 0;
}

/*Imprime uma lista*/
void imprime(LISTA l){
	NO* aux = l.fim;
	do {
		printf("%s\n", aux->nome);
		aux = l.fim->prox;
	} while (aux != l.fim);
}

/* remove o elemento da lista */
int removeElem(NO* no){
	NO* aux = no;
	if (aux != NULL) {
		while (aux->prox != no) {
			aux = aux->prox;
		}
		NO* apaga = aux->prox;
		aux->prox = apaga->prox;
		free(apaga);
		
		return 1;
	}
	return 0;
}

/*grava uma lista em disco*/
void grava(LISTA* l, FILE* nome){
	/*abre um arquivo de saída para escrita*/
	nome = fopen ("saida.txt", "w");
	if (nome != NULL) {
		/*NO auxiliar começa do inicio da lista*/
		NO* aux = (NO*)malloc(sizeof(NO));
		aux = l->fim->prox;
		do {
			fprintf(nome, "%s\n", aux->nome);	
			aux = aux->prox;
		} while(aux != l->fim->prox);
		fclose(nome);
	}
}

int lerArquivo (LISTA* l, FILE* arquivo) {
	/*abre arquivo de entrada para leitura*/
	if (arquivo != NULL) {
		/*recebe número de pessoas*/
		int pessoas;
		fscanf (arquivo, "%d", &pessoas);
		char *nomeCopiar = (char*)malloc(sizeof(char));

		/*pega todos os nomes do arquivo e insere na lista*/
		int i;
		for (i = 0; i < pessoas; i++) {
			fscanf(arquivo, "%s", nomeCopiar);
			insereElem(l, nomeCopiar);
		}

		/*inicialização da lista com os nomes que foram excluidos na ordem*/
		LISTA* resposta = malloc(sizeof(LISTA));
		inicializaLista(resposta);

		/*numero dos dedos retirado do arquivo*/
		int numero;
		fscanf(arquivo, "%d", &numero);


		NO* procura = malloc(sizeof(NO)); /*NO que deseja excluir*/
		NO* procuraAux = malloc(sizeof(NO)); /*próximo NO do que foi excluido*/
		procura = l->fim->prox; /*começa no início*/
		
		/*para cada pessoa*/
		for (i = 1; i <= pessoas; i++) {
			int j;
			/*para chegar na pessoa a ser excluida*/
			for (j = 0; j < numero; j++) {
				procura = procura->prox;
			}
			insereElem(resposta, procura->nome); /*insere excluido na LISTA resposta*/
			procuraAux = procura->prox; /*guarda o próximo do que será excluido para continnuar a contagem*/
			removeElem(procura);
			procura = procuraAux; /*atualiza o "inicio" da lista*/
			fscanf(arquivo, "%d", &numero);	/*pega o próximo número de dedos*/		
		}

		/*grava a LISTA de excluidos no arquivo*/
		FILE* arquivo;
		grava(resposta, arquivo);
		fclose(arquivo);
		return 1;
	}
	return 0;
}	

int main(int argc, char *argv[]) {
	LISTA* l = malloc(sizeof(LISTA));
	inicializaLista(l);
	FILE *arquivo = fopen(argv[1], "r");
	lerArquivo(l, arquivo);
	return 0;
}
