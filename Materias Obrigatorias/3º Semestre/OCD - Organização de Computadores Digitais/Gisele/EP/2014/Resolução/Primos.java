import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Primos {
  public static void primeFactors(int numero) {
    if(numero == 0) System.out.println("Número Zero");
    if (numero == 1) System.out.println("Número Um");
    for (int i = 2; i <= numero; i++) {
      while (numero % i == 0) {
        System.out.print(i+" ");
        numero /= i;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite um número");
    int numero = sc.nextInt();
    primeFactors(numero);
  }
}