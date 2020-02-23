package p1;

public class Pessoa {
	public static int contador = 0;
	
	public Pessoa(){
		contador++;
	}
	
	protected void comer(){
		System.out.println("Pessoa comendo.");
	}
	
	void caminhar(){
		System.out.println("Pessoa caminhando.");
	}
	
	private void correr(){
		System.out.println("Pessoa correndo.");
	}
}
