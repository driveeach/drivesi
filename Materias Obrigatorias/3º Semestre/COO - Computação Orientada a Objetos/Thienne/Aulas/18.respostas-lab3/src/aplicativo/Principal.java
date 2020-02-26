package aplicativo;
import java.util.Scanner;
import notasAlunos.NotasCOO;

public class Principal {
	//O aplicativo deve receber dados de entrada via teclado e oferecer as opções de 
	//associar notas, recuperar notas e imprimir por nome todos os alunos/notas.
	public static void main(String[] args) {
		Scanner scr = new Scanner( System.in );
		Scanner scrNm = new Scanner( System.in );
		Scanner scrNt = new Scanner( System.in );
		NotasCOO n = new NotasCOO();
		int input;
		boolean op = true;
		while(op == true){
			System.out.println( "1-Associa 2-Recupera 3-Imprime 4-Sair" ); 
			input = scr.nextInt();
			switch (input){
				case 1: {
					System.out.println( "Digite o nome:" ); 
					String nomeAluno =  scrNm.next();
					System.out.println( "Digite a nota:" );
					int notaAluno = scrNt.nextInt();
					NotasCOO.associa(nomeAluno, notaAluno);
					scrNm.reset();
					scrNt.reset();
					break;
				}
				case 2:{
					System.out.println( "Digite o nome:" );
					String nomeAluno =  scrNm.next();
					int nota = NotasCOO.recupera(nomeAluno);
					System.out.println("Nome:"+nomeAluno+ "    Nota:"+nota);
					scrNm.reset();
					break;
				}
				case 3: NotasCOO.imprimePorNome(); break;
				default: op=false; break;
			}
		}
		
	}
}
