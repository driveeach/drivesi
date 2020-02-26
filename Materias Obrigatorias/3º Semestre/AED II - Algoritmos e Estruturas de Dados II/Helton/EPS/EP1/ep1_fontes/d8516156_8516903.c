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
#include "grafosmapa.h"
#include "indheap.h"

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

void ImprimeMelhorRota (FILE *fp, char *ruaorig, long nrorig,
                                  char *ruadest, long nrdest, tgrafo *G) {

	if (ruaorig==ruadest){ // Origem e destino na mesma rua
		tapontador p_origem;
		tvertice v_origem;
		BuscaArestaRua(ruaorig, nrorig, &v_origem, &p_origem, G); // Aresta de origem
		tapontador p_destino;
		tvertice v_destino;
		BuscaArestaRua(ruadest, nrdest, &v_destino, &p_destino, G); // Aresta de destino
		double calcula = abs(nrdest-nrorig)/10; // Custo
		fprintf(fp, "%s\t%ld\t%s\t%ld\t%d\t%d\n%s", p_origem->nomerua, nrorig, p_destino->nomerua, nrdest, calcula, 1, p_origem->nomerua); // Impressao
	}
	else{
		tpeso *o1_custo = calloc (G->num_vertices, sizeof(double)); // Correpondentes a um dos vertices da aresta de origem
		tvertice *o1_antec = calloc (G->num_vertices, sizeof(int));
		tvertice vo1;
		tapontador o1_apont = malloc (sizeof(int));
		BuscaArestaRua(ruaorig, nrorig, &vo1, &o1_apont, G); // Encontra o vertice
		Dijkstra(G, vo1, o1_custo, o1_antec); // Acha melhor caminho
		tpeso *o2_custo = calloc (G->num_vertices, sizeof(int)); // Correpondentes ao outro vertice da aresta de origem
		tvertice *o2_antec = calloc (G->num_vertices, sizeof(int));;
		tvertice vo2=o1_apont->vertice;
		tapontador o2_apont = malloc (sizeof(int));
		BuscaArestaRua(ruaorig, nrorig, &vo2, &o2_apont, G); // Encontra o vertice
		Dijkstra(G, vo2, o2_custo, o2_antec); // Acha melhor caminho
		tvertice d1; // Correpondentes a um dos vertices da aresta de destino
		tapontador d1_apont;
		BuscaArestaRua(ruadest, nrdest, &d1, &d1_apont, G); // Encontra o vertice
		tvertice d2=d1_apont->vertice; // Encontra o outro vertice
		
		tpeso op_1 = abs(nrorig-o1_apont->nrini)/10 + o1_custo[d1] + abs(nrdest-o1_apont->nrfim)/10; // Encontra o custo 
		tpeso op_2 = abs(nrorig-o1_apont->nrini)/10 + o1_custo[d2] + abs(nrdest-o1_apont->nrfim)/10;
		tpeso op_3 = abs(nrorig-o1_apont->nrini)/10 + o2_custo[d1] + abs(nrdest-o1_apont->nrfim)/10;
		tpeso op_4 = abs(nrorig-o1_apont->nrini)/10 + o2_custo[d2] + abs(nrdest-o1_apont->nrfim)/10;
		double custo_1 = abs(nrorig-o1_apont->nrini)/10 + o1_custo[d1] + abs(nrdest-o1_apont->nrfim)/10;
		double custo_2 = abs(nrorig-o1_apont->nrini)/10 + o1_custo[d2] + abs(nrdest-o1_apont->nrfim)/10;
		double custo_3 = abs(nrorig-o1_apont->nrini)/10 + o2_custo[d1] + abs(nrdest-o1_apont->nrfim)/10;
		double custo_4 = abs(nrorig-o1_apont->nrini)/10 + o2_custo[d2] + abs(nrdest-o1_apont->nrfim)/10;
		tpeso comparador=op_1;
		tvertice orig_final = vo1; 
		tvertice dest_final = d1;
		tvertice *aux = calloc (G->num_vertices, sizeof(int));
		aux=o1_antec;
		tvertice *antec_final = calloc (G->num_vertices, sizeof(int));
		double custo_final=custo_1;
		if (op_2<comparador){ // Comparacoes para pegar o menor custo
			comparador = op_2;
			dest_final = d2;
			custo_final=custo_2;
		}
		if (op_3<comparador){
			comparador = op_3;
			orig_final = vo2;
			aux=o2_antec;
			custo_final=custo_3;
		}
		if (op_4<comparador){
			comparador = op_4;
			orig_final = vo2;
			dest_final = d2;
			aux=o2_antec;
			custo_final=custo_4;
		}
		int i;
		for (i=0;i<G->num_vertices;i++){ // Inverter o vetor de antecessores
			antec_final[i] = aux[dest_final];
			dest_final=antec_final[dest_final];
		} 
		int num_ruas=G->num_vertices;
		taresta *aresta_orig = malloc (sizeof(int)); // Aresta de origem
		aresta_orig = BuscaAresta(vo1, vo2, G);
		taresta *aresta_dest = malloc (sizeof(int)); // Aresta de destino
		aresta_dest = BuscaAresta(d1, d2, G);
		fprintf(fp, "%s\t%ld\t%s\t%ld\t%d\t%d\n", aresta_orig->nomerua, nrorig, aresta_dest->nomerua, nrdest, custo_final, num_ruas); // Primeira linha de impressao
		for (i=0;i<num_ruas;i++){ // Impressao das ruas
			fprintf(fp, "%s\n", antec_final[i]);
		}
	}	
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