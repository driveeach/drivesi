class Aluno {
	String Nome;
	int Nusp;
	int[] Notas;
}

class TestaTurma {
    public static final int NAO_ENCONTRADO=-1;
	public static void main (String[] args) {
		/* Inicializações */
		String[] Nomes={"Juan","Augusto","Jose","Maria","Aline"};
		String[] Sobrenomes={" Soares"," Mendes"," Silva"," Pereira"," Feigenblatt"};
		Aluno[] Turma;
		Turma = new Aluno[25];
		for (int i=0;i<25;i++) {
			Turma[i]=new Aluno ();
			Turma[i].Nome= Nomes[i/5] + Sobrenomes[i%5];
			Turma[i].Nusp=i*1000+i+1;
		}
		
		/* busca o aluno de NUSP */
		int NUSP=5006;
		int Indice=NAO_ENCONTRADO;
		for (int i=0;i<25;i++) {
			if (Turma[i].Nusp==	NUSP) Indice=i;
		}
		if (Indice!=NAO_ENCONTRADO) System.out.println (Turma[Indice].Nome);
	}
}
