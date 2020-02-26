public class Busca {

	public int binaria(int[] v, int x) {
		return binaria(v, x, 0, v.length - 1);
	}

	private int binaria(int[] v, int x, int esq, int dir) {
		if (esq <= dir) {
			int meio = (esq + dir) / 2;
			if (x < v[meio]) {
				return binaria(v, x, esq, meio - 1);
			} else if (x > v[meio]) {
				return binaria(v, x, meio + 1, dir);
			} else {
				return meio;
			}
		} else {
			return -1;
		}
	}

	public int ternaria(int[] v, int x) {
		return ternaria(v, x, 0, v.length - 1);
	}
	
	private int ternaria(int[] v, int x, int esq, int dir) {
		if (esq <= dir) {
			int primTerco = esq + (dir - esq) / 3;
			int segTerco = esq + (2 * (dir - esq)) / 3;

			if (x > v[primTerco]) {
				// v[primTerco] < x
				if (x < v[segTerco]) {
					// v[primTerco] < x < v[segTerco]
					return ternaria(v, x, primTerco + 1, segTerco - 1);
				} else {
					// x >= v[segTerco]

					if (x > v[segTerco]) {
						// x > v[segTerco]
						return ternaria(v, x, segTerco + 1, dir);
					} else {
						// x = v[segTerco]
						return segTerco;
					}
				}
			} else {

				// x <= v[primTerco]

				if (x < v[primTerco]) {
					// x < v[primTerco]
					return ternaria(v, x, esq, primTerco - 1);
				} else {
					// x = v[primTerco]
					return primTerco;
				}
			}
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		Busca busca = new Busca();
		int[] v = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		for (int i = 0; i < 10; i++) {
			System.out.println(i + " -> " + busca.binaria(v, i));
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " -> " + busca.ternaria(v, i));
		}
		
	}
}
