class Doze {
  public static void Frequencia (int [] lancamentos) {
    int [] resultados = new int [6];
    for (int i = 0; i<resultados.length; i++) {
      int contador = 0;
      for (int j = 0; j<lancamentos.length; j++) {
        if (lancamentos [j] == i+1) {
          contador ++;
        }
      }
      resultados [i] = contador;
    }
    for (int c : resultados) System.out.print (c + " ");
  }
  public static void main (String [] args){
    int [] lancamentos = {1,6,1,2,3,1,2,3};
    Frequencia (lancamentos);
  }
}