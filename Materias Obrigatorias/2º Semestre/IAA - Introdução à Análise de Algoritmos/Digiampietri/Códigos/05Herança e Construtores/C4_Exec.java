package heranca2;

public class C4_Exec {

	public static void main(String[] args) {

		C3 x = new C3();
		x.imprimir();
		System.out.println("Alternativo:");
		x.imprimirAlternativo();
		System.out.println("Alternativo2:");
		x.imprimirAlternativo2();

	}

}

/* RESULTADO DA EXECUÇÃO
Construtor C1
Construtor C2
Imprimir C3
Alternativo:
Imprimir C2
Alternativo2:
Imprimir C3
*/
