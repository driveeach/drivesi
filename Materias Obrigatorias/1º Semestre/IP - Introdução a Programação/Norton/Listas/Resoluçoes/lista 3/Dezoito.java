class Dezoito {
	static int inv = 0;
	static int algarismo = 1;
	
		// calcula o numero de digitos
	static void Digitos (int num) {
		int teste = num;
		while (teste >= 10) {
			teste = teste / 10;
			algarismo++;
		}
	}
	
	// ve se um numero é palindromo ou não
	static boolean inverso (int num){
		Digitos (num);
		int [] dig = new int [algarismo];
		for (int i = 0; i < algarismo; i++) {
			dig [i] = (int) (num / Math.pow (10,algarismo-i-1)); 
		}
		int vezes = 10;
		for (int i = algarismo - 1; i >=1; i--) {
			while (dig[i] >= 10) {
			dig[i] -= dig[i-1] * vezes ;
			}
		}
		for (int i = algarismo - 1; i>=1; i--){
			inv += dig [i] * (Math.pow(10,i));
		}
		inv += dig[0];

		if (num==inv) return true;
		else return false;
	}
	
	public static void main (String[] args) {
		System.out.println (inverso(123454321));
	}
}


