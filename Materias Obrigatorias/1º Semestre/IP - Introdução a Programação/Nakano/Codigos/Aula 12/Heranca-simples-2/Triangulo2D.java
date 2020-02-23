import classe3.*;

class Triangulo2D extends Triangulo {
    private static final double EPSILON=1e-10;
    private double xa, ya;
	private double xb, yb;
	private double xc, yc;
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
	/* rotinas de GA */
    private double calculaCoeficienteAngular (double xa, double ya, double xb, double yb) {
      return (yb-ya)/(xb-xa);
	}
    public boolean ehDegenerado () {
      double Ca, Cb, Cc;   // coeficientes angulares
      Ca=calculaCoeficienteAngular(xa, ya, xb, yb);
      Cb=calculaCoeficienteAngular(xa, ya, xc, yc);
      Cc=calculaCoeficienteAngular(xb, yb, xc, yc);
	  // System.out.println (Ca + " " + Cb + " " + Cc);
      if (Math.abs(Ca-Cb)<EPSILON) {
          return true;
      }
      if (Math.abs(Ca-Cc)<EPSILON) {
          return true;
      }
      if (Math.abs(Cb-Cc)<EPSILON) {
          return true;
      }
	  return false;
    }
}

