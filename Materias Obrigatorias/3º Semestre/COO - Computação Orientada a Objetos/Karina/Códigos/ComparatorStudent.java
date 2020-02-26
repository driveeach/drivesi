import java.util.*;
class ComparatorStudent implements Comparator <Student> {
	public int compare (Student s1, Student s2) {

		if (s1.nUSP < s2.nUSP) {
			return -1;
		}
		else if (s1.nUSP > s2.nUSP) {
			return 1;
		}
		else return 0;
	}
}
