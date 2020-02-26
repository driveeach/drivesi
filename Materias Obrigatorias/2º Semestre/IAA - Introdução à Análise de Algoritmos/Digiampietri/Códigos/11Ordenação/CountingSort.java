package ordenacao;

public class CountingSort {

	public static void main(String[] args){
		int[] A = { 1, 5, 5, 6, 2, 2, 6, 8, 11, 3 ,10, 0};
		int[] res = countingSort(A, max(A)+1);
		imprimirArranjo(A);
		imprimirArranjo(res);
	}

	private static void imprimirArranjo(int[] A) {
		for (int i=0;i<A.length;i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
		
	}

	static int[] countingSort(int A[], int k){
		int n = A.length;
		int[] B = new int[n];
		int[] C = new int[k];
		for (int j=0;j<n;j++) C[A[j]]++;
		for (int i=1;i<k;i++) C[i] += C[i-1];
		for (int j=n-1;j>=0;j--) {
			B[C[A[j]]-1] = A[j];
			C[A[j]]--;
		}
		return B;
	}

	static int max(int A[]){
		int n = A.length;
		if (n > 0){
			int temp = A[0];
			for (int i=1;i<n;i++){
				if (temp < A[i]) temp = A[i];
			}
			return temp;
		}else{
			return -1;
		}

	}


}
