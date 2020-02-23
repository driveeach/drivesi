import classe3.Cor;

class TestaTriangulo2D {
    public static void main (String args[]) {
	    Triangulo2D T1, T2;
		T1=new Triangulo2D();
		//T1.setA(0,0);
		//T1.setB(0,3);
		//T1.setC(4,3);
        //T1.setCor (Cor.VERMELHO);      // ISTO FUNCIONA??
		System.out.println (T1.getCor());
	}
}


//T1.setTudo (0, 0, 0, 0, 0, 0, Cor.VERMELHO);      // ISTO FUNCIONA??
