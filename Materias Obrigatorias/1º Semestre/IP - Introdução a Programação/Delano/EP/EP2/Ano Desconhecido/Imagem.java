package ep2.icc1;

//Esta classe contém métodos que executam operações sobre matriz-imagem
class Imagem
{
	
	/*
	 * converte o array unidimensional vetorImg em uma matriz-imagem bidimensional com nLinhas linhas
	 * e nColunas colunas, que deve ser retornada. A leitura deve ser feita de maneira a preencher uma
	 * linha da matriz de cada vez, isto é, se um vetor tem 5 linhas e 10 colunas, será lida a primeira linha,
	 * depois a segunda linha, e assim por diante. 
	 */
	public static int[][] converteVetorMatriz(int[] vetorImg, int nLinhas, int nColunas)
	{
		int[][] vetorResposta = new int[nLinhas][nColunas]; //atributo bidimensional que armazena a saida
		int posicao = 0; 									//posicao atual do vetor imagem
		
		//percorre a matriz copiando os valores do vetor-imagem em cada posicao consecutiva
		for (int i = 0; i < vetorImg.length; i++)
		{
			for (int j = 0; j < nColunas; j++)
			{
				vetorResposta[i][j] = vetorImg[posicao];
				posicao++;
			}
		}
		return vetorResposta;
	}

	
	//devolve 1 se duas matrizes-imagens são iguais e 0, caso contrário (onde cada matriz-imagem é representada por uma array bidimensional).
	public static int comparaImagem(int[][] imagem1, int[][] imagem2)
	{
		//define o valor da resposta-padrão
		int resposta = 1;

		//compara ambas e procura por algum valor divergente (percorrendo por coluna, eu acho...)
		for (int i = 0; i < imagem1[0].length; i++)
		{
			//encontrou valor diferente nas bordas, muda a resposta para 0 e encerra iteracoes
			if (calculaTamanhoBordaHorizontal(imagem1, i) != calculaTamanhoBordaHorizontal(imagem2, i))
			{
				resposta = 0;
				break;
			}
		}
		return resposta;
	}

	
	/* transforma os pixels em valores zero (se o valor atual for menor ou igual ao limiar) ou 1 
	 * (se o valor atual for menor que o limiar). Este método é útil quando desejamos obter uma 
	 * matriz-imagem onde o fundo é composto por um único valor e o objeto a ser analisado é 
	 * composto por outro valor. Por exemplo: fundo é composto por pixels com valor 0 e objeto 
	 * com pixel com valor 1.
	 */
	void binarizaImagem(int[][] imagem, int limiar)
	{
		//percorre as i linhas e j colunas, comparando o valor com o limiar passado como parametro
		for (int i = 0; i < imagem.length; i++)
		{
			for (int j = 0; j < imagem[0].length; j++)
			{
				//se o valor contido na posicao eh menor que o limiar muda para zero, caso contrario muda para um 
				if (imagem[i][j] <= limiar) imagem[i][j] = 0;
				else imagem[i][j] = 1;
			}
		}
	}

	
	/* devolve quantos pixels pertencem a uma borda horizontal localizada em uma determinada linha 
	 * da matriz-imagem. Uma borda horizontal é uma sequência de dois ou mais algarismos diferentes 
	 * de zeros presentes em uma linha da matriz-imagem.
	 */
	static int calculaTamanhoBordaHorizontal(int[][] imagem, int linha)
	{
		//atributo que armazena o tamanho da borda
		int tamanhoBordaHorizontal = 0;
		
		//percorre uma linha, incrementando o tamanho da borda cada vez que um valor diferente de zero eh encontrado
		for (int i = 0; i < imagem.length; i++)
		{
			if (imagem[linha][i] != 0) tamanhoBordaHorizontal++;
		}
		//operador condicional unario: se o tamanhoDaBorda eh menor que 2, retorna 0 senao retorna o tamanho
		return (tamanhoBordaHorizontal < 2) ? 0 : tamanhoBordaHorizontal;
	}

	
	/* devolve quantos pixels pertencem a uma borda vertical localizada em uma determinada 
	 * coluna da matriz-imagem. Uma borda vertical é uma sequência de dois ou mais algarismos diferentes 
	 * de zeros presentes em uma coluna da matriz-imagem.
	 */
	static int calculaTamanhoBordaVertical(int[][]imagem, int coluna)
	{
		//atributo que armazena o tamanho da borda
		int tamanhoBordaVertical = 0;
		
		//percorre uma coluna, incrementando o tamanho da borda cada vez que um valor diferente de zero eh encontrado
		for (int i = 0; i < imagem[coluna].length; i++)
		{
			if (imagem[i][coluna] != 0) tamanhoBordaVertical++;
		}
		
		//operador condicional unario: se o tamanhoDaBorda eh menor que 2, retorna 0 senao retorna o tamanho
		return (tamanhoBordaVertical < 2) ? 0 : tamanhoBordaVertical;
	}
}
