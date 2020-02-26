package ep1;

public interface InterfaceSala{

	boolean estaLotada();

	boolean adicionarEstudante(Estudante e);

	void imprimirListaDeEstudantes();

	void aumentarTamanhoDaSala(int aumento);

	boolean ehPossivelColocarNestaSala(Estudante[] estudantes);

}


