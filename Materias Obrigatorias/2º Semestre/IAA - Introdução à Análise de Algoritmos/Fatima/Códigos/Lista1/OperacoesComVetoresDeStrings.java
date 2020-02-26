public interface OperacoesComVetoresDeStrings {

	int busca(String[] v, String s);

	void ordena(String[] v);

	String[] intersecao(String[] v1, String[] v2);
}

class OperacoesUm implements OperacoesComVetoresDeStrings {

	public void ordena(String[] v) {

		// Selecao Direta

		int indiceDoMinimo, fim = v.length;
		String temp;

		for (int i = 0; i < fim - 1; i++) {

			// Inicialmente o menor elemento já visto é o primeiro elemento

			indiceDoMinimo = i;
			for (int j = i + 1; j < fim; j++) {
				if (v[j].compareTo(v[indiceDoMinimo]) < 0)
					indiceDoMinimo = j;
			}

			// Coloca o menor elemento no inicio do sub-vetor atual. Para isso,
			// troca de lugar os elementos nos indices e e indiceDoMinimo.

			temp = v[i];
			v[i] = v[indiceDoMinimo];
			v[indiceDoMinimo] = temp;
		}
	}

	public int busca(String[] v, String s) {
		int esq = 0, dir = v.length - 1, meio;

		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (s.compareTo(v[meio]) > 0) {
				esq = meio + 1;
			} else if (s.compareTo(v[meio]) < 0) {
				dir = meio - 1;
			} else {
				return meio; // retorna a posicao em que se encontra o valor
			}
		}
		return -1; // retorna -1 se o valor nao for encontrado
	}

	public String[] intersecao(String[] v1, String[] v2) {
		int noIguais = 0;
		String[] v3;

		// calculando o numero de elementos iguais

		for (int i = 0; i < v1.length; ++i) {
			for (int j = 0; j < v2.length; ++j) {
				if (v1[i].compareTo(v2[j]) == 0) {
					noIguais++;
				}
			}
		}

		if (noIguais == 0) {
			return null;
		}

		v3 = new String[noIguais];
		noIguais = 0;

		for (int i = 0; i < v1.length; ++i) {
			for (int j = 0; j < v2.length; ++j) {
				if (v1[i].compareTo(v2[j]) == 0) {
					v3[noIguais] = v1[i];
					noIguais++;
				}
			}
		}
		return v3;
	}

	void imprimeVetorStrings(String[] v) {
		for (int i = 0; i < v.length; ++i) {
			System.out.print(v[i] + " ");
		}
		System.out.println("");
	}
}

class OperacoesDois implements OperacoesComVetoresDeStrings {
	public void ordena(String[] v) {

		// Outro metodo de busca: Insercao Direta
		int j, fim = v.length;
		String stringAInserir;

		// Cada passo considera que o vetor à esquerda de i está ordenado

		for (int i = 1; i < fim; i++) {

			// Tenta inserir mais uma string na porção inicial do vetor que
			// já está ordenada empurrando para direita todos os elementos
			// maiores do que stringAInserir

			stringAInserir = v[i];
			j = i;
			while ((j > 0) && (v[j - 1].compareTo(stringAInserir) > 0)) {
				v[j] = v[j - 1];
				j--;
			}
			v[j] = stringAInserir;
		}
	}

	public int busca(String[] v, String s) {

		// outro metodo de busca. Sugestao busca ternaria

		int esq = 0, dir = v.length - 1, PrimTerco, SegTerco;

		while (esq <= dir) {

			PrimTerco = esq + (dir - esq) / 3;
			SegTerco = esq + (2 * (dir - esq)) / 3;

			if (s.compareTo(v[PrimTerco]) > 0) {
				// v[PrimTerco] < s
				if (s.compareTo(v[SegTerco]) < 0) {
					// v[PrimTerco] < s < v[SegTerco]
					dir = SegTerco - 1;
					esq = PrimTerco + 1;
				} else {
					// v[SegTerco] <= s

					if (s.compareTo(v[SegTerco]) > 0)
						esq = SegTerco + 1;
					else
						return SegTerco;
				}
			} else {

				// s <= v[PrimTerco]

				if (s.compareTo(v[PrimTerco]) < 0)
					dir = PrimTerco - 1;
				else
					return PrimTerco;
			}
		}

		return -1; // retorna -1 se o valor nao for encontrado
	}

	public String[] intersecao(String[] v1, String[] v2) {

		// outro metodo interseccao. Baseado na fusao de arrays

		int numA = 0, numB = 0, numIguais = 0;

		// Primeira coisa: ordena os vetores!

		ordena(v1);
		ordena(v2);

		while (numA < v1.length && numB < v2.length) {
			if (v1[numA].compareTo(v2[numB]) < 0) {
				numA++;
			} else if (v1[numA].compareTo(v2[numB]) > 0) {
				numB++;
			} else {
				numIguais++;
				numA++;
				numB++;
			}
		}

		String[] v3 = new String[numIguais];

		numIguais = 0;
		numA = 0;
		numB = 0;
		while (numA < v1.length && numB < v2.length) {
			if (v1[numA].compareTo(v2[numB]) < 0) {
				numA++;
			} else if (v1[numA].compareTo(v2[numB]) > 0) {
				numB++;
			} else {
				v3[numIguais] = v1[numA];
				numIguais++;
				numA++;
				numB++;
			}
		}
		return v3;
	}

	void imprimeVetorStrings(String[] vstr) {
		int i;
		for (i = 0; i < vstr.length; ++i)
			System.out.print(vstr[i] + " ");
		System.out.println("");
	}
}