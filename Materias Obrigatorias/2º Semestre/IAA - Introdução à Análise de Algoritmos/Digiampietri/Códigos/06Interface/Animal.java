package interfaces;

import java.util.Date;

public interface Animal {
	/* Interfaces em Java só contem a interface dos métodos (sem corpo) e
	 * pondem conter constantes (declaradas como atributos), podem ou
	 * não ter os modificadores static e final (funcionarão como se tivessem) */

	int constanteDaInterface = 5;
	
	// a assinatura de um método em uma interface pode ter os seguintes modificadores:
	// <nada>, public, abstract
	void nasça(Date dataDeNascimento);
	void tipo();
	void durma();
	double peso();

}
