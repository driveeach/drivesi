package heranca2;

public class C2 extends C1 {
	C2(){
		super(0);
		// super(1); NÃO FUNCIONA: O CONSTRUTOR DA SUPER CLASSE PRECISA SER O PRIMEIRO COMANDO NO CONSTRUTOR DA SUB-CLASSE
		System.out.println("Construtor C2");
	}
	
	void imprimir(){
		System.out.println("Imprimir C2");
	}
}
