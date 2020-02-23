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

class Estatistica
{
     double[] coleçãoDeValores;         //O arranjo de valores digitado pelo usuário
     int quantidadeDeItens;             //A quantidade de valores armazenada na coleção, equivale ao comprimento do arranjo 'coleçãoDeValores'

     void carregaValores(double[] valores)
     {
          quantidadeDeItens = valores.length;               //passa o comprimento do arranjo criado pelo usuário 
          coleçãoDeValores = valores;                       //inicializa a coleçãoDeValores com os valores digitados pelo usuário
          /*
           * coleçãoDeValores = new double[n];
           * for (int i = 0; i < n; i++)
           * {
           *    colecao[i] = valores[i];
           * }
           */
     }
     
     
     double media()    //média aritmética: soma todos os valores e divide pela quantidade de itens
     {
          double soma = 0;
          for (int i = 0; i < quantidadeDeItens; i++) //o laço soma todos os números contidos na 'coleçãoDeValores'
          {
               soma = soma + coleçãoDeValores[i];
          }
          return soma/quantidadeDeItens;
     }

     double moda()    //Método exibe uma das modas encontrada, conforme orientação do professor.
     {
          double moda = 0.0;                  //Inicializa a moda que será calculada
          int contadorDeIguais = 0;           //Armazena a quantidade de valores iguais em cada passagem 
          int maiorNumeroDeIguais = 0;        //Guarda a maior quantidade de valores iguais encontrada 
          //O laço abaixo varre o arranjo 'coleçãoDeValores' comparando um valor com os demais
          for (int i = 0; i < quantidadeDeItens; i++)
          {
               for (int j = 0; j < quantidadeDeItens; j++)
               {
                    if (coleçãoDeValores[i] == coleçãoDeValores[j])  //Testa se os valores contidos na posição i e j da 'coleçãoDeValores' são iguais
                         contadorDeIguais++;                         //Atualiza a contagem dos valores iguais encontrados
               }
               if (contadorDeIguais >= maiorNumeroDeIguais)  //Caso a contagem seja a maior encontrada até o momento, armazena a respectiva moda.
               {
                    moda = coleçãoDeValores[i]; 
                    maiorNumeroDeIguais = contadorDeIguais;  //Atualiza a maior quantidade encontrada.
               }
               contadorDeIguais = 0;                         //Reseta o contador para a próxima iteração.
          }
          return moda;
     }
     
     double amplitude()    //diferença entre o maior e o menor valores
     {
          double menor = coleçãoDeValores[0];   //inicializa o menor valor arbitrariamente com o primeiro valor da coleção
          double maior = coleçãoDeValores[0];   //inicializa o maior valor arbitrariamente com o primeiro valor da coleção
          //o laço abaixo verifica sucessivamente se um valor é menor/maior que o anterior.
          for (int i = 0; i < quantidadeDeItens; i++)
          {
               if (coleçãoDeValores[i] < menor)
                    menor = coleçãoDeValores[i];   //se o valor encontrado é menor que o anterior, armazena.
               if (coleçãoDeValores[i] > maior)
                    maior = coleçãoDeValores[i];   //se o valor encontrado é maior que o anterior, armazena.
          }
          double amplitude = maior - menor;        //efetua o cálculo da amplitude e atribui o resultado para a variável amplitude
          return amplitude;
     }
     
     double desvioPadrao()           //raiz quadrada da média aritmética dos quadrados dos desvios em relação à média
     {
          double somatorio = 0;         //a soma dos quadrados dos desvios
          double media = media();       //chama o método que calcula a média da coleçãoDeValores
          //o laço abaixo calcula a soma dos quadrados dos desvios, elemento por elemento.
          for (int i = 0; i < quantidadeDeItens; i++)
          {
               somatorio = somatorio + (coleçãoDeValores[i]-media)*(coleçãoDeValores[i]-media);  //o desvio é definido por: coleçãoDeValores[i]-media
          }
          double desvioPadrao = Math.sqrt(somatorio/quantidadeDeItens);  //através da chamada Math.sqrt efetua a raiz quadrada da média aritmética dos quadrados dos desvios
          return desvioPadrao;
     }
}