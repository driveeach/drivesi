package notasAlunos;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class NotasCOO {
	private static Map<String,Integer> mapa;
	
	public NotasCOO(){
		mapa = new HashMap<String, Integer>();
	}
	
	public static void associa(String nomeAluno, int notaAluno){
		//associa deve armazenar a nota para o aluno correspondente. Se já houver uma nota
		//armazenada para aquele aluno, a nota antiga será substituída pela nova.
		   mapa.put(nomeAluno, notaAluno);
	}
	
	public static int recupera(String nomeAluno){
		//recupera deve retornar a nota armazenada para o correspondente aluno, ou 0 se não
		//houver nota armazenada para aquele aluno.
		if ( mapa.containsKey( nomeAluno ) )  {
			int notaAluno = mapa.get(nomeAluno);
			return notaAluno; }		
		else return 0;
	}
	
	public static void imprimePorNome(){
		//imprimePorNome deve imprimir uma lista de nomes de estudantes e suas respectivas 
		// notas (um estudante e uma nota por linha), ordenada por nome, de modo ascendente.	
		Set< String > keys = mapa.keySet();
		TreeSet< String > sortedKeys = new TreeSet< String >( keys );
		for ( String key : sortedKeys )
			System.out.printf( "%s\t\t%s   \n", key, mapa.get( key ) );
	}
}
