class Catorze{
	public static void Derivada (double [] coef) {
		double [] deriv = new double [coef.length];
		for (int i = 0; i < coef.length; i++) {
			deriv [i] = coef [i] * i;
		}
		for (int i = 1; i < deriv.length; i++) {
			if (deriv [i] != 0) System.out.print (deriv [i] + " * x^" + (i-1) + " + ");
		}
	}
	public static void main (String [] args) {
		double [] coef = {1,2,3,4,5,6,7,8};
		Derivada (coef);
	}
}