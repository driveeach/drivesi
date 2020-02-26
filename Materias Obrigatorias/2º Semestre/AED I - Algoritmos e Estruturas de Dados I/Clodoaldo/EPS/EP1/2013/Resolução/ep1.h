typedef struct estrutura{
  struct estrutura *prox;
  char nome[20];
} NO;

typedef struct{
  NO* fim;    /*ponteiro para o fim da lista*/
} LISTA;

/*inicializa a lista*/
void inicializaLista(LISTA* l); 

/*Insere um elemento*/
NO* insereElem(LISTA* l, char* nome); 

/*imprime uma lista*/
void imprime(LISTA l);

/*remove um elemento*/
int removeElem(NO* no);

/*grava uma lista em disco*/
void grava(LISTA* l, FILE* nome);

/*Se precisar defina o escopo das outras funções abaixo*/

int lerArquivo (LISTA* l, FILE* arquivo);
