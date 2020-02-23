class Doze {
	public static void main (String[] args) {
		double num1 = 9;
		double num2 = 15;
		double num3 = 12;
		
		if (num1 * num1 == num2 * num2 + num3 * num3 || num2 * num2 == num1 * num1 + num3 * num3 || num3 * num3 == num2 * num2 + num1 * num1)
		System.out.println ("true");
		else System.out.println ("false");
	}
}