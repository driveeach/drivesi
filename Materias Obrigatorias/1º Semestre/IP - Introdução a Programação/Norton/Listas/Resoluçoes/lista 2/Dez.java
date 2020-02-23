class Dez {
	public static void main(String[] args) {
		double velocidade = 350;
			if (velocidade < 62) System.out.println("A tempestade não é classificada como um furacão");
			if (velocidade >= 62 && velocidade <= 118) System.out.println("A tempestade é classificada como uma tempestade tropical");
			if (velocidade >= 119 && velocidade <= 153) System.out.println("A tempestade é classificada como um furacão de categoria 1");
			if (velocidade >= 154 && velocidade <= 177) System.out.println("A tempestade é classificada como um furacão de categoria 2");
			if (velocidade >= 178 && velocidade <= 209) System.out.println("A tempestade é classificada como um furacão de categoria 3");
			if (velocidade >= 210 && velocidade <= 249) System.out.println("A tempestade é classificada como um furacão de categoria 4");
			if (velocidade > 249) System.out.println("A tempestade é classificada como um furacão de categoria 5");
			}  	 
}

