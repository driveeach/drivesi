class Dezesseis {
	public static void Soma (int [] um, int [] dois) {
		int [] resp = new int [um.length+1];
		for (int i = um.length - 1; i >= 0; i--) {
			resp [i+1] = um[i] + dois[i];
		}
		for (int i = (resp.length - 1); i > 0; i--) {
			if (resp [i] >= 10) {
			resp [i] -= 10;
			resp [i-1] += 1;
			}
		}	
		for (int c : resp)System.out.println (c);
	}
	public static void main (String [] args) {
		int [] um = {0,1,2,3,5};
		int [] dois = {1,2,3,4,5};
		Soma (um,dois);
	}
}