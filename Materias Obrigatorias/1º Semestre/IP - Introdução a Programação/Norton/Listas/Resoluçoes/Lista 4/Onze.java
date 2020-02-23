class Onze {
	public static boolean Decrescente (double [] num) {
		boolean [] confirma = new boolean [num.length];
		for (int i = 1; i < num.length; i++) {
			if (num [i-1] > num [i]) {
				confirma [i-1] = true;
			}
		}
		for (boolean c : confirma) {
			if (c != true) return false;
		}
	}
	public static void main (String [] args) {
		double [] num = {1,2,3,4,5,6};
		Decrescente (num);
	}
}