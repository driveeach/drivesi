package escalonador;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList extends ArrayList<BCP> {
	
	private static final long serialVersionUID = 1L;

	public void insertSorted(BCP processo) { //método para inserir ordenado
		add(processo);
		Comparable<BCP> cmp = (Comparable<BCP>)processo;
		for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1)) < 0; i--)
			Collections.swap(this, i, i - 1);
	}
}