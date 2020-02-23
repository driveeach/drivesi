/* Escreva uma classe Rendimentos que contenha os seguintes metodos a fim de contabilizar
o total de rendimentos de uma certa pessoa em um certo ano:
rendimentosDePessoaFisica(double);
rendimentosDePessoaJuridica(double);
rendimentosDeAplicacoesFinanceiras(double);
rendimentosNaoTributaveis(double);
double totalDeRendimentosTributaveis();
Em seguida, escreva uma classe TabelaDeAliquotas que possui:
um metodo aliquota() que recebe como parametro o total de rendimentos tributaveis
de uma pessoa e devolve um numero entre 0 e 1.0 correspondente a aliquota de imposto
que a pessoa devera pagar e
um metodo valorADeduzir() que recebe como par^ametro o total de rendimentos
tributaveis de uma pessoa e devolve o valor a deduzir no calculo do imposto.
Para escrever esta classe, utilize a tabela do IR 2006 abaixo:
Rendimentos Tributaveis		 		Aliquota			Parcela a deduzir
Ate R$ 13.968,00						-						-
De R$ 13.968,01 a R$ 27.912,00		 0.15					 R$ 1.904,40
acima de R$ 27.912,00  				0.275 					R$ 5.076,90
Agora escreva uma classe CalculadoraDeImposto que possui um unico metodo que recebe
como par^ametro o valor dos rendimentos tributaveis de uma pessoa e devolve o valor do
imposto a ser pago.*/
class Rendimentos {
	double pessoaFisica;
	double pessoaJuridica;
	double aplicacoesFinanceiras;
	double naoTributaveis;
	double total;

	void rendimentosDePessoaFisica(double rf) {
		pessoaFisica = rf;
	}
	void rendimentosDePessoaJuridica(double rj){
		pessoaJuridica = rj;
	}
	void rendimentosDeAplicacoesFinanceiras(double ra){
		aplicacoesFinanceiras = ra;
	}
	void rendimentosNaoTributaveis(double rn){
		naoTributaveis = rn;
	}
	double totalDeRendimentosTributaveis(){
		total = pessoaFisica + pessoaJuridica + aplicacoesFinanceiras + naoTributaveis;
		return total;
	}
}
class TabeladeAliquotas {
	double aliquota (double rendimentoTotal) {
		if (rendimentoTotal <= 13968) return 0;
		if (rendimentoTotal > 13968 && rendimentoTotal <= 27912) return 0.15;
		if (rendimentoTotal > 27912) return 0.275;
		return -1;
	}
	double valorADeduzir (double rendimentoTotal) {
		if (rendimentoTotal <= 13968) return 0;
		if (rendimentoTotal > 13968 && rendimentoTotal <= 27912) return 1904.40;
		if (rendimentoTotal > 27912) return 5076.90;
		return -1;
	}
}
class Dez {
	public static void main (String [] args) {
		TabeladeAliquotas a = new TabeladeAliquotas ();
		Rendimentos b = new Rendimentos ();
		b.rendimentosDePessoaFisica (10000);
		b.rendimentosDePessoaJuridica (1000);
		b.rendimentosDeAplicacoesFinanceiras (1000);
		b.rendimentosNaoTributaveis (10000);
		System.out.println ("O total de rendimentos tributaveis é: " + b.totalDeRendimentosTributaveis());
		System.out.println (a.aliquota(b.totalDeRendimentosTributaveis ()));
		System.out.println (a.valorADeduzir(b.totalDeRendimentosTributaveis ()));
	}
}