public class MaxMin {

	int[] maxMin(int[] v, int n) {
		if (n == 2) {
			if (v[0] > v[1]) {
				return new int[] { v[0], v[1] };
			} else {
				return new int[] { v[1], v[0] };
			}
		} else {
			int[] maxMin = maxMin(v, n - 1);
			int max = maxMin[0];
			int min = maxMin[1];

			if (v[n - 1] > max) {
				maxMin[0] = v[n - 1];
			} else if (v[n - 1] < min) {
				maxMin[1] = v[n - 1];
			}
			return maxMin;
		}
	}

	int[] maxMin(int[] v, int ini, int fim) {

		if (ini >= fim - 1) {
			if (v[ini] > v[fim]) {
				return new int[] { v[ini], v[fim] };
			} else {
				return new int[] { v[fim], v[ini] };
			}
		} else {
			int meio = (ini + fim) / 2;
			int[] maxMin1 = maxMin(v, ini, meio);
			int[] maxMin2 = maxMin(v, meio + 1, fim);

			if (maxMin1[0] < maxMin2[0]) {
				maxMin1[0] = maxMin2[0];
			}

			if (maxMin1[1] > maxMin2[1]) {
				maxMin1[1] = maxMin2[1];
			}

			return maxMin1;
		}
	}

	public static void main(String[] args) {
		MaxMin maxMin = new MaxMin();

		int[] a = new int[] { 1, 3, 5, 7, 2, 4, 6, 8 };

		int[] maxMinInc = maxMin.maxMin(a, a.length);

		System.out.print("Max = " + maxMinInc[0]);
		System.out.println(", Min = " + maxMinInc[1]);

		int[] maxMinDevCon = maxMin.maxMin(a, 0, a.length - 1);

		System.out.print("Max = " + maxMinDevCon[0]);
		System.out.println(", Min = " + maxMinDevCon[1]);

	}

}