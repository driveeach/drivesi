/*Reescreva a classe CalculadoraDeImposto, contendo um metodo que nao recebe nenhum
parametro e devolve o imposto a ser pago. A classe CalculadoraDeImposto deve
possuir em seus atributos as classes Rendimentos e TabeladeAliquotas e receber como
parametros em seu construtor todos os valores necessarios para inicializar objetos destas
classes.*/
class CalculadoraDeImposto {
	static Rendimentos a = new Rendimentos ();
	static TabeladeAliquotas b = new TabeladeAliquotas ();
	CalculadoraDeImposto (double pessoaFisica, double pessoaJuridica, double aplicacoesFinanceiras, double naoTributaveis) {
		a.rendimentosDePessoaFisica (pessoaFisica);
		a.rendimentosDePessoaJuridica (pessoaJuridica);
		a.rendimentosDeAplicacoesFinanceiras (aplicacoesFinanceiras);
		a.rendimentosNaoTributaveis (naoTributaveis);
	}
	double impostoAPagar () {
		double impostoAPagar;
		impostoAPagar = (a.totalDeRendimentosTributaveis ()) / b.aliquota(a.totalDeRendimentosTributaveis());
		return impostoAPagar;
	}
}
class Onze {
	public static void main (String [] args) {
		CalculadoraDeImposto inicializacao = new CalculadoraDeImposto (10000.0,10000.0,10000.0,10000.0);
		System.out.println (inicializacao.impostoAPagar());
	}
}