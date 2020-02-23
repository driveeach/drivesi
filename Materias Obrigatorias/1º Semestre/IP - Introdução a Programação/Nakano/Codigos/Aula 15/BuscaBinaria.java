class BuscaBinaria {
    public static final int NAO_ENCONTRADO=-1;
	public static void main (String[] args) {
		/* Inicializações */
		int[] Turma = new int[25];
		for (int i=0;i<25;i++) {
			Turma[i]=i*1000+i+1;
		}
		
		/* busca o aluno de NUSP */
		int NUSP=5006;
		int Inicio=0, Final=24, Indice;
		do {
			Indice=(Inicio+Final)/2;
			if (Turma[Indice] < NUSP) Inicio=Indice+1;
			else Final=Indice-1;
		} while ((Turma[Indice]!=NUSP)&&(Inicio<Final));

		if (Turma[Indice]==NUSP) System.out.println (Indice);
	}
}
