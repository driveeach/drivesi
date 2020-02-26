public class Swap2 {
	/* Este programa NAO funciona pois ao chamarmos o metodo swap copiamos os valores de c e d
	   e apenas modificamos as copias (variaveis a e b), sem invertermos os valores de c e d */

	static void swap (int a, int b){
	  int temp;
	  temp = a;
	  a = b;
	  b = temp;
	}

	public static void main(String[] args) {
	  int c;
	  int d;
	  c = 3;
	  d = 4;
	  swap(c, d);
	  System.out.println("c="+c+" , d="+d);
	}

}
