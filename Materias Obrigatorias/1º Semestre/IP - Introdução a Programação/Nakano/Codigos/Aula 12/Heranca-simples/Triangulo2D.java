import classe3.*;

class Triangulo2D extends Triangulo {
    private static final double EPSILON=1e-10;
    private double xa, ya;
	private double xb, yb;
	private double xc, yc;
	//private Cor C;   // epa! bagunça!!
	public void setA (double NovoXa, double NovoYa) {
		xa=NovoXa;
		ya=NovoYa;
	}
	public void setB (double NovoXb, double NovoYb) {
		xb=NovoXb;
		yb=NovoYb;
	}
	public void setC (double NovoXc, double NovoYc) {
		xc=NovoXc;
		yc=NovoYc;
	}
	public void setTudo (double NovoXa, double NovoYa, double NovoXb, double NovoYb, double NovoXc, double NovoYc, Cor NovaCor) {
		xa=NovoXa;
		ya=NovoYa;
		xb=NovoXb;
		yb=NovoYb;
		xc=NovoXc;
		yc=NovoYc;
		C=NovaCor;
	}
/* PROBLEMA: como recuperar os valores das coordenadas dos pontos?? */
/* A solução boa é construir uma classe "Ponto", construir a 
 * classe Triangulo2D usando 3 pontos e construir métodos que 
 * retornam objetos da classe Ponto. */
 /* A solução suja e simples é ter um "getter" para cada 
  * coordenada. */
	public double getXa () {
		return xa;
	}
	public double getYa () {
		return ya;
	}
	public double getXb () {
		return xb;
	}
	public double getYb () {
		return yb;
	}
	public double getXc () {
		return xc;
	}
	public double getYc () {
		return yc;
	}
}

