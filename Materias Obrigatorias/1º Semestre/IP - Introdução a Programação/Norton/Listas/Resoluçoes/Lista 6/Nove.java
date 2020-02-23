/* Crie uma classe contendo um metodo que, dado um ponto determinado pelas suas coordenadas
x e y, reais, imprime em qual quadrante este ponto esta localizado. O primeiro
quadrante corresponde aos pontos que possuem x e y positivos, o segundo quadrante a
x positivo e y negativo e assim por diante. */
class Quadrante {
	void coordenada(double x, double y) {
		if (x>0 && y>0) System.out.println ("primeiro quadrante");
		if (x>0 && y<0) System.out.println ("segundo quadrante");
		if (x<0 && y>0) System.out.println ("quarto quadrante");
		if (x<0 && y<0) System.out.println ("terceiro quadrante");
	}
}
class Nove {
	public static void main (String [] args) {
		Quadrante a = new Quadrante ();
		a.coordenada (-1,-2);
	}
}