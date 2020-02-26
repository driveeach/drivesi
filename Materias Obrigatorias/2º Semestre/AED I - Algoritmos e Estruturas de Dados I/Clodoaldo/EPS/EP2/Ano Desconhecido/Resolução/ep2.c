#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#define TAM 256

typedef struct NO {
	int simbolo;
	int freq;
	struct NO *fesq;
	struct NO *fdir;
} tNO;

typedef struct {
	tNO *itens[TAM];
	int quant;
} HEAP;

typedef struct {
	tNO *raiz;
	char codigo [TAM][TAM];
} ARVORE;

void inicializaHeap(HEAP *heap) {
	int i;
	for (i=0; i<TAM; i++) heap->itens[i] = (tNO*) malloc(sizeof(tNO));
	heap->quant = -1;
}

void inicializaArvore (ARVORE *arv) {
	arv->raiz = NULL;
	int i;
	for (i=0; i<TAM; i++) arv->codigo[i][0] = '\0';
}

//limpa uma arvore
void limpar_arvore_aux(tNO *raiz) {
  if (raiz != NULL) {
	limpar_arvore_aux(raiz->fesq);
	limpar_arvore_aux(raiz->fdir);
	free(raiz); 
	}
}

void limpar_arvore(ARVORE *arv) {
  limpar_arvore_aux(arv->raiz);
  arv->raiz = NULL;
}

int tamArquivo (FILE *fp) {
	if (fp != NULL) {
		int cont = 0;
		char c;
		while(!feof(fp)){
			fread(&c,sizeof(char),1,fp); 
			cont++;
		}
		fseek (fp,0,SEEK_SET );
		return cont;
	}
}

//le o arquivo char por char e acrescenta no heap
unsigned char* adicionaChar (FILE *arquivo, HEAP *heap, unsigned char *msg, int* fim) {
	if (arquivo != NULL){
		int tam = tamArquivo(arquivo);
		msg = (unsigned char*) malloc((tam+1)*sizeof(unsigned char));
		int fimAux = *fim;
		int aux = 1;
		unsigned char caracter;
		fscanf (arquivo, "%c", &caracter);	
		do {
			if (fimAux>=100*aux-1) {
				aux++;
				msg=realloc(msg, 100*aux);
			}
			inserirHeap(heap, &caracter);
			fimAux++;
			msg[fimAux] = caracter;
			fscanf (arquivo, "%c", &caracter);			
		} while (!feof(arquivo));
		fimAux++;
		msg[fimAux] = '\0';
		*fim = fimAux;		
		return msg;
	}	
}

//insere um tNO com o caracter se ele não existir, caso contrário, aumenta a frequência
int inserirHeap (HEAP *heap, unsigned char *caracter) {
	int pos = (int) *caracter;
	if (heap->quant <TAM) {
		if (heap->itens[pos]->freq == 0) {	
			tNO *novo = (tNO*) malloc(sizeof(tNO));
			novo->simbolo = (int)*caracter;
			novo->freq = 1;
			novo->fesq = NULL;
			novo->fdir = NULL;
			heap->itens[pos] = novo;
			heap->quant++;
		}
		else {
			heap->itens[pos]->freq++;
		}
		return 1;
	}
	return 0;
}

//ordena heap de forma decrescente
void ordenaHeap (HEAP *heap) {
	int i, j;
	for (i=0; i<TAM-1; i++) {
		for (j=i+1; j<TAM; j++) {
			if (heap->itens[i]->freq < heap->itens[j]->freq) {
				tNO *aux = heap->itens[i];
				heap->itens[i] = heap->itens[j];
				heap->itens[j] = aux;
			}
		}
	}
}

void inserirItemOrdenado (HEAP *heap, tNO *novo) {
	int i = heap->quant;
	while (i>=0 && novo->freq > heap->itens[i]->freq) {
		heap->itens[i+1] = heap->itens[i];
		i--;
	}
	heap->itens[i+1] = novo;
	heap->quant++;
}

void removerDoisItens(HEAP *heap){
	int tam = heap->quant;
	tNO* novo = (tNO*) malloc (sizeof(tNO));
	novo->simbolo = '#';
	novo->freq = heap->itens[tam-1]->freq + heap->itens[tam]->freq;
	novo->fesq = heap->itens[tam]; //menor
	novo->fdir = heap->itens[tam-1]; //maior
	heap->quant -= 2;
	inserirItemOrdenado(heap, novo);
}

//junta dois nós e reinsere ele ordenado, até sobrar só um nó que é o nó raiz da árvore
void formarArvore (HEAP* heap, ARVORE *arv) {
	while (heap->quant > 0) {
		removerDoisItens(heap);
	}
	arv->raiz = heap->itens[0];
}

void criarCodigoAux (ARVORE *arv,  tNO *no, char *cod, int fim) {	
	if (no != NULL) {
		if (no->fesq == NULL && no->fdir == NULL) { //nó folha
			int i;
			for (i = 0; i<=fim; i++) {
				arv->codigo[(int)no->simbolo][i] = cod[i];
			}
			arv->codigo[(int)no->simbolo][fim+1] = '\0';
		}
		else {
			if (no->fesq != NULL) {
				fim++;
				cod[fim] = '0';
				criarCodigoAux(arv, no->fesq, cod, fim);
				fim--;
			}
			if (no->fdir != NULL) {
				fim++;
				cod[fim] = '1';
				criarCodigoAux(arv, no->fdir, cod, fim);
				fim--;
			}
		} 
	}
}

//a partir da árvore, gerar códigos para cada caracter
void criarCodigo (ARVORE *arv) {
	char cod[TAM];	
	criarCodigoAux(arv, arv->raiz, cod, -1);
}

void imprimirBinario (char *binario) {
	int i = 0;
	while (binario[i] != '\0'){
		printf("%c ", binario[i]);
		i++;
	}
	printf("\n");
}
 
//percorre a mensagem do arquivo char por char e substitui pelo código binário 
void codificar (ARVORE *arv, unsigned char *msg, char *binario) {
	int fim = -1; //onde parou na codificação
	int i=0;
	while (msg[i] != '\0') {
		char *cod = arv->codigo[(int)msg[i]]; //código do caracter que está sendo lido
		int j=0;
		while (cod[j] != '\0') {			
			fim++;
			binario[fim] = cod[j];
			j++;
		}
		i++;
	}
	fim++;
	binario[fim] = '\0';
	imprimirBinario(binario);
}

unsigned char* decodificar(ARVORE *arv, unsigned char *cod, unsigned char *msg) {
	int i;
	int aux = 1;
	int fim = -1; //aponta para aa última posição da decodificação
	tNO *pno = arv->raiz;	
	for (i=0; cod[i] != '\0'; i++) {
		if (fim>=100*aux-1) {
			aux++;
			msg=realloc(msg, 100*aux);
		}
		if (cod[i] == '0') {
			pno = pno->fesq;
		} else if (cod[i] == '1') {
			pno = pno->fdir;
		} else {
			printf("Simbolo codificado errado!\n");
		}
		if (pno->fesq == NULL && pno->fdir == NULL) {
			fim++;
			msg[fim] = pno->simbolo;
			pno = arv->raiz;
		}
	}
	msg[fim+1] = '\0';
	return msg;
}

int tam_cod(char *cod){ 
	int i,tam=0;
	for (i=0;cod[i]!='\0';i++)  tam++;
	return tam;   
}

void salva_arvore(tNO *p,FILE *fp) { //salva uma arvore em disco  -  ? usado para marcar um no sem filho
  if (p==NULL){
	char c='?';
	fwrite(&c,sizeof(char),1,fp);
  }else{
	//printf("%c",c);
	fwrite(&p->simbolo,sizeof(char),1,fp);
	salva_arvore(p->fesq,fp);
	salva_arvore(p->fdir,fp);
  }
}

void salva_cod(FILE *fp,char *cod){//salva o codigo no arquivo
	int i,tam, cont=0, cod_temp=-1;
	unsigned char a =(int)0; //8 bits todos com valores contendo zeros
	unsigned char b = (int) 1; //8 bits somente o ultimo com bit 1 
	unsigned char *cod_grav; //codigo convertido para caracter
	tam=tam_cod(cod);//calcula o tamanho do codigo de bits
	cod_grav=malloc((tam+1)*sizeof(char));//cria espa�o para armazenar tam caracteres
	for (i=0;cod[i] != '\0';i++){
		cont++; //incrementa o contador
		if (cod[i]=='0') {//bit 0
			a = a<<1; //realiza um deslocamento a esquerda
		}else if (cod[i] == '1'){//bit 1
			a = a<<1; //realiza um deslocamento a esquerda
			a = a|b; //adiciona um bit a primeira posicao 
		}else
			printf("%s","Codifica��o incorreta");
		if (cont==8) {/*completou 8 bits*/
			cod_temp++;
			cod_grav[cod_temp]=a; //armazena o 8 bits
			cont=0;//reseta contador
			a = (int) 0; //zera todos os bits
		}
	}
	/*O numero de bits pode n�o ser multiplo de 8, logo temos que verificar se o contador � diferente de 		zero*/
	if (cont!=0){
		for (i=cont;i<8;i++){
			a = a<<1; //prenche com zeros os bits que n�o s�o necess�rios
		}
		cod_temp++;
		cod_grav[cod_temp]=a; //armazena o valor de a
		cod_grav[cod_temp+1]='\0'; //marca a posicao final
	}    
	fwrite(&tam,sizeof(int),1,fp); //escreve o numero de bits a serem gravados
	for (i=0;i<=cod_temp;i++){
		fwrite(&cod_grav[i],sizeof(char),1,fp);//grava os codigo 
	}
}

void gravaBinario(ARVORE *arv, FILE *fp, char *binario){
	char marca ='@'; //marca o final da arvore
	if(fp == NULL) {
		printf("\nNao consigo abrir o arquivo ! ");
		exit(1);
	}    
	else {
		printf("\nArquivo Criado com Sucesso");
	}
	printf("\nSalvando a arvore no arquivo");
	salva_arvore(arv->raiz,fp);

	printf("\nGravando uma marca de fim de arquivo\n");
	fwrite(&marca,sizeof(char),1,fp);

	printf("\nSalvando codigo no arquivo\n");
	salva_cod(fp,binario);

	printf("\nApagando a estrutura da arvore"); 
	limpar_arvore(arv); 

	printf("\nFechando Arquivo\n"); 
	//fclose (fp);
}

void gravaTxt (FILE *fp, unsigned char* msg) {
	if (fp != NULL) {
		int i = 0;
		while (msg[i] != '\0') {
			fwrite(&msg[i], sizeof(char), 1, fp);
			i++;
		}
	}
}

void imprimirHeap (HEAP *heap) {
	if (heap->quant != -1) {
		int i;
		for (i = 0; i < TAM; i++) {
			if (heap->itens[i]->freq != 0) printf("Símbolo: %c  Decimal: %d  Frequencia: %d\n", (char)heap->itens[i]->simbolo, heap->itens[i]->simbolo, heap->itens[i]->freq);
		}
	}
}

void imprimirArvore (tNO *raiz) {
	if (raiz == NULL) return;
	if (raiz->simbolo != (int)'#') printf("Caracter: %c  Frequencia: %d \n",raiz->simbolo ,raiz->freq);
	imprimirArvore(raiz->fesq);
	imprimirArvore(raiz->fdir);
}

void imprimirCodigos (ARVORE *arv) {
	if (arv->raiz != NULL) {
		int i;
		for (i=0; i<TAM; i++) {
			if (arv->codigo[i][0] != '\0') {
				printf("Símbolo: %d \tCaracter: %c \tCódigo: ", i, (char)i);
				int j = 0;
				while (arv->codigo[i][j] != '\0') {
					printf("%c", arv->codigo[i][j]);
					j++;
				}
				printf("\n");
			}
		}
	}
}

char *ler_cod(FILE *fp){
	int tam,i,val,cont=0;
	int boolean = 1;
	unsigned char a,b, *cod;
	fread(&tam,sizeof(int),1,fp);//le o numero de codigos
	cod=malloc((tam+1)*sizeof(char));//cria espa�o para armazenar tam caracteres
	printf("\nForam encontrados %d codigos no arquivo\n", tam);
	while (boolean){
		fread(&a,sizeof(char),1,fp);//le o proximo caracter
		b=(int)128; 
		for (i=0;i<8;i++){
			if (cont<tam){ //para quando atingiu o numero de bits
				val=(int)(a&b);
				if (val==0) cod[cont]='0';
				else cod[cont]='1';
				b=b>>1;
				cont++;
			}
		}
		if (cont>=tam) boolean = 0;
	}
	cod[cont]='\0';
	return cod;
}

//esta funÃ§Ã£o realiza a leiura de uma arvore em disco
void carrega_arvore(tNO **p,FILE *fp){
  char c;
  fread(&c,sizeof(char),1,fp); 

  if (feof(fp)) return;

  if (c=='@') return;

  if (c!='?'){
	//printf("%c",c);
	tNO *paux = (tNO *)malloc(sizeof(tNO));
  	paux->fdir=NULL;
  	paux->fesq=NULL;
  	paux->simbolo = c;
	*p=paux;
	carrega_arvore(&((*p)->fesq),fp);
	carrega_arvore(&((*p)->fdir),fp);
  }
}

void ler(ARVORE *arv, FILE *fp, unsigned char **binario, unsigned char **msg){
	int cont=0;   
	char c;
	if(fp ==NULL) {
		printf("\nNao consigo abrir o arquivo ! ");
		exit(1);
	}    
	else {
		printf("\nArquivo aberto com Sucesso");
	}
	//procura @
	while(!feof(fp)){
		fread(&c,sizeof(char),1,fp); 
		if (c=='@') break;
	}
	//ate o final do arquivo
	while(!feof(fp)){
		fread(&c,sizeof(char),1,fp); 
		cont++;
	}
	fseek (fp,0,SEEK_SET ); //volta o ponteiro para inicio

	carrega_arvore(&arv->raiz,fp);//carrega a arvore

	while(!feof(fp)){
		fread(&c,sizeof(char),1,fp); 
		if (c=='@') break;
	}
	*binario = ler_cod(fp);
	printf("Texto Binário:\n");
	imprimirBinario(*binario);
	*msg = decodificar(arv, *binario, *msg);
	//printf("Árvore:\n");
	//imprimirArvore(arv->raiz);
}

int main(int argc, char const *argv[]) {
	if (!setlocale (LC_CTYPE, "")) {
		fprintf(stderr, "Não pode abrir a localização especificada");
		return 1;
	}

	HEAP *heap = (HEAP*) malloc(sizeof(HEAP));
	ARVORE *arv = (ARVORE*) malloc(sizeof(ARVORE));
	inicializaHeap(heap);
	FILE *arquivo;
	if (arquivo == NULL) {
		printf("Não consigo abrir o arquivo\n");
		return 1;
	}
	char const *tipo = argv[1];
	char const *arquivoEntrada = argv[2];
	char const *arquivoSaida = argv[3];
	
	if (*tipo == 'c')  { //COMPACTAÇÃO
		arquivo = fopen (arquivoEntrada, "r");
		int *fim = (int*) malloc(sizeof(int));
		*fim = -1;
		unsigned char *msg = adicionaChar(arquivo, heap, msg, fim);
		fclose (arquivo);
		ordenaHeap (heap);
		formarArvore (heap, arv);
		criarCodigo (arv);
		int tam = tam_cod(msg);
		printf("Tamanho msg: %d\n", tam);
		char *binario = (char*) malloc((tam*TAM)*sizeof(char));
		codificar (arv, msg, binario);
		int tam2 = tam_cod(binario);
		printf("Tamanho bin: %d\n", tam2);
		//imprimirBinario(binario);

		FILE *fp = fopen (arquivoSaida,"wb");
		if (fp  == NULL) { //abre o arquivo para escrita em modo binario
		  printf("\nNao consigo abrir o arquivo!");
		  exit(1);
		}
		gravaBinario (arv, fp, binario); //salva o codigo em arquivo
		fclose (fp); //fecha o arquivo
		return 0;
	}
	else if (*tipo == 'd') { //DESCOMPACTAÇÃO
		arquivo = fopen (arquivoEntrada, "rb");
		FILE *fp = fopen (arquivoSaida, "w");
		if (arquivo == NULL || fp == NULL) {
			printf("\nNão consigo abrir o arquivo!");
			exit(1);
		}
		unsigned char *binario;
		unsigned char *msg = (char*) malloc(100*(sizeof(char)));
		ler (arv,arquivo, &binario, &msg);
		imprimirBinario(msg);
		gravaTxt(fp, msg);
		fclose(arquivo);
	}
	else {
		printf("Arquivo com extensão errada!\n");
		return 1;
	}
}
