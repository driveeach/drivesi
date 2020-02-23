class Seis {
	public static void main(String[] args) {
		int x = 10;
		int y = 1;
		int z = 26;
		int max;
		int a = z - y;
		int b =  y + z + a - 2 * x;
	
		if (a < 0) a = y-z;
		
		if (b<0) b = - y - z - a + 2 * x;
		max = (2*x + y + z + a + b) / 4;
		
		System.out.println(max);
	}
}