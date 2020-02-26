public class Triangulo {

	private void imprimeCrescente(int n) {
		if (n > 0) {
			imprimeCrescente(n - 1);
			System.out.print(n);
		}
	}

	private void imprimeDecrescente(int n) {
		if (n > 0) {
			System.out.print(n);
			imprimeDecrescente(n - 1);
		}
	}

	private void imprimeEspaco(int n) {
		if (n > 0) {
			imprimeEspaco(n - 1);
			System.out.print(" ");
		}
	}

	private void imprimeLinha(int espacos, int n) {
		if (n > 0) {
			imprimeEspaco(espacos);
			imprimeCrescente(n - 1);
			System.out.print(n);
			imprimeDecrescente(n - 1);
			System.out.println();
		}
	}

	private void imprimeTrianguloCrescente(int espacos, int n) {
		if (n > 0) {
			imprimeTrianguloCrescente(espacos + 1, n - 1);
			imprimeLinha(espacos, n);
		}
	}

	private void imprimeTrianguloDecrescente(int espacos, int n) {
		if (n > 0) {
			imprimeLinha(espacos, n);
			imprimeTrianguloDecrescente(espacos + 1, n - 1);
		}
	}

	public void imprimeTriangulo(int n) {
		imprimeTrianguloCrescente(0, n);
	}

	public void imprimeDiamante(int n) {
		imprimeTrianguloCrescente(0, n);
		imprimeTrianguloDecrescente(1, n -1);
	}
}
