#include <stdio.h>
#include <stdlib.h>
#include "modulo.h"


int main () {
	ARVORE *arv = (ARVORE*) malloc(sizeof(ARVORE));
	criar(arv);
	INFO *info = (INFO*) malloc (sizeof(INFO));
	info->chave = 5;
	info->valor = 5;
	criarRaiz (arv, info);
	info->chave = 4;
	info->valor = 4;
	inserirEsquerda (arv->raiz, info);
	info->chave = 3;
	info->valor = 3;
	inserirEsquerda (arv->raiz->fesq, info);
	info->chave = 6;
	info->valor = 6;
	inserirDireita (arv->raiz, info);
	info->chave = 4;
	int a = buscaIterativa (arv, info);
	printf("%d\n", a);

	return 0;
}
