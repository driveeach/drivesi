class Inverso {
	static int inv;
	static int dig1;
	static int dig2;
	static int dig3;

	static int inverso (int num){
	dig1 = num / 100;
	dig2 = ((num / 10) - (10 * dig1));
	dig3 = (num - (10 * dig2) - (100 * dig1));
	inv = dig1 + dig2 * 10 + dig3 * 100;
	return inv;
	
	}
	
	public static void main (String[] args) {
	inverso(589);
	System.out.println (inv);
	}
}