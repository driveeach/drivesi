import java.util.*;

class Teste2{
	static List <Integer> lista = new ArrayList <Integer>();
	
	public static void main (String [] args){
		lista.add (8523788);
		lista.add (6516154);
		int numero=8516156;
		if (lista.contains(numero)){
			System.out.println ("Contem!");
		}
		else{
			lista.add(numero);
			Collections.sort(lista);
		}
		System.out.println (Collections.binarySearch (lista, 8516156));
	}
}