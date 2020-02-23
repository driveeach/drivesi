class Oito {
	public static void main (String [] args) {
		double [] va = {1,2,3,4,5};
		double [] vb = {4,5,6,7,8};
		double [] vc = new double [va.length + vb.length];
		int aux = 0;
    
		for (int i = 0; i < va.length; i++) {
			for (int j = 0; j < vb.length; j++) {
				if (va[i] == vb [j]) {
					vb [j] = 0;
				}
			}
		}
		
		while (aux < va.length) {
			vc[aux] = va[aux];
			aux++;
		}
		int aux2 = 0;
		while (aux2 < vb.length) {
			vc[aux] = vb [aux2];
			aux++;
			aux2++;
		}
		for (double c : vc) {
			if (c != 0) {
				System.out.print (c + " ");
			}
		}
	} 
}