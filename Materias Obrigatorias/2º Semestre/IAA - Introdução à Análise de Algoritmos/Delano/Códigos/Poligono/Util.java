import java.util.Scanner;

/**
 * 
 * @author Delano Medeiros Beder
 * 
 * Classe Utilitaria responsavel pela entrada de dados pelo teclado.
 */
public class Util {

 /* Constantes utilizadas */
  
 public static final int TRIANGULO = 1;

 public static final int RETANGULO = 2;

 public static final int QUADRADO = 3;

 public static final int EXIT = 4;

 /**
  * M�todo que retorna a op��o digitada pelo usu�rio
  */
 public int getOption(Scanner scanner) {
  String line = "";
  int option = 0;
  boolean validOption = false;
  System.out.println("-----------------------------------------");
  System.out.println("1 - Area de Triangulo");
  System.out.println("2 - Area de Retangulo");
  System.out.println("3 - Area de Quadrado ");
  System.out.println("4 - Exit");
  System.out.println("-----------------------------------------");

  while (!validOption) {
    System.out.print("Entre com sua opção : ");
    option = scanner.nextInt();

   if (option >= TRIANGULO && option <= EXIT) {
    validOption = true;
   }
   if (!validOption) {
    System.out.println("Opcao Invalida. Tente novamente");
   }
  }
  return option;
 }

 /**
  * Metodo que os tamanhos dos lados do poligono digitado pelo usuario.
  */
 public double[] getSides(Scanner scanner, int number) {
  String line = "";
  boolean valid = true;
  double[] sides = new double[number];
  int i = 0;
  while (i < number) {
    System.out.print("Entre com lado " + (i + 1) + " : ");
    sides[i] = scanner.nextDouble(); // String para double
    i++;
  }

  return sides;
 }
}
