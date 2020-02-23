class Quinze {
	public static void Soma (double [] q, double [] p) {
		double [] r = new double [q.length];
		for (int i = 0; i < r.length; i++) {
			r[i] = q[i] + p[i];
		}
		for (double c : r) System.out.print (c +" ");
	}
	public static void main (String [] args) {
		double [] q = {1,2,3};
		double [] p = {1,2,3};
		Soma (q,p);
	}	
}