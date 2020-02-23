import classe3.Cor;
import classe3.Triangulo;

class TestaTriangulo2D {
    public static void main (String args[]) {
	    Triangulo T1, T2;
		T1=new Triangulo();
		T1.setCor (Cor.AZUL);
        System.out.println (T1.getCor ());

		T2=new Triangulo2D();
		T2.setCor (Cor.VERMELHO);
        System.out.println (T2.getCor ());
		//T2.setA(0,0);
		//T2.setB(0,3);
		//T2.setC(0,15);
	}
}


