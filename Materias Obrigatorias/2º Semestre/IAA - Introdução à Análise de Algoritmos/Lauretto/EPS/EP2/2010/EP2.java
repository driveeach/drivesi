import java.util.*;
import java.io.*;

// Nomeie a classe com seu numero USP
class ep1234567 {
  
  /*************************
   * Metodos 
   *************************/ 
  
  /*******************************************************
   * Dado um vetor de inteiros, esta rotina realiza a ordenacao, 
   * pelo metodo Counting Sort, dos elementos do vetor, 
   * pelo k-esimo digito:
   *  k=1: unidades; k=2: dezenas; k=3: centenas, ect
   * Note que o metodo eh void, por isso o resultado deve ser devolvido 
   * dentro do mesmo vetor de entrada
   *******************************************************/ 
  public static void CountingSortDigito(int[] vetor, int k) {

    // Ideia:
    // Crie um novo vetor contendo o k-esimo digito de todos os numeros
    // Use o counting sort para ordenar o novo vetor, mantendo o sincronismo
    // com o vetor dos numeros originais
    
    
    // Seja X for o vetor dos elementos originais ordenados pelo k-esimo digito.
    // Entao o trecho abaixo copia os elementos para o vetor original
    for (int j=0; j<n; j++) 
      vetor[j] = X[j];
  }

  /*******************************************************
   * Dado um vetor de inteiros, esta rotina realiza a ordenacao, pelo metodo Counting Sort,
   * dos elementos do vetor, de acordo com o i-esimo digito (1=unidades, 2=dezenas, 3=centenas, ect)
   * Note que o metodo eh void, por isso o resultado deve ser devolvido 
   * dentro do mesmo vetor de entrada
   *******************************************************/ 
  public static void RadixSort(int[] vetor) {
    
    // Ideia:
    // Encontre o numero de digitos, d, do maior elemento
    // Use o counting sort sobre o vetor d vezes, do menor para o maior digito 

    int maxelem = vetor[0];
    for (int j=1; j<n; j++)
      if (vetor[j]>maxelem) maxelem=vetor[j];
    
    int ndig = (int) Math.ceil(Math.log(maxelem+1)/Math.log(10));
    
    for (int i=1; i<=ndig; i++)
      CountingSortDigito(vetor, i);
    
  }
  
  /* *********************
   *  Leitura de um arquivo texto contendo numeros inteiros 
   * (um por linha); devolve o conteudo do arquivo em um vetor 
   * *********************/
  public static int[] ReadFile(String filename) throws IOException {

    Reader r = new BufferedReader(new FileReader(filename));
    StreamTokenizer stok = new StreamTokenizer(r);
    stok.parseNumbers();

    int qtelem = 0;
    stok.nextToken();
    while (stok.ttype != StreamTokenizer.TT_EOF) {
      if (stok.ttype == StreamTokenizer.TT_NUMBER)
        qtelem++;
      stok.nextToken();
    }
    r.close();

    int[] vetor = new int[qtelem];

    r = new BufferedReader(new FileReader(filename));
    stok = new StreamTokenizer(r);
    stok.parseNumbers();

    qtelem = 0;
    stok.nextToken();
    while (stok.ttype != StreamTokenizer.TT_EOF) {
      if (stok.ttype == StreamTokenizer.TT_NUMBER) {
        vetor[qtelem] = (int) stok.nval;
        qtelem++;
        stok.nextToken();
      }
    }
    r.close();

    return(vetor);
  }


  /* *********************
   *  Escreve os elementos do vetor de inteiros no arquivo texto especificado
   *  (um elemento por linha do arquivo)
   * *********************/
  public static void WriteFile(int[] vetor, String filename) throws IOException {
  
     PrintWriter out = new PrintWriter(new FileWriter(filename));

     for (int i=0;i<vetor.length;i++) 
       out.format("%10d\n", vetor[i]);
     out.close();
  }  

  public static void main(String[] args) throws IOException {

    int qtparam = args.length;
    if (qtparam>=2) {
      int[] vetor = ReadFile(args[0]);
      
      RadixSort(vetor);
      WriteFile(vetor, args[1]);
    }
  }  
}

