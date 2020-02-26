#include <stdio.h>
#include <stdlib.h>

typedef struct pessoa Pessoa;

struct pessoa {
	char nome[50];
	int idade;
	Pessoa *proximo;
};

Pessoa* apaga (Pessoa *pInicio, char nomeAux[50]) {
	Pessoa *pAtual, *pAnterior;
	pAtual = pInicio;
	pAnterior = pInicio;
	if (strcmp(pInicio->nome, nomeAux) == 0) {
		pInicio = pAtual->proximo;
		free(pAtual);
		return pInicio;
	}
	pAtual = pInicio->proximo;
	while (strcmp (pAtual->nome, nomeAux) != 0) {
		pAnterior = pAtual;
		pAtual = pAtual->proximo;
	}
	pAnterior->proximo = pAtual->proximo;
	free(pAtual->proximo);
	return pInicio;
}

int main() {
	Pessoa *pAtual, *pInicio = NULL, *pAnterior = NULL;
	char continua;
	do {
		pAtual = malloc(sizeof(Pessoa));
		printf("Digite um nome: ");
		gets(pAtual->nome);
		printf("Digite a idade: ");
		scanf("%d", &pAtual->idade); getchar();
		if (pInicio == NULL) {
			pInicio = pAtual;
		}
		if (pAnterior != NULL) {
			pAnterior->proximo = pAtual;
		}
		pAnterior = pAtual;
		printf("Insere mais (S/N)? ");
		continua = getchar(); getchar();
	} while (toupper(continua) != 'N');
	pAtual = pInicio;
	pAtual = apaga(pInicio, "Fernanda");
	while (pAtual != NULL) {
		if (pAtual->nome == "Fernanda") continue;
		printf("Nome: %s - Idade: %d\n", pAtual->nome, pAtual->idade);
		pAtual = pAtual->proximo;
	}
}
