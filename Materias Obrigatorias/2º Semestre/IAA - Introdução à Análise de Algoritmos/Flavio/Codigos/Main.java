package sort;

public class Main {

	public static void imprimeArranjo(int[] v) {
		int i;
		for (i = 0; i < v.length; ++i) {
			System.out.print(v[i] + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		int[] A = new int[] { 0, 1, 3, 5, 2, 4, 6, 1, 2, 3, 4, 5, 6 };
		imprimeArranjo(A);
		A = new CountingSort().ordena(A, 7);
		imprimeArranjo(A);

		int[] C = new int[] { 329, 457, 657, 839, 436, 720, 355 };
		imprimeArranjo(C);
		C = new RadixSort().ordena(C, 3);
		imprimeArranjo(C);

		int[] D = { 1, 3, 5, 7, 9, 2, 4, 6, 8 };
		imprimeArranjo(D);
		new StoogeSort().ordena(D);
		imprimeArranjo(D);

		int[] E = new int[] { 2, 8, 7, 1, 3, 5, 6, 4, 1, 2, 3, 4, 5, 6, 7, 8 };
		imprimeArranjo(E);
		System.out.println(new SelectAleatorio().select(E, 5));
		System.out.println(new Select().select(E, 8));
		System.out.println(new SelectAleatorio().select(E, 12));
		new StoogeSort().ordena(E);
		imprimeArranjo(E);

		int[] F = new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		imprimeArranjo(F);
		new MergeThreeSort().ordena(F);
		imprimeArranjo(F);

		int[] G = new int[] { 1, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, 13, 15, 17 };
		imprimeArranjo(G);
		G = new QuaseOrdenado().ordena(G);
		imprimeArranjo(G);

		int[] H = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		imprimeArranjo(H);
		new FourSort().ordena(H);
		imprimeArranjo(H);

		int[] I = new int[] { 8, 7, 6, 5, 4, 3, 2, 1 };
		imprimeArranjo(I);
		new TwoWaySelectionSort().ordena(I);
		imprimeArranjo(I);

		int[] J = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		imprimeArranjo(J);
		new BogoSort().ordena(J);
		imprimeArranjo(J);
	}
}