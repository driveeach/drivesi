/**
 * 
 * @author Delano Medeiros Beder
 * 
 * Subclasse de Poligono que representa Retangulos.
 */
public class Retangulo extends Poligono {

 /**
  * Construtor da classe Retangulo.
  */
 public Retangulo(double[] pLados) {
  super(pLados);
  /* invoca o construtor da classe Pai passando pLados como parametros. */
 }

 /**
  * 4 lados (a,b,c,d) formam um Ret�ngulo se a == c e b == d.
  * 
  * Esse metodo retorna true se os tamanhos dos lados compoem um retangulo
  * valido e false, caso contrario.
  * 
  */
 public boolean checkLados() {

  boolean isRetangulo;

  isRetangulo = (this.getNumLados() == 4);

  if (isRetangulo) {
   if (this.getLado(1) != this.getLado(3)) { // a == c
    isRetangulo = false;
   } else if (this.getLado(2) != this.getLado(4)) { // b == d
    isRetangulo = false;
   }
  }

  if (!isRetangulo) {
   System.out.println("Nao eh Retangulo");
  }
  return isRetangulo;
 }

 /**
  * Retorna a Area do Retangulo
  */
 public double getArea() {
  /*
   * area = base (1o lado ou 3o lado) x altura (2o lado ou 4o lado)
   */

  return this.getLado(1) * this.getLado(2);
 }
}
