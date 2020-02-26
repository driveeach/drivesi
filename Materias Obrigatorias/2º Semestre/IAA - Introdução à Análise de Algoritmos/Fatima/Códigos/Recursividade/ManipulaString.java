public class ManipulaString {

	public int converte(String s) {
		if (s.length() == 1) {
			return s.charAt(0) - '0';
		} else {
			int n = s.length() - 1;
			return (s.charAt(n) - '0') + 10 * converte(s.substring(0, n));
		}
	}

	private boolean eVogal(char c) {
		switch (c) {
		case 'a':
		case 'A':
		case 'e':
		case 'E':
		case 'i':
		case 'I':
		case 'o':
		case 'O':
		case 'u':
		case 'U':
			return true;
		default:
			return false;
		}
	}

	public boolean palindromo(String s) {
		return palindromo(s, 0, s.length() - 1);
	}

	private boolean palindromo(String s, int inicio, int fim) {

		if (inicio > fim) {
			return true;
		} else {
			boolean ehPalindromo = palindromo(s, inicio + 1, fim - 1);
			if (ehPalindromo) {
				ehPalindromo = (s.charAt(inicio) == s.charAt(fim));
			}
			return ehPalindromo;
		}
	}

	public boolean maisVogais(String s) {
		return nroVogaisConsoantes(s, s.length()) > 0;
	}

	private int nroVogaisConsoantes(String s, int n) {
		if (n == 1) {
			if (eVogal(s.charAt(0))) {
				return 1;
			} else {
				return -1;
			}
		} else {
			int nro = nroVogaisConsoantes(s, n - 1);
			if (eVogal(s.charAt(n - 1))) {
				nro++;
			} else {
				nro--;
			}
			return nro;
		}
	}
}
