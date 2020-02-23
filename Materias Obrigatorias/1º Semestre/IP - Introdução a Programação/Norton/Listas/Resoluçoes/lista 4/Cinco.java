class Cinco {
  public static void Juizes (double [] notas){
    double maior = 0;
    double menor = notas[0];
    double media = 0;
    for (int i = 0; i<notas.length; i++) {
      if (notas[i]>maior) {
        maior = notas [i];
      }
    }
    for (int i = 0; i<notas.length; i++) {
      if (notas[i]<menor) {
        menor = notas[i];
      }
    }
    for (int i = 0; i<notas.length; i++) {
      media += notas[i];
     }
    media = (media - (menor + maior)) / (notas.length - 2);
    System.out.println (media);
  }
  public static void main (String [] args) {
    double [] notas = {1,2,3,4,5,6};
    Juizes(notas); 
  }
}