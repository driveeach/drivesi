class TestaTurmaInt {
    public static final int NAO_ENCONTRADO=-1;
	public static void main (String[] args) {
		/* Inicializações */
		int[] Turma = new int[25];
		for (int i=0;i<25;i++) {
			Turma[i]=i*1000+i+1;
		}
		
		/* busca o aluno de NUSP */
		int NUSP=5006;
		int Indice=NAO_ENCONTRADO;
		for (int i=0;i<25;i++) {
			if (Turma[i] == NUSP) Indice=i;
		}
		if (Indice!=NAO_ENCONTRADO) System.out.println (Indice);
	}
}
