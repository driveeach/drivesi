package especificadores3;

public class ExMatematica1 {
	/* Exemplo simples de classe para auxilio em operacoes matematicas,
	 * que nao usa especificadores (e por isso pode apresentar problemas).
	 */
	double pi = 3.141592;
	
	double quadrado(double x){
		return x*x;
	}
	
	double cubo(double x){
		return x*x*x;
	}
	
	double perimetro(double raio){
		return 2*pi*raio;
	}

}
