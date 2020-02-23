class Dezessete {
	public static void Posicao (int a, int b, int c) {
		int [] pos = new int [2];
		if (a>b && a>c) pos [0] = 1;
		if (b>a && b>c) pos [0] = 2;
		if (c>a && c>a) pos [0] = 3;
		if (a<b && a<c) pos [1] = 1;
		if (b<a && b<c) pos [1] = 2;
		if (c<a && c<a) pos [1] = 3;	
		System.out.println (pos[0] + " " + pos [1]);
	}
	public static void main (String [] args) {
		Posicao (10,25,5);
	}
}