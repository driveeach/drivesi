import java.util.*;


class Teste3{
	static List <Integer> lista = new ArrayList <Integer>();
	
	public static void main (String [] args){
		Scanner sc= new Scanner (System.in);
		String frase=sc.nextLine();
		String [] palavras = frase.split(" ");
		Map <String,Integer> contador = new HashMap <String,Integer>();
		
		for (int a=0;a<palavras.length;a++){
			int cont=0;
			if (contador.containsKey(palavras[a])){
			
				contador.put (palavras[a],contador.get(palavras[a])+1);
			
			}
			else {
			
				contador.put (palavras[a], 1);
			
			}
			
			
		}
		
		System.out.println (contador);
		
	}
}