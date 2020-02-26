import java.util.*;
class Cores {
	public static void removerCores (Collection <String> list, Collection <String> remover) {
		Iterator <String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (remover.contains (iterator.next())) {
				iterator.remove();
			}
		}
	}

	public static void main(String[] args) {
		String [] cores = {"magenta", "red", "white", "blue", "cyan"};
		String [] remover = {"red", "white", "blue"};
		List <String> list = new ArrayList <String> ();
		List <String> removeList = new ArrayList <String> ();
		for (String c: cores) {
			list.add(c);
		}
		for (String c: remover) {
			removeList.add(c);
		}
		System.out.println("Antes de remover:");
		for (String c: list) {
			System.out.print(c + " ");
		}
		removerCores (list, removeList);
		list.add(0, "green");
		System.out.println("\nDepois de remover:");
		for (String c: list) {
			System.out.print(c + " ");
		}
	}
}