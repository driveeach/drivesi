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
		n += 1;
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
	excluirNo( NULL, p, n, 0, &(*raiz));
}

//---------------------------------------------------------
// use main() para fazer chamadas de teste ao seu programa
//---------------------------------------------------------
int main() {
	NO* arv = NULL;
	// serao realizadas chamadas como esta:
	// int n = 7;
	// excluirNivel(&arv, n);
}

// por favor nao inclua nenhum código abaixo da função main()
