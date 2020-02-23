class Ordenacao {
  
  void selecaoDireta(int [] numeros) {
    int i, j, indiceDoMinimo, temp;
    int fim = numeros.length;
    
    for (i = 0; i < fim - 1; i++) {
      
      System.out.println("Estado do vetor no inicio do passo i = " + i);
      imprimeArray(numeros);
        
      // Inicialmente o menor elemento ja visto eh o i-esimo elemento
      
      indiceDoMinimo = i;
      for(j=i+1; j < fim; j++) {
        if(numeros[j] < numeros[indiceDoMinimo]) {
          indiceDoMinimo = j;
        }
      }
      
      // Coloca o menor elemento no inicio do sub-vetor atual. Para isso, 
      // troca de lugar os elementos nos indices e e indiceDoMinimo.
      
      temp = numeros[i];
      numeros[i] = numeros[indiceDoMinimo];
      numeros[indiceDoMinimo] =temp;
      
      System.out.println("Estado do vetor no fim passo i = " + i);
      imprimeArray(numeros);
      System.out.println("-----------------------------------------");
    }
  }

  void insercaoDireta(int [] numeros) {
    int i, j, numeroAInserir;
    int fim = numeros.length;
    
    // Cada passo considera que o vetor a esquerda de i esta ordenado
    
    for(i=1; i < fim; i++) {
      // Tenta inserir mais um n�mero na porcao inicial do vetor que
      // ja esta ordenada empurrando para direita todos os elementos
      // maiores do que numeroAInserir
      
      System.out.println("Estado do vetor no inicio do passo i = " + i);
      imprimeArray(numeros);
      
      numeroAInserir = numeros[i];
      j=i;
      
      while((j>0) && (numeros[j-1] > numeroAInserir)){
        numeros[j] = numeros[j-1];
        j--;
      }
      numeros[j]=numeroAInserir;
 
      System.out.println("Estado do vetor no fim passo i = " + i);
      imprimeArray(numeros);
      System.out.println("-----------------------------------------");
    }
  }

  void bolha(int [] numeros){
    int i, j, temp;
    int fim = numeros.length;
    
    for(i=fim -1; i > 0 ; i--) {
      // Varre o vetor desde o inicio procurando erros de ordenacao.
      // Como cada passagem o maior elemento sobe ate sua
      // posicao correta, nao ha necessidade de ir ate o final.
      
      System.out.println("Estado do vetor no inicio do passo i = " + i);
      imprimeArray(numeros);
      
      for(j=1; j <= i; j++) {
        // Se a ordem esta errada para o par j-1 e j
        if(numeros[j-1] > numeros[j]) {
          // Troca os dois de lugar
          temp = numeros[j-1];
          numeros[j-1] = numeros[j];
          numeros[j] = temp;
        }
      }
      
      System.out.println("Estado do vetor no fim passo i = " + i);
      imprimeArray(numeros);
      System.out.println("-----------------------------------------");
    }
  }
  
  void imprimeArray(int [] v) {
    int i;
    for(i = 0; i < v.length; ++i) {
      System.out.print(v[i] + " ");
    }
    System.out.println("");
  }
  
  int[] arrayAleatorio(int n) {
    int[] vetor = new int[n];
    java.util.Random random = new java.util.Random();
    for (int i = 0; i < n; i++) {
      vetor[i] = random.nextInt(100);
    }
    return vetor;
  }
}