class Quatro {
  public static int Maximo (int[] max) {
    int maior = max[1];
    int posicao = 1;
    for (int i = 0; i<max.length; i++) {
      if (max[i] > maior) {
        posicao = i;
        maior = max[i];
      }
    }
    return posicao;
  }
  public static int Minimo (int [] min) {
   int menor = min[1];
   int posicao = -1;
   for (int i = 0; i<min.length; i++) {
     if (min[i] < menor) {
      posicao = i;
      menor = min[i];
     }
   }
   return posicao;
  }
  public static void main (String [] args) {
    int [] num = {10,45,15,35,5};
    System.out.println (Maximo (num));
    System.out.println (Minimo (num));
  }
}