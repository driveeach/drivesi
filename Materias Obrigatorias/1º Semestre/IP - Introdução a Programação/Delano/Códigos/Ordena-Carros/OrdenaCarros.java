class OrdenaCarros {
  
  void imprime(Carro[] carros) {
    for (int i = 0; i < carros.length; i++) {
      System.out.println(carros[i].getMarca() + "\t\t" + carros[i].getAno());
    }
  }
  
  void selecaoDireta(Carro[] carros) {
    int i, j, indiceDoMinimo;
    int fim = carros.length;
    Carro temp;
    
    for (i = 0; i < fim - 1; i++) {
        
      // Inicialmente o menor elemento ja visto eh o i-esimo elemento
      
      indiceDoMinimo = i;
      for(j=i+1; j < fim; j++) {
        if(carros[j].compareMarca(carros[indiceDoMinimo]) < 0) {
          indiceDoMinimo = j;
        }
      }
      
      // Coloca o menor elemento no inicio do sub-vetor atual. Para isso, 
      // troca de lugar os elementos nos indices e e indiceDoMinimo.
      
      temp = carros[i];
      carros[i] = carros[indiceDoMinimo];
      carros[indiceDoMinimo] =temp;
    }
  }
  
  void insercaoDireta(Carro[] carros) {
    int i, j;
    int fim = carros.length;
    Carro carroAInserir;
    
    // Cada passo considera que o vetor a esquerda de i esta ordenado
    
    for(i=1; i < fim; i++) {
      // Tenta inserir mais um numero na porcao inicial do vetor que
      // ja esta ordenada empurrando para direita todos os elementos
      // maiores do que numeroAInserir
      
      carroAInserir = carros[i];
      j=i;
      
      while((j>0) && (carros[j-1].compareAno(carroAInserir) > 0)){
        carros[j] = carros[j-1];
        j--;
      }
      carros[j] = carroAInserir;
    }
  }
  
  public static void main (String[] args) {
    Carro[] carros = new Carro[3];
    carros[0] = new Carro();
    carros[0].setMarca("Ferrari");
    carros[0].setAno(2008);
    carros[1] = new Carro();
    carros[1].setMarca("Porsche");
    carros[1].setAno(2007);
    carros[2] = new Carro();
    carros[2].setMarca("Honda");
    carros[2].setAno(2004);
    
    OrdenaCarros oc = new OrdenaCarros();
    System.out.println("Antes de ordenar");
    oc.imprime(carros);
    oc.selecaoDireta(carros);
    System.out.println("Ordenado (critério) marca");
    oc.imprime(carros);
    oc.insercaoDireta(carros);
    System.out.println("Ordenado (critério) ano");
    oc.imprime(carros);
  }
}