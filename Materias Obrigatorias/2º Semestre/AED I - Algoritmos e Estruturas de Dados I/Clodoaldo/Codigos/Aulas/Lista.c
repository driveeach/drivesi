#include <stdio.h>
#include <stdlib.h>
#include "modulo.h"

int main() {
	tlista aux;
	tlista aux2;
	ITEM item;
	item.chave = 1;
	item.valor = 1;
	ITEM it;
	it.chave = 2;
	it.valor = 2;

	ITEM item1;
	item1.chave = 3;
	item1.valor = 3;

	criar(&aux); 
	criar(&aux2);
/*	printf("VAZIA: %d\n", vazia(&aux));
	printf("CHEIA: %d\n\n", cheia(&aux));
	
	inserir_fim(&aux, &item);
	printf("VAZIA: %d\n", vazia(&aux));
	printf("CHEIA: %d\n\n", cheia(&aux));
	imprimir(&aux);
	printf("\n");
	insere_posicao(&aux, 0, &it);
	insere_posicao(&aux, 1, &item1);
	imprimir(&aux);

	printf("\n");
	inserir_fim(&aux, &item1);
	imprimir(&aux);

	printf("\n");
	remover(&aux, 2);
	printf("Aux1: \n");
	imprimir(&aux);

	printf("TAMANHO: %d\n", tamanho(&aux));

	printf("%d\n", verifica_ordem(&aux));

	inserir_fim(&aux2, &item1);
	inserir_fim(&aux2, &item);
	printf("Aux2:\n");
	imprimir(&aux2);
	printf("\n");

	concatena_sem_rep(&aux, &aux2);
	printf("Concatenado\n");
	imprimir(&aux);

	inverte(&aux);
	printf("\nInvertida\n");
	imprimir(&aux);*/

	inserir_fim(&aux, &item);
	inserir_fim(&aux, &it);
	inserir_fim(&aux, &item1);
	inserir_fim(&aux2, &it);
	inserir_fim(&aux2, &item1);		
	
	imprimir(&aux);
	printf("\n");
	imprimir(&aux2);

	printf("\nIntercalada\n");
	
	imprimir(intercalar(&aux, &aux2));

	printf("\nEliminar\n");
	eliminar_ocorencia(&aux, &it);
	imprimir(&aux);

	return 0;
}
