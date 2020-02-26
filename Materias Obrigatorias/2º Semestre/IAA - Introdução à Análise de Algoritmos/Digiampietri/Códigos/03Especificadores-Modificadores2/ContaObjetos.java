package especificadores3;

public class ContaObjetos {
	/* O atributo numeroDeObjetos pertence a classe, jah o atributo
	 * codigoDoObjeto pertence aos objetos (instancias).
	 */
	public static int numeroDeObjetos = 0;
	public char codigoDoObjeto;
	
	ContaObjetos(char codigo){
		numeroDeObjetos++;
		System.out.println("Criando objeto de numero " + numeroDeObjetos + " codigo: " + codigo +".");
		codigoDoObjeto = codigo;
	}
	
}
