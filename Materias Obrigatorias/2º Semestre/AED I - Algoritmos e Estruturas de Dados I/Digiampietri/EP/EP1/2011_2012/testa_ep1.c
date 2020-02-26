#include "hash_ep1.c"
/******************************************************************************
     testa_ep.c
 Este programa interage com o usuario para o gerenciamento de chaves em uma
 estrutura hash.
 A estrutura hash utilizara listas ligadas para tratar colisoes.
 Estas listas serao 'simplesmente' ligadas (nao serao duplamente ligadas), nao
 possuirao no cabeca e nao utilizaram sentinelas.
******************************************************************************/

void inserir(HASH *h){
  TCHAVE ch;
  scanf("%d",&ch);
  if (inserirElemento(h,ch)) printf("Elemento %d inserido corretamente.\n",ch);
  else printf("Nao foi possivel inserir elemento %d.\n",ch);
}

void buscar(HASH *h){
  TCHAVE ch;
  scanf("%d",&ch);
  int q = quantidadeDeElementos(h,ch);
  if (q!=0) printf("Ha %d ocorrencia(s) da chave %d.\n",q,ch);
  else printf("Nao foi possivel encontrar a chave %d.\n",ch);
}

void inicializar(HASH *h){
  int tam;
  scanf("%d",&tam);
  inicializarHash(h,tam);
  printf("Tabela hash inicializada. Seu arranjo possui tamanho %d.\n",tam);
}


void excluir(HASH *h){
  TCHAVE ch;
  scanf("%d",&ch);
  if (excluirElemento(h,ch)) printf("Chave %d excluida corretamente.\n",ch);
  else printf("Nao foi possivel excluir a chave %d.\n",ch);
}

void meuLog(HASH *h){
    imprimirInformacoes(h);
}

void help(){
  printf("Comandos validos: \n");
  printf("   i <chave1>: inserir elemento com chave=chave1\n");
  printf("   e <chave1>: excluir elemento com chave=chave1\n");
  printf("   0 <tam1>: inicializa um hash cuja tabela terah tamanho=tam1\n");
  printf("   d : destruir/liberar memoria do hash\n");
  printf("   l : exibir log de utilizacao do hash\n");
  printf("   h : exibir esta mensagem de ajuda\n");
  printf("   b <chave1>: exibir a quantidade de ocorrencis da chave chave1\n");
  printf("   q : sair\n");
}

void destruir(HASH *h){
     int regs = liberarMemoriaTabelaHash(h);
     printf("Hash zerado. Numero de registros apagados: %d.\n", regs);
}


int main(){
  HASH hash1;
  inicializarHash(&hash1,0);
  //help();
  char comando = ' ';
  scanf("%c",&comando);
  while (comando != 'q'){
    switch (comando) {
      case 'i' : inserir(&hash1); break;
      case 'e' : excluir(&hash1); break;
      case 'd' : destruir(&hash1); break;
      case 'l' : meuLog(&hash1); break;
      case 'h' : help(); break;
      case 'b' : buscar(&hash1); break;
      case '0' : inicializar(&hash1); break;
      case 'q' : return 0;

      default: {while (comando != '\n') scanf("%c",&comando);};
    } 
    scanf("%c",&comando);
  }

  return 0;
}
