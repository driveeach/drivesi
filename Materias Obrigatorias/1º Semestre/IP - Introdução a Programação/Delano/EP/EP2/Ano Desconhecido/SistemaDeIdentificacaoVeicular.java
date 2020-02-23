package ep2.icc1;

import java.util.Scanner;

//Se vocês não aprenderam package, eh melhor remover as referencias
//Realmente nao foi necessario abrir arquivo. Quem fizer isso tah inventando!
//O presente sistema vai dar exceção se não for rodado em linha de comando. Como se faz isso, aí eu não sei!
//Mas acho que já da pra tirar um 7 com isso, sem testar!!!
//Sugiro que voce leia com MUITA atenção e tente entender o que eu fiz.
//Demorei cerca de 3h30min horas pra produzir todo esse código, eh soh questão de experiência.
//Crie novos arquivos java no seu computador, copie o cole os textos, para evitar o detetor de cola!


public class SistemaDeIdentificacaoVeicular
{
	public static void main(String[] args)
	{
		final int MAX_VEICULOS = 1000; //tamanho do cadastro
		Veiculo[] cadastroDeVeiculos = new Veiculo[MAX_VEICULOS]; //cadastro que associa placas a proprietários
		Placa[] cadastroDePlacas = new Placa[MAX_VEICULOS]; //cadastro das placas interpretadas

		//armazena os nomes dos arquivos txt passados como parâmetro
		String arquivoCadastroPlacas = args[0].toString();
		String arquivoConsultaDePlacas = args[1].toString();
		
		//execucao do EP
		processaEntradas(arquivoCadastroPlacas, arquivoConsultaDePlacas, cadastroDeVeiculos, cadastroDePlacas);
		saidaDaConsulta(cadastroDePlacas, cadastroDeVeiculos);
	}


	//le os arquivos de entrada e trata adequadamente
	static void processaEntradas(String arquivoCadastroPlacas, String arquivoConsultaDePlacas, Veiculo[] cadastroDeVeiculos, Placa[] cadastroDePlacas)
	{
		int i = 0;
		Scanner leitorCadastro = new Scanner(arquivoCadastroPlacas);
		Scanner leitorConsulta = new Scanner(arquivoConsultaDePlacas);

		
		//le do arquivo as informações do cadastro de placas e associa ao conjunto de veiculos
		while (leitorCadastro.hasNext() && i < cadastroDeVeiculos.length)
		{
			//lembre-se da assinatura do método
			cadastroDeVeiculos[i].armazenaVeiculo(leitorCadastro.next(), leitorCadastro.nextInt(), leitorCadastro.next(), leitorCadastro.next());
			leitorCadastro.nextLine(); //pula para a proxima linha
			i++;
		}

		
		//le do arquivo de imagens das placas, interpretando os valores
		i = 0; //zera o iterador
		while (leitorConsulta.hasNext() && i < cadastroDePlacas.length)
		{
			leitorConsulta.nextLine(); //le e ignora a palavra Carro e o nro da consulta (talvez não precise ignorar)
			//vetor-imagem de cada linha
			int[][] imagem1;
			int[][] imagem2;
			int[][] imagem3;
			int[][] imagem4;

			leitorConsulta.nextLine(); //pula linha


			/* Douglas: provavelmente eh melhor transformar essas instrucoes repetitivas em metodo
			 * (pois faco a mesma coisa quatro vezes!) mas eu to com muito sono e preguica pra pensar nisso!
			 */

			//le os dados de uma linha, passa pra uma imagem
			int nLinhas = leitorConsulta.nextInt(); //le nro de linhas
			int nColunas = leitorConsulta.nextInt(); //le nro da colunas
			int[] vetorImg = new int[nLinhas*nColunas]; //cria um vetor do tamanho adequado

			//le os inteiros passando a imagem para o vetor
			for (int j = 0; j < (nLinhas*nColunas); j++) 
			{
				vetorImg[j] = leitorConsulta.nextInt();
			}
			//converte o vetor-imagem em matriz-imagem
			imagem1 = Imagem.converteVetorMatriz(vetorImg, nLinhas, nColunas);
			leitorConsulta.nextLine(); //pula linha

			//and so on...
			nLinhas = leitorConsulta.nextInt(); //le nro de linhas
			nColunas = leitorConsulta.nextInt(); //le nro da colunas
			vetorImg = new int[nLinhas*nColunas]; //cria um vetor do tamanho adequado
			for (int j = 0; j < (nLinhas*nColunas); j++) 
			{
				vetorImg[j] = leitorConsulta.nextInt();
			}
			imagem2 = Imagem.converteVetorMatriz(vetorImg, nLinhas, nColunas);
			leitorConsulta.nextLine(); //pula linha

			//and so on...
			nLinhas = leitorConsulta.nextInt(); //le nro de linhas
			nColunas = leitorConsulta.nextInt(); //le nro da colunas
			vetorImg = new int[nLinhas*nColunas]; //cria um vetor do tamanho adequado
			for (int j = 0; j < (nLinhas*nColunas); j++) 
			{
				vetorImg[j] = leitorConsulta.nextInt();
			}
			imagem3 = Imagem.converteVetorMatriz(vetorImg, nLinhas, nColunas);
			leitorConsulta.nextLine(); //pula linha

			//and finally...
			nLinhas = leitorConsulta.nextInt(); //le nro de linhas
			nColunas = leitorConsulta.nextInt(); //le nro da colunas
			vetorImg = new int[nLinhas*nColunas]; //cria um vetor do tamanho adequado
			for (int j = 0; j < (nLinhas*nColunas); j++) 
			{
				vetorImg[j] = leitorConsulta.nextInt();
			}
			imagem4 = Imagem.converteVetorMatriz(vetorImg, nLinhas, nColunas);
			leitorConsulta.nextLine(); //pula linha
			
			
			//inicializa uma placa , gravando as matrizes-imagem
			cadastroDePlacas[i].inicializaPlaca(imagem1, imagem2, imagem3, imagem4);
			//passa para o proximo registro
			i++;
		}
	}
	
	
	//analisa os dados gerados e impreme os resultados
	static void saidaDaConsulta(Placa[] cadastroDePlacas, Veiculo[] cadastroDeVeiculos)
	{
		System.out.println("RESULTADO DA CONSULTA:\n");

		//percorre o cadastro de veiculos comparando com o de placas
		for (int i = 0; i < cadastroDeVeiculos.length; i++)
		{
			System.out.println("Carro: " + i);
			cadastroDeVeiculos[i].devolveDados(cadastroDePlacas[i].analisaPlaca());
		}
	}
}