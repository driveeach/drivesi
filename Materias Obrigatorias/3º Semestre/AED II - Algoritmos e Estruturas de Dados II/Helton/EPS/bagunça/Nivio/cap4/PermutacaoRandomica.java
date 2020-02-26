package cap4;
import java.util.Random;
public class PermutacaoRandomica {
  public static double rand0a1 (Random rand) {
    // @{\it utilizar o tempo como semente para o m\'etodo setSeed()}@
    rand.setSeed (System.currentTimeMillis ());
    // @{\it seleciona aleatoriamente um n\'umero no intervalo [0, 1)}@
    return rand.nextDouble ();
  }
  public static void permut (Item v[], int n) {
    Random rand = new Random ();
    for (int i = n - 1; i > 0; i--) {
      int j = (int) (((double) i * rand0a1 (rand)) + 1.0);
      Item b = v[i]; v[i] = v[j]; v[j] = b;
    }
  }
  public static void permut (int v[], int n) {
    Random rand = new Random ();
    for (int i = n - 1; i > 0; i--) {
      int j = (int) (((double) i * rand0a1 (rand)) + 1.0);
      int b = v[i]; v[i] = v[j]; v[j] = b;
    }
  }
  public static void permut (char v[], int n) {
    Random rand = new Random ();
    for (int i = n - 1; i > 0; i--) {
      int j = (int) (((double) i * rand0a1 (rand)) + 1.0);
      char b = v[i]; v[i] = v[j]; v[j] = b;
    }
  }
  public static void main (String[] args) {
    int tam = 20, n =20;
    MeuItem v[] = new MeuItem[tam + 1];
    for (int i = 1; i <= n; i++) v[i] = new MeuItem (i);
    permut (v, n);
    for (int i = 1; i <= n; i++) System.out.print (v[i].toString () + " ");
    System.out.println ();
  }
}
