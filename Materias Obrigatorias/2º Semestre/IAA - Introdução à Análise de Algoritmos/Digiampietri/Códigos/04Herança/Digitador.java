package heranca;

public class Digitador extends Pessoa{
	int letrasPorSegundo;
	Digitador(int letras, String nome, int rg){
		super(nome,rg);
		letrasPorSegundo = letras;
	}
	
	int numeroDeLetrasPorSegundo(){
		return letrasPorSegundo;
	}
}
