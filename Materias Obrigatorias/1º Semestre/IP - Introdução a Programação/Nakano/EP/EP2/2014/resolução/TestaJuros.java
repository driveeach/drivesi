import lista.*;
import depositos.*;

class TestaJuros {

	public static void main(String[] args) {
		double er = 1e-4;
		if (args.length > 0) {
			er = Double.parseDouble(args[0]);
		}
		for (int i = 1; i <10; i++){
			testa(  er, (args.length > 1)?Double.parseDouble(args[1]):Math.random());
		}
	}

	private static void testa(double er, double j) {
		ListaDepositos list = new ListaSimples();
		Data fim = new Data( 6, 20);
		int k =  (int)(15*Math.random())+1;
		for ( int i = 1; i <= k; i++) {
			list.insere( genD( 1000, 4000, 2, 20, fim));
		}
		Deposito saldo = new Deposito( calc(list, j, fim), fim);
		double a = Juros.juros( list, saldo, er);
		double ec = Math.abs(j-a);
		System.out.println( (ec < er)?(" juros = "+j+"; aproximacao = "+a+"; erro de ate "+er):("Erro de "+ec+"; erro maximo esperado seria "+er+"; valores: juros = "+j+"  aproximação = "+a+"\n Posivelmente existe algum erro no codigo"));
	}

	private static Deposito genD(double minD,double maxD, double minT, double maxT, Data fim) {
		double valor = minD + (( maxD - minD) * Math.random());
		int t = ( fim.getMes() + (fim.getAno() * 12)) - (int) (minT + ((maxT - minT) * Math.random()));
		int mes = t%12;
		int ano = t/12;
		return new Deposito( valor, new Data( mes, ano));
	}








































































































































	private static double calc( ListaDepositos list, double j, Data fim) {
		double valor = 0;
		No p = list.getInicio();
        while (p != null) {
			Deposito d = p.getDeposito();
			valor += d.getValor() *Math.pow(1+j, d.getData().mesesEntre( fim));
            p = p.getProx();
        }
		System.out.println(valor);
		return valor;
	}
}
