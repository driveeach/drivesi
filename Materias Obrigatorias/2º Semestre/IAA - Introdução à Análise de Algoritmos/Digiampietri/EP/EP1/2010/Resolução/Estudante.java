package ep1;

public abstract class Estudante {

	static final int TIPO_ESTUDANTE_REGULAR = 1;
	static final int TIPO_ESTUDANTE_ATLETA = 2;
	static final int TIPO_ESTUDANTE_DEDICADO = 3;
	static final int TIPO_SUPER_ESTUDANTE = 4;
	
	private int tipo;
	
	Estudante(int tipoDoEstudante){
		tipo = tipoDoEstudante;
	}
	
	final int retornaTipoDoEstudante(){
		return tipo;
	}
	
	abstract String nomeDoTipoDeEstudante();
	
	abstract boolean implicaCom(Estudante e);

	int numeroDeHorasQueEstuda(){
		return 10;
	}
	
}
