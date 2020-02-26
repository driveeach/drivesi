class Main{
	
	//Exercicio 1
	static int buscaBinaria(int chave, int[]v, int ini, int fim){
		int meio = (ini+fim)/2;
		if (ini >= fim) return -1; //tem que ser >= pois depende se eh impar ou par
		if (chave > v[meio]) return buscaBinaria(chave, v, meio+1, fim);
		if (chave < v[meio]) return buscaBinaria(chave, v, ini, meio-1);
		return meio;
	}
	
	//Exercicio 3
	//R: Nao faz sentido n = 0, pois o calculo comeca de n-1. Nesse caso a soma vale 0.
	static int somaPos(int soma, int[] v, int n){
		int j = n-1;
		if (j < 0) return soma;
		if (v[j] > 0) soma += v[j];
		return (somaPos(soma, v, j));
	}
	
	//Exercicio 4
	static void maxmin(int[] v, int n, int min, int max){ //chamar com min = 9999 e max = -1
		int j = n-1;
		if (j < 0){
			System.out.println(max + "," + min);
			return;
		}
		if (v[j] > max) max = v[j];
		if (v[j] < min) min = v[j];
		maxmin(v, j, min, max);
	}
	
	//Exercicio 5
	//R: Nao faz sentido ini = fim, pois o calculo comeca de fim-1. Nesse caso a soma vale 0.
	static int somaPos(int soma, int[] v, int ini, int fim){
		int j = fim-1;
		if (j < ini) return soma;
		if (v[j] > 0) soma += v[j];
		return (somaPos(soma, v, ini, j));
	}
	
	//Exercicio 6
	static int somaDigitos(int num, int soma){
		if (num/10 == 0) return num%10+soma;
		else return somaDigitos(num/10, num%10+soma);
	}
	
	//Exercicio 7
	static int onde(int x, int[] v, int n){
		int j = n-1;
		if (j == -1) return -1;
		if (v[j] == x) return j;
		else return onde(x, v, n-1);
	}
	
	//Exercicio 8
	static int onde2(int x, int[] v, int ini, int fim){
		int j = fim-1;
		if (j == ini-1) return ini-1;
		if (v[j] == x) return j;
		else return onde2(x, v, ini, fim-1);
	}
	
	
	public static void main(String[] args){
		int soma = 0;
		int[] v = {2 , 4, 1, 6, 3};
		int[] w = {0, -1, -2, 4};
		int[] o = {0, 1, 2, 3, 4};
		
		System.out.println(somaDigitos(666, soma));
		System.out.println(onde(1, v, 5));
		System.out.println(onde2(6, v, 0, 5));
		maxmin(v, 5, 99999, -1);
		System.out.println(somaPos(0, w, 0, 4));
		System.out.println(buscaBinaria(4, o, 0, 5));
		System.out.println(buscaBinaria(7, o, 0, 5));
		
	}
	
	
} 
