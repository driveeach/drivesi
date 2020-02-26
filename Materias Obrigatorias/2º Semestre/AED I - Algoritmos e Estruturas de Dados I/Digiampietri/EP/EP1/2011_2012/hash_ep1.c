#include "hash_ep1.h"

/* ACH2023 - 1o Semestre de 2012
   Turma 04
   Prof. Luciano Antonio Digiampietri
   Aluno:                 - No USP:   
*/

/* IMPORTANTE: NAO USE AS FUNCOES malloc E free, UTILIZE NO LUGAR DELAS AS
   FUNCOES: meuMalloc E meuFree QUE POSSUEM AS MESMAS ASSINATURAS DAS FUNCOES
   ORIGINAIS MAS SERAO FUNDAMENTAIS PARA A VERIFICACAO DA CORRETUDE DOS EPS
   
   NAO INCLUA NENHUMA BIBLIOTECA ADICIONAL, USE APENAS OS COMANDOS VISTOS AO
   LONGO DO SEMESTRE.

   A tabela hash propriamente dita eh um arranjo de ponteiros para registros,
   cada ponteiro valera NULL ou tera o endereco de do primeiro registro da lista
   ligada correspondente a aquela posicao da tabela.
   As listas ligadas serao ordenadas pelo valor da chave, sao listas 
   'simplesmente' ligadas (ou seja, nao sao duplamente ligadas) e, mesmo sendo
   ordenadas, nao eh possivel fazer busca binaria eficiente.
*/

/* Esta funcao retorna a posicao na tabela hash na qual elementos com chave
   igual a ch deverao ser inseridos. Lembrando que os elementos serao inseridos
   na forma de Registros de uma lista ligada ordenada */
int funcaoHash(HASH *h, TCHAVE chave){
    return chave % h->tamanhoDaTabela;
}


/* Esta funco imprime algumas informacoes basicas sobre a estrutura hash
   apontada por h.
*/
void imprimirInformacoes(HASH *h){
     printf("Tabela hash de tamanho %i, com %i registros alocados e %i chaves\n", h->tamanhoDaTabela, h->quantidadeTotalDeRegistros,quantidadeTotalDeChaves(h));
}


/* Esta funcao retorna a quantidade total de chaves na nossa estrutura hash.
   Observe que nao eh apenas o numero de registros mas sim a soma dos campos
   'contador' de todos os registros (ou seja, o valor retornado sempre serah
   maior ou igual a quantidade de registros).*/
int quantidadeTotalDeChaves(HASH *h){
    // completar
    return 0;
}
    

/* Esta funcao deve preparar todos os campos do hash apontado por h
   incluindo a alocao de memoria para a tabela hash, de forma que ela
   tera 'tamanho' posicoes */
void inicializarHash(HASH *h, int tamanho){
     // completar
}

/* Esta funcao deve liberar a memoria alocada para todos os registros das
   listas apontadas pela tabela hash bem como liberar a memoria do arranjo de
   ponteiros alocado para a tabela hash. Alem disso deve retornar o numero
   de registros liberados (neste numero, nao contar a liberacao do arranjo da
   tabela hash propriamente dita). Lembre-se de utilizar a funcao meuFree para 
   liberar a memoria.
   Lembre-se tambem de acertar os valores de TODOS os campos da estrutura HASH
   apontada por h.
*/
int liberarMemoriaTabelaHash(HASH* h){
     // completar
}

/* Esta funcao insere a chave ch na tabela hash apontada por h.
   Neste EP, inserir uma chave significa: se um registro para esta chave jah
   existir no hash, entao aumentar em um o valor do campo contador desse
   registro; se o registro nao existir, entao criar esse registro e inseri-lo
   na lista ligada correta e em ordem de valor. Para saber qual a lista ligada
   correta, dentro da tabela hash, use a funcao funcaoHash que dada uma chave
   indica qual a posicao do arranjo da tabela hash correspondente a esta chave,
   apos inserir, a funcao deve retornar true
   Soh nao serah possivel inserir caso o 'tamanhoDaTabela' valha 0 (zero) isto
   ocorrera apenas se o usuario solicitou a criacao de uma tabela de tamanho
   zero ou se a tabela em questao jah foi liberada pela funcao 
   liberarMemoriaTabelaHash. Nestes casos, a funcao inserirElemento deve
   retornar false.
*/
bool inserirElemento(HASH *h, TCHAVE ch){
     // completar
     return false;
}

/* Esta funcao exclui uma ocorrencia da chave ch na tabela hash apontada por h.
   Neste EP, excluir uma ocorrencia da chave significa: se nao houver um
   registro para esta chave nao faca nada e retorne false;
   se jah existir um registro para esta chave entao devera diminuir uma unidade
   no campo do contador desse registro. Se o contador virar zero entao deverah
   excuir esse registro da lista ligada a que ele pertence, liberar sua memoria
   e fazer os ajustes necessarios na lista ligada ou no tabela hash a qual este
   registro pertencia. Por fim, retornar true caso o registro existe/existisse.
*/
bool excluirElemento(HASH *h, TCHAVE ch){
     // completar
    return false; 
}

/* Esta funcao retorna a quantidade de elementos que possuem a chave ch, esta
   quantidade eh armazenada no campo contador do registro cuja chave vale ch.
   Se nao existir registro com chave igual a ch entao esta funcao deve
   retornar 0 (zero).
*/
int quantidadeDeElementos(HASH *h, TCHAVE ch){
     // completar
    return 0;
}

/* Esta funcao retorna o endereco do registro cuja chave vale ch.
   Se nao existir registro com chave igual a ch entao esta funcao deve
   retornar NULL.
*/
Pont retornaEnderecoDoRegistro(HASH *h, TCHAVE ch){
     // completar
     return NULL;     
}
