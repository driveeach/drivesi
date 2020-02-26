#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int ordem;

typedef struct aux {  
   int num_chaves;
   bool folha;
   float* valores;
   struct aux** filhos;
} no;
typedef struct{  
   int num_nos;
   no* raiz;
   int ordem_arvore;
} arvore;
void cria_arvore(arvore* T, int ordem){
	no* x1= (no*) malloc (sizeof(no));
    x1->folha=true;
    x1->num_chaves=0;
    x1->valores = (float*) calloc (2*ordem - 1, sizeof(float));
    x1->filhos= (no**) calloc (2*ordem, sizeof(no));
	T->raiz=x1;
}
/*no btree_search (no x, float k){
	int i=1;
	while (i<=x->num_chaves && ){
		i=i+1;
	}
	if (i<=x->num_chaves){
	
	}
	if (x->folha) return NULL;
	else return btree_search (, k);
}*/
void btree_split_child (no* x, int i, no* y){
	no* z= (no*) malloc (sizeof(no));
	z->folha=y->folha;
	z->num_chaves=ordem-1;
	int j=1;
	for (j=0; j<ordem-1;j++){
		z->valores[j]=y->valores[j+ordem];
	}
	if (y->folha==false){
		for (j=0; j<ordem;j++){
			z->filhos[j]=y->filhos[j+ordem];
		}
	}
	y->num_chaves=ordem-1;
	for (j=x->num_chaves;j>i;j--){
		x->filhos[j+1] = x->filhos[j];
	}
	x->filhos[i+1]=z;
	for (j=x->num_chaves-1;j>=i;j--){
		x->valores[j+1]=x->valores[j];
	}
	x->valores[i]=y->valores[ordem];
	x->num_chaves=x->num_chaves+1;
}
void btree_insert_nonfull (no* x, float k){
	int i = x->num_chaves;
	if (x->folha){
		while (i>=0 && x->valores[i]){
			x->valores[i+1]=x->valores[i];
			i=i-1;
		}
		x->valores[i+1]=k;
		x->num_chaves=x->num_chaves+1;
	}
	else{
		while (i>=0 && x->valores[i]){
			i=i-1;
		}
		i=i+1;
		if (x->filhos[i]->num_chaves==2*ordem-1){
			btree_split_child (x, i,x->filhos[i]);
			if (k>x->valores[i]){
				i=i+1;
			}
		}
		btree_insert_nonfull (x->filhos[i], k);
	}
}
void btree_insert (arvore* T, no* raiz, float k){
	no* r = T->raiz;
	if (r->num_chaves == 2*ordem-1){
		no* s= (no*) malloc (sizeof(no));
		T->raiz=s;
		s->folha=false;
		s->num_chaves=0;
		s->filhos[0]=r;
		btree_split_child (s, 0, r);
		btree_insert_nonfull (s, k);
	}
	else btree_insert_nonfull (r, k);
}
int main(){
	FILE *arquivo;
	int nrochaves;
	
	int i=0;
	arquivo = fopen("req1.txt","r");
	
	if (arquivo==NULL) return 0;
	
	else{
		fscanf(arquivo, "%d %d \n", &ordem, &nrochaves);
		float chaves[nrochaves];
		int a=0;
		for (a=0; a<nrochaves;a++){
			fscanf (arquivo, "%f", &chaves[a]);
		}
		fclose(arquivo);
		arvore* T= (arvore*) malloc (sizeof(arvore));
		cria_arvore(T, ordem);
		int p=0;
		for (p=0;p<nrochaves;p++){
			btree_insert (T,T->raiz, chaves[p]);
		}
	}
	return 1;
}