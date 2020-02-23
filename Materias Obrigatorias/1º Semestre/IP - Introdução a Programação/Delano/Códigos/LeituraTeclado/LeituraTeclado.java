import java.util.Scanner;

class LeituraTeclado {
  
  Scanner sc = new Scanner(System.in);
  int a, b;
  
  public void leValores() {
    System.out.println("Entre com o valor de A");
    a = sc.nextInt();
    System.out.println("Entre com o valor de B");
    b = sc.nextInt();
  }
  
  public void menu() {
    Scanner sc = new Scanner(System.in);
    int opcao = 0;
    do {
      System.out.println("--------------------------------------");
      System.out.println("1 - Soma");
      System.out.println("2 - Subtracao");
      System.out.println("3 - Multiplicacao");
      System.out.println("4 - Divisao");
      System.out.println("5 - Exit");
      System.out.println("--------------------------------------");
      opcao = sc.nextInt();
      
      switch(opcao) {
        case 1: {
          leValores();
          System.out.println("A + B = " + (a + b));
          break;
        }
        case 2: {
          leValores();
          System.out.println("A - B = " + (a - b));
          break;
        }
        case 3: {
          leValores();
          System.out.println("A * B = " + (a * b));
          break;
        }
        case 4: {
          leValores();
          System.out.println("A / B = " + (a / b));
          break;
        }
      }
    } while (opcao != 5);
  }
  
  public static void main(String[] args) {
    new LeituraTeclado().menu();
  }
}