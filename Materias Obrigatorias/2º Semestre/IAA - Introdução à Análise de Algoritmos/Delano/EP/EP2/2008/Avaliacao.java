public class Avaliacao
{
	public static int quantidadeAjustados;
	int opcao;
	int[] tamanhos = new int[10];
	int totalDeTamanhos;
	int quantidadeArranjos;
	
	int ajustaAvaliacaoAlgoritmoInsertionSort()
	{
		opcao = 1;
		quantidadeAjustados++;
		return quantidadeAjustados;
	}
	
	int ajustaArranjoTipoAleatorios()
	{
		quantidadeArranjos++;
		return quantidadeArranjos;
	}
	
	int ajustaTamanhoArranjos(int tamanho)
	{
		tamanhos[totalDeTamanhos] = tamanho;
		totalDeTamanhos++;
		return totalDeTamanhos;
	}
	
	void executaAvaliacao()
	{
		
	}
	
	void imprimeResultadoTabela()
	{
		
	}
}
