package complexidade;

public class MaximoMinimoExecutavel {

	
	public static void main(String[] args) {
		int elementosNoArranjo = 32;
		/* Note que o arranjo esta inversamente ordenado (o que eh o pior
		   caso para o algoritmo iterativo) */
		int[] ARRANJ = new int[elementosNoArranjo];
		for (int i = 0; i < elementosNoArranjo; i++){
			ARRANJ[i] = elementosNoArranjo - i; 
		}

		MaximoMinimo.comparacoesIterativo = 0;
		MaximoMinimo.comparacoesIndFraca = 0;
		MaximoMinimo.comparacoesIndForte = 0;
		
		int[] resInterativo = MaximoMinimo.maximoMinimoIterativo(ARRANJ);
		System.out.println("ITERATIVO: o maior e o menor valores sao: " + resInterativo[0] + " e " + resInterativo[1]+". Comparacoes: " + MaximoMinimo.comparacoesIterativo);
		
		int[] resIndFraca = MaximoMinimo.maximoMinimoIndFraca(ARRANJ,ARRANJ.length);
		System.out.println("INDUCAO FRACA: O maior e o menor valores sao: " + resIndFraca[0] + " e " + resIndFraca[1]+". Comparacoes: " + MaximoMinimo.comparacoesIndFraca);

		int[] resIndForte = MaximoMinimo.maximoMinimoIndForte(ARRANJ,0,ARRANJ.length-1);
		System.out.println("INDUCAO FORTE: O maior e o menor valores sao: " + resIndForte[0] + " e " + resIndForte[1]+". Comparacoes: " + MaximoMinimo.comparacoesIndForte);
		
		
	}

}
