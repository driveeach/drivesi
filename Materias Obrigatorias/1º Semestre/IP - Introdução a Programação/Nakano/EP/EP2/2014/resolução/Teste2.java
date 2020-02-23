import lista.ListaDepositos;
import lista.ListaSimples;
import depositos.Deposito;
import depositos.Data;

public class Teste2{
	public static void main(String []args){
		ListaSimples ls = new ListaSimples();
		ListaSimples le = new ListaSimples();
		Data dt;
		Deposito dp;
		//Deposito de = new Data(null, null);
		
			
		dt = new Data(3,2011);
		dp = new Deposito(1000,dt);
		ls.insere(dp);
		
		dt = new Data(4,2011);
		dp = new Deposito(1200,dt);
		ls.insere(dp);
		
		dt = new Data(5,2011);
		dp = new Deposito(100,dt);
		ls.insere(dp);
		
		dt = new Data(7,2011);
		dp = new Deposito(1100,dt);
		ls.insere(dp);
		
		dt = new Data(9,2011);
		dp = new Deposito(900,dt);
		ls.insere(dp);
		
		
		dt = new Data(12,2011);
		dp = new Deposito(5000,dt);
		
		Juros jr = new Juros();
		System.out.println(jr.juros(ls,dp,0.001));
		
		
	}
}