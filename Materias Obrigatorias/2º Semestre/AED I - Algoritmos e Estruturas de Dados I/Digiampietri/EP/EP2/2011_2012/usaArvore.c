/******************************************************************************
         usaArvore.c
  Este programa interage com o usuario para o gerenciamento de uma arvore
  arvore para o armazenamento de palavras.
******************************************************************************/
#include "arvoreLetras.c"

/* funcao que le a partir da entrada o numero de letras de uma palavra e essas
   letras e retorna os dados lidos como um ponteira para um estrutura do tipo
   'palavra'.
*/
palavra* carregarPalavra(){
  int i;
  palavra* p = (palavra*) malloc(sizeof(palavra));
  scanf("%i ",&(p->numLetras));
  if (p->numLetras>0){
    p->letras = (char*) malloc(p->numLetras*sizeof(char));
    for (i=0;i<p->numLetras;i++){
        scanf("%c",&(p->letras[i]));
    }
  }
  return p;
}

void inserir(PontArvore t){
  palavra* p = carregarPalavra();
  if (inserirPalavra(t,p)){
    printf("Palavra inserida com sucesso.\n");
  }else{
    printf("Nao foi possivel inserir a palavra: ");
    imprimirUmaPalavra(p);
  }
  free(p->letras);
  free(p);
}

void buscar(PontArvore t){
  palavra *p = carregarPalavra();
  if (buscaPalavra(t,p)){
     printf("Palavra encontrada: ");
     imprimirUmaPalavra(p);
  } else {
    printf("Palavra nao encontrada: ");
    imprimirUmaPalavra(p);
  }
  free(p->letras);
  free(p);
}

void exibir(PontArvore t){
  imprimirPalavras(t);
}

void meuLog(PontArvore t){
  printf("Numero de palavras na arvore: %d, numero de nos: %d, letras da maior palavra: %d\n",t->numeroDePalavras,tamanhoArvore(t),t->tamMaiorPalavra);
}

void help(){
  printf("Comandos validos: \n");
  printf("  i <num letras> <palavra> : inserir elemento na arvore, dados: numero de letras e palavra\n");
  printf("  b <num letras> <palavra> : buscar a palavra definida pelo usuario\n");
  printf("  p : imprimir todas as palavras em ordem\n");
  printf("  l : exibir log de utilizacao da arvore\n");
  printf("  h : exibir esta mensagem de ajuda\n");
  printf("  q : sair do programa\n");
}

int main(){
  ArvoreL arvore;
  inicializarArvore(&arvore);
  /* help(); */
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&arvore); break;
      case 'p' : exibir(&arvore); break;
      case 'l' : meuLog(&arvore); break;
      case 'b' : buscar(&arvore); break;
      case 'h' : help(); break;
      default: {while (comando != '\n') scanf("%c",&comando);};
    }
    scanf("%c",&comando);
  }

  return 0;
}
