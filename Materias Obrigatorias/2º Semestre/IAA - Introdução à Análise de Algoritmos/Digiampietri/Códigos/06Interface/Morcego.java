package interfaces;

import java.util.Date;

/* A classe Morcego PRECISA implementar todos os metodos definidos nas 
 * interfaces Animal e Voador. Note que se as duas interfaces têm métodos com
 * assinaturas/cabeçalhos idênticos isto não é um problema, porém se são métodos
 * com os mesmos nomes e parâmetros porém com tipos de retorno diferentes, 
 * daí não é possível implementar as duas interfaces.
 */
public class Morcego implements Animal, Voador {

	public Date dataDeNascimento;
	
	public void durma() {
		System.out.println("Dormindo de cabeça para baixo.");
	}

	public void nasça(Date data) {
		dataDeNascimento = data;
		System.out.println("Nascendo um novo morcego");
	}

	public double peso() {
		return 1.5*constanteDaInterface;
	}

	public void tipo() {
		System.out.println("Este animal eh um Morcego.");
	}

	public void aterrisse() {
		System.out.println("Aterrissando.");
	}

	// Ao implementar métodos de uma interface, o método NÃO pode ser estático
	public void voe() {
		System.out.println("Levantando voo.");
	}
	

}
