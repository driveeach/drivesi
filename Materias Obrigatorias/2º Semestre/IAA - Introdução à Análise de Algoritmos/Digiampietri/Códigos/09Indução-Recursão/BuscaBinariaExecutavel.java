package recursao;


public class BuscaBinariaExecutavel {

	/**
	 * Esta classe testa a clase {@link BuscaBinaria}.
	 * @see BuscaBinaria
	 */
	public static void main(String[] args) {
		int[] vet = new int[100];
		for (int i=0;i<100;i++){
			vet[i]=2*i;
		}
		
		System.out.println("Buscando 97: " + BuscaBinaria.buscaBinaria(97,vet) + ",  total de chamadas recursivas: " + BuscaBinaria.numeroDeOperacoesRealizadas+".");
		System.out.println("Buscando 16: " + BuscaBinaria.buscaBinaria(16,vet) + ",  total de chamadas recursivas: " + BuscaBinaria.numeroDeOperacoesRealizadas+".");
		System.out.println("Buscando 210: " + BuscaBinaria.buscaBinaria(210,vet) + ",  total de chamadas recursivas: " + BuscaBinaria.numeroDeOperacoesRealizadas+".");
		
		
	}

}
