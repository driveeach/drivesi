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
    int m = 0;
    int tamanho = G->num_vertices;
    int* into = (int*) calloc(tamanho, sizeof(int));
    tvertice* outof = (tvertice*) calloc(tamanho, sizeof(tvertice));
    while(m < tamanho){
        if(m != v0){
            customin[m] = INFINITY;
        } else {
            customin[m] = 0;
        }
        antecessor[m] = -1;
        m++;
    }
	int szheap = 0;
	tvertice aux = 0;
	while(aux < tamanho) {
		InsertHeap (aux, customin, into, outof, &szheap);
		aux++;
	}
    tvertice u, v;
    tapontador w;
    tpeso peso;
    while(!HeapVazio(customin, into, outof, szheap)){
		u = RetiraMenorElemHeap(customin, into, outof, &szheap);
        w = PrimeiroAdj(u, G);
        while(!FimListaAdj(u, w, G)){
			peso = w->peso;
            v = w->vertice;
            if(customin[v] > customin[u] + peso){
                tpeso x = peso + customin[u];
                AlteraValorHeap(v, x, customin, into, outof, szheap);
                antecessor[v] = u;
            }
            w = ProxAdj(u, w, G);
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

    FILE *f = fopen(nomearq, "rt");

    if(f == NULL){
        fclose(f);
        return 0;
    }

    if(fscanf(f, "%d", qtreq) != 1){
        fclose(f);
        return 0;
    }

    int i = 0;
    while(i < *qtreq){
        if(fscanf(f, "%s %ld %s %ld", RuaOrigem[i], &nrorigem[i], RuaDestino[i], &nrdestino[i]) != 4){
            fclose(f);
            return 0;
        }
        i++;
    }
    fclose(f);
    return 1;
}

typedef struct rua{
	char* nome;
	struct rua* prox;
}street;

typedef struct{
	int tamanho;
	street* comeco;
}way;

void custo_da_rua(int nrend, tapontador aresta, tpeso* p1, tpeso* p2){
	long inicio = aresta->nrini;
	long fim = aresta->nrfim;
    tpeso peso = aresta->peso;

    *p1 = (nrend - inicio)*peso/(fim - inicio);
    *p2 = (nrend - fim)*peso/(inicio - fim);
}

/*tpeso custo_da_rua (long endereco, long limite, long outro, tpeso peso) {
	long variacao1 = endereco - limite;
	long variacao2 = outro - limite;
	tpeso retorno = (variacao1*peso)/variacao2;
	return retorno;
}*/

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

	int tamanho = G->num_vertices;

    tvertice* ant1 = (tvertice*) calloc(tamanho,sizeof(tvertice));
	tvertice* ant2 = (tvertice*) calloc(tamanho,sizeof(tvertice));

	tpeso* custos = (tpeso*) calloc (4, sizeof(tpeso));

	tpeso* custo1 = (tpeso*) calloc (tamanho, sizeof(tpeso));
	tpeso* custo2 = (tpeso*) calloc (tamanho, sizeof(tpeso));

	tvertice o1, o2, d1, d2;
	tapontador origem, destino;

	BuscaArestaRua(ruaorig, nrorig, &o1, &origem, G);
	BuscaArestaRua(ruadest, nrdest, &d1, &destino, G);

	o2 = origem->vertice;
	d2 = destino->vertice;

	tpeso po = origem->peso;
    tpeso pd = destino->peso;

    tpeso custo = INFINITY;

    Dijkstra(G, o1, custo1, ant1);
    Dijkstra(G, o2, custo2, ant2);

    tpeso p1, p2, p3, p4;

    custo_da_rua(nrorig, origem, &p1, &p2);
    custo_da_rua(nrdest, destino, &p3, &p4);

    custos[0] = p1 + custo1[d1] + p3;
    custos[1] = p1 + custo1[d2] + p4;
    custos[2] = p2 + custo2[d1] + p3;
    custos[3] = p2 + custo2[d2] + p4;

	if(origem == destino){
        custos[4] = (abs(nrdest - nrorig) * po)/abs(origem->nrfim - origem->nrini);
	} else {
        custos[4] = INFINITY;
	}

    int a = 0;
	int b = 0;
    while(a < 5) {
		if (custos[a] < custo) {
			custo = custos[a];
			b=a;
		}
		a++;
	}

	fprintf(fp, "%s %ld %s %ld %lf ", ruaorig, nrorig, ruadest, nrdest, custo);

    if (b == 4){
        fprintf(fp, "1\n%s\n", ruaorig);
    } else {
        tvertice o, d;
        tvertice* ant = (tvertice*) calloc (tamanho, sizeof(tvertice));

        if(b == 0){
            ant = ant1;
            d = d1;
            o = o1;
        } else if (b == 1){
            ant = ant1;
            d = d2;
            o = o1;
        } else if (b == 2){
            ant = ant2;
            d = d1;
            o = o2;
        } else {
            ant = ant2;
            d = d2;
            o = o2;
        }

        street rua;
        rua.nome = ruadest;
        rua.prox = NULL;

        way cam;
        cam.comeco = &rua;
        cam.tamanho = 1;

        tvertice atual, antecessor;
        tapontador st;
        atual = -1;
        antecessor = d;

        while(atual != o){
            atual = antecessor;
            char* last_street = cam.comeco->nome;
            antecessor = ant[atual];

            if(antecessor != -1){
                st = BuscaAresta(antecessor, atual, G);
            } else {
                st = origem;
            }

            if(comparachave(last_street, st->nomerua) != 0){
                street* r = (street*) malloc(sizeof(street)); //*/
                r->nome = st->nomerua;
                r->prox = cam.comeco;

                cam.comeco = r;
                (cam.tamanho)++;
            }
        }

        fprintf(fp, "%d\n", cam.tamanho);

        street* s;
        for(s = cam.comeco; s != NULL; s = s->prox){
            fprintf(fp, "%s\n", s->nome);
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

