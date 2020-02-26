import java.util.*;

class Estudante {
	public static void main(String[] args) {
		Student [] s = new Student [4];
		Student a = new Student (7971991, "Fernanda", 18);	
		Student b = new Student (7029448, "Jose", 40);
		Student c = new Student (8930559, "Maria", 17);
		Student d = new Student (7283944, "Marco", 21);

		s[0] = a;
		s[1] = b;
		s[2] = c;
		s[3] = d;

		List <Student> classe  = new LinkedList <Student> (Arrays.asList(s));
		
		Collections.sort(classe, new ComparatorStudent());

		for (Student aux : classe ) {
			System.out.print(aux.nUSP);
			System.out.print("\t" + aux.nome + "\t");
			System.out.print(aux.idade +"\n");
		}
		System.out.println(Collections.binarySearch(classe, new Student(7971991), new ComparatorStudent()));
	}
}

