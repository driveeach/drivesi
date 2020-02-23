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
    public Cor getCor (String Msg) {
		System.out.println (Msg);
		return super.getCor();
	}
	/* rotinas de GA */
    public double calculaArea () {
      return Math.abs(((xa*xb+ya*yc+xb*yc)-(yb*xc+xa*yc+ya*xb))/2);
	}
    public boolean ehDegenerado () {
     if (calculaArea()>EPSILON) return false;
	 return true;
    }
}

