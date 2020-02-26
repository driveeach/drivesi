//--------------------------------------------------------------
//--------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <malloc.h>
#include <string.h>
#include <ctype.h>

// ######### ESCREVA O NROUSP DO PRIMEIRO INTEGRANTE AQUI

char* nroUSP1() {
      return("8921781");
}

// ######### ESCREVA O NROUSP DO SEGUNDO INTEGRANTE AQUI (OU DEIXE COM ZERO)

char* nroUSP2() {
      return("8921176");
}

// elemento da lista

typedef struct estr {
        int chave;
        struct estr *prox;
} NO;

// lista

typedef struct {
        NO* inicio;
} LISTA;

LISTA conjunto(int op, NO* p1, NO *p2);

//------------------------------------------
// O EP consiste em implementar esta funcao
//------------------------------------------

NO* busca(LISTA *r,int ch, NO**ant){
    NO* p = r->inicio;
    *ant = NULL;
    while(p){
        if(p->chave>=ch) break;
        *ant=p;
        p=p->prox;
    }
    if(p)
        if(p->chave==ch) return (p);
    return NULL;
}

void inserir(LISTA *l,int ch){
	NO* novo;
	NO* ant;	
	novo=busca(l,ch,&ant);
	if(!novo){
		novo=(NO*)malloc(sizeof(NO));
		novo->chave=ch;	
		if(!l->inicio){
			l->inicio=novo;
			novo->prox=NULL;
		}
		else{
			if(!ant){
				novo->prox=l->inicio;
				l->inicio=novo;
			}
			else{
				novo->prox=ant->prox;
				
				ant->prox=novo;
			}
		}
	}

}

LISTA conjunto(int op, NO* p1, NO *p2) {
    LISTA resp;
	resp.inicio = NULL;
    // seu codigo AQUI

    if(!p1 && !p2) resp.inicio = NULL;

    else{
	    if(op == 1){
	    	if(!p1 || !p2) {
	        	NO* novo = (!p1)? p2 : p1;
	        	while(novo){
                   inserir(&resp, novo->chave);
               	   novo = novo->prox;
	        	}
	        }
			else{
        		while(p1&&p2){
                    if(p1->chave == p2->chave){
                        inserir(&resp, p1->chave);
                    }
                    else{
                        inserir(&resp,p2->chave);
                        inserir(&resp,p1->chave);
                    }
	                p1=p1->prox;
	                p2=p2->prox;
                }
				if(!p1 || !p2) {
					NO* novo = (!p1)? p2 : p1;
					while(novo){
						inserir(&resp, novo->chave);
						novo = novo->prox;
					}
				}
		    }
	    }
	
	    else{
    		while(p2){
    			NO* ref = p1;
		    	while(ref){
					if(ref->chave == p2->chave){
	                    inserir(&resp, p2->chave);  
		    		}
					ref = ref->prox;
		    	}
		    	p2 = p2->prox;
	    	}
		}
	}
    return resp;
}
//---------------------------------------------------------
// use main() para fazer chamadas de teste ao seu programa
//---------------------------------------------------------
int main() {
//p1={0,1,2,3,4,5,6,7}
    NO* no = (NO*) malloc(sizeof(NO));
    no->chave = 0;
    NO* no1 = (NO*) malloc(sizeof(NO));
    no1->chave = 1;
    NO* no2 = (NO*) malloc(sizeof(NO));
    no2->chave = 2;
    NO* no3 = (NO*) malloc(sizeof(NO));
    no3->chave = 3;
    NO* no4 = (NO*) malloc(sizeof(NO));
    no4->chave = 4;
    NO* no5 = (NO*) malloc(sizeof(NO));
    no5->chave = 5;
    NO* no6 = (NO*) malloc(sizeof(NO));
    no6->chave = 6;
    NO* no7 = (NO*) malloc(sizeof(NO));
    no7->chave = 7;
        
    no->prox = no1;
    no1->prox = no2;
    no2->prox = no3;
    no3->prox = no4;
    no4->prox = no5;
    no5->prox = no6;
    no6->prox = no7;
    no7->prox = NULL;

    LISTA* lista = (LISTA*) malloc(sizeof(LISTA));
    lista->inicio = no;

    //p2={1,1,2,5,5,5,5,5,5,6,7}
    NO* nu = (NO*) malloc(sizeof(NO));
    nu->chave = 1;
    NO* nu1 = (NO*) malloc(sizeof(NO));
    nu1->chave = 1;
    NO* nu2 = (NO*) malloc(sizeof(NO));
    nu2->chave = 2;
    NO* nu3 = (NO*) malloc(sizeof(NO));
    nu3->chave = 5;
    NO* nu4 = (NO*) malloc(sizeof(NO));
    nu4->chave = 5;
    NO* nu5 = (NO*) malloc(sizeof(NO));
    nu5->chave = 5;
    NO* nu6 = (NO*) malloc(sizeof(NO));
    nu6->chave = 5;
    NO* nu7 = (NO*) malloc(sizeof(NO));
    nu7->chave = 5;
    NO* nu8 = (NO*) malloc(sizeof(NO));
    nu8->chave = 5;
    NO* nu9 = (NO*) malloc(sizeof(NO));
    nu9->chave = 5;
    NO* nu10 = (NO*) malloc(sizeof(NO));
    nu10->chave = 7;
    NO* nu11 = (NO*) malloc(sizeof(NO));
    nu11->chave = 9;
    
    nu->prox = nu1;
    nu1->prox = nu2;
    nu2->prox = nu3;
    nu3->prox = nu4;
    nu4->prox = nu5;
    nu5->prox = nu6;
    nu6->prox = nu7;
    nu7->prox = nu8;
    nu8->prox = nu9;
    nu9->prox = nu10;
    nu10->prox = nu11;
	nu11->prox = NULL;

    LISTA* listo = (LISTA*) malloc(sizeof(LISTA));
    listo->inicio = nu;
    
    NO* p1 = lista->inicio;
    NO* p2 = listo->inicio;
    LISTA resp = conjunto( 1, p1, p2);

    printf("Teste com operação 1: Uniao \n");
    NO* listaR1 = resp.inicio;
    while (listaR1) {
          printf("%d   ", listaR1->chave);
          listaR1 = listaR1->prox;
    }
    printf("\n");

    LISTA resps = conjunto( 2, p1, p2);

    printf("Teste com operação 2: Intersecçao \n");
    NO* listaR2 = resps.inicio;
    while (listaR2) {
          printf("%d   ", listaR2->chave);
          listaR2 = listaR2->prox;
    }
    printf("\n\n");

    printf("Lista 1 (P1) \n");
    while (p1) {
          printf("%d   ", p1->chave);
          p1 = p1->prox;
    }
    printf("\n");

    printf("Lista 2 (P2) \n");
    while (p2) {
          printf("%d   ", p2->chave);
          p2 = p2->prox;
    }
    printf("\n");

	system("Pause");
    return 0;
}

// por favor nao inclua nenhum código abaixo da função main()
