# include "aed1a.h"

char *aluno() {return("Murilo Galvao Honorio");}      
char *nrousp() {return("6411927");}  
char *turma() {return("02");}        

bool inserir(MATRIZ *m, int lin, int col, int ch)
{
     NO *pL = m->L[lin]; //ponteiro para os elementos da linha
     NO *pC = m->C[col]; //ponteiro para os elementos da coluna
     NO *ant = NULL;     //ponteiro para o elemento anterior da linha/coluna
     NO *novo;
     
     if (lin < 1 || lin > MAX || col < 1 || col > MAX) return (false); //teste de parametros
     if (ch == 0 && !pL) return (true); //inserindo 0 em linha de zeros, nada a fazer
     if (ch == 0 && !pC) return (true); //inserindo 0 em coluna de zeros, nada a fazer

     //rotinas de inclusao
     if (ch != 0)
     {
         if (!pL) //insercao em linha vazia
         {
            novo = (NO*) malloc(sizeof(NO));
            novo->lin = lin;
            novo->col = col;
            novo->chave = ch;
            m->L[lin] = novo;
            novo->proxL = NULL;
         }
         else //linha com elemento(s)
         {
             while (pL)
             {
                   if (pL->col == col) //claramente o elemento ja existe
                   {
                      pL->chave = ch; //troca a chave pela desejada
                      return (true);
                   }
                   if (pL->col > col) break; //coluna ultrapassada
                   ant = pL;
                   pL = pL->proxL;
             }
             novo = (NO*) malloc(sizeof(NO));
             novo->lin = lin;
             novo->col = col;
             novo->chave = ch;
             if (!ant) //primeiro elemento da linha
             {
                novo->proxL = m->L[lin];
                m->L[lin] = novo;
             }
             else //apos elemento existente
             {
                  novo->proxL = ant->proxL;
                  ant->proxL = novo;
             }
         }
         
         ant = NULL; //prepara o ponteiro para a busca na coluna
         if (!pC) //insercao em coluna vazia
         {
            m->C[col] = novo;
            novo->proxC = NULL;
         }
         else
         {
             while (pC) //coluna com outros elementos
             {
                   if (pC->lin > lin) break; //ultrapassou
                   ant = pC;
                   pC = pC->proxC;
             }
             if (!ant) //primeiro elemento da coluna
             {
                novo->proxC = m->C[col];
                m->C[col] = novo;
             }
             else //apos elemento existente
             {
                  novo->proxC = ant->proxC;
                  ant->proxC = novo;
             }
         }
         return (true);
     }
     
     //rotinas de exclusao
     else
     {
         while (pL)
         {
               if (pL->col > col) return (true); //elemento a ser apagado ja vale zero
               if (pL->col == col) break; //elemento encontrado
               ant = pL;
               pL = pL->proxL;
         }
         // atualizar ponteiros da linha
         if (!ant) m->L[lin] = pL->proxL; //era o primeiro elemento da linha
         else ant->proxL = pL->proxL;     //ha elemento anterior
         
         ant = NULL; //reiniciar o ponteiro
         while (pC)
         {
               if (pC->lin == lin) break; //elemento encontrado
               ant = pC;
               pC = pC->proxC;
         }
         //atualizar ponteiros da coluna
         if (!ant) m->C[col] = pC->proxC; //era o primeiro elemento da coluna
         else ant->proxC = pC->proxC;     //ha elemento anterior
         
         //apagar
         free(pL);
         return (true);
     }
}

int valor(MATRIZ m, int lin, int col) 
{
    //coordenadas invalidas
    if (lin < 1 || lin > MAX || col < 1 || col > MAX) return (0);
    
    //realizamos a pesquisa pelo eixo da coordenada maior
    if (lin >= col)
    {
       NO *p = m.L[lin]; //aponta para o primeiro elemento da linha
    
       if (!p) return (0); //linha de zeros
       while (p)
       {
             if (p->col == col) return (p->chave);
             else p = p->proxL;
       }
       return (0); //nao localizado
    }
    else
    {
        NO *p = m.C[col]; //aponta para o primeiro elemento da coluna
    
        if (!p) return (0); //coluna de zeros
        while (p)
        {
              if (p->lin == lin) return (p->chave);
              else p = p->proxC;
        }
        return (0); //nao localizado
    }
}

int colunaCoincidente(MATRIZ m, int lin1, int lin2)
{
    NO *p1 = m.L[lin1];
    NO *p2 = m.L[lin2];
    int colAnt1 = 0; //guarda a coluna do elemento anterior ao apontado por p1
    int colAnt2 = 0; //guarda a coluna do elemento anterior ao apontado por p2
    
    //coordenadas invalidas
    if (lin1 < 1 || lin1 > MAX || lin2 < 1 || lin2 > MAX || lin1==lin2) return (0);
    
    //verificar se a primeira coluna de ambas contem 0
    if (!p1 && !p2) return (1); //considerando que foram inicializadas
    if (p1 && p2)
    {
       if (p1->col > 1 && p2->col > 1) return (1);
    }
    if (!p2 && p1->col > 1) return (1);
    if (!p1 && p2->col > 1) return (1);
    
    while (p1 && p2)
    {
          if (p1->col > p2->col)
          {
             colAnt1 = p1->col;
             colAnt2 = p2->col;
             //testar se as distancias entre as colunas atuais e antecedentes sao maiores que um
             if (p2->col-colAnt2 > 1 && colAnt2+1 < colAnt1) return (colAnt2+1); //retorna o 1o. zero nas duas
             else
             {
                 p2 = p2->proxL;
                 continue;
             }
          }
          if (p2->col > p1->col)
          {
             colAnt1 = p1->col;
             colAnt2 = p2->col;
             //testar se as distancias entre as colunas atuais e antecedentes sao maiores que um
             if (p1->col-colAnt1 > 1 && colAnt1+1 < colAnt2) return (colAnt1+1); //retorna o 1o. zero nas duas
             else
             {
                 p1 = p1->proxL;
                 continue;
             }
          }
          if (p1->col == p2->col)
          {
             if (p1->chave == p2->chave) return (p1->col); //elementos com chave identica
             colAnt1 = colAnt2 = p1->col;
             p1 = p1->proxL;
             p2 = p2->proxL;
             
             /* teste do salto: se houver proximos elementos e suas colunas colunas 
              * estiverem simultaneamente com distancia maior que 1 das anteriores,
              * as posicoes consecutivas as colunas anteriores velem 0.
              */
             if (p1 && p2)
             {
                if (p1->col-colAnt1 > 1 && p2->col-colAnt2 > 1) return (colAnt1+1);
             }
             continue;
          }
    }
    while (p1)
    {
          if (p1->col-colAnt1 > 1) return (colAnt1+1);//elementos nao consecutivos
          colAnt1 = p1->col;
          p1 = p1->proxL;
    }
    while (p2)
    {
          if (p2->col-colAnt2 > 1) return (colAnt2+1);//elementos nao consecutivos
          colAnt2 = p2->col;
          p2 = p2->proxL;
    }
    if (colAnt1+1 > MAX || colAnt2+1 > MAX) return (0); //chegou no final da matriz sem coincidencias
    //determinar onde ocorre a coincidencia de zeros
    if (colAnt1 > colAnt2) return (colAnt1+1);
    else return (colAnt2+1);
}

void copiarColuna(MATRIZ *m, int colOrigem, int colDestino)
{    
     NO *p1 = m->C[colOrigem];
     NO *p2 = m->C[colDestino];
     NO *antCol = NULL;          //ponteiro usado para armazenar o antecessor da colunaDestino
     NO *antLin = NULL;          //usado para as rotinas de insercao/exclusao de valores
     NO *pLin = NULL;            //ponteiro usado para iniciar pesquisa para insercao
     NO *prox = NULL;            //ponteiro usado para manter info em caso de exclusao
     
     //validacoes
     if (colOrigem < 1 || colOrigem > MAX || colDestino < 1 || colDestino > MAX || colOrigem==colDestino); //nao faz nada, colunas invalidas
     else if (!p1 && !p2); //nao faz nada, colunas vazia
     else
     {
         while (p1 && p2)
         {
               if (p1->lin > p2->lin) //exclui elemento apontado por p2 contido na linha indicada por p2 da colDestino
               {
                  //definir o inicio da linha
                  pLin = m->L[p2->lin];
                  antLin = NULL;
              
                  while (pLin)
                  {
                        if (pLin->col == colDestino) break; //elemento encontrado
                        antLin = pLin;
                        pLin = pLin->proxL;
                  }
                  if (!pLin) //linha vazia
                  {
                     p2 = p2->proxC;
                     continue;
                  }
                  // atualizar ponteiros da linha
                  if (!antLin) m->L[p2->lin] = pLin->proxL; //era o primeiro elemento da linha
                  else antLin->proxL = p2->proxL;  //ha elemento anterior
                  //atualizar ponteiros da coluna
                  if (!antCol) m->C[colDestino] = p2->proxC; //era o primeiro elemento da coluna
                  else antCol->proxC = pLin->proxC;     //ha elemento anterior

                  prox = p2->proxC; //aponta para o proximo elemento da colDestino
                  free(p2);         //apagar
                  p2 = prox;
                  continue;
               }

               if (p1->lin < p2->lin) //insere copia do elemento apontado por p1 na linha indicada por p1 da colDestino
               {
                  if (colOrigem < colDestino) //inicia a busca pela a partir de p1
                  {
                     pLin = p1; 
                     antLin = pLin;
                  }
                  else //tem que sair do inicio
                  {
                      pLin = m->L[p1->lin]; 
                      antLin = NULL;
                  }
              
                  while (pLin)
                  {
                        if (pLin->col > colDestino) break; //coluna ultrapassada
                        antLin = pLin;
                        pLin = pLin->proxL;
                  }

                  NO *novo = (NO*) malloc(sizeof(NO));
                  novo->lin = p1->lin;
                  novo->col = colDestino;
                  novo->chave = p1->chave;
                  
                  //ajuste dos ponteiros
                  if (!antLin)
                  {
                     novo->proxL = m->L[p1->lin];
                     m->L[p1->lin] = novo;
                  }
                  else
                  {
                      novo->proxL = antLin->proxL;
                      antLin->proxL = novo;
                  }
              
              
                  if (!antCol)
                  {
                     novo->proxC = m->C[colDestino];
                     m->C[colDestino] = novo;
                  }
                  else
                  {
                      novo->proxC = antCol->proxC;
                      antCol->proxC = novo; //atencao!
                  }
              
                  antCol = novo;
                  p1 = p1->proxC;
                  continue;
               }
               if (p1->lin == p2->lin)
               {
                  //marca anterior e avanca
                  if (p1->chave == p2->chave)
                  {
                     antCol = p2;
                     p1 = p1->proxC;
                     p2 = p2->proxC;
                     continue;
                  }
                  //marca anterior e copia a chave
                  else
                  {
                      antCol = p2;
                      p2->chave = p1->chave;
                      p1 = p1->proxC;
                      p2 = p2->proxC;
                      continue;
                  }
               }
         }
         //ajuste do que sobrou
         while (p1)
         {
                  if (colOrigem < colDestino) //inicia a busca pela a partir de p1
                  {
                     pLin = p1; 
                     antLin = pLin;
                  }
                  else //tem que sair do inicio
                  {
                      pLin = m->L[p1->lin]; 
                      antLin = NULL;
                  }
              
                  while (pLin)
                  {
                        if (pLin->col > colDestino) break; //coluna ultrapassada
                        antLin = pLin;
                        pLin = pLin->proxL;
                  }

                  NO *novo = (NO*) malloc(sizeof(NO));
                  novo->lin = p1->lin;
                  novo->col = colDestino;
                  novo->chave = p1->chave;
                  
                  //ajuste dos ponteiros
                  if (!antLin)
                  {
                     novo->proxL = m->L[p1->lin];
                     m->L[p1->lin] = novo;
                  }
                  else
                  {
                      novo->proxL = antLin->proxL;
                      antLin->proxL = novo;
                  }
              
              
                  if (!antCol)
                  {
                     novo->proxC = m->C[colDestino];
                     m->C[colDestino] = novo;
                  }
                  else
                  {
                      novo->proxC = antCol->proxC;
                      antCol->proxC = novo; //atencao!
                  }
              
                  antCol = novo;
                  p1 = p1->proxC;
                  continue;
         }
         while (p2)
         {
               pLin = m->L[p2->lin];
               antLin = NULL;
              
               while (pLin)
               {
                     if (pLin->col == colDestino) break; //elemento encontrado
                     antLin = pLin;
                     pLin = pLin->proxL;
               }
               if (!pLin) //linha vazia
               {
                  p2 = p2->proxC;
                  continue;
               }
               // atualizar ponteiros da linha
               if (!antLin) m->L[p2->lin] = pLin->proxL; //era o primeiro elemento da linha
               else antLin->proxL = p2->proxL;  //ha elemento anterior
               //atualizar ponteiros da coluna
               if (!antCol) m->C[colDestino] = p2->proxC; //era o primeiro elemento da coluna
               else antCol->proxC = pLin->proxC;     //ha elemento anterior

               prox = p2->proxC; //aponta para o proximo elemento da colDestino
               free(p2);         //apagar
               p2 = prox;
               continue;
         }
     }
}
//Obs.: faltou criar duas funcoes insere/exclui auxiliares para evitar a repeticao do codigo,
//      mas eu nao vou conseguir tirar nota alta na prova mesmo :)

/*
//FUNCIONANDO BEM, MAS A VERSÃO ACIMA NAO PRECISA PERCORRER TODAS AS COLUNAS/LINHAS APOS CADA INSERCAO OU EXCLUSAO
void copiarColuna(MATRIZ *m, int colOrigem, int colDestino)
{   
     NO *p1 = m->C[colOrigem];
     NO *p2 = m->C[colDestino];
     NO *prox = NULL; //ponteiro usado para manter info em caso de exclusao
     
     //validacoes
     if (colOrigem < 1 || colOrigem > MAX || colDestino < 1 || colDestino > MAX || colOrigem==colDestino) goto FIM;
     if (!p1 && !p2) goto FIM;
     
     while (p1 && p2)
     {
           //exclui elemento apontado por p2 contido na linha indicada por p2 da colDestino
           if (p1->lin > p2->lin)
           {
              prox = p2->proxC; //aponta para o proximo elemento da colDestino
              inserir(&*m, p2->lin, colDestino, 0);
              p2 = prox;
              continue;
           }
           //insere copia do elemento apontado por p1 na linha indicada por p1 da colDestino
           if (p1->lin < p2->lin)
           {
              inserir(&*m, p1->lin, colDestino, p1->chave);
              p1 = p1->proxC;
              continue;
           }
           if (p1->lin == p2->lin)
           {
              //nao faz nada
              if (p1->chave == p2->chave)
              {
                 p1 = p1->proxC;
                 p2 = p2->proxC;
                 continue;
              }
              //copia somente a chave
              else
              {
                  p2->chave = p1->chave;
                  p1 = p1->proxC;
                  p2 = p2->proxC;
                  continue;
              }
           }
     }
     while (p1)
     {
           inserir(&*m, p1->lin, colDestino, p1->chave); //insere
           p1 = p1->proxC;
     }
     while (p2)
     {
           inserir(&*m, p2->lin, colDestino, 0); //exclui
           p2 = p2->proxC;
     }
     FIM:;
}
*/
