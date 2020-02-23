/*********************************************************************/
/** ACH 2001 - Introdução à Ciência da Computação I                 **/
/** EACH-USP - Primeiro Semestre de 2008                            **/
/** Turma Matutino - Delano Medeiros Beder                          **/
/**                                                                 **/
/** Segundo Exercício-Programa                                      **/
/** Arquivo: 6411927.zip                                            **/
/**                                                                 **/
/** Murilo Galvão Honório - USP 6411927                             **/
/**                                                                 **/
/** 02/06/2008                                                      **/
/*********************************************************************/

class Rotas
{
     int cidadeIntermediaria;              //armazena o índice da cidade utilizada como escala
     int cidadeA;                          //armazena o índice da cidade da partida
     int cidadeB;                          //armazena o índice da cidade de destino

     /* As duas variáveis abaixo inicializam respectivamente um arranjo do tipo String com o nome das capitais
      * e uma matriz que reproduz a tabela de Distâncias aéreas fornecida pelo exercício. 
      */
     String[] cidades = {"Aracaju", "Fortaleza", "João Pessoa", "Maceió", "Natal", "Recife", "Salvador", "São Luís", "Teresina"};
     int[][] distanciasAereas = new int[][]{{0, 812, 418, 210, 550, 398, 267, 1218, 1272}, 
                                      {812, 0, 562, 730, 444, 640, 1018, 640, 432},
                                      {418, 562, 0, 284, 144, 110, 758, 1208, 987},
                                      {210, 730, 284, 0, 423, 191, 464, 1220, 1126},
                                      {550, 444, 144, 423, 0, 252, 852, 1064, 843},
                                      {398, 640, 110, 191, 252, 0, 654, 1197, 935},
                                      {267, 1018, 758, 464, 852, 654, 0, 1319, 1000},
                                      {1218, 640, 1208, 1220, 1064, 1197, 1319, 0, 320},
                                      {1272, 432, 987, 1126, 843, 935, 1000, 320, 0}};
     
     /* Método que verifica as cidades digitadas pelo usuário, atribuindo um valor de índice correspondente
      * para cada uma delas. Caso alguma das duas cidades não conste no arranjo 'cidades[]', atribui o valor -1,
      * que será utilizado nos testes lógicos do método 'calculaViagem' para exibir as mensagens pertinentes.
      */
     void encontrarIndice(String x, String y)
     {
          cidadeA = -1;   //atribui índice para a cidade de partida, para o caso de não haver correspondência em 'cidades'
          cidadeB = -1;   //atribui índice para a cidade de destino, para o caso de não haver correspondência em 'cidades'
          
          /* O laço abaixo varre o arranjo 'cidades' em busca das cidades digitadas pelo usuário,
           * atribuindo o valor do índice caso encontrem. Foi utilizado 'equalsIgnoreCase' para 
           * permitir uma leitura mais abrangente, com letras maiúsculas ou minúsculas.
           */
          for (int i = 0; i < cidades.length; i++)
          {
               if (x.equalsIgnoreCase(cidades[i]))
                    cidadeA = i; 
               if (y.equalsIgnoreCase(cidades[i]))
                    cidadeB = i;
          }
     }
     
     
     /* Este método lê duas cidades digitadas pelo usuário, então chama o método 'encontrarIndice', explicado acima.
      * Se os índices da partida e destino (cidadeA, cidadeB) forem iguais, exibe a mensagem apropriada.
      * O segundo teste verifica a existência das cidades digitadas em 'cidades[]', pois o método 'encontrarIndice'
      * atribui '-1' caso não as encontre. Sendo assim, não será possível calcular a rota e serão exibidas as mensagens
      * pertinentes. Por fim, se as cidades digitadas passarem por todos os testes a rota adequada será calculada.
      */
     void calculaViagem(String x, String y)
     {
          encontrarIndice(x,y);              // chamada do método que efetua a verificação dos nomes das cidades digitadas pelo usuário.
          if (cidadeA == cidadeB)            // compara se a mesma cidade foi digitada duas vezes
               System.out.println("A mesma cidade foi digitada como partida e destino, não há rota aplicável.");
          else if ((cidadeA < 0) || (cidadeB < 0))      //caso o método 'encontrarIndice' não consiga determinar alguma das cidades, a condição será satisfeita.
          {
               System.out.println("Não é possível determinar a menor rota entre " + x + " e " + y + ".");
               System.out.println("Favor verificar a acentuação ou se as cidades digitadas são capitais da região Nordeste.");
          }
          
          /* Os índices são utilizados para a realização do seguinte teste:
           * Ao apontar para a posição na matriz 'distanciasAereas' referente aos índices cidadeA e cidadeB,
           * se a distância é menor que 400 Km, exibe a mensagem apropriada. Caso contrário, calcula qual a
           * rota mais adequada.
           */
          else
          {
               if (distanciasAereas[cidadeA][cidadeB] < 400)  // conforme determinado no exercício, se a distância for menor que 400 Km o vôo não terá escalas.
               {
                    System.out.println("A menor rota é " + cidades[cidadeA] + "/" + cidades[cidadeB] + " com " + distanciasAereas[cidadeA][cidadeB] + "Km.");
               }
               
               /* Sabendo-se que a distância é maior que 400 Km, testa todas as combinações de viagens com escala
                * possíveis (mediante cidadeA -> escala -> cidadeB), determina qual a menor e imprime a rota.
                */
               else
               {
                    /* As variáveis são inicializadas com valores arbitrários maiores que a distância entre duas cidades quaisquer,
                     * para que a expressão 'if (distanciaComEscala < menorDistancia)' seja válida desde a primeira passagem.
                     */
                    int distanciaComEscala = 10000;                                // a soma da distância entre as três cidades (A até a escala + escala até B)
                    int menorDistancia = 10000;                                    // a menor distância com escala encontrada até o momento
                    for (int escala = 0; escala < distanciasAereas[cidadeA].length; escala++) //todas as cidades de escala possíveis serão testadas. 
                    {
                         if ((distanciasAereas[cidadeA][escala] != 0) && (distanciasAereas[escala][cidadeB] != 0))   // Distância zero trata-se da mesma cidade, portanto desconsideramos.
                         {
                              distanciaComEscala = distanciasAereas[cidadeA][escala] + distanciasAereas[escala][cidadeB];  //somamos as distâncias
                              if (distanciaComEscala < menorDistancia)             //teste para verificar se esta é a distância mais curta
                              {
                                   menorDistancia = distanciaComEscala;            //armazena a distância calculada previamente
                                   cidadeIntermediaria = escala;                   //guarda o índice da cidade utilizada como escala
                              }
                         }
                    }
                    System.out.println("A menor rota é " + cidades[cidadeA] + "/" + cidades[cidadeIntermediaria] + "/" + cidades[cidadeB] + " com " + menorDistancia + "Km.");
               }
          }
     }
}