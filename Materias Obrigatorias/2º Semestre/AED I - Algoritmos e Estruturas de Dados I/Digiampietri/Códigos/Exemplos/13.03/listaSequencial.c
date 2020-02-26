// listaSequencial.c

#include <stdio.h>
#define MAX 50
#define ERRO -1
#define true 1
#define false 0
typedef int bool;
typedef int TCHAVE;
typedef struct {
   TCHAVE chave;
   // campos     
} REGISTRO;
typedef struct{
   REGISTRO A[MAX+1];
   int nroElem;    
} LISTA;
void inicializar(LISTA*l){
   l->nroElem = 0; 
}
void exibir (LISTA*l){
   int i;
   for (i=0; i<l->nroElem; i++){
      printf("%d ", l->A[i].chave); 
   }
   printf("\n");
}
int tamanho(LISTA*l){
   return l->nroElem;     
}
TCHAVE primeiroElem(LISTA*l){
   if(l->nroElem==0)return ERRO;
   else l->A[0].chave;       
}

TCHAVE enesimoElem (LISTA*l, int n){
   if((n<1)&&(n>l->nroElem)) return ERRO;
   else return l->A[n-1].chave;
}
bool inserir(LISTA*l, int i, REGISTRO reg){
   int j;
   if((l->nroElem==MAX)&&(i<1)&&(i>l->nroElem+1)) return false;       
   for (j=l->nroElem; j>i; j--){
       l->A[j]=l->A[j-1];
   }
       l->A[i=1]=reg;
       l->nroElem++;    
       return true;
}
int buscaSeq (Lista*l, TCHAVE ch){
    int i=0;
    while(i<l->nroElem){
      if(ch==l->A[i].chave) returni;
      else i++;                    
    }
    Return ERRO;
}
int buscaSentinela(LISTA*l, TCHAVE ch){
    l->A[l->nroElem].chave = ch;
    int i = 0;
    while(ch!=l->A[i].chave){
      i++;
    }
    if(i==l->nroElem) return ERRO;
    else return i;  
}

bool excluir (LISTA*l, TCHAVE ch){
     int pos,j;
     pos= buscaSentinela(l, ch)
     if(pos==ERRO)return false;
     for(j=pos; j<l->nroElem-1; j++){
       l->A[j]=l->A[j+1];           
     }
     
     
}





