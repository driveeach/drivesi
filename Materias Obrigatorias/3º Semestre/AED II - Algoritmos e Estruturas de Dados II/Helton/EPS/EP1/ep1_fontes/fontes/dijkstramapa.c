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
	int* into = calloc (G->num_vertices, sizeof(int)); // Heap
	int* outof = calloc (G->num_vertices, sizeof(int)); // Heap
	int i=0;
	for (i=0;i<G->num_vertices; i++){ // Inicializacao basica
		customin[i] = INFINITY;
		antecessor[i]=-1;
	}
	customin[v0]=0;
	int tam=0;
	for (i=0;i<G->num_vertices;i++) InsertHeap(i, customin, into, outof, &tam); // Insere vertices no Heap
	while (!HeapVazio(customin, into, outof, tam)){ // Enqto tiver vertices a serem visitados
		tvertice u=RetiraMenorElemHeap(customin, into, outof, &tam); // Pega o menor do Heap
		tapontador a = PrimeiroAdj(u,G);
		tvertice v;
	    while (a!=NULL){ // Visita os adjacentes
			v=a->vertice;
			if (customin[v]>customin[u]+a->peso){ // Se o caminho for menor, atualiza
				AlteraValorHeap (v, (customin[u] + a->peso), customin, into, outof, tam);
				antecessor [v]=u;
			}
			a = ProxAdj(v, a, G);
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
	FILE *arquivo = fopen (nomearq, "r");
	if (arquivo==NULL) return 0;
	else{
		fscanf(arquivo, "%d", qtreq);
		int a=0;
		for (a=0; a<*qtreq;a++){
			fscanf (arquivo, "\n %s %ld %s %ld \n", RuaOrigem[a], &nrorigem[a], RuaDestino[a], &nrdestino[a]);
		}
		fclose(arquivo);
		return 1;
	}
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

typedef struct dijkstra_rua
{
 char* nome_rua;
 struct dijkstra_rua* prox; // Aponta para o prÃ³ximo elemento da pilha de livros
}rua;

typedef struct{
	int pilha_tam;
	rua* pilha_comeco;
}caminho;

void ImprimeMelhorRota (FILE *fp, char *ruaorig, long nrorig,
                                  char *ruadest, long nrdest, tgrafo *G) {

	int tam = G->num_vertices;
	tvertice vo1;
	tapontador o1_apont;
	tapontador o2_apont;
	tpeso* o1_custo = (tpeso*) calloc (tam, sizeof(tpeso)); // Correpondentes a um dos vertices da aresta de origem
	tpeso* o2_custo = (tpeso*) calloc (tam, sizeof(tpeso)); // Correpondentes ao outro vertice da aresta de origem
	tpeso* custos = (tpeso*) calloc (4, sizeof(tpeso));
	tvertice* o1_antec = (tvertice*) calloc(tam,sizeof(tvertice));
	tvertice* o2_antec = (tvertice*) calloc(tam,sizeof(tvertice));
	
		
	tvertice d1; // Correpondentes a um dos vertices da aresta de destino
	tapontador d1_apont;
	BuscaArestaRua(ruadest, nrdest, &d1, &o2_apont, G); // Encontra o vertice
	BuscaArestaRua(ruaorig, nrorig, &vo1, &o1_apont, G); // Encontra o vertice
	
	tvertice d2=o2_apont->vertice; // Encontra o outro vertice
	tvertice vo2=o1_apont->vertice;
	
	
	tpeso po = o2_apont->peso;
	tpeso pd = o1_apont->peso;
	tpeso custo = INFINITY;
	
	Dijkstra(G, vo1, o1_custo, o1_antec); // Acha melhor caminho
	Dijkstra(G, vo2, o2_custo, o2_antec); // Acha melhor caminho
	
	// MUDAR VARIAVEIS
	
	tpeso peso_01;
	tpeso peso_02;
	tpeso peso_03;
	tpeso peso_04;
	peso_01 = (nrorig - o2_apont->nrini)*o2_apont->peso/(o2_apont->nrfim - o2_apont->nrini);
	peso_02 = (nrorig - o2_apont->nrfim)*o2_apont->peso/(o2_apont->nrini - o2_apont->nrfim);
	
	peso_03 = (nrdest - o1_apont->nrini)*o1_apont->peso/(o1_apont->nrfim - o1_apont->nrini);
	peso_04 = (nrdest - o1_apont->nrfim)*o1_apont->peso/(o1_apont->nrini - o1_apont->nrfim);
	
		
	tpeso op_1 = peso_01 + o1_custo[d1] + peso_03; // Encontra o custo 
	tpeso op_2 = peso_01 + o1_custo[d2] + peso_04;
	tpeso op_3 = peso_02 + o2_custo[d1] + peso_03;
	tpeso op_4 = peso_02 + o2_custo[d2] + peso_04;
	tpeso op_5;
	
	if(o1_apont == o2_apont){
		op_5 = (abs(nrdest - nrorig) * po)/abs(o2_apont->nrfim - o2_apont->nrini);
	} else {
        op_5 = INFINITY;
	}

    int x = 0;
	int y = 0;
		
	custos[0]=op_1;
	custos[1]=op_2;
	custos[2]=op_3;
	custos[3]=op_4;
	custos[4]=op_5;
	
	
    while(x < 5) {
		if (custos[x] < custo) {
			custo = custos[x];
			y=x;
		}
		x++;
	}

	fprintf(fp, "%s %ld %s %ld %lf ", ruaorig, nrorig, ruadest, nrdest, custo);

    if (y == 4){
        fprintf(fp, "1\n%s\n", ruaorig);
    }
	else{
		
        tvertice vertice_o;
		tvertice vertice_d;
		
        tvertice* vertice_anterior = (tvertice*) calloc (tam, sizeof(tvertice));
		
		if (y == 1){
            vertice_anterior = o1_antec;
            vertice_d = d2;
            vertice_o = vo1;
        }
		else if (y == 2){
            vertice_anterior = o2_antec;
            vertice_d = d1;
            vertice_o = vo2;
        }
        else if(y == 0){
            vertice_anterior = o1_antec;
            vertice_d = d1;
            vertice_o = vo1;
        }
		else {
            vertice_anterior = o2_antec;
            vertice_d = d2;
            vertice_o = vo2;
        }

        rua rua_;
        rua_.nome_rua = ruadest;
        rua_.prox = NULL;

        caminho cam;
        cam.pilha_comeco = &rua_;
        cam.pilha_tam = 1;

        tvertice vertice_atual;
		tvertice vertice_antecessor;
        tapontador st;
        vertice_atual = -1;
        vertice_antecessor = vertice_d;

        while(vertice_atual != vertice_o){
            vertice_atual = vertice_antecessor;
            char* last_street = cam.pilha_comeco->nome_rua;
            vertice_antecessor = vertice_anterior[vertice_atual];

            if(vertice_antecessor != -1){
                st = BuscaAresta(vertice_antecessor, vertice_atual, G);
            }
			else {
                st = o1_apont;
            }

            if(comparachave(last_street, st->nomerua) != 0){
			
                rua* r = (rua*) malloc(sizeof(rua)); 
                r->nome_rua = st->nomerua;
                r->prox = cam.pilha_comeco;

                cam.pilha_comeco = r;
                (cam.pilha_tam)++;
            }
        }

        fprintf(fp, "%d\n", cam.pilha_tam);

        rua* s;
        for(s = cam.pilha_comeco; s != NULL; s = s->prox){
            fprintf(fp, "%s\n", s->nome_rua);
        }
    }

    fprintf(fp, "\n");
	
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
      printf("Requisicao: %s, %d a %s,%d\n", ruaorigem[idreq],nrorigem[idreq],
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
