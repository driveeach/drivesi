import java.util.*;
class Colecao {
	public static void main(String[] args) {
		String [] aux1 = {"a", "b", "c", "d", "e"};
		String [] aux2 = {"e", "c", "g"};
		List <String> list1 = new LinkedList <String> (Arrays.asList(aux1));
		List <String> list2 = new LinkedList <String> (Arrays.asList(aux2));
		System.out.printf("Lista 1: \n%s\n", list1);
		System.out.printf("Lista 2: \n%s\n", list2);
		System.out.println();
		list1.addAll (2, list2);
		System.out.printf("Lista 1: \n%s\n", list1);
		System.out.printf("Lista 2: \n%s\n", list2);
		Iterator <String> it = list1.iterator();
		while (it.hasNext()){
			if (it.next().equals ("g")) {
				System.out.print("Certo\t");
			}
			else System.out.print("Errado\t");
		}
		Collections.sort(list1);
		Collections.reverseOrder(list1);
		System.out.printf("\nLista 1: \n%s\n", list1);
		System.out.println("\n" + Collections.min(list1));
		System.out.println(Collections.reverseOrder());
	}
}