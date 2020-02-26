/**
 * 
 * @author Delano Medeiros Beder
 * 
 * Subclasse de Pol�gono que representa Tri�ngulos.
 */
public class Triangulo extends Poligono {

 /**
  * Construtor da classe Triangulo.
  */
 public Triangulo(double[] pLados) {
  super(pLados);
  /* invoca o construtor da classe Pai passando pLados como par�metros. */
 }

 /**
  * 3 lados(a,b,c) formam um triangulo, se a soma de dois lados eh maior que o
  * terceiro lado. Isto eh: a + b > c & a + c > b & b + c > a
  * 
  * Esse m�todo retorna true se os tamanhos dos lados compoem um retangulo
  * valido e false, caso contrario.
  * 
  */
 public boolean checkLados() {
  boolean isTriangulo;
  double a;
  double b;
  double c;

  isTriangulo = (this.getNumLados() == 3);

  if (isTriangulo) {
   a = this.getLado(1);
   b = this.getLado(2);
   c = this.getLado(3);

   isTriangulo = (a + b > c) && (a + c > b) && (b + c > a);
  }
  if (!isTriangulo) {
   System.out.println("Nao eh Triangulo");
  }
  return isTriangulo;
 }

 /**
  * Retorna a area do Triangulo
  */
 public double getArea() {
  double p = this.getPerimetro() / 2;
  double a = this.getLado(1);
  double b = this.getLado(2);
  double c = this.getLado(3);

  double area = (double) Math.sqrt(p * (p - a) * (p - b) * (p - c));
  return area;
 }
}
