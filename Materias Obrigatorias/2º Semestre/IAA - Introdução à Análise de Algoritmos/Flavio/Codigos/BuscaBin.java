public class BuscaBin {

	public static int buscaBinRec(int [] a, int ini, int fim, int x){

		if(ini <= fim) {

			int med = (ini + fim) / 2;

			if(x == a[med]) return med;
		
			if(x < a[med]) return buscaBinRec(a, ini, med - 1, x);

			return buscaBinRec(a, med + 1, fim, x);

		}

		return -1;
	}

	public static int buscaBin(int [] a, int x){

		return buscaBinRec(a, 0, a.length - 1, x);
	}

	public static void printArray(int [] a){

		System.out.print("Array = {");

		for(int i : a){

			System.out.print(" " + i);
		}

		System.out.println(" }");
	}

	public static void main(String [] args){

		int [] a = { 1, 2, 4, 7, 9, 14, 20, 25, 31, 45, 59, 87 };
		int x = Integer.parseInt(args[0]);
		int b = buscaBin(a, x);

		printArray(a);

		if(b >= 0){
			System.out.println("Valor " + x + " esta no indice " + b + " do array.");
		}
		else {
			System.out.println("Valor " + x + " nao esta presente no array.");
		}
	}
}
