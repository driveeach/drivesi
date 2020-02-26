package ep3;

public class Objeto {
	private double peso;   // peso do objeto (sempre sera maior que zero)
	private double valor;  // valor do objeto (sempre sera maior que zero)
	
	public Objeto(double peso, double valor){
		this.peso = peso;
		this.valor = valor;
	}

	protected double getPeso() {
		return peso;
	}

	protected double getValor() {
		return valor;
	}
	
	protected static void imprimirListaDeObjetos(Objeto[] objetos) {
		int numObjetos = objetos.length;
		System.out.println("Peso  \tValor");
		for (int i = 0;i<numObjetos;i++){
			System.out.println(objetos[i].getPeso() + "\t" + objetos[i].getValor());
		}
	}

}
