class Treze {
  public static double Resultado (double x, double [] a) {
    double resultado = 0;
    for (int i = 0; i < a.length; i++) {
     resultado += a[i] * (Math.pow (x,i)); 
    }
    return resultado;
  }
  public static void main (String [] args) {
   double x = 2;
   double [] a = {1,2,3};
   System.out.println (Resultado (x, a));
  }
}