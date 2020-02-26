# include "aed1b.h"

char *aluno() {return("Murilo Galvao Honorio");}      // coloque seu nome completo aqui sem caracteres especiais
char *nrousp() {return("6411927");}  // coloque seu nro.USP aqui
char *turma() {return("02");}        // coloque o nro.da sua turma aqui (2 digitos)


//algoritmo que trata os casos de exclusao do algoritmo manutencao (exceto raiz)
void excluir(NO* atual, NO* pai)
{
     if (atual->esq == NULL && atual->dir == NULL)
     {
        if (pai->esq == atual) pai->esq = NULL;
        else pai->dir = NULL;
        free (atual);
        return;
     }
     if (atual->esq != NULL && atual->dir == NULL)
     {
        if (pai->esq == atual) pai->esq = atual->esq;
        else pai->dir = atual->esq;
        free (atual);
        return;
     }
     if (atual->esq == NULL && atual->dir != NULL)
     {
        if (pai->esq == atual) pai->esq = atual->dir;
        else pai->dir = atual->dir;
        free (atual);
        return;
     }
     if (atual->esq != NULL && atual->dir != NULL)
     {
        NO* sucessor = atual->dir;
        NO* paiSucessor = atual;
        while (sucessor->esq != NULL)
        {
              paiSucessor = sucessor;
              sucessor = sucessor->esq;
        }
        atual->chave = sucessor->chave;
        excluir(sucessor, paiSucessor);
     }
}

// insere nova chave, ou exclui se ja existir
void manutencao(NO* *raiz, int ch)
{
     NO* novo = (NO*)malloc(sizeof(NO));
     novo->chave = ch;
     novo->esq = novo->dir = NULL;
     
     if (*raiz == NULL) *raiz = novo;
     else
     {
         //posiciona o ponteiro na raiz
         NO* pai = NULL;
         NO* atual = *raiz;
         
         while (atual != NULL && atual->chave != ch)
         {
               pai = atual;
               if (atual->chave > ch) atual = atual->esq;
               else atual = atual->dir; //(atual->chave < ch)
         }
         
         if (atual == NULL) //insercao
         {
            if (pai->chave > ch) pai->esq = novo;
            else pai->dir = novo;
         }
         else if (pai == NULL) //exclusao, nos casos que envolvem a raiz
         {
              if (atual->esq == NULL && atual->dir == NULL)
              {
                 free(*raiz);
                 *raiz = NULL;
                 return;
              }
              if (atual->esq != NULL && atual->dir == NULL)
              {
                 free(*raiz);
                 *raiz = atual->esq;
                 return;
              }
              if (atual->esq == NULL && atual->dir != NULL)
              {
                 free (*raiz);
                 *raiz = atual->dir;
                 return;
              }
              if (atual->esq != NULL && atual->dir != NULL)
              {
                 NO* sucessor = atual->dir;
                 NO* paiSucessor = atual;
                 while (sucessor->esq != NULL)
                 {
                       paiSucessor = sucessor;
                       sucessor = sucessor->esq;
                 }
                 atual->chave = sucessor->chave;
                 excluir(sucessor, paiSucessor);
                 return;
              }
         }
         else excluir(atual, pai);
     }
}


//algoritmo recursivo em-ordem: percorre a ABB, assim que encontra uma chave
//maior que o valor de n passado, encerra as chamadas recursivas (mediante bool
//encontrado), devolvendo ponteiro para o no em questao
NO *sucessorAux(NO* p, int n, bool *encontrado)
{
   NO *aux;
   if (p)
   {
      aux = sucessorAux(p->esq, n, encontrado);
      if (*encontrado) return (aux);
      if (p->chave > n)
      {
         *encontrado = true;
         return (p);
      }
      aux = sucessorAux(p->dir, n, encontrado);
      if (*encontrado) return (aux);
   }
   return NULL;
}

// retorna a proxima chave na ordem numerica crescente
NO *sucessor(NO *raiz, int n)
{
   bool encontrado = false;
   return (sucessorAux(raiz, n, &encontrado)); //funcao auxiliar faz o trabalho sujo ;)
}

//algoritmo recursivo pre-ordem:
//contabiliza o trajeto - passos a esquerda e a direita (pE, pD) para chegar a 
//uma folha a cada chamada recursiva, se a altura atingida for menor que a
//anterior armazena a chave que sera retornada por folhaMaisProxima 
int folha(NO *raiz, int chave, int pE, int pD, int *altura)
{
    if (raiz)
    {
       if (!raiz->esq && !raiz->dir)
       {
          //avalia a altura conforme o percurso, caso seja menor do que a encontrada,
          //armazena a chave e a altura
          if ((pE+pD) <= *altura) 
          {
             *altura = (pE+pD);
             chave = raiz->chave;
          }
          return (chave);
       }
       pD++;
       chave = folha(raiz->dir, chave, pE, pD, altura);
       pD--;
       pE++;
       chave = folha(raiz->esq, chave, pE, pD, altura);
       pE--;
    }
    return (chave);
}

// retorna folha mais proxima da raiz
int folhaMaisProxima(NO *raiz)
{
    int altura = 100000; //numero arbitrariamente alto, utilizado na primeira verificacao de altura
    return (folha(raiz, 0, 0, 0, &altura)); //
}
