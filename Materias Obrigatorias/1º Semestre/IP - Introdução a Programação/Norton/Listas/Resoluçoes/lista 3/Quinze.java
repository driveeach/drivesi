class Quinze {
	public static void primeira (int n) {
		for (int a = 1; a <= n && n >=0; a++) System.out.print("*"); 
	}
	
	public static void segunda (int n) {
		int a = 1; 
		while (a <= n && n>=0) {System.out.print ("*"); a++;}
	}
	
	public static void terceira (int n) {
		int a = 1;
		if (n > 0) {
			do { 
				System.out.print ("*");
				a++;
			} while (a<=n);
		}
	}
	
	public static void main (String [] args) {
	primeira(10);
	System.out.println();
	segunda(10);
	System.out.println();
	terceira(10);
	}
}