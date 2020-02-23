class Treze {
	public static void lado () {
		for (int l = 1; l<=10; l++) System.out.print("*");
	}
	public static void altura () {
		System.out.print("*");
		for (int espaco = 1; espaco <=8; espaco++) System.out.print(" ");
		System.out.println("*");
	}
	public static void main (String [] args) {
	lado();
	System.out.println();
	for (int linha = 1; linha <= 3; linha++) altura();
	lado();
	}
}

