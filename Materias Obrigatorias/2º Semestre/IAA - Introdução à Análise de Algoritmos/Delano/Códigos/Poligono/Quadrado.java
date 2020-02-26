/**
 * 
 * @author Delano Medeiros Beder
 * 
 * Subclasse de Ret�ngulo/Pol�gono que representa quadrados.
 * 
 */
public class Quadrado extends Retangulo {

 /**
  * Construtor da classe Quadrado.
  */
 public Quadrado(double[] pLados) {
  super(pLados);
  /* invoca o construtor da classe Pai passando pLados como par�metros. */
 }

 /**
  * 4 lados (a,b,c,d) formam um quadrado se os 4 lados sao iguais.
  * 
  * Esse metodo retorna true se os tamanhos dos lados compoem um quadrado
  * valido e false, caso contrario.
  * 
  */
 public boolean checkLados() {

  boolean isQuadrado;
  double lado;

  isQuadrado = (this.getNumLados() == 4);
  if (isQuadrado) {
   lado = this.getLado(1);

   for (int i = 2; i <= 4 && isQuadrado; i++) {
    if (this.getLado(i) != lado) {
     isQuadrado = false;
    }
   }
  }

  if (!isQuadrado) {
   System.out.println("Nao eh Quadrado");
  }
  return isQuadrado;
 }

 /**
  * Retorna a Area do Quadrado
  */
 public double getArea() {
  /*
   * Area = lado * lado
   */
  double lado = this.getLado(1);
  return lado * lado;
 }

}
