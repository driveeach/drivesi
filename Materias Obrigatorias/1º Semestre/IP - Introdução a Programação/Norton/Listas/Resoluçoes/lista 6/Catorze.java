/*Defina uma classe Pessoa cujos objetos representam uma pessoa contendo as seguintes
informacoes: nome, sexo, numero do documento de identidade e data de nascimento da
pessoa (dia, mes e ano). Defina um construtor de objetos dessa classe e tambem um
metodo idade() para retornar a idade da pessoa (um numero inteiro de anos) dado como
parametro a data atual. Caso a data passada como argumento seja anterior a data de
nascimento da pessoa o metodo deve retornar -1.*/
class Pessoas {
	static int dia;
	static int mes;
	static int ano;
	static int idade;
	Pessoas (String nome, char sexo, int rg, int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		System.out.println ("Nome: "+nome);
		System.out.println ("Sexo: " +sexo);
		System.out.println ("RG: " +rg);
		System.out.println ("Data de nascimento: " +dia+ "/" +mes+ "/" +ano);
		System.out.println ("Idade: " +Pessoas.idade (15,5,2012));
	}
	static int idade (int diaa, int mesa, int anoa) {
		if (anoa < ano) return -1;
		if (anoa == ano && mesa < mes) return -1;
		if (anoa == ano && mesa == mes && diaa < dia) return -1;
		idade = anoa - ano;
		if (mesa < mes) idade -= 1;
		if (mesa == mes && diaa < dia) idade -= 1; 
		return idade;
	}
}

class Catorze {
	public static void main (String [] args) {
		Pessoas a = new Pessoas ("Fernanda", 'F', 470526828, 20, 7, 1994);
	}
}