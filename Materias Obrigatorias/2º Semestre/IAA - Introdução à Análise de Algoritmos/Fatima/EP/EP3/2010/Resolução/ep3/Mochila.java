package ep3;

public class Mochila {
	private double pesoMaximo;              // peso maximo comportado pela mochila
	private double pesoUsado = 0;           // peso dentro da mochila
	private double valorDentroDaMochila = 0; // valor dentro da mochila
	private int numObjetosNaMochila = 0;     // numero de objetos dentro da mochila
	
	public Mochila(double pesoMaximo){
		this.pesoMaximo = pesoMaximo;
	}

	protected double getPesoMaximo() {
		return pesoMaximo;
	}

	protected double getPesoUsado() {
		return pesoUsado;
	}

	protected void setPesoUsado(double pesoUsado) {
		this.pesoUsado = pesoUsado;
	}

	protected double getValorDentroDaMochila() {
		return valorDentroDaMochila;
	}

	protected void setValorDentroDaMochila(double valorDentroDaMochila) {
		this.valorDentroDaMochila = valorDentroDaMochila;
	}
	
	protected void imprimirDados(){
		System.out.println("Peso maximo:\t" + pesoMaximo);
		System.out.println("Peso usado:\t" + pesoUsado);
		System.out.println("Valor carregado:\t" + valorDentroDaMochila);
		System.out.println("Numero de objetos:\t" + numObjetosNaMochila);
	}

	protected int getNumObjetosNaMochila() {
		return numObjetosNaMochila;
	}

	protected void setNumObjetosNaMochila(int numObjetosNaMochila) {
		this.numObjetosNaMochila = numObjetosNaMochila;
	}
	
}
