class Dezessete {
	static void Primo (int a) {
		double resultado = 0;
		double [] array = new double [a];
		int i = 0;
		
		for (int num = 1; num<=a; num++) { 
			resultado = a/num;
			if(a%num == 0) {
				array [i] = resultado;
				i++;
			}
		}
		
		if (array[0] == a && array[1] == 1) System.out.println ("O numero " +a+ " e primo!");
		else System.out.println ("O numero " +a+ " nao e primo!");
	}
	
	public static void main (String [] args) {
		Primo(12);
	}
}