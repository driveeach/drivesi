public class Aritmetica {

	int multiplica(int m, int n, int nroEspacos) {
		int valor;
		imprimeEspacosEmBranco(nroEspacos);
		System.out.println("--> M(" + m + "," + n + ")");
		if (n == 0) {
			valor = 0; 
		} else {
			valor = m + multiplica(m, n-1, nroEspacos + 1); 
		}
		imprimeEspacosEmBranco(nroEspacos);
		System.out.println("<-- M(" + m + "," + n + ") retornando " + valor);
		return valor;
	}
	
	private void imprimeEspacosEmBranco(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("  ");
		}
	}
	
	int soma(int m, int n) {
		if (n == 0) {
			return m;
		} else {
			return soma(sucessor(m), predecessor(n));
		}
	}
	
	private int sucessor(int n) {
		return n + 1;
	}
	
	private int predecessor(int n) {
		return n - 1;
	}
}
