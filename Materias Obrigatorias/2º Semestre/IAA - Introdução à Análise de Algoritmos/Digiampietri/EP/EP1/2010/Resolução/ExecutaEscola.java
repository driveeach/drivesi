package ep1;

public class ExecutaEscola {

	public static void main(String[] args) {
		Escola minhaEscola = new Escola(3);

		System.out.println(" 1. Resposta esperada: 'true' :\t" + minhaEscola.adicionarSala(0, 10, false));
		System.out.println(" 2. Resposta esperada: 'false':\t" + minhaEscola.adicionarSala(0, 10, false));
		System.out.println(" 3. Resposta esperada: 'true' :\t" + minhaEscola.adicionarSala(1, 10, true));
		System.out.println(" 4. Resposta esperada: 'false':\t" + minhaEscola.adicionarSala(3, 10, false));
		
		System.out.println();
		
		System.out.println(" 5. Resposta esperada: 'true' :\t" + minhaEscola.inserirEstudanteNaSala(0, new EstudanteAtleta()));
		System.out.println(" 6. Resposta esperada: 'false':\t" + minhaEscola.inserirEstudanteNaSala(0, new SuperEstudante()));
		System.out.println(" 7. Resposta esperada: 'true' :\t" + minhaEscola.inserirEstudanteNaSala(0, new EstudanteAtleta()));
		System.out.println(" 8. Resposta esperada: 'true' :\t" + minhaEscola.inserirEstudanteNaSala(0, new EstudanteAtleta()));
		System.out.println(" 9. Resposta esperada: 'true' :\t" + minhaEscola.inserirEstudanteNaSala(0, new EstudanteAtleta()));
		
		System.out.println();

		System.out.println("10. Resposta esperada: 'true' :\t" + minhaEscola.inserirEstudanteNaSala(1, new EstudanteRegular()));
		System.out.println("11. Resposta esperada: 'true' :\t" + minhaEscola.inserirEstudanteNaSala(1, new EstudanteDedicado()));
		System.out.println("12. Resposta esperada: 'false':\t" + minhaEscola.inserirEstudanteNaSala(1, new EstudanteAtleta()));
		
		System.out.println();
		
		System.out.println("Listando sala 0 ========================");
		minhaEscola.listarEstudantesDaSala(0);
		System.out.println();
		System.out.println("Listando sala 1 ========================");
		minhaEscola.listarEstudantesDaSala(1);
		System.out.println();
		System.out.println("Listando sala 2 ========================");
		minhaEscola.listarEstudantesDaSala(2); // NADA DEVE SER LISTADO AQUI
		
		System.out.println();
		
		
		
		Estudante[] conjunto = new Estudante[2];
		conjunto[0] = new EstudanteRegular();
		conjunto[1] = new EstudanteDedicado();
		System.out.println("13. Resposta esperada: 'true' :\t" + minhaEscola.adicionarSala(2, 5, true));
		System.out.println("14. Resposta esperada: 'false':\t" + minhaEscola.verificaSeEhPossivelAdicionarConjuntoDeEstudantesASala(0, conjunto));
		System.out.println("15. Resposta esperada: 'true' :\t" + minhaEscola.verificaSeEhPossivelAdicionarConjuntoDeEstudantesASala(1, conjunto));
		System.out.println("16. Resposta esperada: 'true' :\t" + minhaEscola.verificaSeEhPossivelAdicionarConjuntoDeEstudantesASala(2, conjunto));
		
		Estudante e1 = new SuperEstudante();
		Estudante e2 = new EstudanteAtleta();
		Estudante e3 = new EstudanteDedicado();
		Estudante e4 = new EstudanteRegular();

		InterfaceAtleta a1 = new SuperEstudante();
		InterfaceAtleta a2 = new EstudanteAtleta();  
		//InterfaceAtleta a3 = new EstudanteDedicado(); // Esta linha não funciona
		//InterfaceAtleta a4 = new EstudanteRegular(); // Esta linha não funciona
		
		InterfaceDedicado d1 = new SuperEstudante();
		//InterfaceDedicado d2 = new EstudanteAtleta(); // Esta linha não funciona 
		InterfaceDedicado d3 = new EstudanteDedicado(); 
		//InterfaceDedicado d4 = new SuperRegular(); // Esta linha não funciona
	
		System.out.println();
		
		Estudante[] estudantes = {new EstudanteRegular(), new EstudanteDedicado(), new EstudanteAtleta(), new SuperEstudante()};
		for (int i=0;i<4;i++){
			System.out.println("Estudante '" + estudantes[i].nomeDoTipoDeEstudante() + "' estudando " + estudantes[i].numeroDeHorasQueEstuda() + " horas por semana.");
		}
		
	}

}


/* SAIDA ESPERADA DA EXECUÇÃO DESTA CLASSE:   

 1. Resposta esperada: 'true' :	true
 2. Resposta esperada: 'false':	false
 3. Resposta esperada: 'true' :	true
 4. Resposta esperada: 'false':	false

 5. Resposta esperada: 'true' :	true
 6. Resposta esperada: 'false':	false
 7. Resposta esperada: 'true' :	true
 8. Resposta esperada: 'true' :	true
 9. Resposta esperada: 'true' :	true

10. Resposta esperada: 'true' :	true
11. Resposta esperada: 'true' :	true
12. Resposta esperada: 'false':	false

Listando sala 0 ========================
atleta
atleta
atleta
atleta

Listando sala 1 ========================
regular
dedicado

Listando sala 2 ========================

13. Resposta esperada: 'true' :	true
14. Resposta esperada: 'false':	false
15. Resposta esperada: 'true' :	true
16. Resposta esperada: 'true' :	true

Estudante 'regular' estudando 10 horas por semana.
Estudante 'dedicado' estudando 16 horas por semana.
Estudante 'atleta' estudando 8 horas por semana.
Estudante 'super' estudando 10 horas por semana.

*/