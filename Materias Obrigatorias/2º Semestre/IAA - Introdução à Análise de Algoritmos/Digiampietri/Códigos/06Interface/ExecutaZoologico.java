package interfaces;

import java.util.Date;


public class ExecutaZoologico {

	public static void main(String[] args) {
		Animal zoologico[] = new Animal[5];
		zoologico[0] = new Cachorro(10);
		zoologico[1] = new Morcego();
		zoologico[2] = new Cachorro(20);
		zoologico[3] = new Morcego();
		zoologico[4] = new Cachorro(30);
		for (int cont=0;cont<5;cont++){
			zoologico[cont].tipo();
			zoologico[cont].durma();
			System.out.println();
		}
		System.out.println(Animal.constanteDaInterface);
	}
	

}

/* RESULTADO DA EXECUÇÃO
Este animal eh um Cachorro.
Dormindo.

Este animal eh um Morcego.
Dormindo de cabeça para baixo.

Este animal eh um Cachorro.
Dormindo.

Este animal eh um Morcego.
Dormindo de cabeça para baixo.

Este animal eh um Cachorro.
Dormindo.

5
*/