class Busca {

  
  int buscaBinaria(int [] vetor, int valor) {
    int esq = 0,
      dir = vetor.length - 1,
      meio;
    
    while (esq <= dir) {
      meio = (esq + dir)/2;
      if(valor > vetor[meio])
        esq = meio + 1;
      else if(valor < vetor[meio])
        dir = meio - 1;
      else 
        return meio; // retorna a posicao em que se encontra o valor no vetor
    }
    return -1; // retorna -1 se o valor nao for encontrado
  }
  
  int buscaBinaria(String [] vetor, String valor) {
    int esq = 0,
      dir = vetor.length - 1,
      meio;
    
    while (esq <= dir) {
      meio = (esq + dir)/2;
      int comp = valor.compareTo(vetor[meio]);
      if(comp > 0)
        esq = meio + 1;
      else if(comp < 0)
        dir = meio - 1;
      else 
        return meio; // retorna a posicao em que se encontra o valor no vetor
    }
    return -1; // retorna -1 se o valor nao for encontrado
  }
  
  int buscaSequencial(int [] vetor, int valor) {
    int pos = -1;
    
    for (int i = 0; pos == -1 && i < vetor.length; i++) {
      if (vetor[i] == valor) {
        pos = i;
      }
    }
    return pos;
  }
}