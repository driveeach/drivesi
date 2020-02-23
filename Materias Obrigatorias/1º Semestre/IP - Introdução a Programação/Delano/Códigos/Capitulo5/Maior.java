/**
 * Classe que devolve o maior de 2 ou 3 numeros
 */
public class Maior {
  
  /**
   * Metodo que devolve o maior entre 2 numeros (a e b)
   * 
   * @param a - 1o numero
   * @param b - 2o numero
   */
  public int maior(int a, int b) {
    if (a > b) {
      return a;
    }
    else {
      return b;
    }
  }
  
  /**
   * Metodo que devolve o maior entre 3 numeros (a e b)
   * 
   * @param a - 1o numero
   * @param b - 2o numero
   * @param c - 3o numero
   */
  public int maior(int a, int b, int c) {
    if (a > b && a > c) {
      return a;
    }
    else if (b > c) {
      return b;
    } else {
      return c;
    }
  }
}