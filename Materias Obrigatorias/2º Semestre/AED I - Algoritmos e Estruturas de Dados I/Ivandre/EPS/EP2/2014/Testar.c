//--------------------------------------------------------------

//--------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <malloc.h>
#include <string.h>
#include <ctype.h>

// ######### ESCREVA O NROUSP DO PRIMEIRO INTEGRANTE AQUI


// ######### ESCREVA O NROUSP DO SEGUNDO INTEGRANTE AQUI (OU DEIXE COM ZERO)


// no da arvore
typedef struct estr {
        int chave;
        struct estr *esq;
		struct estr *dir;
} NO;

void excluirNivel(NO **raiz, int n);

//Exibição horizontal------------------------APAGAR--------------------------********************
void horiz(NO* p, int i){
	if(p){
		horiz(p->dir, i+1);
		int j;
		for(j = 1; j <= i; j++) printf("--- ");
		printf("%d \n", p->chave);
		horiz( p->esq, i+1);
	}
}
// Busca binária não recursiva devolvendo o nó pai
NO* buscaNo(NO* raiz, int ch, NO* *pai) {
	NO* atual = raiz;
	*pai = NULL;
	while (atual) {
		if(atual->chave == ch) return(atual);
		*pai = atual;
		if(ch < atual->chave) atual = atual->esq;
		else atual = atual->dir;
	}
	return(NULL);
}
//Inserir
int inserir(NO* *raiz, int ch){
	NO* pai;
	NO* aux = buscaNo(*raiz, ch, &pai);
	if(aux) return -1;
	NO* novo = (NO*) malloc(sizeof(NO));
	novo->chave = ch;
	novo->esq = NULL;
	novo->dir = NULL;
	if(!pai){
		*raiz = novo;
		return 1;
	}
	else{
		if(ch < pai->chave) pai->esq = novo;
		else pai->dir = novo;
		return 1;
	}
}
//Criar ABB a partir de um vetor
void vetorABB(NO* *raiz, int ini, int fim, int a[]){
	if(ini <= fim){
		int i = ini + (fim - ini) / 2;
		inserir( raiz, a[i]);
		vetorABB( raiz, ini, i-1, a);
		vetorABB( raiz, i+1, fim, a);
	}
}
//----------------------------****************     APAGAR
void excluir(NO* pai, NO* p) 
{
	if(!p->dir && !p->esq)
	{	
		if(pai->dir == p) pai->dir = NULL;
		else if(pai->esq == p) pai->esq = NULL;
	}
	else if(!p->dir || !p->esq)
	{
		NO* ex = (p->dir)? p->dir : p->esq;
		if(pai->dir == p) pai->dir = ex;
		else if(pai->esq == p) pai->esq = ex;
	}
	free(p);
	return ;	
}

void excluirNo(NO* pai, NO* p, int niv, int n, NO* *raiz) 
{
	if(p)
	{	
		n +=1;
		if( n == niv)
		{
			if(p->dir && p->esq)
			{	
	            NO* ex = NULL;
	            if(pai)
				{     
	            	if(pai->dir == p)
					{
	            		p = p->dir;
	            		while(p->esq)
						{
	            			ex = p;
	            			p = p->esq;
	            		}
	            		pai->dir->chave = p->chave;
	            		if(ex) excluir( ex, p);
	            		else excluir( pai->dir, p);
	            		return;
	            	}
	            	else if(pai->esq == p)
					{
	            		p = p->esq;
	            		while(p->dir)
						{
	            			ex= p;
	            			p = p->dir;
	            		}
	            		pai->esq->chave = p->chave;
	            		if(ex) excluir(ex, p);
	            		else excluir( pai->esq, p);
	            		return;
	            	}
	            } 
	            else 
				{
	                 NO* aux= NULL;
	                 ex = p->dir;
	                 while(ex->esq)
					 {
	                     aux =ex;
	                     ex= ex->esq;
	                 }
	                 p->chave = ex->chave;
	                 if(aux) excluir( aux, ex);
	                 else excluir( p, ex);
	                 return;
	            }  
			}
            else 
			{
                if(n!=1) excluir( pai, p);
                else
				{
					if(!p->dir || !p->esq)
					{
						*raiz = (p->dir)? p->dir : p->esq;
						free(p);
					}	
					else
					{
						free(*raiz);
                    	*raiz = NULL;
					}
					return ;
                }
            }
		}
		else
		{
			excluirNo( p, p->esq, niv, n, &(*raiz));
			excluirNo( p, p->dir, niv, n, &(*raiz));
		}	
	}
}


//------------------------------------------
// O EP consiste em implementar esta funcao
//------------------------------------------
void excluirNivel(NO **raiz, int n) {

	NO* p = *raiz; 	// use p para percorrer a estrutura
					// sem modificar a raiz indevidamente
	// seu codigo AQUI
	printf("aa");
	excluirNo( NULL, p, n, 0, &(*raiz));
}

//---------------------------------------------------------
// use main() para fazer chamadas de teste ao seu programa
//---------------------------------------------------------
int main() {
/* NO* n1 = (NO*) malloc(sizeof(NO));
	n1->chave = 60;
	n1->esq = NULL;
	n1->dir = NULL;
	*/
	NO* raiz = NULL; 
	inserir(&raiz,1000);
	inserir(&raiz,90);
	inserir(&raiz,66);
	inserir(&raiz,70);
	inserir(&raiz,80);
	inserir(&raiz,90);
	inserir(&raiz,100);
	inserir(&raiz,75);
	inserir(&raiz,82);
	inserir(&raiz,193);
	inserir(&raiz,67);
	inserir(&raiz,90);
	inserir(&raiz,68);
	inserir(&raiz,71);
	inserir(&raiz,74);
	inserir(&raiz,89);
	inserir(&raiz,93);
	inserir(&raiz,76);
	inserir(&raiz,82);
	inserir(&raiz,89);
	inserir(&raiz,130);
	inserir(&raiz,82);
	inserir(&raiz,81);
	inserir(&raiz,79);
	inserir(&raiz,63);
	inserir(&raiz,69);
	
	
	NO* arv = raiz;
	
	
	// serao realizadas chamadas como esta:
	// int n = 7;
	// excluirNivel(&arv, n);
//	*/
	//APAGAR ******************************
/*		NO* arv = NULL;
	int k = 63;
	int a[k];
	int i;
	for(i=0; i < k; i++){
		a[i] = i;
	}
	vetorABB(&arv, 0, k, a);
	
	horiz(arv,0);	
	printf("\n");
    excluirNivel(&arv, 4);
	printf("\n");
	horiz(arv,0);
	printf("\n");
    excluirNivel(&arv, 1);
	printf("\n");*/
	horiz(arv,0);
	printf("\n");
    excluirNivel(&arv, 1);
	printf("\n");
	horiz(arv,0);
	
	
	system("Pause");
	//******************

}

// por favor nao inclua nenhum código abaixo da função main()
