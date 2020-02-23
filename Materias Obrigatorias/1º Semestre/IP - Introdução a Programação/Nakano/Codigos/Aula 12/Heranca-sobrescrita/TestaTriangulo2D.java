import classe3.Cor;

class TestaTriangulo2D {
    public static void main (String args[]) {
	    Triangulo2D T1, T2;
		T1=new Triangulo2D();
		T1.setA(0,0);
		T1.setB(0,3);
		T1.setC(0,15);
		T1.setCor (Cor.VERMELHO);
        System.out.println (T1.getCor ());
	}
}


