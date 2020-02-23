class Catorze {
	public static void quadrado (int m, int n) {
		for (int l = 1; l<=m; l++) System.out.print("*");
		
		System.out.println();
		
		for (int linha = 1; linha <= n-2; linha++) {
		System.out.print("*");
		for (int espaco = 1; espaco <= m-2; espaco++) System.out.print(" ");
		System.out.println("*");
		}
		
		for (int l = 1; l<=m; l++) System.out.print("*");
	}
	
	public static void main (String [] args) {
	quadrado (20,30);
	}
}

