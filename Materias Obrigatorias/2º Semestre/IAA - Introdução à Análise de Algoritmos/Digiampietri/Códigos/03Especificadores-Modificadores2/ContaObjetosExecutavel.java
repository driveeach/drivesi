package especificadores3;

public class ContaObjetosExecutavel {

	public static void main(String[] args) {
		ContaObjetos o1 = new ContaObjetos('a');
		ContaObjetos o2 = new ContaObjetos('b');
		ContaObjetos o3 = new ContaObjetos('c');
		
		System.out.println("Codigo: " + o1.codigoDoObjeto + " numero de objetos criados " + ContaObjetos.numeroDeObjetos);
		System.out.println("Codigo: " + o2.codigoDoObjeto + " numero de objetos criados " + ContaObjetos.numeroDeObjetos);
		System.out.println("Codigo: " + o3.codigoDoObjeto + " numero de objetos criados " + ContaObjetos.numeroDeObjetos);
	}

}

/* RESULTADO DA EXECUCAO DO PROGRAMA:

Criando objeto de numero 1 codigo: a.
Criando objeto de numero 2 codigo: b.
Criando objeto de numero 3 codigo: c.
Codigo: a numero de objetos criados 3
Codigo: b numero de objetos criados 3
Codigo: c numero de objetos criados 3

*/ 