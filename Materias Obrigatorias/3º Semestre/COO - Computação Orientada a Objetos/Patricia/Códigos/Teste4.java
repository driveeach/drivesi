import java.util.*;


public class Teste4 <E>{
	
	public static E getMidPointValue (E[] array){
	
		return array[array.length/2];
		
	}
	
	public static void main (String [] args){
		int [] array = {1,2,3,4,5};
		System.out.println (getMidPointValue(array));
	}
}