package gulosos;

public class SelecaoDeAtividadesGuloso {

	// as atividades devem ser ordenadas pelo campo fim
	// ou seja, as atividades que acabam primeiro ficam na frente
	private static int[] inicio = { 1,3,0,5,3,5, 6, 8, 8, 2,12 };
	private static int[] fim    = { 4,5,6,7,8,9,10,11,12,13,14 };
	private static int numeroDeAtividades = 11;
	
	static int selecaoGulosa(int[] ini, int[] fim, int n){
		int ultimaSelecionada=0;
		int selecionadas = 0;
		if (n==0) return 0;
		System.out.print("a"+1 + " ");  // a primeira atividade é sempre selecionada
		selecionadas++;
		for (int i=1;i<n;i++)
			if (ini[i]>=fim[ultimaSelecionada]){
				System.out.print("a"+(i+1) + " ");
				selecionadas++;
				ultimaSelecionada = i;
			}
		System.out.println();
		return selecionadas;
	}
	
	public static void main(String[] args) {
		int atividadesSelecionadas = selecaoGulosa(inicio,fim,numeroDeAtividades);
		System.out.println("Foram selecionadas " + atividadesSelecionadas + " atividades.");
	}

}
