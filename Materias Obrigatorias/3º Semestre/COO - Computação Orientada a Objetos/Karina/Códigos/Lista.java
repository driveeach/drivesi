import java.util.*;
class Lista {
	public static void main(String[] args) {
		List <Integer> L1 = new ArrayList <Integer>();
		List <Integer> L2 = new LinkedList <Integer>();
		for (int i = 0; i<10; i++) {
			L1.add(i,i);
			L2.add(i, i);
		}
		for (int c: L1) {
			System.out.print (L1.get(c) + " ");
		}
		System.out.println();
		for (int c: L2 ) {
			System.out.print(L2.get(c) + " ");
		}
	}
}