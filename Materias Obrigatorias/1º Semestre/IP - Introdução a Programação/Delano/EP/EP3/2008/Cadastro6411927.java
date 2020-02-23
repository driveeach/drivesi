/*********************************************************************/
/** ACH 2001 - Introdução à Ciência da Computação I                 **/
/** EACH-USP - Primeiro Semestre de 2008                            **/
/** Turma 02 Matutino - Prof. Delano Medeiros Beder                 **/
/**                                                                 **/
/** Terceiro Exercício-Programa                                     **/
/** Arquivo: Cadastro6411927.java                                   **/
/**                                                                 **/
/** Aluno Murilo Galvão Honório            NºUsp: 6411927           **/
/**                                                                 **/
/** Data da entrega: 23/06/2008                                     **/
/*********************************************************************/


class Cliente
{
     //atributos que armazenam respectivamente o nome e o telefone do cliente
     String nome;
     String telefone;
     
     //construtor do objeto Cliente
     Cliente(String nome, String telefone)
     {
          this.nome = nome;
          this.telefone = telefone;
     }

     //médoto para obter o nome do cliente
     String getNome()
     {
          return this.nome;
     }
     
     //médoto para obter o telefone do cliente
     String getTelefone()
     {
          return this.telefone;
     }
}



class CadastroDeClientes
{
     Cliente[] listaDeClientes = new Cliente[10];         //inicialização da lista com o tamanho máximo definido
     int tamanhoAtual = 0;                                //utilizada para determinar o tamanho atual da lista de clientes
     boolean listaOrdenada;                               //variável que determina se a lista está ordenada
     

     
     // Adiciona o cadastro de um novo cliente, no final da lista de clientes, na posição representada pelo
     // tamanho atual (pois as posições são contadas a partir de zero).
     // Se o novo cliente for adicionado com sucesso, o método devolve 'true'. Caso contrário, devolve 'false'.
     boolean adicionaCliente(String nome, String telefone)
     {
          if (tamanhoAtual < listaDeClientes.length)  //teste que verifica se a operação não vai exceder o tamanho máximo permitido para o arranjo.
          {
               listaDeClientes[tamanhoAtual] = new Cliente(nome, telefone);
               tamanhoAtual++;
               listaOrdenada = false;  //por questões práticas, devemos pressupor que ao adicionarmos um novo cadastro a lista ficará desordenada.
               return true;
          }
          else
               return false;
     }
     
     
     
     // Realiza uma busca binária pelo cadastro de um cliente utilizando como termo o nome procurado e devolvendo o telefone.
     // Caso o método não encontre o cadastro, devolve uma String vazia.     
     String obtemTelefone(String nomeProcurado)
     {
          int esquerda = 0, direita = tamanhoAtual-1, meioAtual;
          
          // Teste para verificar a necessidade de ordenar a lista, pois isso só faz sentido para lista que não se
          // encontra ordenada e cujo tamanho é maior do que um.
          if (listaOrdenada == false && tamanhoAtual > 1) 
               ordenaLista();
          
          while (esquerda <= direita) 
          {
               meioAtual = (direita + esquerda)/ 2;
               
               //inicialização da variável que armazena o resultado da comparação alfabética entre o nome procurado e o 
               //nome contido na atual posição do meio
               int comparação = nomeProcurado.compareTo(listaDeClientes[meioAtual].getNome()); 
               
               //se a comparação resultar 0 temos o nome desejado
               if(comparação > 0)
                    esquerda = meioAtual + 1;
               else if(comparação < 0)
                    direita = meioAtual - 1;
               else 
                    return listaDeClientes[meioAtual].getTelefone(); //ao encontrar o nome faz chamada do método que devolve o telefone
          }
          return "";  //devolve string vazia
     }
     
     
     // Ordena a lista de clientes colocando seus nomes em ordem alfabética, utilizando o método da bolha fornecido em aula.
     // Imprime a mensagem "Ordenando cadastros." quando chamado.
     void ordenaLista()         
     {
          System.out.println("Ordenando cadastros.");
          
          int i, j;
          int posFinal = tamanhoAtual-1;     //a posição final equivale ao tamanho atual da lista menos 1.
          Cliente auxiliar;                  //utilizado para efetuarmos as trocas entre os elementos.
          
          for(i = posFinal; i > 0 ; i--) 
          {
               // Varre o vetor desde o início procurando erros de ordenação. Como cada passagem o maior elemento sobe até sua
               // posição correta, não há necessidade de ir até o final.
               for(j = 1; j <= i; j++) 
               {
                    // Caso em ordem alfabética o nome contido em "j-1" deva estar após o contido em "j", compareTo devolve 
                    // maior que zero e, então, a troca é realizada.
                    if (listaDeClientes[j-1].getNome().compareTo(listaDeClientes[j].getNome()) > 0) 
                    {
                         // Troca os dois de lugar
                         auxiliar = listaDeClientes[j-1];
                         listaDeClientes[j-1] = listaDeClientes[j];
                         listaDeClientes[j] = auxiliar;
                    }
               }
          }
          listaOrdenada = true;            //ao término das operações marca a lista como ordenada.
     }
     
     
     
     // Recebe um objeto do tipo CadastroDeClientes. Realiza a fusão entre a lista de cadastros do objeto 'cadastros' recebido com
     // a lista local de cadastros. Nesta fusão, cria um novo array que comporta todos os cadastros.
     void juntaCadastros(CadastroDeClientes cadastros)
     {
          // O primeiro bloco de código verifica se as listas estão ordenadas e ordena se necessário, pois
          // a fusão seqüencial necessita de listas ordenadas.
          if (this.listaOrdenada == false && this.tamanhoAtual > 1)
               this.ordenaLista();
          if (cadastros.listaOrdenada == false && cadastros.tamanhoAtual > 1)
               cadastros.ordenaLista();
          
          //variáveis utilizadas para varrer os vetores
          int posLocal = 0;  //posição na lista de clientes local
          int posReceb = 0;  //posição na lista de clientes recebida
          int posFundi = 0;  //posição na lista de clientes fundida
          
          // Utilizando o método fusão de seqüências ordenadas visto em aula, criamos uma lista fundida de 'Cliente' cujo tamanho
          // consiste da soma dos tamanhos das listas atuais.    
          Cliente[] listaFundida = new Cliente[this.tamanhoAtual + cadastros.tamanhoAtual];
          
          
          while ((posLocal < this.tamanhoAtual) && (posReceb < cadastros.tamanhoAtual))
          {
               if ((cadastros.listaDeClientes[posReceb].getNome().compareTo(this.listaDeClientes[posLocal].getNome())) <= 0) 
               {
                    listaFundida[posFundi] = cadastros.listaDeClientes[posReceb];
                    posReceb++;
               }
               else
               {
                    listaFundida[posFundi] = this.listaDeClientes[posLocal];
                    posLocal++;
               }
               posFundi++;
          }
          
          // Completamos a lista fundida com os elementos que sobraram.
          while (posLocal < this.tamanhoAtual)
          {
               listaFundida[posFundi] = this.listaDeClientes[posLocal];
               posFundi++;
               posLocal++;
          }
          while (posReceb < cadastros.tamanhoAtual)
          {
               listaFundida[posFundi] = cadastros.listaDeClientes[posReceb];
               posFundi++;
               posReceb++;
          }
          
          // Por fim, copiamos o conteúdo da lista fundida para a lista de clientes local e atualizamos o tamanho atual da mesma.
          this.listaDeClientes = listaFundida;
          tamanhoAtual = listaDeClientes.length;
     }
     
}