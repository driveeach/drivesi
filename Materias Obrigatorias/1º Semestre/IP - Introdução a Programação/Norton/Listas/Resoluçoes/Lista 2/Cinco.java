class Cinco {
	public static void main(String[] args) {
	double valor = 100; // valor da compra à vista
	double modo = 2; // modo de pagamento - 2x ou 3x
	if (modo == 2) {
	valor *= 1.1 / 2;
	System.out.println("Pagamento em duas vezes de R$ " +valor);
	}
	
	else {
	valor *= 1.2 / 3;
	System.out.println ("Pagamento em três vezes de R$ " +valor);
	}
	
	}
}