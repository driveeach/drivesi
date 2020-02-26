# include <malloc.h>
# include <stdio.h>
# include <conio.h>
# include <stdlib.h>
# include <string.h>

# define max 8
# define infi 1000
       
typedef struct estrutura
{
        int vertice;
        int dista;
        estrutura *prox;
} NO;

typedef struct
{
        NO* inicio;
} Lista;

typedef struct
{
 		NO inicioL [max]; 		
}VetorLista;

typedef struct
{
 		int aresta[max][max];		
}Matriz;

// Inicialização
void inicializarLista(Lista *l)
{
 l->inicio = NULL;
}

// Exibição da lista completa
void exibirLista(Lista l)
{
 NO* p = l.inicio;
     while (p)
     {
     printf("%d ",p->vertice);
     p = p->prox;
     }
}

VetorLista converte(Matriz m){
	 int i;
	 int j;
	 VetorLista vl;
	 for(int i=0; i<max; i++){
	 		 vl.inicioL[i].prox=NULL;
	 		 }
	 for(i = 1; i < max; i++){
	 	   NO* ant = NULL;
		   bool pronto = true;	 	 	    	   
	 	   for(j = 1; j<max; j++){
		   		 if(m.aresta[i][j] < infi && m.aresta[i][j] > 0){
				 			NO* novo = (NO *) malloc(sizeof(NO));
				 			novo->vertice = j;
				 			novo->dista = m.aresta[i][j];				 				 
				 			if(!pronto)ant->prox = novo;				 				 
				 			ant = novo;				 				 
				 			novo->prox = NULL;				 				 
				 			if(pronto)	 vl.inicioL[i].prox = novo; pronto = false;
				 			}		   		 
		   		 }	 	   
	 	   }
	 	   return vl;
	 }

int main(void){
	 Matriz m;
	 int i,j;
	 for(int i = 0;i<max;i++){
	 		 for(int j = 0;j<max; j++){
			 		 m.aresta[i][j] = infi;
					 }
	 }
	 m.aresta[1][2] = 4;
	 m.aresta[1][3] = 2;
	 m.aresta[2][3] = 5;
	 m.aresta[2][4] = 1;
	 m.aresta[2][5] = 3;
	 m.aresta[3][4] = 3;
	 m.aresta[3][5] = 2;
	 m.aresta[4][5] = 2;
	 m.aresta[4][7] = 4;
	 m.aresta[5][6] = 3;
	 m.aresta[5][7] = 3;
	 m.aresta[6][7] = 1;
	 VetorLista vl;
	 vl = converte(m);
	 for(int i = 1; i<max; i++){	 		 
	 	 NO* a = vl.inicioL[i].prox;
	 	 printf("\nLista %d:", i);
		  while(a){
		  				  printf("\tDe %d ate %d tem %dcm", i,a->vertice,a->dista);
		  				  a = a->prox;
						  }
						  }				  
	 getche();
}
