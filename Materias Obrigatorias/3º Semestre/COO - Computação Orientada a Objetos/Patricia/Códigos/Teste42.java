import java.util.*;


public class Teste4 <E>{
	
	public E getMidPointValue (E[] array){
		return array[array.length/2];
		
	}
	
	public static void main (String [] args){
		Teste4 o = new Teste4(); 
		Integer[] array = {1,2,3,4,5};
		System.out.println (o.getMidPointValue(array));
	}
}