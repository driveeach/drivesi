class Seis {
  public static void Ponderada (double [] num, double [] peso) {
    double som1 = 0;
    double som2 = 0;
    double media = 0;
    for (int i = 0; i<num.length; i++) {
     som1 += num[i] * peso[i]; 
    }
    for (int i = 0; i<num.length; i++) {
     som2 += peso[i];
    }
    media = som1 / som2;
    System.out.println (media);
  }
  public static void main (String [] args) {
    double [] num = {1,2,3};
    double [] peso = {1,2,3};
    Ponderada (num,peso);
  }
}