class Quatro {
	public static void main(String[] args) {
	double modo=1; // pagamento normal (0) ou antecipado (1)
	double numero = 2; //número horas/aula
	double valor = 10; //valor horas/aula
	double pagamento;
	if (modo == 0) pagamento = numero * valor;
	else pagamento = (numero * valor) * 0.93; 
	System.out.println ("R$ " +pagamento);
	}
}