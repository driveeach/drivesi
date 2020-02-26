import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Teste{
	static List <String> lista = new ArrayList <String>();
	static String [] primeiro = {"casa", "recado", "celular"};
	static String [] segundo = {"casa2", "recado", "porta"};
	static List <String> lista2 = new ArrayList <String>();
	public static void main (String [] args){
	
		
		
		
		for (int a=0;a<3;a++){
		
			System.out.println (primeiro[a]);
			
		}
		
		System.out.println ("Chama o metodo");
		converte (primeiro, lista);
		converte (segundo, lista2);
	}
	
	
	
	public static void converte (String [] array, List <String> list){
	
		System.out.println ("Entrou");
		
		Iterator it = list.iterator();
		
		for (int a=0;a<3;a++){
		
			list.add(array[a]);
			
		}	
		it = list.iterator();
		while (it.hasNext()){
			
			System.out.println (it.next());
		
		}
	
	}

}