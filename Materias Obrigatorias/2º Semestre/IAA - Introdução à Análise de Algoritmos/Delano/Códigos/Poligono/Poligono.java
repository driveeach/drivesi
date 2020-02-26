/**
 * @author Delano Medeiros Beder
 * 
 * Classe Abstrata Poligono: raiz da hierarquia de Poligonos
 * 
 */
public abstract class Poligono {

 /**
  * Atributo da classe: Array de ponto flutuante (double) que representa os
  * tamanhos dos lados de um pol�gono.
  */
 private double lados[];

 /**
  * Construtor da classe Poligono. Esse construtor recebe como parametros os
  * tamanhos dos lados do pol�gono.
  * 
  * @param pLados
  *            Parametro usado para inicializar os tamanhos dos lados do
  *            poligono.
  */
 public Poligono(double pLados[]) {
  this.setLados(pLados);
 }

 // Metodos

 /**
  * Retorna o numero de lados do Poligono
  */
 public int getNumLados() {
  return this.lados.length;
 }

 /**
  * Retorna o tamanho do i-esimo lado do pol�gono. Isto e, getLado(1) retorna
  * o tamanho do primeiro lado, getLado(2) do segundo lado e assim
  * sucessivamente.
  */
 public double getLado(int i) {
  return lados[i - 1];
 }

 /**
  * Retorna os tamanhos de todos os lados do pol�gono
  */
 public double[] getLados() {
  return lados;
 }

 /**
  * Seta os tamanhos de todos os lados do pol�gono
  */
 public void setLados(double[] pLados) {
  int numLados = pLados.length;
  lados = new double[numLados];
  for (int i = 0; i < numLados; i++) {
   lados[i] = pLados[i];
  }
 }

 /**
  * Retorna o perimetro do Poligono. Isto e, a soma dos tamanhos dos lados.
  * 
  */
 public final double getPerimetro() {
  double perimetro = 0;
  for (int i = 0; i < lados.length; i++) {
   perimetro = perimetro + lados[i];
  }
  return perimetro;
 }

 /**
  * Metodo abstrato a ser implementado pelas subclasses. Retorna a area do
  * Poligono.
  */
 public abstract double getArea();

 /**
  * Metodo abstrato a ser implementado pelas subclasses. Retorna true se os
  * tamanhos dos lados compoem um poligono valido e false, caso contrario.
  * 
  */
 public abstract boolean checkLados();

 public void imprimeArea() {
  System.out.println("Area do Poligono �: " + this.getArea());
 }

}