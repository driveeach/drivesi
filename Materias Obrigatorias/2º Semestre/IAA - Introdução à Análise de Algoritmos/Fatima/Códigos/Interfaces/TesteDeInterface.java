// Implementando mais de uma interface por vez.

// Objetos podem ser considerados do mesmo tipo, desde que implementem a mesma interface.
// Objetos podem tambem implementar interfaces diferentes. Desde que seus metodos implementem
// as assinaturas dos metodos das diferentes interfaces.

public class TesteDeInterface {
	
	static public void main(String[] args) {
		TransportadorDePessoas t = new Onibus();
		Voador v = new Ave();
		Aviao a = new Aviao();

		t.entramPessoas();
		t.saemPessoas();
		v.voa();
		v.aterrissa();

		// Com o Onibus e Ave nao podemos chamar a outra interface

		a.entramPessoas();
		a.voa();
		a.aterrissa();
		a.saemPessoas();
		v = a;
		v.voa(); // liga turbinas
	}
}