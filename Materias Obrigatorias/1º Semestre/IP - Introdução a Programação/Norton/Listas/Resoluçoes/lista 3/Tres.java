class Tres {
	static void horiz() {
		for (int comp = 1; comp < 11; comp++) System.out.print("*");
		System.out.println();
	}
	
	static void lado() {
		System.out.print("*");
		for (int espaco = 1; espaco <= 8; espaco++) System.out.print(" ");
		System.out.println("*");
	}
	
	public static void A () {
		int num = 0;
		do {
		num = num + 1;
		System.out.println(num);
		} while (num <= 5);
	}
	
	public static void B () {
		int num = 6;
		do {
		num = num + 1;
		System.out.println(num);
		} while (num <= 5);
	}
	
	public static void main (String[] args) {
		A();
		B();
		
		for(int cont = 1; cont <= 5; cont++) System.out.println("Oi");

		for(int cont = 1; cont <= 5; cont++) System.out.print("Oi");

		for(int cont = 1; cont <= 5; cont++) {
		System.out.print("Oi");
		System.out.print(" Artur");
		}
		
				
		for(int cont = 1; cont <= 5; cont++) {
		System.out.print("Oi");
		System.out.print("Artur");
		}
		
		System.out.println ();
		
		horiz();
		for (int vert = 1; vert < 7; vert++) lado();
		horiz();
	
	}
}