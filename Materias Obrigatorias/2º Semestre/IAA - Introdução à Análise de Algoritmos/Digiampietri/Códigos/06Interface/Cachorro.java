package interfaces;

import java.util.Date;

/* A classe Cachorro PRECISA implementar todos os metodos definidos na 
 * Interface Animal, além disso pode ter outros atributos e métodos
 */
public class Cachorro implements Animal{

	private int peso;
	
	Cachorro(int peso){
		this.peso = peso;
	}
	
	public void durma() {
		System.out.println("Dormindo.");
	}

	public void nasça(Date data) {
		System.out.println("Nascendo um novo cachorro: " + data);
	}

	public double peso() {
		return peso;
	}

	public void tipo() {
		System.out.println("Este animal eh um Cachorro.");
	}
}
