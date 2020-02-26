class Lista1 {

	public static boolean vetorElemIguais(int[] a, int i){
		if( i < a.length-1){
			if (vetorElemIguais( a, i+1)){
				if (a[i] == a[i+1]){
					vetorElemIguais( a, i+1);
				}
				else return false;
			}	
			else return false;
		}
		return true;
	}  
	
	public static void imprimeVetor(int[] a, int i){
		if(i < a.length){
			System.out.print(a[i]+" ");
			imprimeVetor( a, i+1);
		}
	}
	
	public static boolean vetoresIguais(int[] a, int[] b, int i){
		if(a.length == b.length) {
			if(i < a.length){
				if(vetoresIguais( a, b, i+1)){
					if(a[i] == b[i]) return true;
					else return false;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static int contaCaracter(String[] a, String c, int i){
		if( i < a.length ){
			int x =0;
			if(a[i].equals(c)) return x+= contaCaracter(a, c , i+1)+1;
			else x += contaCaracter(a, c, i+1);
			return x;
		}
		return 0;
	}
	
	public static int fatorial(int i){
		if(i == 1) return 1;
		else return i *= fatorial(i-1);
	}
	
	public static int potencia(int b, int n){
		if(n == 1) return b;
		else return b *=  potencia( b, n-1);
	}
	
	public static void main(String[] args){
		int[] x = { 1, 2, 3, 4, 5, 6};
		int[] z = { 1, 2, 3, 4, 5, 8};
		String[] c = {"c","d","f","g","c","lll","f","c"};
		
		String y = vetorElemIguais( x, 0)? "True" : "False";
		System.out.println(y);
		imprimeVetor( x,0);
		String w = vetoresIguais( x, z, 0)? "True" : "False";
		System.out.println("\n" + w);
		System.out.println("\n"+ contaCaracter(c,"y",0));
		System.out.println("\n"+ fatorial(5));
		System.out.println("\n"+ potencia( 2, 11));
	}
}