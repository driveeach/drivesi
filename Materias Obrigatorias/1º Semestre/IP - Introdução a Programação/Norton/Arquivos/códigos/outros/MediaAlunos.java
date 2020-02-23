import java.util.Scanner;

class MediaAlunos{
	
	public static void main (String [] args){
		int numeroAlunos = 0;
		int i = 1;
		float medias = 0;
		float mediaAlunos = 0;
		Scanner user_input = new Scanner(System.in);
		Scanner mediaInput = new Scanner(System.in);
		System.out.println("Digite o numero de alunos da sala:");
		numeroAlunos = user_input.nextInt();

		while(i<=numeroAlunos){
			System.out.println("Digite a media do aluno "+i);
			medias += mediaInput.nextInt();
			i++;
		}

		mediaAlunos = medias/numeroAlunos;
		System.out.println("A média da sala é: "+mediaAlunos);
	}
	

}