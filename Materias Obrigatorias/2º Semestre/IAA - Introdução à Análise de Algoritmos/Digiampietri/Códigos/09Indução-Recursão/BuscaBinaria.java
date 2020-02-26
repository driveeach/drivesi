package recursao;

public class BuscaBinaria {
	static int numeroDeOperacoesRealizadas = 0;
	
	/**
	 * Metodo principal da busca binaria que recebe um valorBuscado e um arranjo 
	 * de inteiros e retorna a posicao deste valor dentro do arranjo, ou -1 caso
	 *  nao encontre o valorBuscado.
	 * @param valorBuscado: inteiro que serah procurado dentro do vetor
	 * @param vetor: arranjo de inteiros onde o valorBuscado serah procurado
	 * @return posicao do valorBuscado dentro do vetor, ou -1 caso nao seja encontrado
	 */
	static public int buscaBinaria(int valorBuscado, int[] vetor){
		numeroDeOperacoesRealizadas = 0;
		return buscaBinariaRecursiva(valorBuscado, vetor, 0, vetor.length-1);
	}

	/**
	 * Metodo que implementa uma busca binaria recursiva em um arranjo ordenado
	 * e retorna a posicao do valorBuscado dentro do arranjo ou -1 caso o
	 * valorBuscado nao seja encontrado. Procura nas coordenadas de ini ateh fim.
	 * @param valorBuscado: valor cujo indice deve ser retornado
	 * @param vetor: arranjo de inteiros onde o valorBuscado serah procurado
	 * @param ini: posicao a partir da qual o valorBuscado serah procurado
	 * @param fim: posicao maxima para a qual o valorBuscado serah procurado
	 * @return posicao do valorBuscado dentro do arranjo (limitando-se
	 * as coordenadas de ini ateh fim), ou -1 caso nao seja encontrado
	 */
	private static int buscaBinariaRecursiva(int valorBuscado, int[] vetor, int ini, int fim) {
		numeroDeOperacoesRealizadas++;
		if (fim<ini) return -1;
		int meio = (fim+ini)/2;
		System.out.println("Meio/elemento meio: " + meio + "/"+vetor[meio]);
		if (valorBuscado==vetor[meio]) return meio;
		else if(valorBuscado>vetor[meio]) return buscaBinariaRecursiva(valorBuscado, vetor, meio+1, fim);
		else return buscaBinariaRecursiva(valorBuscado, vetor, ini, meio-1);
		
	}
	

	
	
	
}
