import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

class No {

	public int ix;
	public int iy;
	public No proximo;

	public No(int ix, int iy ){
		this.ix = ix;
		this.iy = iy;
		this.proximo = null;
	}

}
class Lista3 {

	public static String retorno(String[] b, int tam, int cont, String w){
		w ="";
		if( cont < tam ){
			for(int i = 0; i < tam; i++){
				w = b[cont]+ b[i]; 
				//w = b[i]+ retorno( b, tam, cont+1, w);
				System.out.println("cont: " +cont+ " - i: "+ i +" - carc: "+ w);
				w="";
			}
			w = retorno( b, tam, cont+1, w);
		}
		else{
			return "";
		}
		//System.out.println("cont: " +cont+" - carc: "+ w);
		return w;
	}
	
	public static void permutaCaracter(String[] a, int n){
		String q = "";
		retorno( a, n, 0, q);
	}
	
	public static void imprimeMatriz(String[][] m){
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++)
				System.out.print(m[i][j]);
			System.out.println("");
		}
	}
	
	public static int buscaSeqRec(int[] v, int x, int cont){
		if(cont < v.length){
			if(v[cont] == x )return cont;
			else return buscaSeqRec( v, x, cont+1);
		}
		return - 1;
	}
	
	public static int buscaSeq(int[] v, int x){
		Date inicio = Calendar.getInstance().getTime();
		int a = buscaSeqRec( v, x, 0);
		Date fim = Calendar.getInstance().getTime();
		System.out.println("Busca Sequencial realizada em (" + ((fim.getTime() - inicio.getTime())/1000.0) + " s)");
		return a;
	}
	
	public static int buscaSeqRecL(int[] v, int x, int ini, int fim){
		if (ini < fim){
			int meio = (ini+ fim) / 2;
			if( v[meio] == x) return meio;
			int a = buscaSeqRecL( v, x, ini, meio);
			return (a == -1)? buscaSeqRecL( v, x, meio+1, fim) : a;
		} 
		else return (v[ini] == x)? ini : -1; 
	}
	
	public static int buscaSeqL(int v[], int x){
		Date inicio = Calendar.getInstance().getTime();
		int a = buscaSeqRecL( v, x, 0, v.length - 1);
		Date fim = Calendar.getInstance().getTime();
		System.out.println("Busca Sequencial Logaritmica realizada em (" + ((fim.getTime() - inicio.getTime())/1000.0) + " s)");
		return a;
	}
	
	public static void imprimeVetor(int [] a){
		System.out.print("Array = {");
		for(int i : a){
			System.out.print(" " + i);
		}
		System.out.println(" }");
	}
	
	public static boolean possivel(int x, int y, int x2, int y2, String[][] m){	
		String a = ".";
		String b = "X";
		boolean result = (x >= 0 && x <= m.length -1 );           
		result = result && (y >= 0 && y <= m.length - 1);         
		result = result && ( x2 >= 0 && x2 <= m.length - 1);
		result = result && ( y2 >= 0 && y2 <= m.length - 1);
		result = result && (a.equals(m[x][y].trim()) && !b.equals(m[x2][y2].trim()));
		return result;
	}
	
	public static No fazCaminho(int ix, int iy, int fx, int fy,  int cont, String[][] m, int[] cx, int[] cy){
		boolean fim = (cont < m.length);
		int i = 0;
		int u, v;
		while(!fim && i < 4){
			u = ix + cx[i];
			v = iy + cy[i];
			if(possivel(u,v,fx,fy,m)){
				No novo = new No( u, v);
				novo.proximo = fazCaminho(u,v,fx,fy,cont+1,m,cx,cy);
					
			}
			i++;
		}
		return null;
	}
	
	public static void resolveCaminho(int ix, int iy, int fx, int fy, String[][] m){
		int[] cx = { -1, 1, 0, 0};
		int[] cy = { 0, 0, 1, -1};
		fazCaminho(ix,iy,fx,fy,0,m,cx,cy);
		
 	}

	public static void main(String [] args){
		String[] y = {"a", "b", "c"};
		//permutaCaracter( y, 3);
		String[][] matriz = { {" . ", " . ", " . ", " . ", " . "},
							  {" X ", " . ", " X ", " X ", " . "},
							  {" . ", " . ", " X ", " . ", " . "},
							  {" X ", " . ", " X ", " . ", " X "},
							  {" X ", " . ", " . ", " . ", " . "} };
		imprimeMatriz(matriz);
		
		Scanner sc = new Scanner(System.in);
		int ix, iy, fx, fy;
		System.out.println("\n Entre com as cordenadas da Matriz onde nao haja X no formato inicio/fim x y x y ");
		ix = sc.nextInt();
		iy = sc.nextInt();
		fx = sc.nextInt();
		fy = sc.nextInt();
		
		//fazCaminho(ix, iy, fx, fy, matriz);
		/*
		Scanner sc = new Scanner(System.in);
		int[] w = { 1, 5, 7, 9, 10, 2, 4, 3, 6, 8, 14, 11, 12, 15, 13, 16, 22, 18, 19, 17, 21, 20 };
		int x;
		imprimeVetor(w);
		x = sc.nextInt(); 
		System.out.println(buscaSeq(w,x));
		System.out.println(buscaSeqL(w,x));*/
		
		
		
	}
}