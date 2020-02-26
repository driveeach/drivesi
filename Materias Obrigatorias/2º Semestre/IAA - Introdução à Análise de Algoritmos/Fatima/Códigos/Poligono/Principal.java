import java.util.Scanner;

/**
 * 
 * @author Delano Medeiros Beder
 * 
 * Classe que "representa" o programa principal. Ela que cria e invoca metodos
 * nas demais classes dessa aplicacao.
 * 
 */
public class Principal {

 public static void main(String[] args) {

  Poligono p = null;
  int option;
  Util util = new Util();

  Scanner scanner = new Scanner(System.in);

  do {
   option = util.getOption(scanner);

   switch (option) {
   case Util.QUADRADO: {
    do {
     double[] sides = util.getSides(scanner, 4);
     p = new Quadrado(sides);
    } while (!p.checkLados());
    break;
   }
   case Util.RETANGULO: {
    do {
     double[] sides = util.getSides(scanner, 4);
     p = new Retangulo(sides);
    } while (!p.checkLados());
    break;
   }
   case Util.TRIANGULO: {
    do {
     double[] sides = util.getSides(scanner, 3);
     p = new Triangulo(sides);
    } while (!p.checkLados());
    break;
   }
   }
   if (option != Util.EXIT) {
    System.out.println("Area = " + p.getArea());
   }
  } while (option != Util.EXIT);

 }
}
