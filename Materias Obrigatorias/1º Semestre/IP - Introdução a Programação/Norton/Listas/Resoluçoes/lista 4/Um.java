class Um {
	public static void Maximo (float [] num) {
		float maior = 0;
		for (int i = 0; i<num.length; i++) {
			if (maior > num[i]) maior = maior;
			else maior = num[i];
		}
		System.out.println (maior);
	}
	public static void main (String [] args) {
	float [] array = {5,1,20,10,12,38};
	Maximo (array);
	}
}