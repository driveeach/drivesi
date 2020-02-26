/* Arquivo arvoreLetras.c */
#include "arvoreLetras.h"

/* ACH2023 - 1o Semestre de 2012
   Turma 02
   Prof. Luciano Antonio Digiampietri
   Aluno:                 - No USP:
*/

/* Esta funcao imprime todas as letras da palavra apontada por p */
void imprimirUmaPalavra(palavra* p){
     int i;
     for (i=0;i<p->numLetras;i++) printf("%c",p->letras[i]);
     printf("\n");
}

/* Esta funcao imprime as n primeiras letras da palavra apontada por p.
   Pode ser utilizada, por exemplo, como funcao auxiliar da
   funcao imprimirPalavrasAux.
 */
void imprimirNLetrasPalavra(palavra* p, int n){
     int i;
     for (i=0;i<n;i++) printf("%c",p->letras[i]);
     printf("\n");
}



/* Esta funcao inicializa todos os campos da arvore apontada por a */
void inicializarArvore(PontArvore a){
     /* completar */
}

/* Funcao (potencialmente) recursiva que pode ser utilizada como auxiliar da
   funcao inserirPalavra. Ha tres destas funcoes, escolha uma para implementar,
   ou utilize apenas a funcao inserirPalavra.
 */
bool inserirPalavraAux1(PontNo raizSubArvore, palavra* p, int letra){
	/* completar se achar necessario */
	return false;
}

/* Funcao (potencialmente) recursiva que pode ser utilizada como auxiliar da
   funcao inserirPalavra. Ha tres destas funcoes, escolha uma para implementar,
   ou utilize apenas a funcao inserirPalavra.
 */
bool inserirPalavraAux2(PontNo* raizSubArvore, palavra* p, int numLetraAtual){
	/* completar se achar necessario */
	return false;
}

/* Funcao (potencialmente) recursiva que pode ser utilizada como auxiliar da
   funcao inserirPalavra. Ha tres destas funcoes, escolha uma para implementar,
   ou utilize apenas a funcao inserirPalavra.
*/
bool inserirPalavraAux3(PontNo raizSubArvore, PontNo* endPai, palavra* p, int letra){
	/* completar se achar necessario */
	return false;
}

/* Funcao que recebe uma palavra e, caso ela nao exista na arvore, 
   a insere, atualiza o contador de palavras da arvore e retorna true.
   Caso a palavra ja exista, deve apenas retornar false.
   Esta funcao pode utilizar umas das tres funcoes auxiliares:
        inserirPalavraAux1
        inserirPalavraAux2  (esta foi a escolhida pelo professor)
        inserirPalavraAux3
   Cada uma tem uma assinatura diferente, para voce poder escolher qual a melhor
   para sua implementacao, caso precise de um metodo auxiliar para a insercao.
*/
bool inserirPalavra(PontArvore a, palavra* p){
	/* completar */
	return false;
}

/* Funcao auxiliar que dado o endereco de um no, retorna a quantidade de 
   registros a partir deste no, isto eh, 1 para o no (se ele nao for null), e
   mais todos os nos que estejam em sua lista de proximos (prox) e que estejam
   na subarvore formada por seu filho.
*/
int tamanhoArvoreAux(PontNo no){
	/* completar se achar necessario */
    return 0;
}

/* Funcao que dado o endereco de uma arvore, retorna a quantidade de 
   registros desta arvore. Lembre-se que o numero de registros nao eh o numero
   de palavras. Esta funcao pode utilizar a funcao tamanhoArvoreAux como
   auxiliar.
*/
int tamanhoArvore(PontArvore a){
	/* completar */
	return 0;
}

/* Funcao que dado o ponteiro para um no, uma subPalavra que jah tenha 
   numLetraAtual-1 letras preenchidas, imprime em ordem alfabetica todas as 
   palavras formadas a partir daquele no (lembrando que isto um palavra pode
   ser encerrada no proprio no caso no->fimDePalavra==true, alem disso pode
   continuar caso o no tenha um filho ou ainda pode haver palavras dos nos
   que sejam os proximos (irmaos) do no atual, neste caso a letra do no atual
   nao faz parte dessas palavras.
   RECOMENDACAO: guarde na variavel subPalavra as letras ancestrais da letra
   atual e use a funcao imprimirNLetrasPalavra sempre que atingir o fim de uma
   palavra.
*/
void imprimirPalavrasAux(PontNo subArvore, palavra* subPalavra, int numLetraAtual){
	/* completar se achar necessario */
}

/* Funcao que imprime em ordem alfabetica todas as palavras contidas em uma dada
   arvore apontada por a. Esta funcao pode utilizar a funcao 
   imprimirPalavrasAux como auxiliar.
*/
void imprimirPalavras(PontArvore a){
	/* completar */
}

/* Funcao que busca a partir do no apontado por raizSubArvore a palavra apontada
   por a, alem disso contem um indicador numLetraAtual que informa qual eh
   a letra atual da busca (esta funcao considera que as demais letras jah foram
   encontradas.
   Esta funcao retorna false caso nao encontre a palavra e true caso encontre.
*/
bool buscaPalavraAux(PontNo raizSubArvore, palavra* p, int numLetraAtual){
	/* completar se achar necessario */
	return false;
}

/* Funcao que busca na arvore apontada por a, a palavra apontada por b e retorna
   true caso encontre. Caso contrario retorna false.
   Esta funcao pode utilizar a funcao buscaPalavraAux como auxiliar, 
   passando como parametro qual a letra atual da busca.
*/
bool buscaPalavra(PontArvore a, palavra* p){
	/* completar */
	return false;
}
