import java.util.Scanner;
class Dezessete {
  public static int potencia (int num) {
    int aux = 0;
    while (num%2 == 0) {
      num/=2;
      aux++;
      if (num == 1) return aux;
    }
    return -1;
  }
  public static void main (String [] args) {
    Scanner sc = new Scanner (System.in);
    String resposta = null;
    int num = 0;
    do {
      System.out.println ("Digite o numero");
      num = sc.nextInt();
      System.out.println ("O valor da potencia: "+potencia (num));
      System.out.println ("Quer continuar?");
      resposta = sc.next();
    }
    while (resposta.equals("sim"));
  }
}