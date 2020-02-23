class Dezesseis {
	public static void Tabela (double a, int n, double juros) {
	//a = investimento inicial; n = numero de meses; juros = juros em porcentagem
		juros /= 100;
		for (int mes = 1; mes <= n; mes++) System.out.println ("\t" +mes+ "\t\t\t R$" +Math.pow(a*(1+juros), mes));
	}
	public static void main (String [] args) {
		System.out.println ("Numero de Meses \t Investimento acumulado");
		Tabela (100,20,50);
		
	}
}
