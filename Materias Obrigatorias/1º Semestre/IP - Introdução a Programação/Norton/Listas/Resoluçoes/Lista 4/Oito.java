class l4ex8 {
	public static void main (String [] args) {
		double [] va = {0,1,2,3,4,5};
		double [] vb = {4,5,6,7,8};
		double [] vc = new double [va.length + vb.length];

		for (int i = 0; i < va.length; i++){
			if (va [i] == 0){
				System.out.print("0.0 ");
			}
		}

		for (int i = 0; i < vb.length; i++){
			if (vb [i] == 0){
				System.out.print("0.0 ");
			}
		}


		for (int i = 0; i < va.length; i++){
			vc [i] = va [i];
			for (int j = 0; j < vb.length; j++) {
				if (va [i] == vb [j]) {
					vb [j] = 0;
				}
				vc [va.length + j] = vb[j];
			}
		}
		
		for (double c : vc) {
			if (c != 0){
				System.out.print (c + " ");
			}
		}
	}
}
