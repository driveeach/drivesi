package ep3_2011;

public class ExecutaTestesEP3 {
	
	public static void main(String[] args) {

		Aluno[] exemplo;
		int numGrupos;
		double mediaTotalTeorica;
		long tempoInicial;

		/*
		 * o exemplo de numero 3 podera demorar varios segundos pois, a priori,
		 * precisara executar 5 elevado a 15 operacoes (5^15 = 30.517.578.125)
		 */
		//TODO se quiser executar o Exemplo 3 completo mude o valor da seguinte variavel para true
		boolean EXECUTAR_EXEMPLO3_COMPLETO = false;

		System.out.println("##########################################################");
		
		// EXEMPLO 1
		Aluno[] alunosEx1 = new Aluno[4];
		alunosEx1[0] = new Aluno("Aluno1", 1, 7.25);
		alunosEx1[1] = new Aluno("Aluno2", 2, 5.2);
		alunosEx1[2] = new Aluno("Aluno3", 3, 4.25);
		alunosEx1[3] = new Aluno("Aluno4", 4, 8.5);

		System.out.println("\nExemplo 1, um grupo:");

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx1);
		numGrupos = 1;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx1);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		System.out.println("\nExemplo 1, dois grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx1);
		numGrupos = 2;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx1);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}


		System.out.println("\nExemplo 1, quatro grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx1);
		numGrupos = 4;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx1);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		System.out.println("##########################################################");
		
		// EXEMPLO 2
		Aluno[] alunosEx2 = new Aluno[8];
		alunosEx2[0] = new Aluno("Aluno1", 1, 7.25);
		alunosEx2[1] = new Aluno("Aluno2", 2, 5.2);
		alunosEx2[2] = new Aluno("Aluno3", 3, 4.25);
		alunosEx2[3] = new Aluno("Aluno4", 4, 8.5);
		alunosEx2[4] = new Aluno("Aluno5", 5, 3.5);
		alunosEx2[5] = new Aluno("Aluno6", 6, 8.8);
		alunosEx2[6] = new Aluno("Aluno7", 7, 2.5);
		alunosEx2[7] = new Aluno("Aluno8", 8, 2.5);

		System.out.println("Exemplo 2, um grupo:");

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx2);
		numGrupos = 1;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx2);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		System.out.println("\nExemplo 2, dois grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx2);
		numGrupos = 2;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx2);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}


		System.out.println("\nExemplo 2, quatro grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx2);
		numGrupos = 4;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx2);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		System.out.println("##########################################################");
		
		// EXEMPLO 3
		Aluno[] alunosEx3 = new Aluno[15];
		alunosEx3[0] = new Aluno("Aluno1", 1, 9.25);
		alunosEx3[1] = new Aluno("Aluno2", 2, 5.2);
		alunosEx3[2] = new Aluno("Aluno3", 3, 4.25);
		alunosEx3[3] = new Aluno("Aluno4", 4, 8.5);
		alunosEx3[4] = new Aluno("Aluno5", 5, 3.5);
		alunosEx3[5] = new Aluno("Aluno6", 6, 8.8);
		alunosEx3[6] = new Aluno("Aluno7", 7, 2.5);
		alunosEx3[7] = new Aluno("Aluno8", 8, 7.25);
		alunosEx3[8] = new Aluno("Aluno9", 9, 8);
		alunosEx3[9] = new Aluno("Aluno10", 10, 7);
		alunosEx3[10] = new Aluno("Aluno11", 11, 6);
		alunosEx3[11] = new Aluno("Aluno12", 12, 5);
		alunosEx3[12] = new Aluno("Aluno13", 13, 4);
		alunosEx3[13] = new Aluno("Aluno14", 14, 3);
		alunosEx3[14] = new Aluno("Aluno15", 15, 2);		

		System.out.println("Exemplo 3, um grupo:");

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx3);
		numGrupos = 1;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx3);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		System.out.println("\nExemplo 3, tres grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx3);
		numGrupos = 3;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx3);
		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		if (EXECUTAR_EXEMPLO3_COMPLETO){

			System.out.println("\nExemplo 3, cinco grupos:");
			exemplo = Aluno.clonarArranjoDeAlunos(alunosEx3);
			numGrupos = 5;
			mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

			tempoInicial = System.currentTimeMillis();
			AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
			System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
			if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
				double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
				Aluno.imprimirSolucao(exemplo);
				System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
			}else{
				System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
			}

			exemplo = Aluno.clonarArranjoDeAlunos(alunosEx3);

			tempoInicial = System.currentTimeMillis();
			AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
			System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
			if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
				double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
				Aluno.imprimirSolucao(exemplo);
				System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
			}else{
				System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
			}
		}

		System.out.println("##########################################################");
		
		// EXEMPLO 4
		Aluno[] alunosEx4 = new Aluno[6];
		alunosEx4[0] = new Aluno("Aluno1", 1, 9.25);
		alunosEx4[1] = new Aluno("Aluno2", 2, 6.2);
		alunosEx4[2] = new Aluno("Aluno3", 3, 6.25);
		alunosEx4[3] = new Aluno("Aluno4", 4, 6.5);
		alunosEx4[4] = new Aluno("Aluno5", 5, 1.5);
		alunosEx4[5] = new Aluno("Aluno6", 6, 6.8);

		System.out.println("\nExemplo 4, dois grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx4);
		numGrupos = 2;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx4);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}

		System.out.println("\nExemplo 4, tres grupos:");
		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx4);
		numGrupos = 3;
		mediaTotalTeorica = Aluno.mediaTotalTeoricaDeCadaGrupo(exemplo, numGrupos);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiGuloso(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo guloso: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo guloso:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo guloso nao eh uma solucao valida.");
		}

		exemplo = Aluno.clonarArranjoDeAlunos(alunosEx4);

		tempoInicial = System.currentTimeMillis();
		AtribuiGrupos.atribuiBacktracking(exemplo, numGrupos);
		System.out.println("Tempo de execucao do algoritmo usando backtracking: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		if (Aluno.verificaSeDistribuicaoEhValida(exemplo, numGrupos)){
			double diferenca = Aluno.diferencaEmRelacaoAMediaTeorica(exemplo, numGrupos, mediaTotalTeorica);
			Aluno.imprimirSolucao(exemplo);
			System.out.println("Diferenca algoritmo usando backtracking:\t" + diferenca);
		}else{
			System.out.println("A solucao apresentada pelo algoritmo usando backtracking nao eh uma solucao valida.");
		}
		
		
	}
}


/* UM DOS EXEMPLOS DE SOLUCAO CORRETA:

##########################################################
Exemplo 1, um grupo:
Tempo de execucao do algoritmo guloso: 1 ms.
Aluno3	Aluno2	Aluno1	Aluno4	
4.25	5.2	7.25	8.5	
0	0	0	0	
Media de cada grupo:	Grupo 0: 25.2
Diferenca algoritmo guloso:	0.0
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	
7.25	5.2	4.25	8.5	
0	0	0	0	
Media de cada grupo:	Grupo 0: 25.2
Diferenca algoritmo usando backtracking:	0.0

Exemplo 1, dois grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno3	Aluno2	Aluno1	Aluno4	
4.25	5.2	7.25	8.5	
0	1	1	0	
Media de cada grupo:	Grupo 0: 12.75	Grupo 1: 12.45
Diferenca algoritmo guloso:	0.21213203435596475
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	
7.25	5.2	4.25	8.5	
0	0	1	1	
Media de cada grupo:	Grupo 0: 12.45	Grupo 1: 12.75
Diferenca algoritmo usando backtracking:	0.21213203435596475

Exemplo 1, quatro grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno3	Aluno2	Aluno1	Aluno4	
4.25	5.2	7.25	8.5	
0	1	2	3	
Media de cada grupo:	Grupo 0: 4.25	Grupo 1: 5.2	Grupo 2: 7.25	Grupo 3: 8.5
Diferenca algoritmo guloso:	3.3399101784329472
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	
7.25	5.2	4.25	8.5	
0	3	1	2	
Media de cada grupo:	Grupo 0: 7.25	Grupo 1: 4.25	Grupo 2: 8.5	Grupo 3: 5.2
Diferenca algoritmo usando backtracking:	3.339910178432947
##########################################################
Exemplo 2, um grupo:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno7	Aluno8	Aluno5	Aluno3	Aluno2	Aluno1	Aluno4	Aluno6	
2.5	2.5	3.5	4.25	5.2	7.25	8.5	8.8	
0	0	0	0	0	0	0	0	
Media de cada grupo:	Grupo 0: 42.5
Diferenca algoritmo guloso:	0.0
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	Aluno7	Aluno8	
7.25	5.2	4.25	8.5	3.5	8.8	2.5	2.5	
0	0	0	0	0	0	0	0	
Media de cada grupo:	Grupo 0: 42.5
Diferenca algoritmo usando backtracking:	0.0

Exemplo 2, dois grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno7	Aluno8	Aluno5	Aluno3	Aluno2	Aluno1	Aluno4	Aluno6	
2.5	2.5	3.5	4.25	5.2	7.25	8.5	8.8	
0	1	1	0	0	1	1	0	
Media de cada grupo:	Grupo 0: 20.75	Grupo 1: 21.75
Diferenca algoritmo guloso:	0.7071067811865476
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	Aluno7	Aluno8	
7.25	5.2	4.25	8.5	3.5	8.8	2.5	2.5	
0	1	1	1	1	0	0	0	
Media de cada grupo:	Grupo 0: 21.05	Grupo 1: 21.45
Diferenca algoritmo usando backtracking:	0.282842712474618

Exemplo 2, quatro grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno7	Aluno8	Aluno5	Aluno3	Aluno2	Aluno1	Aluno4	Aluno6	
2.5	2.5	3.5	4.25	5.2	7.25	8.5	8.8	
0	1	2	3	3	2	1	0	
Media de cada grupo:	Grupo 0: 11.3	Grupo 1: 11.0	Grupo 2: 10.75	Grupo 3: 9.45
Diferenca algoritmo guloso:	1.4115594213493114
Tempo de execucao do algoritmo usando backtracking: 2 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	Aluno7	Aluno8	
7.25	5.2	4.25	8.5	3.5	8.8	2.5	2.5	
0	1	1	2	0	3	2	3	
Media de cada grupo:	Grupo 0: 10.75	Grupo 1: 9.45	Grupo 2: 11.0	Grupo 3: 11.3
Diferenca algoritmo usando backtracking:	1.4115594213493114
##########################################################
Exemplo 3, um grupo:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno15	Aluno7	Aluno14	Aluno5	Aluno13	Aluno3	Aluno12	Aluno2	Aluno11	Aluno10	Aluno8	Aluno9	Aluno4	Aluno6	Aluno1	
2.0	2.5	3.0	3.5	4.0	4.25	5.0	5.2	6.0	7.0	7.25	8.0	8.5	8.8	9.25	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
Media de cada grupo:	Grupo 0: 84.25
Diferenca algoritmo guloso:	0.0
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	Aluno7	Aluno8	Aluno9	Aluno10	Aluno11	Aluno12	Aluno13	Aluno14	Aluno15	
9.25	5.2	4.25	8.5	3.5	8.8	2.5	7.25	8.0	7.0	6.0	5.0	4.0	3.0	2.0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
Media de cada grupo:	Grupo 0: 84.25
Diferenca algoritmo usando backtracking:	0.0

Exemplo 3, tres grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno15	Aluno7	Aluno14	Aluno5	Aluno13	Aluno3	Aluno12	Aluno2	Aluno11	Aluno10	Aluno8	Aluno9	Aluno4	Aluno6	Aluno1	
2.0	2.5	3.0	3.5	4.0	4.25	5.0	5.2	6.0	7.0	7.25	8.0	8.5	8.8	9.25	
0	1	2	2	1	0	0	1	2	2	1	0	0	1	2	
Media de cada grupo:	Grupo 0: 27.75	Grupo 1: 27.75	Grupo 2: 28.75
Diferenca algoritmo guloso:	0.816496580927726
Tempo de execucao do algoritmo usando backtracking: 227 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	Aluno7	Aluno8	Aluno9	Aluno10	Aluno11	Aluno12	Aluno13	Aluno14	Aluno15	
9.25	5.2	4.25	8.5	3.5	8.8	2.5	7.25	8.0	7.0	6.0	5.0	4.0	3.0	2.0	
0	0	0	1	0	2	1	2	1	1	0	2	2	2	1	
Media de cada grupo:	Grupo 0: 28.2	Grupo 1: 28.0	Grupo 2: 28.05
Diferenca algoritmo usando backtracking:	0.1471960144387967

Exemplo 3, cinco grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno15	Aluno7	Aluno14	Aluno5	Aluno13	Aluno3	Aluno12	Aluno2	Aluno11	Aluno10	Aluno8	Aluno9	Aluno4	Aluno6	Aluno1	
2.0	2.5	3.0	3.5	4.0	4.25	5.0	5.2	6.0	7.0	7.25	8.0	8.5	8.8	9.25	
0	1	2	3	4	4	3	2	1	0	0	1	2	3	4	
Media de cada grupo:	Grupo 0: 16.25	Grupo 1: 16.5	Grupo 2: 16.7	Grupo 3: 17.3	Grupo 4: 17.5
Diferenca algoritmo guloso:	1.0630145812734653
Tempo de execucao do algoritmo usando backtracking: 67891 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	Aluno7	Aluno8	Aluno9	Aluno10	Aluno11	Aluno12	Aluno13	Aluno14	Aluno15	
9.25	5.2	4.25	8.5	3.5	8.8	2.5	7.25	8.0	7.0	6.0	5.0	4.0	3.0	2.0	
0	0	1	1	2	3	0	2	4	4	2	3	1	3	4	
Media de cada grupo:	Grupo 0: 16.95	Grupo 1: 16.75	Grupo 2: 16.75	Grupo 3: 16.8	Grupo 4: 17.0
Diferenca algoritmo usando backtracking:	0.23452078799117101
##########################################################

Exemplo 4, dois grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno5	Aluno2	Aluno3	Aluno4	Aluno6	Aluno1	
1.5	6.2	6.25	6.5	6.8	9.25	
0	1	1	0	0	1	
Media de cada grupo:	Grupo 0: 14.8	Grupo 1: 21.7
Diferenca algoritmo guloso:	4.879036790187177
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	
9.25	6.2	6.25	6.5	1.5	6.8	
0	1	1	1	0	0	
Media de cada grupo:	Grupo 0: 17.55	Grupo 1: 18.95
Diferenca algoritmo usando backtracking:	0.9899494936611655

Exemplo 4, tres grupos:
Tempo de execucao do algoritmo guloso: 0 ms.
Aluno5	Aluno2	Aluno3	Aluno4	Aluno6	Aluno1	
1.5	6.2	6.25	6.5	6.8	9.25	
0	1	2	2	1	0	
Media de cada grupo:	Grupo 0: 10.75	Grupo 1: 13.0	Grupo 2: 12.75
Diferenca algoritmo guloso:	1.7440374613713625
Tempo de execucao do algoritmo usando backtracking: 0 ms.
Aluno1	Aluno2	Aluno3	Aluno4	Aluno5	Aluno6	
9.25	6.2	6.25	6.5	1.5	6.8	
0	1	2	2	0	1	
Media de cada grupo:	Grupo 0: 10.75	Grupo 1: 13.0	Grupo 2: 12.75
Diferenca algoritmo usando backtracking:	1.7440374613713625

 */