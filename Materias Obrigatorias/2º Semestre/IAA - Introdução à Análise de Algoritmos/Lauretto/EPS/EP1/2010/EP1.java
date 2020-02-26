import java.util.*;

class Bafometro {

  /*************************
   * Constantes 
   *************************/ 
  // Constantes indicando o estado de cada esquina
  static final int Bar = -1;       // Esquina contem o Bar
  static final int Casa = -2;      // Esquina contem a casa do Jorge
  static final int Policia = -10;  // Esquina contem um policial
  static final int Livre = 0;      // Esquina livre (sem policial e nao foi visitada)
  
  // Numero de cenarios em cada simulaccao
  static final int QtCenarios = 1000;

  /*************************
   * Atributos
   *************************/ 

  //Movimentos possiveis: abaixo, direita, acima, esquerda 
  final int[] dx = { 1, 0, -1, 0 };    // dx: deslocamento nas linhas da matriz
  final int[] dy = { 0, 1,  0, -1 };   // dy: deslocamento nas colunas da matriz

  int qtlin, qtcol;      // Numero de linhas e colunas na matriz de esquinas
  int xBar, yBar;        // Coordenadas do bar
  int xCasa, yCasa;      // Coordenadas da casa do Jorge

  int[][] table; // A matriz table representa as esquinas do bairro. Cada posicao contem um inteiro
                 // representando o estado da esquina. Alem dos estados basicos (vide constantes acima),
                 // toda esquina visitada pelo Jorge poderah conter o numero do passo dado pelo Jorge 

  Random random; // serie de numeros aleatorios
  
  /*************************
   * Metodos 
   *************************/ 
  
  /*
   * Metodo construtor da classe Bafometro: inicializa o tamanho da matriz, coordenadas 
   * do bar, coordenadas da casa do Jorge e semente da serie do gerador de numeros semi-aleatorios.
   */ 
  public Bafometro(int qtlin, int qtcol, int xBar, int yBar, 
                   int xCasa, int yCasa, long semente) {
    this.qtlin = qtlin; this.qtcol = qtcol;
    this.xBar = xBar; this.yBar = yBar;
    this.xCasa = xCasa; this.yCasa = yCasa;
    
    this.table = new int[qtlin][qtcol];
    for (int x=0; x<qtlin; x++)
      for (int y=0; y<qtcol; y++) this.table[x][y] = Livre;
    this.table[xBar][yBar] = Bar;
    this.table[xCasa][yCasa] = Casa;

    // Se a semente do gerador eh igual a 0, o gerador eh iniciado com 
    if (semente==0) 
      random = new Random();
    else
      random = new Random(semente);
  }

  
  /*
   * Funcao para distribuicao dos policiais nas esquinas
   * Cada esquina terá um policial com probabilidade ProbPolicia 
  */
  void DistribuiPolicia(double ProbPolicia) {
    // Distribui policiais nas esquinas
    for (int x=0; x<qtlin; x++) {
      for (int y=0; y<qtcol; y++) {
        if (random.nextDouble()<=ProbPolicia)
          table[x][y] = Policia; 
        else
          table[x][y] = Livre;
      }
    }
    // Recoloca na tabela de esquinas o bar e a casa do jorge
    table[xBar][yBar] = Bar;
    table[xCasa][yCasa] = Casa;
  }
  
  /*
   * Metodo EsquinaAceitavel: verifica se a esquina (x,y) eh aceitavel para Jorge visitar.
   * Condicoes: que (x,y) esteja dentro da matriz; que nao esteja ocupada pela policia;
   * que esteja livre ou corresponda aa coordenada da casa do Jorge
   */
  boolean EsquinaAceitavel(int x, int y) {
    // Escreva aqui sua implementacao para o metodo

  }


  
  /*
   * TentaMovimento: tenta percorrer o proximo quarteirao sem ser preso; se for possivel, 
   * tenta recursivamente percorrer os proximos quarteiroes ateh chegar em casa ou 
   * ateh que todos os caminhos possiveis (sem voltar para o bar) tenham sido testados.
   * Parametros: 
   *     x,y: ponto atual
   *     passo: indica o passo atual (distancia percorrida do bar ateh o ponto atual)
   * Retorno: 
   *     0 se a tentativa de chegar em casa nao for bem sucedida
   *     tamanho do caminho ateh a casa, se a tentativa for bem sucedida
   */
  int TentaMovimento(int x, int y, int passo) {
    // Escreva aqui sua implementacao para o metodo

  
  }
  

  /*
   * MostraCaminho: Verifica se o Jorge conseguirah ir do bar ateh sua casa 
   * sem ser preso. 
   * Parametros de entrada: nenhum
   * Saida: O metodo deve imprimir na tela duas informacoes:
   *    1) Se Jorge conseguir chegar em casa, deve imprimir 
   *          "Sucesso - caminho de tamanho XXX" onde XXX eh o numero de esquinas percorridas 
   *          do bar ateh a casa do Jorge
   *       Se Jorge nao conseguir chegar em casa, deve imprimir:
   *          "Fracasso"
   *    2) Conteudo da matriz representando as esquinas
   */
  public void MostraCaminho() {
    int tamcaminho = TentaMovimento(xBar, yBar, 0);
    if (tamcaminho>0) 
      System.out.println("Sucesso - caminho de tamanho " + tamcaminho); 
    else 
      System.out.println("Fracasso");

    for (int x=0; x<qtlin; x++) {
      for (int y=0; y<qtcol; y++) 
        System.out.format("%5d ", table[x][y]);
      System.out.println(); 
    }
    System.out.println();
  }
  
  /*
   * Funcao Simula_Cenarios: Dadas as coordenadas do bar e da casa do Jorge, esta funcao
   * estima a probabilidade de sucesso (Jorge ir do bar ateh sua casa sem ser preso) 
   * em funcao da probabilidade de haver um policial em cada esquina.
   * 
   * Parametro: Vetor com as probabilidades de ocorrencia de policiais
   * Resultado: matriz com k linhas (onde k eh o tamanho do vetor de probabilidades de ocorrencias
   *        de policiais) e 3 colunas:
   *        Resultado[i][0]: Probabilidade de ocorrencia de policiais no bairro
   *        Resultado[i][1]: Proporcao de tentativas bem sucedidas de chegar em casa (qtsucessos/qtcenarios)
   *        Resultado[i][2]: Comprimento medio dos caminhos bem sucedidos; 0 se nao houver caminhos bem sucedidos
   */     
  double[][] Simula_Cenarios (double[] VetProbPolicia) {
    // Escreva aqui sua implementacao para o metodo
  
  }
}

/*
 * O nome da classe abaixo deve ter o prefixo 'ep' seguido pelo seu numero USP. 
 * Salve este modulo com o mesmo nome (com extensao .java).
 */
public class ep1234567 { 

  
  /*
   * Programa Principal:
   *    A chamada do programa deve receber os seguintes parametros:
   *    qtlin, qtcol: quantidade de linhas e colunas da matriz de esquinas (inteiros positivos)
   *    xBar, yBar:   coordenadas do bar (inteiros positivos; xBar<qtlin, yBar<qtcol)
   *    xCasa, yCasa: coordenadas da casa do Jorge (inteiros positivos: xCasa<qtlin, yCasa<qtcol)
   *    semente:      semente do gerador de numeros aleatorios para distribuicao probabilistica 
   *                  da policia entre as esquinas; se o valor for 0, a escolha da semente serah
   *                  automatica. (Longo nao negativo)
   *    ProbPol_1, ProbPol_2, ... ProbPol_k: vetor contendo as probabilidades da policia estar presente em 
   *                  cada esquina. (double, 0 < ProbPol_i < 1  i=1,2,...,k)
   */
  public static void main(String[] args) {
    final int ParamMin = 8;
    int qtparam = args.length;
    if (qtparam<ParamMin)
      // Se o numero de parametros estiver incorreto, imprime mensagem de erro
      System.out.println("Parametros:  <qtlin> <qtcol> <xBar> <yBar> <xCasa> <yCasa> <semente> " 
            + "<ProbPol_1> [ <ProbPol_2> ... <ProbPol_k> ]");
    else {
      int qtlin = Integer.parseInt(args[0]);
      int qtcol = Integer.parseInt(args[1]);
      int xBar = Integer.parseInt(args[2]);
      int yBar = Integer.parseInt(args[3]);
      int xCasa = Integer.parseInt(args[4]);
      int yCasa = Integer.parseInt(args[5]);
      long semente = Long.parseLong(args[6]);
      
      // VetProbPolicia: vetor de coberturas (probabilidades de, em cada esquina,
      //   haver um policial)
      double[] VetProbPolicia = new double[qtparam-ParamMin+1];
      for (int i=ParamMin-1; i<qtparam; i++)
        VetProbPolicia[i-(ParamMin-1)] = Double.parseDouble(args[i]); 
      
      // Inicializa a estrutura da classe
      Bafometro bairro = new Bafometro(qtlin, qtcol, xBar, yBar, xCasa, yCasa, semente);

      // Se foi passada apenas uma cobertura como parametro: 
      //   - Sorteia a distribuicao da policia no bairro
      //   - Roda o algoritmo de tentativa e erro e imprime o resultado (Sucesso/Fracasso)
      //   - Imprime o conteudo da matriz de esquinas 
      // Se foram passados dois ou mais valores de cobertura como parametros:
      //   - Para cada valor de cobertura:
      //   -   Simular 1000 cenarios, onde cada cenario consiste em:
      //           sortear a distribuicao da policia no bairro
      //           rodar o algotirmo tentativa e erro 
      //           incrementar o contador de sucessos e somar o comprimento do caminho
      if (VetProbPolicia.length == 1) {
        bairro.DistribuiPolicia(VetProbPolicia[0]);        
        bairro.MostraCaminho();
      } else {
        double[][] Resultados = bairro.Simula_Cenarios(VetProbPolicia);
        System.out.println(); 
        for (int i=0; i<Resultados.length; i++) {
          for (int j=0; j<Resultados[0].length; j++)
            System.out.format("%5f ", Resultados[i][j]);
          System.out.println(); 
        }
      }    
    }
  }  
}
