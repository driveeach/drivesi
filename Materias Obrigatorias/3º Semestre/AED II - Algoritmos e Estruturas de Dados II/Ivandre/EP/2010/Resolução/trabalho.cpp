#include "grafos.h"

char *aluno1() {
	return ("Murilo Galvao Honorio");
}

char *aluno2() {
	return ("XXXXXXXXXXXX");
}

char *nrousp1() {
	return ("6411927");
}

char *nrousp2() {
	return ("XXXXXXX");
}

char *turma() {
	return ("02");
}

/*
    CaminhoRec:
    Entrada: um grafo representado por listas de adjacencias;
             numeros dos vertices i e fim validos;
             booleano chave sinaliza se o agente pode acessar salas fechadas;
             um ponteiro para um percurso representado por lista ligada.
    Saida: verdadeiro, se um caminho entre a e b foi encontrado;
           falso caso contrario.
           
Trata-se de uma variacao do algoritmo de busca em profundidade.
Realiza recursivamente a busca, com condicao de parada quando as chamadas atin-
girem i == fim. Neste momento, acrescenta-se o NO final na lista ligada do cami-
nho e encerram-se sequencialmente as chamadas na pilha de execucao, adicionando-
se os NO's subsequentes no inicio da lista ligada, ate criar o NO inicio.
*/
bool caminhoRec(VERTICE *g, int i, int fim, bool chave, NO **resp)
{
    g[i].flag = 1;
    if (i == fim)
    {
        NO* novo = (NO*) malloc(sizeof(NO));
        novo->v = i;
        novo->prox = NULL;
        *resp = novo;
        return (true);
    }

    NO *p = g[i].inicio;
    while (p)
    {
        if (g[p->v].flag == 0 && (g[p->v].aberto == true || chave))
        {
            bool resposta = caminhoRec(g, p->v, fim, chave, resp);
            if (resposta) 
            {
                NO* novo = (NO*) malloc(sizeof(NO));
                novo->v = i;
                novo->prox = *resp;
                *resp = novo;
                return (true);
            }
        }
        p = p->prox;
    }
    return (false);
}

NO *caminho(VERTICE *g, int inicio, int fim, int chave)
{
    NO *resp = NULL;
    NO *aux = NULL;
    bool existeCaminho, chavePossivel;
    int i;
    
    /*
    Verificacao de inconsistencias
    */
    //inicio ou fim fora do intervalo [1..20] (parametros inconsistentes):
    if (inicio < 1 || inicio > 20 || fim < 1 || fim > 20) return (NULL);
    //chave fora do intervalo [-1]U[1..20] (parametros inconsistentes):
    if ((chave < 1 || chave > 20) && chave != -1) return (NULL);
    //inicio igual a fim nao constitui um percurso (parametros inconsistentes):
    if (inicio == fim) return (NULL);
    //inicio trancado e chave em outra sala (parametros inconsistentes):
    if (g[inicio].aberto == false && chave != inicio) return (NULL);
    //inicio sem arestas, portanto fim nao eh alcancavel desde inicio:
    if (g[inicio].inicio == NULL) return (NULL);
    //fim sem arestas, portanto nao eh alcancavel:
    if (g[fim].inicio == NULL) return (NULL);
    //fim trancado e chave no fim, portanto fim inacessivel:
    if (g[fim].aberto == false && chave == fim) return (NULL);
    
    
    /*
    Testar caminho direto de inicio ate fim
    */
    for (i = 1; i <= V; i++) g[i].flag = 0; //preparar grafo para busca
    if (chave != inicio) existeCaminho = caminhoRec(g, inicio, fim, false, &resp);
    //considerei valido quando a chave encontra-se na sala de inicio
    else existeCaminho = caminhoRec(g, inicio, fim, true, &resp);
    if (existeCaminho) return (resp);
    
    /*
    Verificar se ha alguma possibilidade de obter a chave
    */
    if (chave == -1) chavePossivel = false;
    //chave sem arestas, portanto nao eh alcancavel:
    else if (g[chave].inicio == NULL) chavePossivel = false;
    //chave nao pode ser obtida, pois esta trancada na propria sala:
    else if (g[chave].aberto == false) chavePossivel = false;
    //chave pode ser alcancavel desde inicio:
    else chavePossivel = true;
    
    /*
    Testar se eh possivel alcancar a chave e a partir dela alcancar o fim
    */
    if (existeCaminho == false && chavePossivel)
    {
        //testar caminho de inicio ate chave
        for (i = 1; i <= V; i++) g[i].flag = 0; //preparar grafo para busca
        existeCaminho = caminhoRec(g, inicio, chave, false, &resp);
        
        if (existeCaminho)
        {
            //testar caminho de chave ate fim
            for (i = 1; i <= V; i++) g[i].flag = 0; //preparar grafo para busca
            aux = NULL;
            existeCaminho = caminhoRec(g, chave, fim, true, &aux);
            
            if (existeCaminho) 
            {
                //unir os caminhos de inicio ate chave e de chave ate fim
                NO* p = resp;
                while (p->prox)
                {
                    p = p->prox;
                }
                p->prox = aux->prox;
                return (resp);
            }
        }
    }
    return(NULL);
}
