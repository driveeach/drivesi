import java.util.*;

class Teste {
	public static void main(String[] args) {
		String [] lista = {"vermelho", "amarelo", "azul", "verde"};
		List <String> list = new LinkedList <String> (Arrays.asList(lista));
		System.out.printf("Elementos do arranjo:\n%s\n ", list);
		Collections.sort(list);
		System.out.printf("Elementos do arranjo:\n%s\n ", list);
		System.out.println(Collections.binarySearch(list, "verde"));
	}
}