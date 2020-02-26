# include <stdio.h>
# include <malloc.h>
# define false 0
# define true 1

typedef int bool;

/* Estrutura de dados para guardar uma palavra no formato: arranjo de caracteres
   e campo para indicar a quantidade de letras */
typedef struct {
  char* letras;
  int numLetras;
} palavra;

/* Estrutura de dados para representar cada no de nossa arvore.
   Cada no representa uma letra, possui um campo bool para indicar se alguma
   palavra acaba naquele no (fimDePalavra) e alem disso possui um ponteiro para 
   um no filho (caso uma ou mais palavras continuem a partir da letra atual) e
   prox para indicar se o no tem algum irmao (ou seja, uma palavra que tem as 
   mesmas iniciais do no atual, mas que continue com outras letras [lembrando
   que a lista de palavras eh ordenada de maneira alfabetica, entao o irmao/prox
   deve possuir uma letra que seja maior do que a do no atual]).
   Se o no nao possuir filho entao o ponteiro filho deve valer NULL, se um no
   nao possuir irmao entao o ponteiro prox deve valer NULL.
 */
typedef struct NoAux {
  char letra;
  bool fimDePalavra;
  struct NoAux* prox;
  struct NoAux* filho;
} No, * PontNo;

/* A arvore em si, que eh basicamente um ponteiro para a raiz
   (que pode ser NULL), um inteiro com o numero de palavras existentes na arvore
   (numeroDePalavras), e um inteiro contendo o numero de letras da maior palavra
   da arvore (tamMaiorPalavra)
*/
typedef struct {
  int numeroDePalavras;
  int tamMaiorPalavra;
  PontNo raiz;
} ArvoreL, * PontArvore;

void inicializarArvore(PontArvore a);
bool inserirPalavraAux1(PontNo raizSubArvore, palavra* p, int letra);
bool inserirPalavraAux2(PontNo* raizSubArvore, palavra* p, int numLetraAtual);
bool inserirPalavraAux3(PontNo raizSubArvore, PontNo* endPai, palavra* p, int letra);
bool inserirPalavra(PontArvore a, palavra* p);
int tamanhoArvoreAux(PontNo no);
int tamanhoArvore(PontArvore a);
void imprimirPalavrasAux(PontNo subArvore, palavra* subPalavra, int numLetraAtual);
void imprimirPalavras(PontArvore a);
bool buscaPalavraAux(PontNo subArvore, palavra* p, int letraAtual);
bool buscaPalavra(PontArvore a, palavra* p);
