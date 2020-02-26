package ep3_2011;

public class Aluno {

	private String nome;
	private int numeroUSP;
	private double mediaPonderada;
	private int grupo;

	public Aluno(String pNome, int pNumeroUSP, double pMediaPonderada){
		nome = pNome;
		numeroUSP = pNumeroUSP;
		mediaPonderada = pMediaPonderada;
		grupo = -1;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public String getNome() {
		return nome;
	}

	public int getNumeroUSP() {
		return numeroUSP;
	}

	public double getMediaPonderada() {
		return mediaPonderada;
	}

	public static boolean verificaSeDistribuicaoEhValida(Aluno[] alunos, int grupos){
		int[] alunosPorGrupo = new int[grupos];
		int grupoAtual;
		for (int i=0;i<alunos.length;i++){
			grupoAtual = alunos[i].getGrupo(); 
			if (grupoAtual < 0 || grupoAtual >= grupos) return false;
			alunosPorGrupo[grupoAtual]++;
		}
		int numAlunosPorGrupo = alunos.length/grupos;
		for (int g=0;g<grupos;g++){
			if (alunosPorGrupo[g] != numAlunosPorGrupo) return false;
		}
		return true;
	}

	public static double mediaTotalTeoricaDeCadaGrupo(Aluno[] alunos, int grupos){
		double notaTotal = 0;
		for (int i=0;i<alunos.length;i++){
			notaTotal += alunos[i].getMediaPonderada(); 
		}
		return notaTotal/grupos;
	}

	// Este metodo considera que a entrada eh uma distribuicao valida de grupos
	public static double diferencaEmRelacaoAMediaTeorica(Aluno[] alunos, int grupos, double mediaTeorica){
		double[] notaTotalGrupo = new double[grupos];
		for (int i=0;i<alunos.length;i++){
			notaTotalGrupo[alunos[i].getGrupo()]+=alunos[i].getMediaPonderada();
		}
		double diferenca = 0;
		for (int g=0;g<grupos;g++){
			diferenca += Math.pow(notaTotalGrupo[g] - mediaTeorica,2);
		}
		return Math.sqrt(diferenca);
	}

	public static Aluno clonarAluno(Aluno alunoOriginal){
		Aluno clone = new Aluno(alunoOriginal.getNome(), alunoOriginal.getNumeroUSP(), alunoOriginal.getMediaPonderada());
		clone.setGrupo(alunoOriginal.getGrupo());
		return clone;
	}

	public static Aluno[] clonarArranjoDeAlunos(Aluno[] alunos){
		Aluno[] clones = new Aluno[alunos.length];
		for (int i=0;i<alunos.length;i++){
			clones[i] = clonarAluno(alunos[i]);
		}
		return clones;
	}

	// Este metodo considera que a entrada eh uma distribuicao valida de grupos
	public static void imprimirSolucao(Aluno[] alunos){
		int maxGrupo = 0;
		for (int a=0;a<alunos.length;a++){
			System.out.print(alunos[a].getNome() + "\t");
			if (alunos[a].getGrupo()>maxGrupo) maxGrupo=alunos[a].getGrupo();
		}
		System.out.println();
		for (int a=0;a<alunos.length;a++){
			System.out.print(alunos[a].getMediaPonderada() + "\t");
		}
		System.out.println();
		double[] mediaTotalPorGrupo = new double[maxGrupo+1];
		for (int a=0;a<alunos.length;a++){
			System.out.print(alunos[a].getGrupo() + "\t");
			mediaTotalPorGrupo[alunos[a].getGrupo()]+=alunos[a].getMediaPonderada();
		}
		System.out.println();
		System.out.print("Media de cada grupo:");
		for (int g=0;g<=maxGrupo;g++){
			System.out.print("\tGrupo " + g + ": " + mediaTotalPorGrupo[g]);
		}
		System.out.println();
	}
}
