import java.util.Scanner;

public class EpOCD {

	private static int bloco(int n) {

		Scanner s = new Scanner(System.in);
		int total = 2;

		while (n > 0) {
			n--;
			int next = s.nextInt();

			if (total == 2)
				total = next % 2;

			else if (total == 1) {
				
				if (next % 2 != 0) {

				} else {
					total = -1;
				}

			} else if (total == 0) {
				if (next % 2 == 0) {

				} else {
					total = -1;

				}
			}

		}


		return total;

	}

	public static boolean verificaPiramidavel(int elementos) {
		
		int i = elementos;
		for (int j = 0; j < elementos; j++) {
			i -= j + 1;
			
			if (i == 0)return true;
		}
		
		return false;		
		
	}

	public static void main(String[] args) {

		
		Scanner s = new Scanner(System.in);

		int blocosALer = s.nextInt();
		
		if (!verificaPiramidavel(blocosALer)){
			System.out.println("n�o");
			return;
		}
		
		
		int proxBlocoDeveSer = 0;
		int j = 0;

		for (int i = 0; i < blocosALer;) {

			int numerosNoBloco = j + 1;
			j++;
			
			
			int blocoTotal = bloco(numerosNoBloco);
			
			if (blocoTotal == -1){
				System.out.println("n�o");
				return;
			}
			
			
			if (i == 0) {
				proxBlocoDeveSer = inverteBit(blocoTotal);

			}else {
				if (blocoTotal != proxBlocoDeveSer) {
					System.out.println("n�o");
					return;
				}
				
				proxBlocoDeveSer = inverteBit(blocoTotal);
			}
			
			i = numerosNoBloco + i;

		}
		
		System.out.println(j);

	}

	private static int inverteBit(int bit) {
		return bit * -1 + 1;
	}

}
