# include <iostream>
# include <stdio.h>
# include <stdlib.h>
# include <malloc.h>
# include <time.h>
# include <stdbool.h> //o booleano so funciona se incluir isso 
# include <conio.h>
# include <string.h>

typedef struct s{
      int dia;
      char desc[22];
      float valor;
      s *prox;
} NO;

typedef struct
{
        NO* inicio;
} LISTA;

void inicializar(LISTA *l)
{
     l->inicio = NULL;
}

void inclusao(LISTA *l)
{
     NO *novo = (NO*) malloc(sizeof(NO));
     NO *ant;
     
     int temp;
     char desc[22];
     float val;
     
     printf("Inclusao de transacao:\nInsira o dia: ");
     fflush(stdin);
     scanf("%i", &temp);
     novo->dia = temp;
     
     printf("Insira a descricao: ");
     fflush(stdin);
     fgets(desc, 22, stdin);
     //novo->desc = desc;
     strcpy(novo->desc, desc);
     
     printf("Insira o valor: ");
     fflush(stdin);
     scanf("%f", &val);
     novo->valor = val;
     
     if (!l->inicio)
     {  
         l->inicio = novo;
         novo->prox = NULL;
     }
     else
     {
         NO *p = l->inicio;
         if (p->dia > novo->dia)
         {
            novo->prox = p;
            l->inicio = novo;
         }
         else
         {
             while (p->dia < novo->dia)
             {     
                   ant = p;
                   p = p->prox;
             }      
             novo->prox = p;
             ant->prox = novo;
         }
     }            
     
}

bool exclusao(LISTA *l)
{
     int dia;
     NO *p = l->inicio;
     NO *ant;
     
     fflush (stdin);
     printf("Digite o dia desejado: ");
     scanf("%i", &dia);
     //testar se é válido....
     
     if (!l->inicio) return (false);
     while (p)
     {
           if (p->dia == dia)
           {
              l->inicio = p->prox;
              free(p);
              printf("Elemento excluido.");
              return(true);
           }
           if (p->prox->dia == dia)
           { 
              ant = p;
              ant->prox = p->prox->prox;
              p = p->prox;
              free(p);
              printf("Elemento excluido.");
              return(true);
           } 
           else p = p->prox;
     }
     return (false);
}

void extrato(LISTA *l)
{
     //gerar o function Ordenar
     printf("---------------------------------\n");
     printf("dia  descricao             valor \n");
     printf("---------------------------------\n");
     NO *p = l->inicio;
     while (p)
     {
           printf("%02d   %s %#-2.2f\n", p->dia, p->desc, p->valor);
           p = p->prox;
     }
}

void novoMes(LISTA *l)
{
     NO *p = l->inicio;
     float saldoTotal = 0;
     NO *ant;
     
     if (!l->inicio)
     {
        l->inicio = (NO*) malloc(sizeof(NO));
        l->inicio->dia = 1;
        strcpy(l->inicio->desc, "saldo do mes anterior");
        l->inicio->valor = 0;
     }
     else
     {
         while (p)
         {
               saldoTotal = p->valor + saldoTotal;
               ant = p;
               p = p->prox;
               free(ant);
         }
         l->inicio = (NO*) malloc(sizeof(NO));
         l->inicio->dia = 1;
         strcpy(l->inicio->desc, "saldo do mes anterior");
         l->inicio->valor = saldoTotal;
     }
}

int main(void)
{
    int opcao = 0;
    LISTA l;
    inicializar(&l);
    
    printf("Autor: Murilo Galvao Honorio\n");
    printf("Digite o numero correspondente a opcao desejada.\n");
    printf("1-Inclusao\n2-Exclusao\n3-Extrato\n4-Novo mes\n5-Fim\n");

    while (opcao != 5)
    {
          scanf("%i", &opcao);
          switch (opcao)
          {
                 case 1:
                      inclusao(&l);
                      break;
                 case 2:
                      if (exclusao(&l)) printf ("Sucesso.\n");
                      else printf ("Fracasso.\n");
                      break;
                 case 3:
                      extrato(&l);
                      break;
                 case 4:
                      novoMes(&l);
                      break;
                 case 5:
                      printf("Saindo...");
                      break;
                 default:
                         printf("Opcao invalida.\n");
                         fflush(stdin);
                         break;
          }
    }
    printf("Pressione qualquer tecla para encerrar.\n");
    getch();
}


