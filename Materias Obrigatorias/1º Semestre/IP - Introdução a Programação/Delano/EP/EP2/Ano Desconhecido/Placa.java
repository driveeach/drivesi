package ep2.icc1;

public class Placa
{
	//atributos da classe placa
	int[][] digito1;
	int[][] digito2;
	int[][] digito3;
	int[][] digito4;
	
	//recebe 4 matrizes-imagens, uma referente a cada dígito da placa
	void inicializaPlaca(int[][] imagem1, int[][] imagem2, int[][] imagem3, int[][] imagem4)
	{
		this.digito1 = imagem1;
		this.digito2 = imagem2;
		this.digito3 = imagem3;
		this.digito4 = imagem4;
	}
	
	
	
	//verifica 4 matrizes-imagens referentes aos 4 números presentes em placas de veículos e devolve um número de 4 algarismos
	int analisaPlaca()
	{
		//atributo que armazena o numero da placa
		int numeroDaPlaca = 0;
		//verificamos os digitos das placas e adicionamos na casa decimal adequada
		numeroDaPlaca = verificaDigito(this.digito1)*1000;
		numeroDaPlaca += verificaDigito(this.digito2)*100;
		numeroDaPlaca += verificaDigito(this.digito3)*10;
		numeroDaPlaca += verificaDigito(this.digito4);

		return numeroDaPlaca;
	}
	
	//recebe uma matriz-imagem e devolve o dígito que ela significa
	//falta definir o padrão de interpretacao baseado nas bordas... 
	//soh vendo um arquivo ou viajando MUITO, mas agora jah encheu meu saco e acho que soh falta esse
	int verificaDigito(int[][] imagem)
	{
		int[][] algarismo0 = null; //aqui vai o padrão que a prof vai mandar...
		int[][] algarismo1 = null;
		int[][] algarismo2 = null;
		int[][] algarismo3 = null;
		int[][] algarismo4 = null;
		int[][] algarismo5 = null;
		int[][] algarismo6 = null;
		int[][] algarismo7 = null;
		int[][] algarismo8 = null;
		int[][] algarismo9 = null;
		
		if (Imagem.comparaImagem(imagem, algarismo0) == 1) return 0;
		if (Imagem.comparaImagem(imagem, algarismo1) == 1) return 1;
		if (Imagem.comparaImagem(imagem, algarismo2) == 1) return 2;
		if (Imagem.comparaImagem(imagem, algarismo3) == 1) return 3;
		if (Imagem.comparaImagem(imagem, algarismo4) == 1) return 4;
		if (Imagem.comparaImagem(imagem, algarismo5) == 1) return 5;
		if (Imagem.comparaImagem(imagem, algarismo6) == 1) return 6;
		if (Imagem.comparaImagem(imagem, algarismo7) == 1) return 7;
		if (Imagem.comparaImagem(imagem, algarismo8) == 1) return 8;
		if (Imagem.comparaImagem(imagem, algarismo9) == 1) return 9;
		
		return -1;
	}
}
