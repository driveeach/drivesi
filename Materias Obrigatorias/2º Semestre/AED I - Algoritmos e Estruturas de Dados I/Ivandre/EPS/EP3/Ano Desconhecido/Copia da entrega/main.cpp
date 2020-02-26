//#include "aed1b.h"
//#include <cstdlib>
//#include <iostream>
//
//using namespace std;
//
//void imprimir(NO* p)
//{
//     if (p)
//     {
//        imprimir(p->esq);
//        printf("-%d", p->chave);
//        imprimir(p->dir);
//     }
//}
//
//
//int main()
//{
//    int valor;
//    NO *raiz = NULL; //incializacao
//    printf("Insira os valores...\n");
//    
//    while (valor != 0)
//    {
//          fflush(stdin);
//          scanf("%d", &valor);
//          if (valor > MAX) break;
//          manutencao(&raiz, valor);
//    }
//    
//    if (valor == 300)
//    {
//       fflush(stdin);
//       printf("Sucessor: insira valor de 'n'\n");
//       scanf("%d", &valor);
//       NO * s = NULL;
//       s = sucessor(raiz, valor);
//       if (s) printf("Resposta: sucessor de %d eh %d!\n", valor, s->chave);
//       else printf("Sucessor nao encontrado.\n");
//    }
//    
//    if (valor == 666)
//    {
//       valor = folhaMaisProxima(raiz);
//       printf("Resposta: %d\n", valor);
//    }
//    
//    if (valor == 777)
//    {
//       printf("Impressao em-ordem:\n");
//       imprimir(raiz);
//    }
//
//    printf("Saiu...\n");
//
//
//    //codigo do professor
//    system("PAUSE");
//    return EXIT_SUCCESS;
//}
//
    #include "aed1b.h"
    #include <cstdlib>
    #include <iostream>
    
    using namespace std;
    
    void inicializarArvore(NO* *raiz){
         *raiz = NULL;
         }
    
    
    void exibir(NO *p){
         if(p){
               printf("%d", p->chave);
               if(p->esq || p->dir){
                         printf("(");
                         exibir(p->esq);
                         printf(",");
                         exibir(p->dir);
                         printf(")");                     
                         }
               }
         }
    
    void destruirArvore(NO* *p)
    {
    if(*p)
    {
     destruirArvore(&(*p)->esq);
     destruirArvore(&(*p)->dir);
     free(*p);
    }
     *p = NULL; // evita que a raiz aponte para endereço inválido
    }
    
    int maior(int i, int j){
        if(i>j) return i;
        else return j;
    }
    int altura(NO *p){
        int r =0;
        if(p){
              if(!p->esq && !p->dir) return(r);
              int auxEsq = altura(p->esq);
              int auxDir = altura(p->dir);
              r = maior(auxEsq, auxDir) + 1;    
              }
              return(r);           
    }
    
    void indentRec(int h, NO *p){
         int i;
         if(p){
               indentRec(h+1, p->dir);
               for(i = 1; i<= h; i++) printf("   ");
               printf("%d\n", p->chave);
               indentRec(h+1, p->esq);
               }
         }
    
    void indent(NO *p){
         int h = altura(p);
         indentRec(h, p);
         }
    
    int main()
    {
     NO* raiz;
     inicializarArvore(&raiz);
     //excluiu uma raiz sem filhos  
     manutencao(&raiz, 10);          
     manutencao(&raiz, 10);     
     
     //excluiu uma raiz com 1 filho esq     
     manutencao(&raiz, 10);
     manutencao(&raiz, 5);      
     manutencao(&raiz, 10);
     destruirArvore(&raiz);
     //excluiu uma raiz com 1 filho dir     
     manutencao(&raiz, 10);
     manutencao(&raiz, 15);  
     manutencao(&raiz, 10);
     destruirArvore(&raiz);
     //excluiu uma raiz com 2 filhos 
     manutencao(&raiz, 10);
     manutencao(&raiz, 5);   
     manutencao(&raiz, 12);
     manutencao(&raiz, 4);
     manutencao(&raiz, 7);
     manutencao(&raiz, 11);
     manutencao(&raiz, 13);
     manutencao(&raiz, 10);
     destruirArvore(&raiz);
     printf("Aqui.\n");
     
     manutencao(&raiz, 10);
     manutencao(&raiz, 5);   
     manutencao(&raiz, 12);
     manutencao(&raiz, 4);
     manutencao(&raiz, 7);
     manutencao(&raiz, 11);
     manutencao(&raiz, 13);
     indent(raiz);
     printf("----.\n");
     manutencao(&raiz, 4);
     manutencao(&raiz, 5);
     manutencao(&raiz, 13);
     manutencao(&raiz, 12);    
     indent(raiz); 
     printf("----.\n");
     manutencao(&raiz, 10);
     manutencao(&raiz, 5); 
     manutencao(&raiz, 12);
     manutencao(&raiz, 4);
     manutencao(&raiz, 7);
     manutencao(&raiz, 11);
     manutencao(&raiz, 13);
     indent(raiz);
     
     NO* n = sucessor(raiz, 2);  
     printf("\no sucessor de 2 eh o %d", n->chave);
     
     n = sucessor(raiz, 10);  
     printf("\no sucessor de 10 eh o %d", n->chave);
     
     n = sucessor(raiz, 12);  
     printf("\no sucessor de 12 eh o %d", n->chave);
      
     n = sucessor(raiz, 13);  
     if(!n) printf("\no sucessor de 13 nao existe");
     
     int num = folhaMaisProxima(raiz);
     printf("\na folha mais proxima eh o %d\n", num);
      
     manutencao(&raiz, 22);
     indent(raiz); 
     num = folhaMaisProxima(raiz);
     printf("\na folha mais proxima eh o %d\n", num);
     
     manutencao(&raiz, 20);
     manutencao(&raiz, 25);
     indent(raiz); 
     num = folhaMaisProxima(raiz);
     printf("\na folha mais proxima eh o %d", num);

      getchar();
        
    }
    

