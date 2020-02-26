/************************************
	ACH2024 - Algoritmos e Estruturas de Dados II
	Prof. Marcelo de Souza Lauretto

  Modulo: dijkstra.c
	Implementacao do Algoritmo de Dijkstra

  Renomear este modulo com o seguinte padrao:
    d<numerousp1>_<numerousp2>.c
  Exemplo:
    d1234567_7654321.c

*************************************/

#include<math.h>
#include<limits.h>

#include "grafosmapa.c"
#include "indheap.c"

#define _DEBUG_

/*
  Algoritmo de Dijkstra
  Dado um grafo G e um vertice de origem v0, esta funcao implementa
  o algoritmo de Dijkstra para encontrar o caminho de menor custo de
  v0 ateh cada um dos demais vertices do grafo.
  A saida da funcao eh o vetor de custos minimos e o vetor de
  antecessores.

  Observacao: Assume-se que o espaco para os vetores customin e
  antecessor jah estao alocados.
*/
void Dijkstra(tgrafo *G, tvertice v0,
              tpeso *customin, tvertice *antecessor) {
	int nv = G->num_vertices;
	//Vetores de controle do heap indireto: 
	//Vetor que, dada uma posicao no vetor customin, retorna a posicao correspondente do vertice no heap 
	int* into = (int*) calloc (nv, sizeof(int));
	//Vetor que, dada uma posicao no heap, retorna a posicao do vertice correspondente no vetor customin
	tvertice* outof = (tvertice*) calloc (nv, sizeof(tvertice)); 
	
	//Inicializacao
	/* Inicializar os custos de chegada a todos os vertices, exceto o inicial, como infinitos, 
	ja que nao chegamos a calcula-los ainda. Alem disso, como nao foram calculados ainda, os antecessores 
	que fazem parte do custo minimo para cada vertice, sao definidos como -1 (desconhecido)*/
	int i;
	for (i = 0; i < nv; i++) {
		antecessor[i] = -1;
		customin[i] = INFINITY;
    }
	customin[v0] = 0;
	int tam_heap = 0;
	tvertice v;
	//insere todos os vertices do grafo no heap, organizando-os de acordo com seus respectivos pesos
	for (v = 0; v < nv; v++) {
		InsertHeap (v, customin, into, outof, &tam_heap);  
	}
	//Variaveis auxiliares: 
	tvertice u, atual;
	tapontador percorre;
	tpeso peso_atual;
	//enquanto o heap nao se torna vazio
	while (!HeapVazio(customin, into, outof, tam_heap)) {
		/*remove o elemento de menor peso (contido no vetor customin). Observacao: na primeira iteracao,
		u eh o vertice de origem jah que todos os outros tem peso infinito*/
		u = RetiraMenorElemHeap (customin, into, outof, &tam_heap); 
		percorre = PrimeiroAdj (u, G); //obtem a primeira aresta da lista de adjacencias de u
		//percorre a lista de adjacencias de u ateh o fim
		while (!FimListaAdj(u, percorre, G)) {
			atual = percorre->vertice;
			peso_atual = percorre->peso;
			/*Relaxamento: se o peso do vertice atual eh maior do que o peso da aresta (u,atual) somado 
			ao peso de u, o peso e o antecessor do vertice atual sao atualizados*/
			if (customin[atual] > customin[u] + peso_atual) {
				tpeso aux = customin[u] + peso_atual;
				AlteraValorHeap (atual, aux, customin, into, outof, tam_heap);
				antecessor[atual] = u;
			}
			percorre=ProxAdj(u, percorre, G);
		}    
    }	
}

/* Carrega o arquivo de requisicoes de rota, devolvendo o status
  (1=sucesso, 0=fracasso).
A primeira linha conterah o numero de requisicoes, e cada uma das demais linhas
conterah uma requisicao, formada pelo endereco de origem (rua e numero) e endereco
de destino (rua e numero). Mais especificamente, o lay-out serah:

n
ruaorigem_1 nrorigem_1 ruadestino_1 nrdestino_1
ruaorigem_2 nrorigem_2 ruadestino_2 nrdestino_2
...
ruaorigem_n nrorigem_n ruadestino_n nrdestino_n

Os campos serão separados por espacos ou tabulacoes.
Os nomes das ruas terao no maximo 50 caracteres e não conterão espacos.
Os números serao do tipo inteiro longo.

Exemplo de arquivo de requisicoes:

4
Conselheiro_Aguiar	177	Joao_Bley_Filho	124
Paschoal_Ardito	7	Marques_De_Sao_Vicente	79
Silvia	177	Silvia	24
Euclides_Miragaia	9	Marques_De_Sao_Vicente	84
*/
int CarregaRequisicoes(char *nomearq, int *qtreq,
                        char RuaOrigem[][MAXSTRING],
                        char RuaDestino[][MAXSTRING],
                        long *nrorigem, long *nrdestino) {
	//abertura do arquivo:
	FILE* arquivo=fopen(nomearq, "r"); /*le o arquivo de nome <nomearq> e coloca o ponteiro para o
	inicio do arquivo na variavel "arquivo" */
	//Caso ocorra um erro na leitura do arquivo
	if(arquivo==NULL) {
		return 0; 
	}
	//Guarda o conteudo da primeira linha, o numero de requisicoes, na variavel qtreq
	fscanf(arquivo, "%d", qtreq);
	//Le cada linha e vai colocando as strings e numeros em seus determinados arrays
 	int i;
	for(i=0; i<*qtreq; i++ ){ //enquanto o arquivo nao termina
		fscanf(arquivo, "%s %ld %s %ld", RuaOrigem[i], &nrorigem[i], RuaDestino[i],&nrdestino[i]);
	}
	//fechamento do arquivo:
	fclose(arquivo);
	return 1;
}

//Inicio de estrutura auxiliar
// Estrutura de lista ligada para auxiliar na impressão das ruas distintas presentes em um caminho mínimo
typedef struct AUX_NO{
	char* nome_rua;
	struct AUX_NO* prox;
}NO, *pNO;

typedef struct{
	int nr_ruas;
	pNO inicio;
}CAMINHO;

//Inicializacao da estrutura auxiliar 
void Inicializar_Caminho(CAMINHO* cam){
    cam->inicio=NULL;
    cam->nr_ruas=0;
}

/* Adiciona uma rua no inicio da lista ligada que corresponde ao caminho minimo. Dessa forma, o primeiro elemento 
da lista corresponde a ultima rua adicionada.*/
void Adicionar_Rua( CAMINHO* cam, char* rua){
    pNO novo_no=(pNO)malloc(sizeof(NO));
    novo_no->nome_rua=rua;
    novo_no->prox=cam->inicio;
    cam->inicio=novo_no;
    cam->nr_ruas=cam->nr_ruas+1; //depois da insercao, o numero de ruas presentes na lista eh incrementado. 
}

/* Metodo auxiliar que verifica se dois arrays de char (strings, em si) sao iguais. Caso forem, retorna 1; 
se não, retorna 0.*/
int strings_iguais (char* s1, char* s2){
    int i = 0;
    while (s1[i] != '\0' && s2[i] != '\0') {
        if (s1[i] != s2[i]) {
            return 0;
        }
        else {
            i++;           
        }
    }    
    if (s1[i] != '\0' || s2[i] != '\0') {
        return 0;
    }
    else {
        return 1;
    }
}

// Metodo que retorna o valor absoluto de um numero. 
long modulo (long num) {
	if (num >= 0) {
		return num; 
	}
	else {
		return (num*(-1)); 
	}
}

/*O metodo a seguir possui os seguintes parametros: 
endereco: endereco de partida ou de chegada de um caminho
peso: peso da aresta onde se encontra o endereco 
limite: o numero minimo (ou maximo) da aresta a partir do qual deseja-se calcular o custo
outro: a outra extremidade da aresta
Ele utiliza uma regra de tres simples para calcular o custo do segmento de aresta entre o endereco e o limite.
Por exemplo, seja A uma rua cujos numeros vao de 10 a 100 e tem distancia (peso) de 50 m. Qual a distancia entre 
os numeros 10 e 50 ? Basta utilizar este metodo com parametros endereco = 50, limite = 10, peso = 50 e outro = 100*/
tpeso custo (long endereco, long limite, long outro, tpeso peso) {
	long variacao1 = endereco - limite; 
	long variacao2 = outro - limite; 
	tpeso retorno = (variacao1*peso)/variacao2; 
	return retorno; 
}

/* Dado um endereco de origem e um endereco de destino no grafo G,
   esta funcao chama o algoritmo de Dijkstra e escolhe a rota de menor
   custo entre os dois endereços.
   As ruas que compoem a melhor rota são impressas no arquivo apontado por fp.

   Argumentos:
     fp: ponteiro do arquivo de saida
     ruaorig, nrorig: rua/numero do endereco de origem
     ruadest, nrdest: rua/numero do endereco de destino
     G: grafo representando o mapa

  Esta funcao gravarah no arquivo de saida um bloco de linhas com a rota de
  menor custo entre o endereco de origem e o endereco de destino.
  A primeira linha do bloco bloco conterá os campos:
    Nome da rua de origem (string sem espaços e sem aspas, até 50 caracteres)
    Número de origem (inteiro longo)
    Nome da rua de destino (string sem espaços e sem aspas, até 50 caracteres)
    Número de destino (inteiro longo)
    Custo mínimo (double)
    Número de ruas distintas do endereço de origem ao endereço de destino (inteiro)
  As demais linhas do bloco conterão os nomes das ruas por onde a rota de menor
  custo deverá passar. Somente as ruas distintas no trajeto deverão ser exibidas
  (arestas consecutivas com mesmo nome de rua não devem ser listadas na rota final).
*/
void ImprimeMelhorRota (FILE *fp, char *ruaorig, long nrorig, char *ruadest, long nrdest, tgrafo *G) {
	int nv=G->num_vertices;
	//Vetor que contera os pesos de cada vertice, referentes ao caminho minimo entre estes e uma origem fixada
	tpeso* customin=(tpeso*) calloc(nv,sizeof(tpeso));
	
	//vetor de antecessores referentes ao caminho minimo partindo de uma origem x
    tvertice* antecessor1=(tvertice*) calloc(nv,sizeof(tvertice)); 
	//vetor de antecessores referentes ao caminho minimo partindo de uma origem y
	tvertice* antecessor2=(tvertice*) calloc(nv,sizeof(tvertice));

	//Custos das 5 possibilidades de caminho minimo existentes: 
	/*caminhos possiveis
	1: ir-de(nrorig>>>v_origem1) + ir-de(v_origem1'nrini1'>>>v_destino1'nrini2') + ir-de(v_destino1>>>nrdest)
	2: ir-de(nrorig>>>v_origem1) + ir-de(v_origem1'nrini1'>>>v_destino2'nrini2') + ir-de(v_destino2>>>nrdest)
	3: ir-de(nrorig>>>v_origem2) + ir-de(v_origem2'nrfim1'>>>v_destino1'nrfim2') + ir-de(v_destino1>>>nrdest)
	4: ir-de(nrorig>>>v_origem2) + ir-de(v_origem2'nrfim1'>>>v_destino2'nrfim2') + ir-de(v_destino2>>>nrdest)
	5: se nrorig e nrdest estao na mesma aresta: ir-de (nrorig>>>nrdest)
	*/
	tpeso* custos_possiveis = (tpeso*) calloc (5, sizeof(tpeso)); 
	
	//Origem:
	/*v_origem1 e v_origem2 recebem os dois vertices ligados pela aresta a_origem onde se encontra o numero 
	de origem passado como parâmentro para o método BuscaArestaRua */
	tvertice v_origem1; 
	tapontador a_origem; 
	BuscaArestaRua(ruaorig, nrorig, &v_origem1, &a_origem,G);
	tvertice v_origem2 = a_origem->vertice; 
	long nrini1 = a_origem->nrini;
	long nrfim1 = a_origem->nrfim; 
    tpeso peso_origem = a_origem->peso; 
	
	//Destino:
	/*v_destino1 e v_destino2 recebem os dois vertices ligados pela aresta a_destino onde se encontra o numero
	de destino passado como parâmentro para o método BuscaArestaRua	*/
	tvertice v_destino1; 
	tapontador a_destino; 
	BuscaArestaRua(ruadest, nrdest, &v_destino1, &a_destino,G);
	tvertice v_destino2 = a_destino->vertice; 
	long nrini2 = a_destino->nrini; 
	long nrfim2 = a_destino->nrfim; 
	tpeso peso_destino = a_destino->peso; 
	
	//Caminhos minimos com origem em v_origem1: engloba casos 1 e 2	
	Dijkstra (G, v_origem1, customin, antecessor1);
	//customin[v_destino1] = ir-de(v_origem1'nrini1'>>>v_destino1'nrini2')
	//customin[v_destino2] = ir-de(v_origem1'nrini1'>>>v_destino2'nrfim2')
	//primeira opcao de custo:
	custos_possiveis[0] = custo (nrorig, nrini1, nrfim1, peso_origem) + customin[v_destino1] + custo (nrdest, nrini2, nrfim2, peso_destino); 
	//segunda opcao de custo
	custos_possiveis[1] = custo (nrorig, nrini1, nrfim1, peso_origem) + customin[v_destino2] + custo (nrdest, nrfim2, nrini2, peso_destino); 
    
	//Caminhos minimos com origem em v_origem2: engloba casos 3 e 4
	Dijkstra (G, v_origem2, customin, antecessor2);
	//customin[v_destino1] = ir-de(v_origem2'nrfim1'>>>v_destino1'nrini2')
	//customin[v_destino2] = ir-de(v_origem2'nrfim1'>>>v_destino2'nrfim2')	
	//terceira opcao de custo 
	custos_possiveis[2] = custo (nrorig, nrfim1, nrini1, peso_origem) + customin[v_destino1] + custo (nrdest, nrini2, nrfim2, peso_destino); 
	//quarta opcao de custo:
	custos_possiveis[3] = custo (nrorig, nrfim1, nrini1, peso_origem) + customin[v_destino2] + custo (nrdest, nrfim2, nrini2, peso_destino); 
	
	//quinta opcao de custo: 
	//se os numeros de origem e de destino pertencem a mesma aresta: 
	if (a_origem == a_destino) { 
		// Regra de tres para descobrir o peso do segmento de aresta correspondente
		long variacao_endereco = modulo (nrdest - nrorig); 
		long variacao_limites = modulo (nrfim1 - nrini1); 
		custos_possiveis[4] = (variacao_endereco*peso_origem)/variacao_limites;	
	}
	/*caso contrario: associa-se um peso absurdo ao caso 5 para que ele nunca seja eleito como 
	caminho minimo */
	else {
		custos_possiveis[4] = INFINITY; 
	}
	
	/*Verifica qual dos custos eh o menor e o salva na variavel custominimo. Alem disso, armazena em
	j o numero do caso escolhido (menos um)*/
	tpeso custominimo = INFINITY; 
	int i;
	int j=0;
	for (i = 0; i < 5; i++) {
		if (custos_possiveis[i] < custominimo) {
			custominimo = custos_possiveis[i]; 
			j=i;
		}
	}
	
	//Escreve os resultados no arquivo
	fprintf( fp, "%s ", ruaorig);
	fprintf( fp, "%ld ", nrorig);
	fprintf( fp, "%s ", ruadest);
	fprintf( fp, "%ld ", nrdest);    
	fprintf(fp, "%lf ", custominimo); //custo minimo
	
	if (j < 4) {
		//Variaveis e vetor auxiliares
		tvertice v_origem;
		tvertice v_destino;
		tvertice* antecessor=(tvertice*) calloc(nv,sizeof(tvertice));
		/* Dependendo de qual eh a melhor rota, atribui os vertices de origem e de destino a, respectivamente, v_origem e v_destino.
		Alem disso, atribui antecessor1 ou antecessor2 a antecessor */
		if(j==0){
			v_origem=v_origem1;
			v_destino=v_destino1;
			antecessor=antecessor1; 
		}else if(j==1){
			v_origem=v_origem1;
			v_destino=v_destino2;
			antecessor=antecessor1;
		}else if(j==2){
			v_origem=v_origem2;
			v_destino=v_destino1;
			antecessor=antecessor2;
		}else if(j==3){
			v_origem=v_origem2;
			v_destino=v_destino2;
			antecessor=antecessor2; 
		}		
		
		//Identificar caminho minimo feito:
		CAMINHO caminho_minimo;
		Inicializar_Caminho( &caminho_minimo );
		/*Uma vez que o caminho minimo eh dado pelo vetor antecessor, as ruas sao inseridas no caminho na ordem 
		contraria aquela q seria percorrida de fato. */
		//Adiciona, primeiramente, a rua de destino
		Adicionar_Rua( &caminho_minimo, ruadest );
		tvertice p; 
		tvertice v_aux=v_destino;
		//Enquanto o vertice de origem nao for atingido
		while ( p != v_origem ){
			/* Na primeira iteracao, p eh igual ao vertice de destino. Na proxima, no entanto, a mesma atribuicao, a seguir, 
			faz com que p se torne seu antecessor */
			p=v_aux;
			//armazena a ultima rua que foi visitada
			char* ultima_rua_visitada= caminho_minimo.inicio->nome_rua;
			//armazena o antecessor do vertice atual. Na primeira iteracao, o vertice atual eh o vertice de destino
			v_aux = antecessor[ p ];
			tapontador aresta_aux;
			//se o antecessor eh diferente de -1, ou seja, se o vertice atual nao eh o vertice de origem:  
			if( v_aux!=-1 ){
				//a aresta correspondente ao vertice atual eh pesquisada e armazenada em aresta_aux
				aresta_aux=BuscaAresta( v_aux, p, G );
			}
			//caso contrario: 
			else {
				//a aresta de origem eh armazenada em aresta_aux
				aresta_aux=a_origem;
			}
			/* se a rua associada a aresta_aux ainda nao estiver na lista ligada de ruas, ela eh adicionada. 
			Para fazer essa verificacao, ela eh comparada à ultima rua que foi visitada pelo metodo 
			strings_iguais */ 
			int ruas_iguais=strings_iguais( aresta_aux->nomerua, ultima_rua_visitada ) ;
			if ( ! ruas_iguais ){
				//Se as ruas forem diferentes: adicionar essa ultima ao caminho
				Adicionar_Rua( &caminho_minimo, aresta_aux->nomerua );
			}			
		}
		//Escrever, no arquivo, numero de ruas distintas:
		fprintf( fp, "%d\n", caminho_minimo.nr_ruas );
		/* Escrever, no arquivo, cada uma das ruas presentes no caminho minimo. Tal escrita deve ser feita na 
		ordem contraria à insercao, visto que a insercao eh feita usando-se o vetor antecessor */
		pNO percorre=caminho_minimo.inicio;
		while( percorre != NULL ){
			fprintf( fp, "%s\n",  percorre->nome_rua);
			percorre=percorre->prox;
		}
	}	
	else {
		/*Como o caso 5 eh mais simples, ele eh tratado a parte. 
		Nesse caso, apenas uma rua eh usada no caminho minimo e se trata da rua de origem*/
		fprintf( fp, "%d\n", 1 );
		fprintf( fp, "%s\n", ruaorig);
	}
	//Pula uma linha no arquivo para separar uma requisicao da outra. 
	fprintf( fp, "\n" );
}

/*
  Funcao principal do programa: Carrega o grafo, carrega e executa a lista
  de requisicoes e escreve as melhores rotas.
*/
int main(int argc, char *argv[]) {
	tgrafo G;
	int qtreq;  // quantidade de requisicoes de rota
	int idreq;
	char ruaorigem[100][MAXSTRING], ruadestino[100][MAXSTRING];
	long nrorigem[100], nrdestino[100];
	FILE *fpout;
	int tamcaminho;
	if (argc<4) {
		printf("GPS.EXE \nSintaxe: gps <arqmapa> <arqrequisicao> <arqsaida>\n\n");
		exit(0);
	}
	if (LeGrafo(argv[1], &G) &&
	CarregaRequisicoes(argv[2], &qtreq, ruaorigem, ruadestino,
		         nrorigem, nrdestino)) {
		fpout = fopen(argv[3], "wt");
		if (fpout==NULL)
		return(0);
		fprintf(fpout, "%d\n", qtreq);
		fclose(fpout);
		for (idreq=0; idreq<qtreq; idreq++) {
			#ifdef _DEBUG_
			printf("Requisicao: %s, %ld a %s,%ld\n", ruaorigem[idreq],nrorigem[idreq],
			     ruadestino[idreq], nrdestino[idreq]);
			#endif
			fpout = fopen(argv[3], "at");
			ImprimeMelhorRota(fpout, ruaorigem[idreq], nrorigem[idreq],
					       ruadestino[idreq], nrdestino[idreq], &G);
			fclose(fpout);
		}
		LiberaGrafo(&G);
	}
	return(1);
}