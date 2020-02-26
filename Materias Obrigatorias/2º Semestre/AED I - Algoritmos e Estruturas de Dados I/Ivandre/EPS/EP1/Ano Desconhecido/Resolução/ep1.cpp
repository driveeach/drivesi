# include <iostream>
# include <stdio.h>
# include <stdlib.h>
# include <conio.h>
# include <malloc.h>


typedef struct s{
      int desc; //deposito, saque, pgto. xxx, cheque ####
      float valor;
      s *prox;
} TRANSACAO;

typedef struct
{
        TRANSACAO* inicio;
} DIA;


void exibeCabecalho()
{
     printf("Autor: Murilo Galvao Honorio\n");
     printf("Digite o numero correspondente a opcao desejada:\n");
     printf("1-Inclusao\n2-Exclusao\n3-Extrato\n4-Novo mes\n5-Fim\n");
}

void imprime(DIA mes, int dia)
{
     TRANSACAO* t = mes.inicio;
     float saldo = 0;
     
     if (t)
     {
        printf("\n%02d   ", dia);
        while (t)
        {
              if (t->desc == 1) printf("deposito         ");
              if (t->desc == 2) printf("saque            ");
              if (t->desc == 3) printf("pagamento        ");
              if (t->desc == 4) printf("cheque           ");
              if (t->desc == 5) printf("saldo do mes ant.");
              printf("%.2f\n", t->valor);
              saldo += t->valor;
              t = t->prox;
              printf("     ");
        }
        printf("SALDO            %.2f\n", saldo);
     }
}

//soma os valores enquanto destroi a lista
float consolidar(DIA *mes)
{
    TRANSACAO *atual;
    TRANSACAO *prox;
    float totalDiario = 0;
    
    atual = mes->inicio;
    
    while (atual)
    {
          totalDiario += atual->valor;
          prox = atual->prox;
          free(atual);
          atual = prox;
    }
    mes->inicio = NULL;
    return (totalDiario);
}

int main(void)
{
    int opcao = 0;
    DIA mes[30];
    
    int dia;
    int desc;
    float valor;
    
    //inicializacao
    for (int i = 1; i < 31; i++) mes[i].inicio = NULL;
    
    
    while (opcao < 1000)
    {
          system("cls");
          exibeCabecalho();
          fflush(stdin);
          scanf("%d", &opcao);

          if (opcao < 1 || opcao > 5)
          {
             printf("Opcao invalida.\n");
             getch();
             continue;
          }
          if (opcao == 1)
          {
             system("cls");
             printf("---Inclusao:---\n");

             printf("Dia: ");
             fflush(stdin);
             scanf("%i", &dia);

             if (dia >= 1 && dia <= 30)
             {
                printf("Descricao (1 dep, 2 saq, 3 pgto, 4 cheq): ");
                fflush(stdin);
                scanf("%d", &desc);
                
                if (desc >= 1 && desc <= 4)
                {
                   printf("Valor: ");
                   scanf("%f", &valor);
                   if (desc != 1 && valor > 0)
                   {
                      valor = valor*(-1);
                   }
                }
                else
                {
                    printf("Operacao invalida.");
                    getch();
                    continue;
                }
                
                //criacao da transacao
                TRANSACAO* novo = (TRANSACAO*)malloc(sizeof(TRANSACAO));
                novo->desc = desc;
                novo->valor = valor;
                novo->prox = NULL;
                
                if (!mes[dia].inicio)
                {
                   mes[dia].inicio = novo;
                }
                else
                {
                    TRANSACAO* t = mes[dia].inicio;
                    while (t->prox) t = t->prox;
                    t->prox = novo;
                }
                
                printf("Operacao bem-sucedida.");
                getch();
                continue;
             }
             else
             {
                 printf("Dia invalido, operacao nao foi concluida.");
                 getch();
                 continue;
             }
          }
          if (opcao == 2)
          {
             system("cls");
             printf("---Exclusao:---\n");
             
             printf("Dia: ");
             fflush(stdin);
             scanf("%d", &dia);

             if (dia >= 1 && dia <= 30)
             {
                printf("Valor: ");
                fflush(stdin);
                scanf("%f", &valor);
                
                TRANSACAO *t = mes[dia].inicio;
                TRANSACAO *ant = NULL;
                TRANSACAO *lista[30];
                int i = 0;
                int resp;
                
                if (!t)
                {
                   printf("Nao constam transacoes para o dia indicado.");
                   getch();
                }
                else
                {
                    while (t)
                    {
                          if (t->valor == valor)
                          {
                             lista[i] = ant;
                             i++;
                             lista[i] = t;
                             i++;
                          }
                          ant = t;
                          t = t->prox;
                    }
                    if (i == 0)
                    {
                       printf("Transacao inexistente.");
                       getch();
                       continue;
                    }
                    
                    if (i == 2)
                    {
                       if (lista[0]) lista[0]->prox = lista[1]->prox;
                       else mes[dia].inicio = lista[1]->prox;
                       free(lista[1]);
                    }
                    else
                    {
                        printf("\nIndique a transacao a ser apagada: \n");
                        for (int j = 1; j <= i; j++)
                        {
                            printf("%d - %d, %.2f\n", (j/2)+1, lista[j]->desc, lista[j]->valor);
                            j++;
                        }
                        printf("\nNumero: ");
                        fflush(stdin);
                        scanf("%d", &resp);
                        
                        if (resp > i/2+1)
                        {
                           printf("Opcao invalida.\n");
                           getch();
                        }
                        else
                        {
                            resp = (resp*2)-1;
                            if (lista[resp]) lista[resp-1]->prox = lista[resp]->prox;
                            else mes[dia].inicio = lista[resp]->prox;
                            free(lista[resp]);
                        }
                    }
                }
                continue;
             }
             else
             {
                 printf("Dia invalido, operacao nao foi concluida.");
                 getch();
                 continue;
             }
          }
          if (opcao == 3)
          {
             system("cls");
             printf("Extrato-------------\n");
             printf("------------------------------\n");
             printf("dia  descricao        valor   \n");
             printf("------------------------------\n");
             
             for (int i = 1; i <= 30; i++)
             {
                 imprime(mes[i], i);
             }
             printf("\n------------------------------\n");
             getch();
             continue;
          }
          if (opcao == 4)
          {
             system("cls");
             float saldoFinal = 0;
             //aramazenar e excluir
             for (int i = 1; i <=30; i++)
             {
                 saldoFinal += consolidar(&mes[i]);
             }
             
             TRANSACAO* inicial = (TRANSACAO*)malloc(sizeof(TRANSACAO));
             inicial->desc = 5;
             inicial->valor = saldoFinal;
             inicial->prox = NULL;
             mes[1].inicio = inicial;
             
             printf("Mes consolidado. Saldo final: %.2f. Novo mes iniciado.\n", saldoFinal);
             getch();
             continue;
          }
          if (opcao == 5)
          {
             system("cls");
             printf("Saindo, digite qualquer tecla para fechar..\n");
             break;
          }
    }
    
    getch();
}
